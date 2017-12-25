import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTree;

public class Form1 {

  private JPanel panel1;
  private JPanel Card1;
  private JPanel Card2;
  private JButton nextButton;
  private JButton prevButton;
  private JList list1;
  private JButton editButton;
  private JButton addButton;
  private JButton removeButton;
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

  public Form1() {
    nextButton.addActionListener(
        e -> {
          if (panel1.getLayout() instanceof CardLayout) {
            ((CardLayout) panel1.getLayout()).next(panel1);
          }
        });
    prevButton.addActionListener(
        e -> {
          if (panel1.getLayout() instanceof CardLayout) {
            ((CardLayout) panel1.getLayout()).previous(panel1);
          }
        });
    nextButton2.addActionListener(
        e -> {
          if (panel1.getLayout() instanceof CardLayout) {
            ((CardLayout) panel1.getLayout()).next(panel1);
          }
        });
    backButton.addActionListener(
        e -> {
          if (panel1.getLayout() instanceof CardLayout) {
            ((CardLayout) panel1.getLayout()).previous(panel1);
          }
        });
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Form1");
    frame.setContentPane(new Form1().panel1);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
