package data.holder;

import java.io.File;
import java.util.List;

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
    }
  }

  private void setText(String text) {

  }

  private void setRollDices(Integer dices, String rollDices) {

  }

  private void setBinaryFile(File binaryFile, byte[] binaryData) {

  }

  private void clearEntropy() {
    entropySequenceSource = EntropySequenceSource.NONE;
    sequenceRadix = null;
    sequence = null;
    binaryFile = null;
    text = null;
  }

  public EntropySequenceSource getEntropySequenceSource() {
    return entropySequenceSource;
  }

  public Integer getSequenceRadix() {
    return sequenceRadix;
  }

  public List<Integer> getSequence() {
    return sequence;
  }
}
