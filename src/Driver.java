import javax.swing.JFrame;

public class Driver extends JFrame {
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("SimpleMale");
    frame.add(new GUI(frame.getContentPane()));
    frame.pack();
    frame.setResizable(false);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}