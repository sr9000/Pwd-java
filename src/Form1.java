import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Form1 {

  private JTextField a0TextField;
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
  private JTable table1;
  private JButton addButton1;
  private JButton editButton1;
  private JButton removeButton1;
  private JTable table2;
  private JButton loadPresetButton;
  private JButton savePresetButton;
  private JSpinner spinner1;
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
  private JTextPane textPane1;
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
          }
        });

    cancelButton.addActionListener(
        e -> {
          if (cardAlphabet.getLayout() instanceof CardLayout) {
            ((CardLayout) cardAlphabet.getLayout()).previous(cardAlphabet);
          }
        });
    acceptButton.addActionListener(
        e -> {
          if (cardAlphabet.getLayout() instanceof CardLayout) {
            ((CardLayout) cardAlphabet.getLayout()).previous(cardAlphabet);
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
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Form1");
    frame.setContentPane(new Form1().panelMain);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setSize(frame.getWidth(), 410);
    frame.setVisible(true);
  }
}
