package sequence.radix.converter;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class SequenceRadixConverterIntegerTest {

  @Test
  void convert() {
    ISequenceRadixConverterFabric<Integer> fabric = new SequenceRadixConverterFabricInteger();
    ISequenceRadixConverterFabric.SequenceRadixConverter<Integer> converter = fabric.create(11, 3);
    List<Integer> srcList = new ArrayList<>();
    for (int i = 1000; i < 1072; i += 7) {
      srcList.add(i % 11);
    }
    Dictionary<Integer, Integer> dict = new Hashtable<>();
    List<Integer> dstList = converter.convert(dict, srcList.stream()).collect(Collectors.toList());
    Integer l = dstList.size();
  }
}
