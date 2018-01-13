package configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PresetChars {

  @XmlElement private Boolean lowerCaseLettersCheckBox; // a b c ...
  private static List<String> lowerCaseLetters;
  @XmlElement private Boolean upperCaseLettersCheckBox; // A B C ...
  private static List<String> upperCaseLetters;
  @XmlElement private Boolean digitsCheckBox; // 0 1 2 ...
  private static List<String> digits;
  @XmlElement private Boolean commercialAtCheckBox; // @
  private static List<String> commercialAt;
  @XmlElement private Boolean underscoreCheckBox; // _
  private static List<String> underscore;
  @XmlElement private Boolean dollarSignCheckBox; // $
  private static List<String> dollarSign;
  @XmlElement private Boolean dotCheckBox; // .
  private static List<String> dot;
  @XmlElement private Boolean bracesCheckBox; // <> () [] {}
  private static List<String> braces;
  @XmlElement private Boolean specialCheckBox; // + - =
  private static List<String> special;
  @XmlElement private Boolean customCharactersCheckBox;
  @XmlElement private List<String> customCharacters;
  @XmlElement private Integer minimalOccurences;

  static {
    lowerCaseLetters = new ArrayList<>('z' - 'a' + 1);
    for (char ch = 'a'; ch <= 'z'; ch++) {
      lowerCaseLetters.add(String.valueOf(ch));
    }

    upperCaseLetters = new ArrayList<>('Z' - 'A' + 1);
    for (char ch = 'A'; ch <= 'Z'; ch++) {
      upperCaseLetters.add(String.valueOf(ch));
    }

    digits = new ArrayList<>('9' - '0' + 1);
    for (char ch = '0'; ch <= '9'; ch++) {
      digits.add(String.valueOf(ch));
    }

    commercialAt = Collections.singletonList("@");
    underscore = Collections.singletonList("_");
    dollarSign = Collections.singletonList("$");
    dot = Collections.singletonList(".");

    braces = Arrays.asList("<", ">", "{", "}", "[", "]", "(", ")");
    special = Arrays.asList("+", "-", "=");
  }

  public PresetChars() {
    this.lowerCaseLettersCheckBox = false;
    this.upperCaseLettersCheckBox = false;
    this.digitsCheckBox = false;
    this.commercialAtCheckBox = false;
    this.underscoreCheckBox = false;
    this.dollarSignCheckBox = false;
    this.bracesCheckBox = false;
    this.dotCheckBox = false;
    this.specialCheckBox = false;
    this.customCharactersCheckBox = false;
    this.minimalOccurences = 0;
    this.customCharacters = new ArrayList<>();
  }

  public List<String> ProduceCharacterSet() {
    List<String> characters = new ArrayList<>();
    if (lowerCaseLettersCheckBox) {
      characters.addAll(lowerCaseLetters);
    }
    if (upperCaseLettersCheckBox) {
      characters.addAll(upperCaseLetters);
    }
    if (digitsCheckBox) {
      characters.addAll(digits);
    }
    if (commercialAtCheckBox) {
      characters.addAll(commercialAt);
    }
    if (underscoreCheckBox) {
      characters.addAll(underscore);
    }
    if (dollarSignCheckBox) {
      characters.addAll(dollarSign);
    }
    if (dotCheckBox) {
      characters.addAll(dot);
    }
    if (bracesCheckBox) {
      characters.addAll(braces);
    }
    if (specialCheckBox) {
      characters.addAll(special);
    }
    if (customCharactersCheckBox) {
      characters.addAll(customCharacters);
    }
    return characters;
  }

  public boolean isValid() {
    boolean isValid =
        lowerCaseLettersCheckBox
            || upperCaseLettersCheckBox
            || digitsCheckBox
            || commercialAtCheckBox
            || underscoreCheckBox
            || dollarSignCheckBox
            || dotCheckBox
            || bracesCheckBox
            || specialCheckBox
            || customCharactersCheckBox;
    isValid = isValid && (customCharactersCheckBox != customCharacters.isEmpty());
    isValid = isValid && (minimalOccurences >= 0);
    return isValid;
  }

  public boolean lowerCaseLettersCheckBox() {
    return lowerCaseLettersCheckBox;
  }

  public void setLowerCaseLettersCheckBox(boolean lowerCaseLettersCheckBoxValue) {
    this.lowerCaseLettersCheckBox = lowerCaseLettersCheckBoxValue;
  }

  public void setLowerCaseLettersCheckBox(JCheckBox lowerCaseLettersCheckBox) {
    this.setLowerCaseLettersCheckBox(lowerCaseLettersCheckBox.isSelected());
  }

  public boolean upperCaseLettersCheckBox() {
    return upperCaseLettersCheckBox;
  }

  public void setUpperCaseLettersCheckBox(boolean upperCaseLettersCheckBoxValue) {
    this.upperCaseLettersCheckBox = upperCaseLettersCheckBoxValue;
  }

  public void setUpperCaseLettersCheckBox(JCheckBox upperCaseLettersCheckBox) {
    this.setUpperCaseLettersCheckBox(upperCaseLettersCheckBox.isSelected());
  }

  public boolean digitsCheckBox() {
    return digitsCheckBox;
  }

  public void setDigitsCheckBox(boolean digitsCheckBoxValue) {
    this.digitsCheckBox = digitsCheckBoxValue;
  }

  public void setDigitsCheckBox(JCheckBox digitsCheckBox) {
    this.setDigitsCheckBox(digitsCheckBox.isSelected());
  }

  public boolean commercialAtCheckBox() {
    return commercialAtCheckBox;
  }

  public void setCommercialAtCheckBox(boolean commercialAtCheckBoxValue) {
    this.commercialAtCheckBox = commercialAtCheckBoxValue;
  }

  public void setCommercialAtCheckBox(JCheckBox commercialAtCheckBox) {
    this.setCommercialAtCheckBox(commercialAtCheckBox.isSelected());
  }

  public boolean underscoreCheckBox() {
    return underscoreCheckBox;
  }

  public void setUnderscoreCheckBox(boolean underscoreCheckBoxValue) {
    this.underscoreCheckBox = underscoreCheckBoxValue;
  }

  public void setUnderscoreCheckBox(JCheckBox underscoreCheckBox) {
    this.setUnderscoreCheckBox(underscoreCheckBox.isSelected());
  }

  public boolean dollarSignCheckBox() {
    return dollarSignCheckBox;
  }

  public void setDollarSignCheckBox(boolean dollarSignCheckBoxValue) {
    this.dollarSignCheckBox = dollarSignCheckBoxValue;
  }

  public void setDollarSignCheckBox(JCheckBox dollarSignCheckBox) {
    this.setDollarSignCheckBox(dollarSignCheckBox.isSelected());
  }

  public boolean bracesCheckBox() {
    return bracesCheckBox;
  }

  public void setBracesCheckBox(boolean bracesCheckBoxValue) {
    this.bracesCheckBox = bracesCheckBoxValue;
  }

  public void setBracesCheckBox(JCheckBox bracesCheckBox) {
    this.setBracesCheckBox(bracesCheckBox.isSelected());
  }

  public boolean dotCheckBox() {
    return dotCheckBox;
  }

  public void setDotCheckBox(boolean dotCheckBoxValue) {
    this.dotCheckBox = dotCheckBoxValue;
  }

  public void setDotCheckBox(JCheckBox dotCheckBox) {
    this.setDotCheckBox(dotCheckBox.isSelected());
  }

  public boolean specialCheckBox() {
    return specialCheckBox;
  }

  public void setSpecialCheckBox(boolean specialCheckBoxValue) {
    this.specialCheckBox = specialCheckBoxValue;
  }

  public void setSpecialCheckBox(JCheckBox specialCheckBox) {
    this.setSpecialCheckBox(specialCheckBox.isSelected());
  }

  public boolean customCharactersCheckBox() {
    return customCharactersCheckBox;
  }

  public void setCustomCharactersCheckBox(boolean customCharactersCheckBoxValue) {
    this.customCharactersCheckBox = customCharactersCheckBoxValue;
  }

  public void setCustomCharactersCheckBox(JCheckBox customCharactersCheckBox) {
    this.setCustomCharactersCheckBox(customCharactersCheckBox.isSelected());
  }

  public List<String> customCharacters() {
    return new ArrayList<>(customCharacters);
  }

  public void setCustomCharacters(List<String> customCharacters) {
    this.customCharacters = new ArrayList<>();
    for (String str : customCharacters) {
      if (!str.isEmpty()) {
        this.customCharacters.add(str);
      }
    }
  }

  public void setCustomCharacters(String[] customCharacters) {
    this.setCustomCharacters(Arrays.asList(customCharacters));
  }

  public void setCustomCharacters(String customCharactersString) {
    this.setCustomCharacters(customCharactersString.replaceAll("\\s+", "").split(","));
  }

  public void setCustomCharacters(JTextPane customCharactersTextPane) {
    this.setCustomCharacters(customCharactersTextPane.getText());
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    if (lowerCaseLettersCheckBox) {
      str.append("[a-z]");
    }
    if (upperCaseLettersCheckBox) {
      str.append("[A-Z]");
    }
    if (digitsCheckBox) {
      str.append("[0-9]");
    }
    if (commercialAtCheckBox) {
      str.append("[@]");
    }
    if (underscoreCheckBox) {
      str.append("[_]");
    }
    if (dollarSignCheckBox) {
      str.append("[$]");
    }
    if (dotCheckBox) {
      str.append("[.]");
    }
    if (bracesCheckBox) {
      str.append("[{,},<,>,[,],(,)]");
    }
    if (specialCheckBox) {
      str.append("[+,-,=]");
    }
    if (customCharactersCheckBox) {
      str.append('[');
      for (String s : customCharacters) {
        str.append(s);
        str.append(',');
      }
      str.setCharAt(str.lastIndexOf(","), ']');
    }
    return str.toString();
  }

  public int minimalOccurences() {
    return minimalOccurences;
  }

  public void setMinimalOccurences(int minimalOccurences) {
    this.minimalOccurences = minimalOccurences;
  }
}
