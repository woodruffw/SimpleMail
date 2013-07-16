/*  GUI.java        Author: Victor Reyes, William Woodruff
 *  Provides a simple GUI for utilizing Sender
 *  Licensed under the MIT License
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel {
  
  //Sender obj
  Sender sdr = new Sender();
  
  //Array containing currently supported email-providers
  String[] providerStrings = {"Yahoo!", "Gmail", /*"Hotmail"*/};
  
  //Fields
  protected JTextField use = new JTextField(8);
  protected JPasswordField pas = new JPasswordField(8);
  protected JTextField add = new JTextField(8);
  protected JTextField sub = new JTextField(8);
  protected JTextArea tex = new JTextArea(20, 5);
  protected JComboBox jcb = new JComboBox(providerStrings);
  
  //Labels
  protected JLabel luse = new JLabel("Username");
  protected JLabel lpas = new JLabel("Password");
  protected JLabel ladd = new JLabel("Email Address");
  protected JLabel lsub = new JLabel("Subject");
  protected JLabel ltex = new JLabel("Body");
  
  public GUI(Container pane) {
    Listener list = new Listener();
    JPanel main = new JPanel();
    main.setPreferredSize(new Dimension(600, 420));
    main.setLayout(new GridLayout(2, 1, 0, 5));
    
    JPanel sp = new JPanel();
    sp.setLayout(new GridLayout(5, 2, 1, 1));
    sp.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    
    JPanel cp = new JPanel();
    cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
    cp.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    
    //Fields
    luse.setHorizontalAlignment(SwingConstants.CENTER);
    lpas.setHorizontalAlignment(SwingConstants.CENTER);
    ladd.setHorizontalAlignment(SwingConstants.CENTER);
    lsub.setHorizontalAlignment(SwingConstants.CENTER);
    tex.setLineWrap(true);
    tex.setWrapStyleWord(true);
    
    //Buttons
    JButton send = new JButton("Send");
    send.addActionListener(list);
    
    //Nests everything
    sp.add(jcb);
    sp.add(new Container());
    sp.add(luse);
    sp.add(use);
    sp.add(lpas);
    sp.add(pas);
    sp.add(ladd);
    sp.add(add);
    sp.add(lsub);
    sp.add(sub);
    cp.add(ltex);
    cp.add(tex);
    cp.add(send, BorderLayout.LINE_END);
    main.add(sp);
    main.add(cp);
    add(main);
    
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) 
    {
      System.out.println("Unable to set system L&F. Continuing w/ java defaults.");
    } 
  }
  
  private class Listener implements ActionListener {
    
    public void actionPerformed(ActionEvent e)
    {
      sdr.setUsername(use.getText());
      sdr.setPassword(new String(pas.getPassword()));
      sdr.setRecipient(add.getText());
      sdr.setSubject(sub.getText());
      sdr.setText(tex.getText());
      if (jcb.getSelectedItem().equals("Yahoo!"))
        sdr.setProperties(Sender.SMTP_YAHOO);
      else if (jcb.getSelectedItem().equals("Gmail"))
        sdr.setProperties(Sender.SMTP_GMAIL);
      
      sdr.mail();
    }
  } 
}