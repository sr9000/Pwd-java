import configuration.PresetChars;
import configuration.TableOfPresetChars;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class Form1 {

  private Integer editableRow;
  private JTextField minimalPasswordLength;
  private JButton acceptButton;
  private JButton addButton;
  private JButton caBackButton;
  private JButton cancelButton;
  private JButton caNextButton;
  private JPanel cardAlphabet;
  private JPanel cardPresetChars;
  private JPanel cardEntropy;
  private JPanel cardLength;
  private JPanel cardViewChars;
  private JButton ceBackButton;
  private JButton clBackButton;
  private JButton clNextButton;
  private JButton editButton;
  private JButton getMyPasswordButton;
  private JButton loadTableButton;
  private JPanel panelMain;
  private JProgressBar passwordProgressBar;
  private JSpinner pwdLenSpinner;
  private JButton removeButton;
  private JButton saveTableButton;
  private JTable tableCharacterSets;
  private JButton addButton1;
  private JButton editButton1;
  private JButton removeButton1;
  private JTable tableEntropySequences;
  private JButton loadPresetButton;
  private JButton savePresetButton;
  private JSpinner minimalOccurences;
  private JCheckBox lowerCaseLettersCheckBox;
  private JCheckBox upperCaseLettersCheckBox;
  private JCheckBox digitsCheckBox;
  private JCheckBox commercialAtCheckBox;
  private JCheckBox underscoreCheckBox;
  private JCheckBox dollarSignCheckBox;
  private JCheckBox bracesCheckBox;
  private JCheckBox dotCheckBox;
  private JCheckBox specialCheckBox;
  private JCheckBox customSetCheckBox;
  private JTextPane customSetTextPane;
  private JPanel cardAddEntropy;
  private JPanel cardSelectEntropy;
  private JButton nextButton;
  private JButton backButton;
  private JRadioButton pregeneratedRandomBinaryDataRadioButton;
  private JRadioButton diceRollEntropyRadioButton;
  private JPanel cardDices;
  private JButton cancelButton1;
  private JButton backButton1;
  private JButton acceptButton1;
  private JSpinner spinner2;
  private JTextPane textPane2;
  private JPanel cardText;
  private JButton cancelButton2;
  private JButton backButton2;
  private JButton acceptButton2;
  private JTextPane textPane3;
  private JPanel cardBinary;
  private JButton cancelButton3;
  private JButton backButton3;
  private JButton acceptButton3;
  private JTextPane textPane4;
  private JButton button1;
  private JTextField textField1;
  private JRadioButton textBasedEntropyRadioButton;
  private JPanel cardGetPassword;
  private JButton copyToClipboardButton;
  private JButton saveAsFileButton;
  private JButton generateNewPasswordButton;
  private JCheckBox viewPasswordCheckBox;
  private JTextPane textPane5;
  private JCheckBox encodeWithBase64CheckBox;
  private JCheckBox encryptionAES256CheckBox;
  private JPasswordField passwordField1;
  private JPasswordField passwordField2;

  public Form1() {

    caNextButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).next(panelMain);
            int minLen = Integer.max(1, Integer.parseInt(minimalPasswordLength.getText()));
            pwdLenSpinner.setValue(minLen);
          }
        });
    clNextButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
        });

    clBackButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).previous(panelMain);
          }
        });
    ceBackButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).previous(panelMain);
          }
        });

    addButton.addActionListener(
        e -> {
          if (cardAlphabet.getLayout() instanceof CardLayout) {
            ((CardLayout) cardAlphabet.getLayout()).next(cardAlphabet);
            clonePresetChars(new PresetChars());
          }
        });
    editButton.addActionListener(
        e -> {
          if (cardAlphabet.getLayout() instanceof CardLayout) {
            if (!tableCharacterSets.getSelectionModel().isSelectionEmpty()) {
              editableRow = tableCharacterSets.getSelectedRow();
              ((CardLayout) cardAlphabet.getLayout()).next(cardAlphabet);
              clonePresetChars(
                  (PresetChars) tableCharacterSets.getModel().getValueAt(editableRow, 0));
            } else {
              JOptionPane.showMessageDialog(panelMain, "No rows selected!");
            }
          }
        });
    removeButton.addActionListener(
        e -> {
          if (cardAlphabet.getLayout() instanceof CardLayout) {
            if (!tableCharacterSets.getSelectionModel().isSelectionEmpty()) {
              int row = tableCharacterSets.getSelectedRow();
              int[] rows = tableCharacterSets.getSelectedRows();
              if (rows.length == 1) {
                int answer =
                    JOptionPane.showConfirmDialog(
                        panelMain,
                        "Remove row #"
                            + (row + 1)
                            + "?"
                            + System.lineSeparator()
                            + "Row content: "
                            + tableCharacterSets.getValueAt(row, 0),
                        "Confirm row removing",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (answer == JOptionPane.YES_OPTION) {
                  ((DefaultTableModel) tableCharacterSets.getModel()).removeRow(row);
                }
              } else {
                StringBuilder question = new StringBuilder();
                question.append("Remove rows #");
                for (int r : rows) {
                  question.append(r + 1);
                  question.append(',');
                }
                question.setCharAt(question.lastIndexOf(","), '?');

                int answer =
                    JOptionPane.showConfirmDialog(
                        panelMain,
                        question,
                        "Confirm multiple rows removing",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (answer == JOptionPane.YES_OPTION) {
                  Arrays.sort(rows);
                  List<Integer> lrows = Arrays.stream(rows).boxed().collect(Collectors.toList());
                  Collections.reverse(lrows);
                  for (Integer r : lrows) {
                    ((DefaultTableModel) tableCharacterSets.getModel()).removeRow(r);
                  }
                }
              }
            } else {
              JOptionPane.showMessageDialog(panelMain, "No rows selected!");
            }
          }
        });
    saveTableButton.addActionListener(
        e -> {
          TableOfPresetChars tableOfPresetChars = new TableOfPresetChars();

          assignTableOfPresetChars(tableOfPresetChars);

          if (!tableOfPresetChars.getTableOfPresetChars().isEmpty()) {
            generalSaver(
                ", when saving table of presets...", tableOfPresetChars, TableOfPresetChars.class);
          } else {
            JOptionPane.showMessageDialog(panelMain, "Nothing to save!");
          }
        });
    loadTableButton.addActionListener(
        e ->
            generalLoader(
                ", when loading table of presets...",
                table -> {
                  final String replace = "Replace current list with loaded values.";
                  final String append = "Append current list with loaded values.";
                  final String merge = "Merge current list and loaded values.";
                  String[] values = {replace, append, merge};

                  Object selected =
                      JOptionPane.showInputDialog(
                          panelMain,
                          "How you want to load new values?",
                          "Selecting loading method",
                          JOptionPane.DEFAULT_OPTION,
                          null,
                          values,
                          replace);
                  if (selected != null) { // null if the user cancels.
                    String selectedString = selected.toString();
                    switch (selectedString) {
                      case replace:
                        replaceWithNewTableOfPresetChars(table);
                        break;
                      case append:
                        appendWithNewTableOfPresetChars(table);
                        break;
                      case merge:
                        mergeWithNewTableOfPresetChars(table);
                        break;
                    }
                  }
                },
                TableOfPresetChars.class));

    cancelButton.addActionListener(
        e -> {
          if (cardAlphabet.getLayout() instanceof CardLayout) {
            editableRow = null;
            ((CardLayout) cardAlphabet.getLayout()).previous(cardAlphabet);
          }
        });
    acceptButton.addActionListener(
        e -> {
          if (cardAlphabet.getLayout() instanceof CardLayout) {
            PresetChars presetChars = new PresetChars();

            assignPresetChars(presetChars);

            if (presetChars.isValid()) {
              if (editableRow == null) {
                ((DefaultTableModel) (tableCharacterSets.getModel()))
                    .addRow(new Object[] {presetChars, presetChars.getMinimalOccurences()});
              } else {
                ((DefaultTableModel) (tableCharacterSets.getModel())).removeRow(editableRow);
                ((DefaultTableModel) (tableCharacterSets.getModel()))
                    .insertRow(
                        editableRow,
                        new Object[] {presetChars, presetChars.getMinimalOccurences()});
                editableRow = null;
              }
              ((CardLayout) cardAlphabet.getLayout()).previous(cardAlphabet);
            } else {
              JOptionPane.showMessageDialog(panelMain, "Character set filled incorrectly!");
            }
          }
        });
    savePresetButton.addActionListener(
        e -> {
          PresetChars presetChars = new PresetChars();

          assignPresetChars(presetChars);

          if (presetChars.isValid()) {
            generalSaver(", when saving preset...", presetChars, PresetChars.class);
          } else {
            JOptionPane.showMessageDialog(panelMain, "Character set filled incorrectly!");
          }
        });
    loadPresetButton.addActionListener(
        e -> generalLoader(", when loading preset...", this::clonePresetChars, PresetChars.class));

    addButton1.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
          if (cardAddEntropy.getLayout() instanceof CardLayout) {
            ((CardLayout) cardAddEntropy.getLayout()).first(cardAddEntropy);
          }
        });
    backButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).previous(panelMain);
          }
        });

    cancelButton1.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).first(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
        });
    backButton1.addActionListener(
        e -> {
          if (cardAddEntropy.getLayout() instanceof CardLayout) {
            ((CardLayout) cardAddEntropy.getLayout()).first(cardAddEntropy);
          }
        });
    acceptButton1.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).first(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
        });

    cancelButton2.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).first(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
        });
    backButton2.addActionListener(
        e -> {
          if (cardAddEntropy.getLayout() instanceof CardLayout) {
            ((CardLayout) cardAddEntropy.getLayout()).first(cardAddEntropy);
          }
        });
    acceptButton2.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).first(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
        });

    cancelButton3.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).first(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
        });
    backButton3.addActionListener(
        e -> {
          if (cardAddEntropy.getLayout() instanceof CardLayout) {
            ((CardLayout) cardAddEntropy.getLayout()).first(cardAddEntropy);
          }
        });
    acceptButton3.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).first(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
        });

    nextButton.addActionListener(
        e -> {
          if (!(cardAddEntropy.getLayout() instanceof CardLayout)) {
            return;
          }
          ((CardLayout) cardAddEntropy.getLayout()).next(cardAddEntropy);
          if (diceRollEntropyRadioButton.isSelected()) {
            return;
          }
          ((CardLayout) cardAddEntropy.getLayout()).next(cardAddEntropy);
          if (textBasedEntropyRadioButton.isSelected()) {
            return;
          }
          ((CardLayout) cardAddEntropy.getLayout()).next(cardAddEntropy);
        });

    getMyPasswordButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).last(panelMain);
          }
        });
    generateNewPasswordButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).first(panelMain);

            passwordProgressBar.setValue(0);
            DefaultTableModel model = (DefaultTableModel) tableEntropySequences.getModel();
            while (model.getRowCount() > 0) {
              model.removeRow(0);
            }
          }
        });

    customSetCheckBox.addItemListener(
        e -> {
          if (e.getStateChange() == ItemEvent.SELECTED) {
            customSetTextPane.setText("");
            customSetTextPane.setEditable(true);
          } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            customSetTextPane.setText("");
            customSetTextPane.setEditable(false);
          }
        });

    minimalOccurences.addChangeListener(
        e -> {
          int occurences = (Integer) (minimalOccurences.getValue());
          if (occurences < 0) {
            minimalOccurences.setValue(0);
          }
        });

    tableCharacterSets
        .getModel()
        .addTableModelListener(
            e -> {
              DefaultTableModel model = (DefaultTableModel) e.getSource();
              int minPassLen = 0;
              for (int i = 0; i < model.getRowCount(); i++) {
                Object val = model.getValueAt(i, 1);
                minPassLen += (Integer) val;
              }
              minimalPasswordLength.setText(String.valueOf(minPassLen));

              TableOfPresetChars tableOfPresetChars = new TableOfPresetChars();
              assignTableOfPresetChars(tableOfPresetChars);
              caNextButton.setEnabled(tableOfPresetChars.produceCharacterSet().size() > 1);
            });

    pwdLenSpinner.addChangeListener(
        e -> {
          int len = (Integer) (pwdLenSpinner.getValue());
          int minLen = Integer.max(1, Integer.parseInt(minimalPasswordLength.getText()));
          if (len < minLen) {
            pwdLenSpinner.setValue(minLen);
          }
        });
  }

  private void assignTableOfPresetChars(TableOfPresetChars tableOfPresetChars) {
    List<PresetChars> list = new ArrayList<>();

    for (int i = 0; i < tableCharacterSets.getRowCount(); i++) {
      list.add((PresetChars) tableCharacterSets.getValueAt(i, 0));
    }
    tableOfPresetChars.setTableOfPresetChars(list);
  }

  private void replaceWithNewTableOfPresetChars(TableOfPresetChars tableOfPresetChars) {
    DefaultTableModel model = (DefaultTableModel) tableCharacterSets.getModel();
    while (model.getRowCount() > 0) {
      model.removeRow(0);
    }
    for (PresetChars presetChars : tableOfPresetChars.getTableOfPresetChars()) {
      model.addRow(new Vector<>(Arrays.asList(presetChars, presetChars.getMinimalOccurences())));
    }
  }

  private void appendWithNewTableOfPresetChars(TableOfPresetChars tableOfPresetChars) {
    DefaultTableModel model = (DefaultTableModel) tableCharacterSets.getModel();
    for (PresetChars presetChars : tableOfPresetChars.getTableOfPresetChars()) {
      model.addRow(new Vector<>(Arrays.asList(presetChars, presetChars.getMinimalOccurences())));
    }
  }

  private void mergeWithNewTableOfPresetChars(TableOfPresetChars tableOfPresetChars) {
    DefaultTableModel model = (DefaultTableModel) tableCharacterSets.getModel();
    List<PresetChars> list = new ArrayList<>();

    for (Object v : model.getDataVector()) {
      Vector<Object> vec = (Vector<Object>) v;
      list.add((PresetChars) vec.get(0));
    }

    for (PresetChars presetChars : tableOfPresetChars.getTableOfPresetChars()) {
      if (!list.contains(presetChars)) {
        model.addRow(new Vector<>(Arrays.asList(presetChars, presetChars.getMinimalOccurences())));
      }
    }
  }

  private <T> void generalLoader(
      String callerId, Consumer<T> process, Class<T> classOfObjectToLoad) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify a file to load");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("XML FILES", "xml", "XML");
    fileChooser.setFileFilter(filter);

    int userSelection = fileChooser.showOpenDialog(panelMain);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToLoad = fileChooser.getSelectedFile();
      if (fileToLoad.exists() && !fileToLoad.isFile()) {
        JOptionPane.showMessageDialog(
            panelMain,
            "You should specify true file (not directory or something else)!",
            "Error" + callerId,
            JOptionPane.ERROR_MESSAGE);
      } else if (!fileToLoad.getName().toLowerCase().endsWith(".xml")) {
        JOptionPane.showMessageDialog(
            panelMain,
            "You should specify \".xml\" extension manually!",
            "Error" + callerId,
            JOptionPane.ERROR_MESSAGE);
      } else if (!fileToLoad.exists()) {
        JOptionPane.showMessageDialog(
            panelMain,
            "File should exist to be loaded!",
            "Error" + callerId,
            JOptionPane.ERROR_MESSAGE);
      } else {
        try {
          JAXBContext jaxbContext = JAXBContext.newInstance(classOfObjectToLoad);
          T objToLoad = (T) jaxbContext.createUnmarshaller().unmarshal(fileToLoad);
          process.accept(objToLoad);
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(
              panelMain, ex.toString(), "Exception" + callerId, JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }

  private <T> void generalSaver(String callerId, T objToSave, Class<T> classOfObjectToSave) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify a file to save");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("XML FILES", "xml", "XML");
    fileChooser.setFileFilter(filter);

    int userSelection = fileChooser.showSaveDialog(panelMain);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      if (fileToSave.exists() && !fileToSave.isFile()) {
        JOptionPane.showMessageDialog(
            panelMain,
            "You should specify true file (not directory or something else)!",
            "Error" + callerId,
            JOptionPane.ERROR_MESSAGE);
      } else if (!fileToSave.getName().toLowerCase().endsWith(".xml")) {
        JOptionPane.showMessageDialog(
            panelMain,
            "You should specify \".xml\" extension manually!",
            "Error" + callerId,
            JOptionPane.ERROR_MESSAGE);
      } else {
        try {
          JAXBContext jaxbContext = JAXBContext.newInstance(classOfObjectToSave);
          boolean ignoredBoolean = fileToSave.createNewFile();
          jaxbContext.createMarshaller().marshal(objToSave, fileToSave);
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(
              panelMain, ex.toString(), "Exception" + callerId, JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }

  private void assignPresetChars(PresetChars presetChars) {
    presetChars.setBracesCheckBox(bracesCheckBox);
    presetChars.setCommercialAtCheckBox(commercialAtCheckBox);
    presetChars.setCustomCharacters(customSetTextPane);
    presetChars.setCustomCharactersCheckBox(customSetCheckBox);
    presetChars.setDigitsCheckBox(digitsCheckBox);
    presetChars.setDollarSignCheckBox(dollarSignCheckBox);
    presetChars.setDotCheckBox(dotCheckBox);
    presetChars.setLowerCaseLettersCheckBox(lowerCaseLettersCheckBox);
    presetChars.setSpecialCheckBox(specialCheckBox);
    presetChars.setUnderscoreCheckBox(underscoreCheckBox);
    presetChars.setUpperCaseLettersCheckBox(upperCaseLettersCheckBox);
    presetChars.setMinimalOccurences((Integer) (minimalOccurences.getValue()));
  }

  private void clonePresetChars(PresetChars presetChars) {
    bracesCheckBox.setSelected(presetChars.getBracesCheckBox());
    commercialAtCheckBox.setSelected(presetChars.getCommercialAtCheckBox());
    customSetCheckBox.setSelected(presetChars.getCustomCharactersCheckBox());
    digitsCheckBox.setSelected(presetChars.getDigitsCheckBox());
    dollarSignCheckBox.setSelected(presetChars.getDollarSignCheckBox());
    dotCheckBox.setSelected(presetChars.getDotCheckBox());
    lowerCaseLettersCheckBox.setSelected(presetChars.getLowerCaseLettersCheckBox());
    specialCheckBox.setSelected(presetChars.getSpecialCheckBox());
    underscoreCheckBox.setSelected(presetChars.getUnderscoreCheckBox());
    upperCaseLettersCheckBox.setSelected(presetChars.getUpperCaseLettersCheckBox());
    minimalOccurences.setValue(presetChars.getMinimalOccurences());

    if (presetChars.getCustomCharactersCheckBox()) {
      customSetTextPane.setEditable(true);
      StringBuilder customSetText = new StringBuilder();
      for (String str : presetChars.getCustomCharacters()) {
        customSetText.append(str);
        customSetText.append(",");
      }
      customSetText.setCharAt(customSetText.lastIndexOf(","), ' ');
      customSetTextPane.setText(customSetText.toString());
    } else {
      customSetTextPane.setEditable(false);
      customSetTextPane.setText("");
    }
  }

  private static void setup_tableCharacterSets(Form1 form) {
    DefaultTableModel model = (DefaultTableModel) form.tableCharacterSets.getModel();
    form.tableCharacterSets.setDefaultEditor(Object.class, null);
    model.addColumn("Character Set Preview");
    model.addColumn("Minimal Occurences");
  }

  private static void setup_tableEntropySequences(Form1 form) {
    DefaultTableModel model = (DefaultTableModel) form.tableEntropySequences.getModel();
    form.tableEntropySequences.setDefaultEditor(Object.class, null);
    model.addColumn("Entropy Preview");
    model.addColumn("Entropy Source");
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Ultimate Password Generator");

    Form1 myForm = new Form1();
    setup_tableCharacterSets(myForm);
    setup_tableEntropySequences(myForm);

    frame.setContentPane(myForm.panelMain);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    frame.setSize(frame.getWidth(), 410);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
