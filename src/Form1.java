import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Form1 {

  private JPanel panelMain;
  private JPanel cardAlphabet;
  private JPanel cardLength;
  private JButton caNextButton;
  private JButton prevButton;
  private JButton clNextButton;
  private JPanel Card3;
  private JButton fromTextButton;
  private JButton fromDicesButton;
  private JButton fromBinaryButton;
  private JButton backButton;
  private JButton nextButton1;
  private JProgressBar progressBar1;
  private JButton removeButton1;
  private JList list2;
  private JPanel cardViewChars;
  private JButton addButton;
  private JButton editButton;
  private JButton removeButton;
  private JButton caBackButton;
  private JTable table1;
  private JPanel cardAddChars;
  private JPanel cardEditChars;
  private JButton loadTableButton;
  private JButton saveTableButton;
  private JTextField a0TextField;
  private JButton cancelButton;
  private JButton acceptButton;
  private JCheckBox lowerCaseABCheckBox;
  private JCheckBox UPPERCASEABCheckBox;
  private JCheckBox digits012CheckBox;
  private JSpinner spinner2;
  private JCheckBox commercialAtCheckBox;
  private JCheckBox dollarSign$CheckBox;
  private JCheckBox dotCheckBox;
  private JCheckBox underscore_CheckBox;
  private JCheckBox bracesCheckBox;
  private JButton loadPresetButton1;
  private JButton savePresetButton1;
  private JCheckBox specialCheckBox;
  private JEditorPane editorPane1;
  private JCheckBox customSetCheckBox;
  private JButton clBackButton;
  private JSpinner spinner1;

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
    backButton.addActionListener(
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
    frame.setVisible(true);
  }
}
