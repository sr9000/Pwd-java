import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Form1 {

  private JPanel panelMain;
  private JPanel cardAlphabet;
  private JPanel Card2;
  private JButton nextButton;
  private JButton prevButton;
  private JSpinner spinner1;
  private JButton nextButton2;
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
  private JButton backButton1;
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

  public Form1() {

    nextButton2.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
        });
    nextButton.addActionListener(
        e -> {
          if (panelMain.getLayout() instanceof CardLayout) {
            ((CardLayout) panelMain.getLayout()).next(panelMain);
          }
        });

    prevButton.addActionListener(
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
