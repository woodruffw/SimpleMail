/*  Driver.java  Author: Victor Reyes, William Woodruff
 * Acts as a launchpoint for GUI.java
 * Licensed under the MIT License
 */

import javax.swing.JFrame;

public class Driver extends JFrame {
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("SimpleMail");
    frame.add(new GUI(frame.getContentPane()));
    frame.pack();
    frame.setResizable(false);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}