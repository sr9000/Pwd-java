package sequence.radix.converter;

import java.util.Dictionary;
import java.util.stream.Stream;

/** @ param &lt;T&gt; Numeric type of values oin sequences to convert. */
public interface ISequenceRadixConverterFabric<T extends Number> {
  /**
   * Creates converter for sequences with specific sources and destination radix.
   *
   * @param srcRadix Number of different values from 0 to srcRadix-1 of source number sequence.
   * @param dstRadix Number of different values from 0 to dstRadix-1 of destination number sequence.
   * @return Converter from sequences with srcRadix different values to sequences with dstRadix
   *     different values.
   */
  SequenceRadixConverter<T> create(T srcRadix, T dstRadix);

  /** @ param &lt;T&gt; Numeric type of values in sequences to convert. */
  abstract class SequenceRadixConverter<T extends Number> {

    /**
     * Method saves maximum entropy of source sequence. Skipped values will be forgotten.
     *
     * @param srcStream Stream of numbers to convert.
     * @return New stream, with changed radix.
     */
    public abstract Stream<T> convert(Stream<T> srcStream);

    /**
     * Method saves maximum entropy of source sequence. Skipped values will be saved into
     * skippedValues.
     *
     * @param srcStream Stream of numbers to convert.
     * @param skippedValues Dictionary to save skipped value by its index (starts from 0).
     * @return New stream, with changed radix.
     */
    public abstract Stream<T> convert(Dictionary<Integer, T> skippedValues, Stream<T> srcStream);

    /**
     * Initialize Coverter and do some work with internal state.
     *
     * @param srcRadix Number of different values from 0 to srcRadix-1 of source number sequence.
     * @param dstRadix Number of different values from 0 to dstRadix-1 of destination number
     *     sequence.
     * @return This instance after all initialization work.
     */
    protected abstract SequenceRadixConverter<T> init(T srcRadix, T dstRadix);
  }
}
