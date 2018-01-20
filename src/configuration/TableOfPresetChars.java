package configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TableOfPresetChars {

  @XmlElementWrapper(name = "ListTableOfPresetChars")
  public List<PresetChars> tableOfPresetChars;

  public TableOfPresetChars() {
    tableOfPresetChars = new ArrayList<>();
  }

  public List<String> produceCharacterSet() {
    List<String> list = new ArrayList<>();
    for (PresetChars presetChars : tableOfPresetChars) {
      list.addAll(presetChars.produceCharacterSet());
    }
    return list;
  }

  public List<PresetChars> getTableOfPresetChars() {
    return new ArrayList<>(tableOfPresetChars);
  }

  public void setTableOfPresetChars(List<PresetChars> tableOfPresetChars) {
    this.tableOfPresetChars = new ArrayList<>();
    for (PresetChars presetChars : tableOfPresetChars) {
      if (presetChars.isValid()) {
        this.tableOfPresetChars.add(presetChars);
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof TableOfPresetChars)) {
      return false;
    }
    TableOfPresetChars tableOfPresetChars = (TableOfPresetChars) o;
    return Objects.equals(this.tableOfPresetChars, tableOfPresetChars.tableOfPresetChars);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tableOfPresetChars);
  }
}
