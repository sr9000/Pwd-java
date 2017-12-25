package sequence.radix.converter;

public class SequenceRadixConverterFabricInteger implements ISequenceRadixConverterFabric<Integer> {

  @Override
  public SequenceRadixConverter<Integer> create(Integer srcRadix, Integer dstRadix) {
    return new SequenceRadixConverterInteger().init(srcRadix, dstRadix);
  }
}
