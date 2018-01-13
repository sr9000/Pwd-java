import com.sun.deploy.util.ArrayUtil;
import configuration.PresetChars;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class Form1 {

  private Integer EditableRow;
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
  private JProgressBar progressBar1;
  private JSpinner pwdLenSpinner;
  private JButton removeButton;
  private JButton saveTableButton;
  private JTable tableCharacterSets;
  private JButton addButton1;
  private JButton editButton1;
  private JButton removeButton1;
  private JTable table2;
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
            ClonePresetChars(new PresetChars());
          }
        });
    editButton.addActionListener(
        e -> {
          if (cardAlphabet.getLayout() instanceof CardLayout) {
            if (!tableCharacterSets.getSelectionModel().isSelectionEmpty()) {
              EditableRow = tableCharacterSets.getSelectedRow();
              ((CardLayout) cardAlphabet.getLayout()).next(cardAlphabet);
              ClonePresetChars(
                  (PresetChars) tableCharacterSets.getModel().getValueAt(EditableRow, 0));
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

    cancelButton.addActionListener(
        e -> {
          if (cardAlphabet.getLayout() instanceof CardLayout) {
            EditableRow = null;
            ((CardLayout) cardAlphabet.getLayout()).previous(cardAlphabet);
          }
        });
    acceptButton.addActionListener(
        e -> {
          if (cardAlphabet.getLayout() instanceof CardLayout) {
            PresetChars presetChars = new PresetChars();

            AssignPresetChars(presetChars);

            if (presetChars.isValid()) {
              if (EditableRow == null) {
                ((DefaultTableModel) (tableCharacterSets.getModel()))
                    .addRow(new Object[] {presetChars, presetChars.minimalOccurences()});
              } else {
                ((DefaultTableModel) (tableCharacterSets.getModel())).removeRow(EditableRow);
                ((DefaultTableModel) (tableCharacterSets.getModel()))
                    .insertRow(
                        EditableRow, new Object[] {presetChars, presetChars.minimalOccurences()});
                EditableRow = null;
              }
              ((CardLayout) cardAlphabet.getLayout()).previous(cardAlphabet);
            } else {
              JOptionPane.showMessageDialog(panelMain, "Character set filled incorrectly!");
            }
          }
        });

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
              if (e.getType() == TableModelEvent.INSERT || e.getType() == TableModelEvent.UPDATE) {
                DefaultTableModel model = (DefaultTableModel) e.getSource();
                int minPassLen = 0;
                for (int i = 0; i < model.getRowCount(); i++) {
                  Object val = model.getValueAt(i, 1);
                  minPassLen += (Integer) val;
                }
                minimalPasswordLength.setText(String.valueOf(minPassLen));
              }
            });
  }

  private void AssignPresetChars(PresetChars presetChars) {
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

  private void ClonePresetChars(PresetChars presetChars) {
    bracesCheckBox.setSelected(presetChars.bracesCheckBox());
    commercialAtCheckBox.setSelected(presetChars.commercialAtCheckBox());
    customSetCheckBox.setSelected(presetChars.customCharactersCheckBox());
    digitsCheckBox.setSelected(presetChars.digitsCheckBox());
    dollarSignCheckBox.setSelected(presetChars.dollarSignCheckBox());
    dotCheckBox.setSelected(presetChars.dotCheckBox());
    lowerCaseLettersCheckBox.setSelected(presetChars.lowerCaseLettersCheckBox());
    specialCheckBox.setSelected(presetChars.specialCheckBox());
    underscoreCheckBox.setSelected(presetChars.underscoreCheckBox());
    upperCaseLettersCheckBox.setSelected(presetChars.upperCaseLettersCheckBox());
    minimalOccurences.setValue(presetChars.minimalOccurences());

    if (presetChars.customCharactersCheckBox()) {
      customSetTextPane.setEditable(true);
      StringBuilder customSetText = new StringBuilder();
      for (String str : presetChars.customCharacters()) {
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

  private static void Setup_tableCharacterSets(Form1 form) {
    DefaultTableModel model = (DefaultTableModel) form.tableCharacterSets.getModel();
    model.addColumn("Character Set Preview");
    model.addColumn("Minimal Occurences");
    form.tableCharacterSets.setDefaultEditor(Object.class, null);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Ultimate Password Generator");

    Form1 myForm = new Form1();
    Setup_tableCharacterSets(myForm);

    frame.setContentPane(myForm.panelMain);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setSize(frame.getWidth(), 410);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
