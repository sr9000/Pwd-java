package form1;

import configuration.Aes256CbcEncryptedPassword;
import configuration.PresetChars;
import configuration.TableOfPresetChars;
import dataholder.EntropySequence;
import dataholder.EntropySequence.EntropySequenceSource;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.DestroyFailedException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import sequence.radix.converter.ISequenceRadixConverterFabric.SequenceRadixConverter;
import sequence.radix.converter.SequenceRadixConverterFabricInteger;
import special.data.UTF8_ENCODER;
import special.data.UTF8_ENCODER.EncoderOverflowException;
import special.math.Randomness;
import special.security.DestroyableSecretKeySpec;
import special.security.OpenSSLDecryptor;
import special.security.PBKDF2HmacSHA256;

public class Form1 {

  private static final int ITS_OVER_9000 = 9000;
  private static final int MAXIMUM_BINARY_DATA_TO_READ = 100000;
  private static final int MAXIMUM_BINARY_DATA_TO_PREVIEW = 1000;
  public static final int INTERMEDIATE_RADIX_SEQUENCE = 3;
  public static final int PBKDF2Iterations = 1000000;
  public static final int AESKeyLength = 256;
  public static final String PBKDF_2_WITH_HMAC_SHA_256 = "PBKDF2WithHmacSHA256";
  public static final String AES = "AES";
  public static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
  public static final String MD5 = "MD5";
  public static final String SHA256 = "SHA256";
  public static final String EVP_BYTES_TO_KEY = "EVP_BytesToKey";
  public static final String PBKDF2 = "PBKDF2";
  public static final String HMAC_SHA256 = "HmacSHA256";
  private char[] aaa34349500f063 = new char[2];
  private byte[] initializationVector = new byte[2];
  private byte[] salt = new byte[2];
  private int totalaaa34349500f063 = 0;
  private boolean foreverUnsecure = false;
  private boolean successfullEncryption = false;
  private char[] prod = new char[2];
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
  private JPanel cardText;
  private JButton textEntropyCancelButton;
  private JButton textEntropyBackButton;
  private JButton textEntropyAcceptButton;
  private JTextPane textEntropyTextPane;
  private JPanel cardBinary;
  private JButton binaryFileCancelButton;
  private JButton binaryFileBackButton;
  private JButton binaryFileAcceptButton;
  private JButton binaryFileButton;
  private JTextField binaryFileTextField;
  private JRadioButton textBasedEntropyRadioButton;
  private JPanel cardGetPassword;
  private JButton copyToClipboardButton;
  private JButton saveAsFileButton;
  private JButton generateNewPasswordButton;
  private JCheckBox viewPasswordCheckBox;
  private JTextPane previewPassTextPane;
  private JCheckBox encodeWithBase64CheckBox;
  private JCheckBox encryptionAES256CheckBox;
  private JPasswordField passwordField1;
  private JPasswordField passwordField2;
  private JTextArea binaryDataTextPane;
  private JTextArea rollDicesTextPane;
  private JRadioButton MD5KDFPKCS5v1RadioButton;
  private JRadioButton PBKDF2PKCS5v21RadioButton;
  private JLabel aesComment1;
  private JTextArea aesComment7;
  private JLabel aesComment2;
  private JLabel aesComment3;
  private JLabel aesComment4;
  private JRadioButton SHA256KDFPKCS5v1RadioButton;
  private JLabel aesComment6;

  public Form1() {

    caNextButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).next(panelMain);
            int minLen = Integer.max(20, Integer.parseInt(minimalPasswordLength.getText()));
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
              if (tableCharacterSets.getSelectedRows().length > 1) {
                editableRow = null;
                JOptionPane.showMessageDialog(
                    panelMain, "To edit, you should select less than one row!");
                return;
              }
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
                          JOptionPane.PLAIN_MESSAGE,
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
          diceRollEntropyRadioButton.setSelected(true);
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
          entropyEditorBackButton();
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

            acceptOrEditEntropy(entropySequence);
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
          entropyEditorBackButton();
        });
    textEntropyAcceptButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            EntropySequence entropySequence = new EntropySequence();
            entropySequence.setEntropySequenceSource(
                EntropySequenceSource.TEXT, null, null, textEntropyTextPane.getText(), null, null);

            acceptOrEditEntropy(entropySequence);
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
          entropyEditorBackButton();
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

              acceptOrEditEntropy(entropySequence);
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
            a22f0d441128df60();
            ((CardLayout) panelMain.getLayout()).last(panelMain);
          }
        });
    generateNewPasswordButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).first(panelMain);

            viewPasswordCheckBox.setSelected(false);
            encryptionAES256CheckBox.setSelected(false);
            encodeWithBase64CheckBox.setSelected(false);

            setAesVisibility(false);

            previewPassTextPane.setText("");
            passwordField1.setText("");
            passwordField2.setText("");

            clearWithRandom(new SecureRandom(), this.aaa34349500f063);
            clearWithRandom(new SecureRandom(), this.prod);

            this.totalaaa34349500f063 = 0;
            this.foreverUnsecure = false;
            this.successfullEncryption = false;

            passwordProgressBar.setValue(0);
            getMyPasswordButton.setEnabled(false);

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
          if (len > ITS_OVER_9000) {
            pwdLenSpinner.setValue(ITS_OVER_9000);
          }
        });

    rollDicesRadixSpinner.addChangeListener(
        e -> {
          int len = (Integer) (rollDicesRadixSpinner.getValue());
          if (len < 2) {
            rollDicesRadixSpinner.setValue(2);
          }
        });

    binaryFileButton.addActionListener(
        e -> {
          JFileChooser fileChooser = new JFileChooser();
          fileChooser.setDialogTitle("Specify a file to load");

          int userSelection = fileChooser.showOpenDialog(panelMain);

          if (userSelection == JFileChooser.APPROVE_OPTION) {
            binaryFile = fileChooser.getSelectedFile();
            binaryFileTextField.setText(binaryFile.getAbsolutePath());
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
                assignBinaryFilePreviewTextPane();
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
    editButton1.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            if (!tableEntropySequences.getSelectionModel().isSelectionEmpty()) {
              editableRow = tableEntropySequences.getSelectedRow();
              if (tableEntropySequences.getSelectedRows().length > 1) {
                editableRow = null;
                JOptionPane.showMessageDialog(
                    panelMain, "To edit, you should select less than one row!");
                return;
              }

              EntropySequence entropySequence =
                  (EntropySequence) tableEntropySequences.getModel().getValueAt(editableRow, 1);

              if (entropySequence.isValid()) {
                if (!(cardAddEntropy.getLayout() instanceof CardLayout)) {
                  return;
                }

                ((CardLayout) panelMain.getLayout()).next(panelMain);
                ((CardLayout) cardAddEntropy.getLayout()).first(cardAddEntropy);
                ((CardLayout) cardAddEntropy.getLayout()).next(cardAddEntropy);

                if (entropySequence.getEntropySequenceSource()
                    == EntropySequenceSource.ROLL_DICES) {
                  rollDicesRadixSpinner.setValue(entropySequence.getSequenceRadix());
                  rollDicesTextPane.setText(entropySequence.getRollDices());
                  return;
                }
                ((CardLayout) cardAddEntropy.getLayout()).next(cardAddEntropy);
                if (entropySequence.getEntropySequenceSource() == EntropySequenceSource.TEXT) {
                  textEntropyTextPane.setText(entropySequence.getText());
                  return;
                }
                ((CardLayout) cardAddEntropy.getLayout()).next(cardAddEntropy);
                binaryDataTextPane.setText("");
                binaryFileTextField.setText(entropySequence.getBinaryFile().getAbsolutePath());
                binaryFile = entropySequence.getBinaryFile();
                binaryData = entropySequence.getBinaryData();
                assignBinaryFilePreviewTextPane();
              } else {
                JOptionPane.showMessageDialog(panelMain, "Selected value is not valid!");
              }
            } else {
              JOptionPane.showMessageDialog(panelMain, "No rows selected!");
            }
          }
        });

    removeButton1.addActionListener(
        e -> {
          if (!tableEntropySequences.getSelectionModel().isSelectionEmpty()) {
            int row = tableEntropySequences.getSelectedRow();
            int[] rows = tableEntropySequences.getSelectedRows();
            if (rows.length == 1) {
              int answer =
                  JOptionPane.showConfirmDialog(
                      panelMain,
                      "Remove row #"
                          + (row + 1)
                          + "?"
                          + System.lineSeparator()
                          + "Row content: "
                          + tableEntropySequences.getValueAt(row, 1),
                      "Confirm row removing",
                      JOptionPane.YES_NO_OPTION,
                      JOptionPane.QUESTION_MESSAGE);

              if (answer == JOptionPane.YES_OPTION) {
                ((DefaultTableModel) tableEntropySequences.getModel()).removeRow(row);
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
                  ((DefaultTableModel) tableEntropySequences.getModel()).removeRow(r);
                }
              }
            }
          } else {
            JOptionPane.showMessageDialog(panelMain, "No rows selected!");
          }
        });

    tableEntropySequences
        .getModel()
        .addTableModelListener(
            e -> {
              SequenceRadixConverterFabricInteger fab = new SequenceRadixConverterFabricInteger();

              if (tableCharacterSets.getRowCount() == 0
                  || tableEntropySequences.getRowCount() == 0) {
                passwordProgressBar.setValue(passwordProgressBar.getMinimum());
                return;
              }

              List<Integer> ternary = new ArrayList<>();
              assignTernarySequenceFromEntropy(fab, ternary);

              if (ternary.isEmpty()) {
                passwordProgressBar.setValue(passwordProgressBar.getMinimum());
                return;
              }

              int done = 0;
              TableOfPresetChars tableOfPresetChars = new TableOfPresetChars();
              assignTableOfPresetChars(tableOfPresetChars);

              if (tableOfPresetChars.getTableOfPresetChars().isEmpty()) {
                passwordProgressBar.setValue(passwordProgressBar.getMinimum());
                return;
              }

              List<PresetChars> restrictedPresetChars =
                  getRestrictedPresetChars(tableOfPresetChars);
              for (PresetChars presetChars : restrictedPresetChars) {
                if (ternary.isEmpty()) {
                  break;
                }
                int inc =
                    fab.create(
                            INTERMEDIATE_RADIX_SEQUENCE, presetChars.produceCharacterSet().size())
                        .convert(ternary, presetChars.getMinimalOccurences())
                        .size();
                if (inc == 0) {
                  break;
                }
                done += inc;
              }

              int targetLen = (Integer) pwdLenSpinner.getValue();
              if (targetLen > done) {
                done +=
                    fab.create(
                            INTERMEDIATE_RADIX_SEQUENCE,
                            tableOfPresetChars.produceCharacterSet().size())
                        .convert(ternary, targetLen - done)
                        .size();
              }

              int percentDone =
                  Math.max(
                      passwordProgressBar.getMinimum(),
                      Math.min(
                          passwordProgressBar.getMaximum(),
                          passwordProgressBar.getMinimum()
                              + ((Double)
                                      (((double)
                                              (passwordProgressBar.getMaximum()
                                                  - passwordProgressBar.getMinimum()))
                                          * ((double) done)
                                          / ((double) targetLen)))
                                  .intValue()));

              passwordProgressBar.setValue(percentDone);
              getMyPasswordButton.setEnabled(percentDone == passwordProgressBar.getMaximum());
            });

    encryptionAES256CheckBox.addItemListener(
        e -> {
          if (e.getStateChange() == ItemEvent.SELECTED) {
            encodeWithBase64CheckBox.setSelected(true);
            encodeWithBase64CheckBox.setEnabled(false);
            setAesVisibility(true);
          } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            encodeWithBase64CheckBox.setEnabled(true);
            setAesVisibility(false);
          }
          generatePasswordAction();
        });
    viewPasswordCheckBox.addItemListener(e -> generatePasswordAction());
    encodeWithBase64CheckBox.addActionListener(e -> generatePasswordAction());
    MD5KDFPKCS5v1RadioButton.addActionListener(e -> generatePasswordAction());
    PBKDF2PKCS5v21RadioButton.addActionListener(e -> generatePasswordAction());
    passwordField1.addActionListener(e -> generatePasswordAction());
    passwordField2.addActionListener(e -> generatePasswordAction());
    passwordField1.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            super.focusGained(e);
          }

          @Override
          public void focusLost(FocusEvent e) {
            super.focusLost(e);
            generatePasswordAction();
          }
        });
    passwordField2.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            super.focusGained(e);
          }

          @Override
          public void focusLost(FocusEvent e) {
            super.focusLost(e);
            generatePasswordAction();
          }
        });

    copyToClipboardButton.addActionListener(
        e -> {
          String clipBoard = null;
          if (encryptionAES256CheckBox.isSelected()) {
            if (!this.successfullEncryption) {
              JOptionPane.showMessageDialog(
                  panelMain, "Password should be first successfully encrypted!");
              return;
            }
            Aes256CbcEncryptedPassword data = new Aes256CbcEncryptedPassword();
            assignAes256CbcEncryptedPassword(data);

            JAXBContext jaxbContext = null;
            try {
              jaxbContext = JAXBContext.newInstance(Aes256CbcEncryptedPassword.class);
              StringWriter result = new StringWriter();
              jaxbContext.createMarshaller().marshal(data, result);
              clipBoard = result.toString();
            } catch (JAXBException ex) {
              JOptionPane.showMessageDialog(
                  panelMain,
                  ex.toString(),
                  "Exception copy to Clipboard",
                  JOptionPane.ERROR_MESSAGE);
            }
          } else {
            clipBoard = String.valueOf(prod);
          }

          StringSelection selection = new StringSelection(clipBoard);
          Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
          clipboard.setContents(selection, selection);
        });
  }

  private void assignAes256CbcEncryptedPassword(Aes256CbcEncryptedPassword data) {
    data.encryptedData = String.valueOf(prod);
    data.initializationVector =
        String.valueOf(bytes2chars(Base64.getEncoder().encode(this.initializationVector)));
    data.saltForKeyDerivationFunction =
        String.valueOf(bytes2chars(Base64.getEncoder().encode(this.salt)));
    if (MD5KDFPKCS5v1RadioButton.isSelected()) {
      data.keyDerivationFunction = EVP_BYTES_TO_KEY;
      data.messageDigestFunction = MD5;
    } else if (SHA256KDFPKCS5v1RadioButton.isSelected()) {
      data.keyDerivationFunction = EVP_BYTES_TO_KEY;
      data.messageDigestFunction = SHA256;
    } else {
      data.keyDerivationFunction = PBKDF2;
      data.messageDigestFunction = HMAC_SHA256;
    }
  }

  private void generatePasswordAction() {
    this.successfullEncryption = false;
    if (encryptionAES256CheckBox.isSelected()) {
      if (!Arrays.equals(passwordField1.getPassword(), passwordField2.getPassword())) {
        previewPassTextPane.setText("Master password and confirm master password are different.");
        return;
      } else {
        previewPassTextPane.setText("Master password is successfully confirmed.");
      }
    } else {
      previewPassTextPane.setText("");
    }

    this.successfullEncryption = true;

    clearWithRandom(new SecureRandom(), this.prod);
    clearWithRandom(new SecureRandom(), this.salt);

    byte[] arr1 = new byte[2];
    byte[] arr2 = new byte[2];
    byte[] arr3 = new byte[2];
    byte[] arr4 = new byte[2];
    if (encryptionAES256CheckBox.isSelected()) {
      // byte routine
      arr3 = chars2bytes(this.aaa34349500f063.clone(), this.totalaaa34349500f063);
      arr4 = chars2bytes(passwordField1.getPassword().clone(), passwordField1.getPassword().length);
      if (MD5KDFPKCS5v1RadioButton.isSelected()) {
        arr1 = MD_KDFPKCS5v1Routine(arr3.clone(), arr4.clone(), MD5);
      } else if (SHA256KDFPKCS5v1RadioButton.isSelected()) {
        arr1 = MD_KDFPKCS5v1Routine(arr3.clone(), arr4.clone(), SHA256);
      } else {
        arr1 = PBKDF2PKCS5v21Routine(arr3.clone(), arr4.clone());
      }
    } else {
      arr1 = chars2bytes(this.aaa34349500f063.clone(), this.totalaaa34349500f063);
    }
    if (encodeWithBase64CheckBox.isSelected()) {
      arr2 = Base64.getEncoder().encode(arr1);
      prod = bytes2chars(arr2.clone());
    } else {
      prod = new char[totalaaa34349500f063];
      System.arraycopy(this.aaa34349500f063, 0, this.prod, 0, totalaaa34349500f063);
    }
    clearWithRandom(new SecureRandom(), arr1);
    clearWithRandom(new SecureRandom(), arr2);
    clearWithRandom(new SecureRandom(), arr3);
    clearWithRandom(new SecureRandom(), arr4);

    if (viewPasswordCheckBox.isSelected()) {
      if (foreverUnsecure || encryptionAES256CheckBox.isSelected()) {
        previewPassTextPane.setText(new String(prod));
      } else {
        int answer =
            JOptionPane.showConfirmDialog(
                panelMain,
                "If you make the password visible once, "
                    + "it will forever fall into unsecured memory.\n"
                    + "Are you REALLY sure, that you want to show it now?",
                "Confirm make password visible.",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (answer == JOptionPane.YES_OPTION) {
          foreverUnsecure = true;
          viewPasswordCheckBox.setSelected(true);
          previewPassTextPane.setText(new String(prod));
        }
      }
    }
  }

  private byte[] PBKDF2PKCS5v21Routine(byte[] aaa, byte[] masterPassword) {
    byte[] ciphertext = new byte[0];
    DestroyableSecretKeySpec secret = null;
    try {
      this.salt = new SecureRandom().generateSeed(8);

      // SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF_2_WITH_HMAC_SHA_256);
      // KeySpec spec = new PBEKeySpec(masterPassword, this.salt, PBKDF2Iterations, AESKeyLength);

      byte[] keyEncoded =
          PBKDF2HmacSHA256.deriveKey(
              masterPassword.clone(), this.salt, PBKDF2Iterations, AESKeyLength);
      secret = new DestroyableSecretKeySpec(keyEncoded, AES);
      clearWithRandom(new SecureRandom(), keyEncoded);

      Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
      cipher.init(Cipher.ENCRYPT_MODE, secret);

      AlgorithmParameters params = cipher.getParameters();
      this.initializationVector = params.getParameterSpec(IvParameterSpec.class).getIV();

      ciphertext = cipher.doFinal(aaa);

    } catch (NoSuchAlgorithmException
        | NoSuchPaddingException
        | IllegalBlockSizeException
        | InvalidParameterSpecException
        | BadPaddingException
        | InvalidKeyException ex) {

      this.successfullEncryption = false;
      JOptionPane.showMessageDialog(
          panelMain, ex.toString(), "Exception aes encryption (PBKDF2)", JOptionPane.ERROR_MESSAGE);
    } finally {
      if (secret != null && !secret.isDestroyed()) {
        secret.destroy();
      }
    }

    clearWithRandom(new SecureRandom(), aaa);
    clearWithRandom(new SecureRandom(), masterPassword);

    return ciphertext;
  }

  private byte[] MD_KDFPKCS5v1Routine(byte[] aaa, byte[] masterPassword, String nameMD) {
    byte[] cipherText = new byte[0];
    DestroyableSecretKeySpec key = null;
    byte[][] keyAndIV = null;
    try {
      this.salt = new SecureRandom().generateSeed(8);

      MessageDigest md = MessageDigest.getInstance(nameMD);
      keyAndIV = OpenSSLDecryptor.openSSLEVP(this.salt, masterPassword, md);
      key = new DestroyableSecretKeySpec(keyAndIV[OpenSSLDecryptor.INDEX_KEY].clone(), AES);
      IvParameterSpec iv = new IvParameterSpec(keyAndIV[OpenSSLDecryptor.INDEX_IV].clone());

      Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
      cipher.init(Cipher.ENCRYPT_MODE, key, iv);

      AlgorithmParameters params = cipher.getParameters();
      this.initializationVector = params.getParameterSpec(IvParameterSpec.class).getIV();

      cipherText = cipher.doFinal(aaa);

    } catch (NoSuchAlgorithmException
        | NoSuchPaddingException
        | IllegalBlockSizeException
        | InvalidParameterSpecException
        | BadPaddingException
        | InvalidKeyException
        | InvalidAlgorithmParameterException ex) {
      this.successfullEncryption = false;
      JOptionPane.showMessageDialog(
          panelMain,
          ex.toString(),
          "Exception aes encryption (" + nameMD + ")",
          JOptionPane.ERROR_MESSAGE);
    } finally {
      if (key != null && !key.isDestroyed()) {
        key.destroy();
      }
    }

    clearWithRandom(new SecureRandom(), aaa);
    clearWithRandom(new SecureRandom(), masterPassword);
    if (keyAndIV != null) {
      if (keyAndIV[OpenSSLDecryptor.INDEX_KEY] != null) {
        clearWithRandom(new SecureRandom(), keyAndIV[OpenSSLDecryptor.INDEX_KEY]);
      }
      if (keyAndIV[OpenSSLDecryptor.INDEX_IV] != null) {
        clearWithRandom(new SecureRandom(), keyAndIV[OpenSSLDecryptor.INDEX_IV]);
      }
    }

    return cipherText;
  }

  private char[] bytes2chars(byte[] arr) {
    char[] res = new char[arr.length];

    for (int i = 0; i < arr.length; i++) {
      res[i] = (char) arr[i];
    }

    clearWithRandom(new SecureRandom(), arr);

    return res;
  }

  private byte[] chars2bytes(char[] chArr, int meanfullCount) {

    char[] chArrMn = new char[meanfullCount];
    System.arraycopy(chArr, 0, chArrMn, 0, meanfullCount);

    int l = 0;
    int r = 8 * meanfullCount;
    while (l < r) {
      int m = (l + r) / 2;
      byte[] btArr2 = new byte[m];
      try {
        UTF8_ENCODER.encodeArrayLoop(chArrMn, btArr2);
      } catch (EncoderOverflowException ignored) {
        l = m + 1;
        continue;
      } finally {
        clearWithRandom(new SecureRandom(), btArr2);
      }
      r = m;
    }

    byte[] arr = new byte[l];
    try {
      UTF8_ENCODER.encodeArrayLoop(chArrMn, arr);
    } catch (EncoderOverflowException ignored) {
    } finally {
      clearWithRandom(new SecureRandom(), chArr);
      clearWithRandom(new SecureRandom(), chArrMn);
    }

    return arr;
  }

  private void setAesVisibility(boolean visible) {
    // aesComment1.setEnabled(visible);
    aesComment2.setEnabled(visible);
    aesComment3.setEnabled(visible);
    // aesComment4.setEnabled(visible);
    // aesComment5.setEnabled(visible);
    // aesComment6.setEnabled(visible);
    // aesComment7.setEnabled(visible);

    passwordField1.setEnabled(visible);
    passwordField2.setEnabled(visible);

    MD5KDFPKCS5v1RadioButton.setEnabled(visible);
    SHA256KDFPKCS5v1RadioButton.setEnabled(visible);
    PBKDF2PKCS5v21RadioButton.setEnabled(visible);
  }

  private List<PresetChars> getRestrictedPresetChars(TableOfPresetChars tableOfPresetChars) {
    return tableOfPresetChars
        .getTableOfPresetChars()
        .stream()
        .filter(PresetChars::isValid)
        .filter(x -> x.getMinimalOccurences() > 0)
        .collect(Collectors.toList());
  }

  private void assignTernarySequenceFromEntropy(
      SequenceRadixConverterFabricInteger fab, List<Integer> ternary) {
    for (int i = 0; i < tableEntropySequences.getRowCount(); i++) {
      EntropySequence entropySequence = (EntropySequence) tableEntropySequences.getValueAt(i, 1);
      if (entropySequence.isValid()) {
        SequenceRadixConverter<Integer> conv =
            fab.create(entropySequence.getSequenceRadix(), INTERMEDIATE_RADIX_SEQUENCE);
        ternary.addAll(conv.convert(entropySequence.getSequence()));
      }
    }
  }

  private void a22f0d441128df60() {
    byte[] bt = new byte[1024];
    SecureRandom rnd = new SecureRandom();
    for (int i = 0; i < 1000; ++i) {
      rnd.nextBytes(bt);
      rnd = new SecureRandom(bt.clone());
    }

    if (this.aaa34349500f063 != null) {
      clearWithRandom(rnd, this.aaa34349500f063);
    }

    this.aaa34349500f063 = new char[(int) pwdLenSpinner.getValue()];

    SequenceRadixConverterFabricInteger fab = new SequenceRadixConverterFabricInteger();

    List<Integer> ternary = new ArrayList<>();
    assignTernarySequenceFromEntropy(fab, ternary);

    TableOfPresetChars tableOfPresetChars = new TableOfPresetChars();
    assignTableOfPresetChars(tableOfPresetChars);

    List<PresetChars> restrictedPresetChars = getRestrictedPresetChars(tableOfPresetChars);

    int[] indInd2 = new int[] {0, 0};

    for (PresetChars presetChars : restrictedPresetChars) {
      List<String> set = presetChars.produceCharacterSet();
      Collections.shuffle(set, rnd);

      List<Integer> assignations =
          fab.create(INTERMEDIATE_RADIX_SEQUENCE, set.size())
              .convert(ternary, presetChars.getMinimalOccurences());
      if (assignations.size() == 0) {
        break;
      }

      fill1a0b3a1a2a99b7ea(rnd, indInd2, set, assignations);
    }

    int targetLen = (Integer) pwdLenSpinner.getValue();
    if (targetLen > indInd2[0]) {
      List<String> set = tableOfPresetChars.produceCharacterSet();
      Collections.shuffle(set, rnd);

      List<Integer> assignations =
          fab.create(INTERMEDIATE_RADIX_SEQUENCE, set.size())
              .convert(ternary, targetLen - indInd2[0]);

      fill1a0b3a1a2a99b7ea(rnd, indInd2, set, assignations);
    }

    Randomness.shuffleArray(rnd, this.aaa34349500f063);

    this.totalaaa34349500f063 = indInd2[1];
  }

  private void clearWithRandom(SecureRandom rnd, char[] arr) {
    for (int j = 0; j < 1000; j++) {
      for (int i = 0; i < arr.length; i++) {
        arr[i] = (char) rnd.nextInt();
      }
    }
  }

  private void clearWithRandom(SecureRandom rnd, byte[] arr) {
    for (int j = 0; j < 1000; j++) {
      for (int i = 0; i < arr.length; i++) {
        arr[i] = (byte) rnd.nextInt();
      }
    }
  }

  private void fill1a0b3a1a2a99b7ea(
      SecureRandom rnd, int[] indInd2, List<String> set, List<Integer> assignations) {
    for (int choose : assignations) {
      String choosed = set.get(choose);

      while (this.aaa34349500f063.length - indInd2[1] < choosed.length()) {
        char[] arr = new char[this.aaa34349500f063.length * 2 + 1];
        System.arraycopy(this.aaa34349500f063, 0, arr, 0, indInd2[1]);
        clearWithRandom(rnd, this.aaa34349500f063);
        this.aaa34349500f063 = arr;
      }

      for (char ch : choosed.toCharArray()) {
        this.aaa34349500f063[indInd2[1]] = ch;
        indInd2[1]++;
      }
      indInd2[0]++;
    }
  }

  private void entropyEditorBackButton() {
    if (cardAddEntropy.getLayout() instanceof CardLayout) {
      ((CardLayout) cardAddEntropy.getLayout()).first(cardAddEntropy);
      if (editableRow != null) {
        editableRow = null;
        if (panelMain.getLayout() instanceof CardLayout) {
          ((CardLayout) panelMain.getLayout()).previous(panelMain);
        }
      }
    }
  }

  private void acceptOrEditEntropy(EntropySequence entropySequence) {
    if (entropySequence.isValid()) {
      if (editableRow == null) {
        DefaultTableModel model = (DefaultTableModel) tableEntropySequences.getModel();
        model.addRow(
            new Object[] {entropySequence.getEntropySequenceSource().name(), entropySequence});
      } else {
        ((DefaultTableModel) (tableEntropySequences.getModel())).removeRow(editableRow);
        ((DefaultTableModel) (tableEntropySequences.getModel()))
            .insertRow(
                editableRow,
                new Object[] {entropySequence.getEntropySequenceSource().name(), entropySequence});
        editableRow = null;
      }
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

  private void assignBinaryFilePreviewTextPane() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < Math.min(MAXIMUM_BINARY_DATA_TO_PREVIEW, binaryData.length); i++) {
      byte v = binaryData[i];
      str.append(bytePreview(v));
    }
    binaryDataTextPane.setText(str.toString());
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
