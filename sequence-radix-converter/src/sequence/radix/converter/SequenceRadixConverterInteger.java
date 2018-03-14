package sequence.radix.converter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;
import sequence.radix.converter.ISequenceRadixConverterFabric.SequenceRadixConverter;

public class SequenceRadixConverterInteger
    extends ISequenceRadixConverterFabric.SequenceRadixConverter<Integer> {

  private static final int MAX_ANALYZED_SEQUENCE_LEN = 1000;
  private Integer srcRadix;
  private Integer dstRadix;
  private Dictionary<Integer, SubsequenceConvertOption> convertOptions;
  private List<Integer> sortedConvertOptionsKeys;
  private Integer maxSrcSequenceLenToConvert;

  SequenceRadixConverterInteger() {}

  private class SubsequenceConvertOption {
    private Integer srcSequenceLength;
    private Integer dstSequenceLength;
    private BigInteger maxConvertibleValue;

    public SubsequenceConvertOption() {}

    SubsequenceConvertOption(Integer srcSeqLn, Integer dstSeqLen, BigInteger maxConvVal) {
      srcSequenceLength = srcSeqLn;
      dstSequenceLength = dstSeqLen;
      maxConvertibleValue = maxConvVal;
    }

    public Integer getSrcSequenceLength() {
      return srcSequenceLength;
    }

    public Integer getDstSequenceLength() {
      return dstSequenceLength;
    }

    public BigInteger getMaxConvertibleValue() {
      return maxConvertibleValue;
    }
  }

  @Override
  public List<Integer> convert(List<Integer> srcSequence) {
    return collapseList(srcSequence);
  }

  @Override
  public List<Integer> convert(List<Integer> srcSequence, Integer targetCount) {
    int takeNumber = 0;
    List<Integer> result = new LinkedList<>();
    do {
      if (takeNumber + 1 > srcSequence.size()) {
        break;
      }
      takeNumber++;
      result = collapseList(srcSequence.subList(0, takeNumber));
    } while (result.size() < targetCount);
    for (int i = 0; i < takeNumber; i++) {
      srcSequence.remove(0);
    }
    if (result.size() > targetCount) {
      result = result.subList(0, targetCount);
    }
    return result;
  }

  private List<Integer> collapseList(List<Integer> srcValuesList) {
    final BigInteger zero = new BigInteger("0", 10);
    final BigInteger srcMultiplier = new BigInteger(srcRadix.toString(), 10);
    final BigInteger dstMultiplier = new BigInteger(dstRadix.toString(), 10);

    List<Integer> result = new LinkedList<>();

    Integer index = 0;
    while (true) {
      // check exit condition
      if (srcValuesList.size() < sortedConvertOptionsKeys.get(0)) {
        break;
      }
      // find number_of_src_values_to_take
      Integer numberOfSrcValuesToTake = sortedConvertOptionsKeys.get(0);
      for (Integer numberOfSrcValues : sortedConvertOptionsKeys) {
        if (numberOfSrcValues > srcValuesList.size()) {
          break;
        }
        numberOfSrcValuesToTake = numberOfSrcValues;
      }
      // take srcValues and convert option
      SubsequenceConvertOption convertOption = convertOptions.get(numberOfSrcValuesToTake);
      List<Integer> takenSrcValues = srcValuesList.subList(0, numberOfSrcValuesToTake);

      // compute src number
      BigInteger srcNumber = zero;
      for (Integer srcDigit : takenSrcValues) {
        srcNumber = srcNumber.multiply(srcMultiplier).add(new BigInteger(srcDigit.toString(), 10));
      }
      // convert number if it is convertible
      if (srcNumber.compareTo(convertOption.maxConvertibleValue) <= 0) {
        // erase elements from list
        srcValuesList = srcValuesList.subList(numberOfSrcValuesToTake, srcValuesList.size());
        index += numberOfSrcValuesToTake;
        // convert sequence
        for (int i = 0; i < convertOption.dstSequenceLength; ++i) {
          BigInteger[] divAndRem = srcNumber.divideAndRemainder(dstMultiplier);
          srcNumber = divAndRem[0];
          result.add(divAndRem[1].intValueExact());
        }
      } else {
        srcValuesList.remove(0);
      }
    }
    return result;
  }

  @Override
  protected SequenceRadixConverter<Integer> init(Integer srcRadix, Integer dstRadix) {
    if (srcRadix <= 0) {
      throw new IllegalArgumentException("Argument \"srcRadix\" has negative value");
    }
    if (dstRadix <= 0) {
      throw new IllegalArgumentException("Argument \"dstRadix\" has negative value");
    }

    this.srcRadix = srcRadix;
    this.dstRadix = dstRadix;
    return this.generateConvertOptions();
  }

  private SequenceRadixConverter<Integer> generateConvertOptions() {
    /*
     This function search pairs (a : b), where `a` is number of src-values, `b` is number of
     dst-values. Each pair with `a` has better `fitness_value` than all other pairs with lower `a`
     key-value. `fitness_value` represents effectivity of dst-values producing from sequence of
     src-values.
     (`number_of_dst_values` / `number_of_src_values`) shows how many dst-values produced by each
     src-value.
     (`dst_number` / `src_number`) shows percentage of sequences of src-values, that successfully
     translated into dst-values.
     Ideal `fitness_value` is 100.00% * ln(dstRadix) / ln(srcRadix), where `ln` is natural
     logarithm.
    */

    // Instance values
    convertOptions = new Hashtable<>();
    sortedConvertOptionsKeys = new ArrayList<>();

    // Constants
    final BigInteger srcMultiplier = new BigInteger(srcRadix.toString(), 10);
    final BigInteger dstMultiplier = new BigInteger(dstRadix.toString(), 10);
    final BigInteger one = new BigInteger("1", 10);

    // special check
    if (dstRadix == 1) {
      maxSrcSequenceLenToConvert = 1;
      sortedConvertOptionsKeys.add(maxSrcSequenceLenToConvert);
      convertOptions.put(
              maxSrcSequenceLenToConvert,
              new SubsequenceConvertOption(
                      maxSrcSequenceLenToConvert, MAX_ANALYZED_SEQUENCE_LEN, one));
      return this;
    }

    // Algorithm values
    double lastOptimalFitnessValue = 0;
    BigInteger srcNumber = one;
    BigInteger dstNumber = one;
    BigInteger nextDstNumber = dstNumber.multiply(dstMultiplier);
    int dstSequenceLength = 0;

    // Analyze sequences
    for (int srcSequenceLength = 1;
        srcSequenceLength <= MAX_ANALYZED_SEQUENCE_LEN;
        ++srcSequenceLength) {

      // compute nearest dstNumber to srcNumber
      srcNumber = srcNumber.multiply(srcMultiplier);
      while (nextDstNumber.compareTo(srcNumber) <= 0) {
        dstNumber = nextDstNumber;
        nextDstNumber = nextDstNumber.multiply(dstMultiplier);
        ++dstSequenceLength;
      }

      double fitnessValue =
          dstNumber.multiply(new BigInteger(String.valueOf(dstSequenceLength), 10)).doubleValue()
              / srcNumber
                  .multiply(new BigInteger(String.valueOf(srcSequenceLength), 10))
                  .doubleValue();

      if (fitnessValue > lastOptimalFitnessValue) {
        lastOptimalFitnessValue = fitnessValue;
        maxSrcSequenceLenToConvert = srcSequenceLength;
        sortedConvertOptionsKeys.add(maxSrcSequenceLenToConvert);
        convertOptions.put(
            maxSrcSequenceLenToConvert,
            new SubsequenceConvertOption(
                srcSequenceLength, dstSequenceLength, dstNumber.subtract(one)));
      }
    }

    return this;
  }
}
