import configuration.PresetChars;
import configuration.TableOfPresetChars;
import data.holder.EntropySequence;
import data.holder.EntropySequence.EntropySequenceSource;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;

public class Form1 {

  public static final int MAXIMUM_BINARY_DATA_TO_READ = 100000;
  public static final int MAXIMUM_BINARY_DATA_TO_PREVIEW = 100;
  private File binaryFile;
  private byte[] binaryData;
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
  private JButton seNextButton;
  private JButton seBackButton;
  private JRadioButton pregeneratedRandomBinaryDataRadioButton;
  private JRadioButton diceRollEntropyRadioButton;
  private JPanel cardDices;
  private JButton rollDicesCancelButton;
  private JButton rollDicesBackButton;
  private JButton rollDicesAcceptButton;
  private JSpinner rollDicesRadixSpinner;
  private JTextPane rollDicesTextPane;
  private JPanel cardText;
  private JButton textEntropyCancelButton;
  private JButton textEntropyBackButton;
  private JButton textEntropyAcceptButton;
  private JTextPane textEntropyTextPane;
  private JPanel cardBinary;
  private JButton binaryFileCancelButton;
  private JButton binaryFileBackButton;
  private JButton binaryFileAcceptButton;
  private JTextPane binaryDataTextPane;
  private JButton binaryFileButton;
  private JTextField binaryFileTextField;
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
    seBackButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).previous(panelMain);
          }
        });

    rollDicesCancelButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).first(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
        });
    rollDicesBackButton.addActionListener(
        e -> {
          if (cardAddEntropy.getLayout() instanceof CardLayout) {
            ((CardLayout) cardAddEntropy.getLayout()).first(cardAddEntropy);
          }
        });
    rollDicesAcceptButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {

            EntropySequence entropySequence = new EntropySequence();
            entropySequence.setEntropySequenceSource(
                EntropySequenceSource.ROLL_DICES,
                null,
                null,
                null,
                (Integer) rollDicesRadixSpinner.getValue(),
                rollDicesTextPane.getText());

            acceptEntropy(entropySequence);
          }
        });

    textEntropyCancelButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).first(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
        });
    textEntropyBackButton.addActionListener(
        e -> {
          if (cardAddEntropy.getLayout() instanceof CardLayout) {
            ((CardLayout) cardAddEntropy.getLayout()).first(cardAddEntropy);
          }
        });
    textEntropyAcceptButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            EntropySequence entropySequence = new EntropySequence();
            entropySequence.setEntropySequenceSource(
                EntropySequenceSource.TEXT, null, null, textEntropyTextPane.getText(), null, null);

            acceptEntropy(entropySequence);
          }
        });

    binaryFileCancelButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).first(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
        });
    binaryFileBackButton.addActionListener(
        e -> {
          if (cardAddEntropy.getLayout() instanceof CardLayout) {
            ((CardLayout) cardAddEntropy.getLayout()).first(cardAddEntropy);
          }
        });
    binaryFileAcceptButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            if (binaryFile == null) {
              JOptionPane.showMessageDialog(
                  panelMain,
                  "No binary file specified.",
                  "Error with binary entropy",
                  JOptionPane.ERROR_MESSAGE);

            } else if (!binaryFile.exists()) {
              JOptionPane.showMessageDialog(
                  panelMain,
                  "Specified file does not exist.",
                  "Error with binary entropy",
                  JOptionPane.ERROR_MESSAGE);

            } else if (!binaryFile.isFile()) {
              JOptionPane.showMessageDialog(
                  panelMain,
                  "Specified path is pointed onto NOT a file.",
                  "Error with binary entropy",
                  JOptionPane.ERROR_MESSAGE);

            } else {
              EntropySequence entropySequence = new EntropySequence();
              entropySequence.setEntropySequenceSource(
                  EntropySequenceSource.BINARY_FILE, binaryFile, binaryData, null, null, null);

              acceptEntropy(entropySequence);
            }
          }
        });

    seNextButton.addActionListener(
        e -> {
          if (!(cardAddEntropy.getLayout() instanceof CardLayout)) {
            return;
          }
          ((CardLayout) cardAddEntropy.getLayout()).next(cardAddEntropy);
          if (diceRollEntropyRadioButton.isSelected()) {
            rollDicesRadixSpinner.setValue(2);
            rollDicesTextPane.setText("");
            return;
          }
          ((CardLayout) cardAddEntropy.getLayout()).next(cardAddEntropy);
          if (textBasedEntropyRadioButton.isSelected()) {
            textEntropyTextPane.setText("");
            return;
          }
          ((CardLayout) cardAddEntropy.getLayout()).next(cardAddEntropy);
          binaryDataTextPane.setText("");
          binaryFileTextField.setText("");
          binaryFile = null;
          binaryData = null;
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

    rollDicesRadixSpinner.addChangeListener(
        e -> {
          int len = (Integer) (rollDicesRadixSpinner.getValue());
          if (len < 2) {
            pwdLenSpinner.setValue(2);
          }
        });

    binaryFileButton.addActionListener(
        e -> {
          JFileChooser fileChooser = new JFileChooser();
          fileChooser.setDialogTitle("Specify a file to load");

          int userSelection = fileChooser.showOpenDialog(panelMain);

          if (userSelection == JFileChooser.APPROVE_OPTION) {
            binaryFile = fileChooser.getSelectedFile();
            binaryFileTextField.setText(binaryFile.getName());
            binaryDataTextPane.setText("");

            if (binaryFile.exists() && !binaryFile.isFile()) {
              JOptionPane.showMessageDialog(
                  panelMain,
                  "You should specify true file (not directory or something else)!",
                  "Error with loading binary entropy",
                  JOptionPane.ERROR_MESSAGE);
            } else if (!binaryFile.exists()) {
              JOptionPane.showMessageDialog(
                  panelMain,
                  "File should exist to be loaded!",
                  "Error with loading binary entropy",
                  JOptionPane.ERROR_MESSAGE);
            } else {
              try {
                FileInputStream fin = new FileInputStream(binaryFile);
                binaryData =
                    new byte[(int) Math.min(MAXIMUM_BINARY_DATA_TO_READ, binaryFile.length())];
                fin.read(binaryData);
                StringBuilder str = new StringBuilder();
                for (int i = 0;
                    i < Math.min(MAXIMUM_BINARY_DATA_TO_PREVIEW, binaryData.length);
                    i++) {
                  byte v = binaryData[i];
                  str.append(bytePreview(v));
                }
                binaryDataTextPane.setText(str.toString());
              } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                    panelMain,
                    ex.toString(),
                    "Exception with loading binary entropy",
                    JOptionPane.ERROR_MESSAGE);
              }
            }
          }
        });
  }

  private static char bytePreview(byte v) {
    if (v < -64) {
      return '-';
    } else if (v < 0) {
      return '+';
    } else if (v < 64) {
      return '=';
    } else {
      return '#';
    }
  }

  private void acceptEntropy(EntropySequence entropySequence) {
    if (entropySequence.isValid()) {
      DefaultTableModel model = (DefaultTableModel) tableEntropySequences.getModel();
      model.addRow(
          new Object[] {entropySequence.getEntropySequenceSource().name(), entropySequence});

      ((CardLayout) panelMain.getLayout()).first(panelMain);
      ((CardLayout) panelMain.getLayout()).next(panelMain);
      ((CardLayout) panelMain.getLayout()).next(panelMain);
    } else {
      JOptionPane.showMessageDialog(
          panelMain,
          "Specified entropy data is unusable. Try to input other data.",
          "Unusable entropy data",
          JOptionPane.INFORMATION_MESSAGE);
    }
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
