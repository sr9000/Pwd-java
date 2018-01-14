package sequence.radix.converter;

import java.util.List;

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
     * @param srcSequence Sequence of numbers to convert.
     * @return New sequence, with changed radix.
     */
    public abstract List<T> convert(List<T> srcSequence);

    /**
     * Method saves maximum entropy of source sequence. Skipped values will be forgotten. Use no
     * more values than necessary to specified number of new stream values.
     *
     * @param srcSequence Sequence of numbers to convert.
     * @param targetCount Numbers of necessary new sequence values.
     * @return New sequence, with changed radix.
     */
    public abstract List<T> convert(List<T> srcSequence, Integer targetCount);

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
