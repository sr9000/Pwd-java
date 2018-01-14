package configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PresetChars {

  private Boolean lowerCaseLettersCheckBox; // a b c ...
  private Boolean upperCaseLettersCheckBox; // A B C ...
  private Boolean digitsCheckBox; // 0 1 2 ...
  private Boolean commercialAtCheckBox; // @
  private Boolean underscoreCheckBox; // _
  private Boolean dollarSignCheckBox; // $
  private Boolean dotCheckBox; // .
  private Boolean bracesCheckBox; // <> () [] {}
  private Boolean specialCheckBox; // + - =
  private Boolean customCharactersCheckBox;

  @XmlElementWrapper(name = "ListCustomCharacters")
  private List<String> customCharacters;

  private Integer minimalOccurences;

  private static List<String> lowerCaseLetters;
  private static List<String> upperCaseLetters;
  private static List<String> digits;
  private static List<String> commercialAt;
  private static List<String> underscore;
  private static List<String> dollarSign;
  private static List<String> dot;
  private static List<String> braces;
  private static List<String> special;

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

  public Boolean getLowerCaseLettersCheckBox() {
    return lowerCaseLettersCheckBox;
  }

  public void setLowerCaseLettersCheckBox(Boolean lowerCaseLettersCheckBoxValue) {
    this.lowerCaseLettersCheckBox = lowerCaseLettersCheckBoxValue;
  }

  public void setLowerCaseLettersCheckBox(JCheckBox lowerCaseLettersCheckBox) {
    this.setLowerCaseLettersCheckBox(lowerCaseLettersCheckBox.isSelected());
  }

  public Boolean getUpperCaseLettersCheckBox() {
    return upperCaseLettersCheckBox;
  }

  public void setUpperCaseLettersCheckBox(Boolean upperCaseLettersCheckBoxValue) {
    this.upperCaseLettersCheckBox = upperCaseLettersCheckBoxValue;
  }

  public void setUpperCaseLettersCheckBox(JCheckBox upperCaseLettersCheckBox) {
    this.setUpperCaseLettersCheckBox(upperCaseLettersCheckBox.isSelected());
  }

  public Boolean getDigitsCheckBox() {
    return digitsCheckBox;
  }

  public void setDigitsCheckBox(Boolean digitsCheckBoxValue) {
    this.digitsCheckBox = digitsCheckBoxValue;
  }

  public void setDigitsCheckBox(JCheckBox digitsCheckBox) {
    this.setDigitsCheckBox(digitsCheckBox.isSelected());
  }

  public Boolean getCommercialAtCheckBox() {
    return commercialAtCheckBox;
  }

  public void setCommercialAtCheckBox(Boolean commercialAtCheckBoxValue) {
    this.commercialAtCheckBox = commercialAtCheckBoxValue;
  }

  public void setCommercialAtCheckBox(JCheckBox commercialAtCheckBox) {
    this.setCommercialAtCheckBox(commercialAtCheckBox.isSelected());
  }

  public Boolean getUnderscoreCheckBox() {
    return underscoreCheckBox;
  }

  public void setUnderscoreCheckBox(Boolean underscoreCheckBoxValue) {
    this.underscoreCheckBox = underscoreCheckBoxValue;
  }

  public void setUnderscoreCheckBox(JCheckBox underscoreCheckBox) {
    this.setUnderscoreCheckBox(underscoreCheckBox.isSelected());
  }

  public Boolean getDollarSignCheckBox() {
    return dollarSignCheckBox;
  }

  public void setDollarSignCheckBox(Boolean dollarSignCheckBoxValue) {
    this.dollarSignCheckBox = dollarSignCheckBoxValue;
  }

  public void setDollarSignCheckBox(JCheckBox dollarSignCheckBox) {
    this.setDollarSignCheckBox(dollarSignCheckBox.isSelected());
  }

  public Boolean getBracesCheckBox() {
    return bracesCheckBox;
  }

  public void setBracesCheckBox(Boolean bracesCheckBoxValue) {
    this.bracesCheckBox = bracesCheckBoxValue;
  }

  public void setBracesCheckBox(JCheckBox bracesCheckBox) {
    this.setBracesCheckBox(bracesCheckBox.isSelected());
  }

  public Boolean getDotCheckBox() {
    return dotCheckBox;
  }

  public void setDotCheckBox(Boolean dotCheckBoxValue) {
    this.dotCheckBox = dotCheckBoxValue;
  }

  public void setDotCheckBox(JCheckBox dotCheckBox) {
    this.setDotCheckBox(dotCheckBox.isSelected());
  }

  public Boolean getSpecialCheckBox() {
    return specialCheckBox;
  }

  public void setSpecialCheckBox(Boolean specialCheckBoxValue) {
    this.specialCheckBox = specialCheckBoxValue;
  }

  public void setSpecialCheckBox(JCheckBox specialCheckBox) {
    this.setSpecialCheckBox(specialCheckBox.isSelected());
  }

  public Boolean getCustomCharactersCheckBox() {
    return customCharactersCheckBox;
  }

  public void setCustomCharactersCheckBox(Boolean customCharactersCheckBoxValue) {
    this.customCharactersCheckBox = customCharactersCheckBoxValue;
  }

  public void setCustomCharactersCheckBox(JCheckBox customCharactersCheckBox) {
    this.setCustomCharactersCheckBox(customCharactersCheckBox.isSelected());
  }

  public List<String> getCustomCharacters() {
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

  public Integer getMinimalOccurences() {
    return minimalOccurences;
  }

  public void setMinimalOccurences(Integer minimalOccurences) {
    this.minimalOccurences = minimalOccurences;
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

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof PresetChars)) {
      return false;
    }
    PresetChars presetChars = (PresetChars) o;
    return Objects.equals(lowerCaseLettersCheckBox, presetChars.lowerCaseLettersCheckBox)
        && Objects.equals(upperCaseLettersCheckBox, presetChars.upperCaseLettersCheckBox)
        && Objects.equals(digitsCheckBox, presetChars.digitsCheckBox)
        && Objects.equals(commercialAtCheckBox, presetChars.commercialAtCheckBox)
        && Objects.equals(underscoreCheckBox, presetChars.underscoreCheckBox)
        && Objects.equals(dollarSignCheckBox, presetChars.dollarSignCheckBox)
        && Objects.equals(dotCheckBox, presetChars.dotCheckBox)
        && Objects.equals(bracesCheckBox, presetChars.bracesCheckBox)
        && Objects.equals(specialCheckBox, presetChars.specialCheckBox)
        && Objects.equals(customCharactersCheckBox, presetChars.customCharactersCheckBox)
        && Objects.equals(customCharacters, presetChars.customCharacters)
        && Objects.equals(minimalOccurences, presetChars.minimalOccurences);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        lowerCaseLettersCheckBox,
        upperCaseLettersCheckBox,
        digitsCheckBox,
        commercialAtCheckBox,
        underscoreCheckBox,
        dollarSignCheckBox,
        dotCheckBox,
        bracesCheckBox,
        specialCheckBox,
        customCharactersCheckBox,
        customCharacters,
        minimalOccurences);
  }
}
