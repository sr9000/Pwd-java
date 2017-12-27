import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
  private JCheckBox lowerCaseABCheckBox;
  private JCheckBox UPPERCASEABCheckBox;
  private JCheckBox digits012CheckBox;
  private JCheckBox commercialAtCheckBox;
  private JCheckBox underscore_CheckBox;
  private JCheckBox dollarSign$CheckBox;
  private JCheckBox bracesCheckBox;
  private JCheckBox dotCheckBox;
  private JCheckBox specialCheckBox;
  private JCheckBox customSetCheckBox;
  private JTextPane textPane1;

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
