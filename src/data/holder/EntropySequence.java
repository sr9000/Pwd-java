package data.holder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import special.math.Randomness;

public class EntropySequence {

  public enum EntropySequenceSource {
    BINARY_FILE,
    ROLL_DICES,
    TEXT,
    NONE
  }

  private EntropySequenceSource entropySequenceSource;
  private Integer sequenceRadix;
  private List<Integer> sequence;

  private File binaryFile;
  private byte[] binaryData;
  private String text;
  private String rollDices;

  public boolean isValid() {
    return sequenceRadix != null
        && sequence != null
        && (sequenceRadix > 1)
        && (sequence.size() > 0);
  }

  public void setEntropySequenceSource(
      EntropySequenceSource entropySequenceSource,
      File binaryFile,
      byte[] binaryData,
      String text,
      Integer dices,
      String rollDices) {
    this.clearEntropy();
    switch (entropySequenceSource) {
      case BINARY_FILE:
        this.setBinaryFile(binaryFile, binaryData);
        break;
      case ROLL_DICES:
        this.setRollDices(dices, rollDices);
        break;
      case TEXT:
        this.setText(text);
        break;
      case NONE:
        this.clearEntropy();
        break;
    }
  }

  private void setText(String text) {
    this.entropySequenceSource = EntropySequenceSource.TEXT;
    this.text = text;

    Map<Character, Integer> frqMap = new Hashtable<>();
    for (Character ch : this.text.replaceAll("\\s+", "").toCharArray()) {
      frqMap.merge(ch, 1, (a, b) -> a + b);
    }
    List<Map.Entry<Character, Integer>> frq = new ArrayList<>(frqMap.entrySet());
    frq.sort(Comparator.comparing(o -> (o.getValue())));

    Entry<Integer, Integer> uniformCoords =
        Randomness.findOptimalSegment(
            frq.stream().map(Entry::getValue).collect(Collectors.toList()));
    if (uniformCoords == null) {
      return;
    }

    List<Character> list =
        frq.subList(uniformCoords.getKey(), uniformCoords.getValue())
            .stream()
            .map(Entry::getKey)
            .collect(Collectors.toList());

    java.util.Collections.shuffle(list);
    Map<Character, Integer> chMap = new Hashtable<>();
    for (int i = uniformCoords.getKey(); i < uniformCoords.getValue(); i++) {
      chMap.put(list.get(i - uniformCoords.getKey()), i - uniformCoords.getKey());
    }
    List<Integer> seq =
        text.chars()
            .mapToObj(c -> (char) c)
            .filter(list::contains)
            .map(chMap::get)
            .collect(Collectors.toList());

    this.sequenceRadix = uniformCoords.getValue() - uniformCoords.getKey();
    this.sequence = seq;
  }

  private void setRollDices(Integer dices, String rollDices) {
    this.entropySequenceSource = EntropySequenceSource.ROLL_DICES;
    this.sequenceRadix = dices;
    this.rollDices = rollDices;

    this.sequence = new LinkedList<>();
    for (String txtRollDice : rollDices.split("[^0-9]+")) {
      try {
        Integer rollDice = Integer.parseInt(txtRollDice);
        rollDice--;
        if (rollDice >= 0 && rollDice < dices) {
          this.sequence.add(rollDice);
        }
      } catch (NumberFormatException ignored) {
      }
    }
  }

  private void setBinaryFile(File binaryFile, byte[] binaryData) {
    this.entropySequenceSource = EntropySequenceSource.BINARY_FILE;
    this.binaryFile = new File(binaryFile.getAbsolutePath());
    this.binaryData = binaryData.clone();

    this.sequenceRadix = 256;
    this.sequence = new LinkedList<>();

    if (this.binaryData == null) {
      return;
    }

    for (byte bt : binaryData) {
      int x = bt;
      if (x < 0) {
        x = 127 - x;
      }
      this.sequence.add(x);
    }
  }

  private void clearEntropy() {
    entropySequenceSource = EntropySequenceSource.NONE;
    sequence = null;
    sequenceRadix = null;
    rollDices = null;
    binaryFile = null;
    binaryData = null;
    text = null;
  }

  public EntropySequenceSource getEntropySequenceSource() {
    return entropySequenceSource;
  }

  public Integer getSequenceRadix() {
    return sequenceRadix;
  }

  public List<Integer> getSequence() {
    return new ArrayList<>(sequence);
  }

  public File getBinaryFile() {
    return new File(binaryFile.getAbsolutePath());
  }

  public byte[] getBinaryData() {
    return binaryData.clone();
  }

  public String getText() {
    return text;
  }

  public String getRollDices() {
    return rollDices;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(sequence.size());
    str.append(" / 0..");
    str.append(sequenceRadix - 1);
    str.append(" / ");
    for (int i = 0; i < Math.min(5, sequence.size() - 1); i++) {
      str.append(sequence.get(i));
      str.append(", ");
    }
    str.append("...");
    return str.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof EntropySequence)) {
      return false;
    }
    EntropySequence entropySequence = (EntropySequence) o;
    return Objects.equals(entropySequenceSource, entropySequence.entropySequenceSource)
        && Objects.equals(sequenceRadix, entropySequence.sequenceRadix)
        && Objects.equals(sequence, entropySequence.sequence)
        && Objects.equals(binaryFile, entropySequence.binaryFile)
        && Objects.equals(text, entropySequence.text)
        && Objects.equals(rollDices, entropySequence.rollDices)
        && Arrays.equals(binaryData, entropySequence.binaryData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        entropySequenceSource, sequenceRadix, sequence, binaryFile, text, rollDices, binaryData);
  }
}
