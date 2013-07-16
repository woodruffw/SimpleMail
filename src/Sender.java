/* Sender.java          Authors: William Woodruff, Victor Reyes
 * Provides a clean outline for Sender objects capable of GMail, Yahoo!, and Hotmail SMTP
 * Licensed under the MIT License 
 */

//imports da packages
import java.util.*;
import javax.swing.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Sender
{
  //constants
  public static final Properties SMTP_GMAIL = generate_SMTP_GMAIL();
  public static final Properties SMTP_YAHOO = generate_SMTP_YAHOO();
  public static final Properties SMTP_HOTMAIL = generate_SMTP_HOTMAIL();
  
  
  //priv. instances
  private String username, password;
  private String recipient, subject, text;
  
  private Properties props;
  private Session ses;
  private Message mes;
  
  //free constructor, use with getters/setters
  public Sender()
  {
    username = "";
    password = "";
    recipient = "";
    subject = "";
    text = "";
    props = null;
    ses = null;
  }
  
  //arugument constructor, use with params - gmail smtp by default
  public Sender(String uname, String pass, String recip, String subj, String txt)
  {
    username = uname;
    password = pass;
    recipient = recip;
    subject = subj;
    text = txt;
    props = SMTP_GMAIL;
    ses = Session.getInstance(props,
        new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });
    try
    {
      mes = new MimeMessage(ses);
      mes.setFrom(new InternetAddress(username));
      mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
      mes.setSubject(subject);
      mes.setText(txt);
      
      Transport.send(mes);
      
    }
    catch(Exception ex)
    {
      if (ex instanceof AddressException)
      {
        JOptionPane.showMessageDialog(null, "Invalid address.", "Error", JOptionPane.ERROR_MESSAGE);
      }
      else if (ex instanceof SendFailedException)
      {
        JOptionPane.showMessageDialog(null, "Unable to send message. Fix fields.", "Error", JOptionPane.ERROR_MESSAGE);
      }
      else if(ex instanceof AuthenticationFailedException)
      {
        JOptionPane.showMessageDialog(null, "Password or address invalid incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Unknown error.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  
  //setUsername
  //@param sets account username
  public void setUsername(String name)
  {
    username = name;
  }
  
  //setPassword
  //@param sets account password
  public void setPassword(String pass)
  {
    password = pass; 
  }
  
  //setRecipient
  //@param sets message recipient
  public void setRecipient(String recip)
  {
    recipient = recip;
  }
  
  //setSubject
  //@param sets message subject
  public void setSubject(String subj)
  {
    subject = subj;
  }
  
  //setText
  //@param sets message text
  public void setText(String txt)
  {
    text = txt;
  }
  
  //setProperties
  //@param sets properties
  public void setProperties(Properties p)
  {
    props = p;
  }
  
  
  //getUsername
  //@return username
  public String getUsername()
  {
    return username;
  }
  
  //getPassword
  //@return password
  public String getPassword()
  {
    return password; 
  }
  
  //getRecipient
  //@return recipient
  public String getRecipient()
  {
    return recipient;
  }
  
  //getSubject
  //@return subject
  public String getSubject()
  {
    return subject;
  }
  
  //getText
  //@return text
  public String getText()
  {
    return text;
  }
  
  //getProperties
  //@return properties
  public Properties getProperties()
  {
    return props; 
  }
  
  //prep
  //prepares the message for transport via Transport.send()
  private void prep()
  {
    ses = Session.getInstance(props,
                              new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });  
    
    try
    {
      mes = new MimeMessage(ses);
      mes.setFrom(new InternetAddress(username));
      mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
      mes.setSubject(subject);
      mes.setText(text);
    }
    catch (MessagingException e)
    {
      //you fucked up
      e.printStackTrace();
    } 
  }
  
  //sends da message of the gang
  public void mail()
  {
    prep();
    try
    {
      Transport.send(mes); 
    }
    catch (MessagingException e)
    {
      //you fucked up
      e.printStackTrace();
    }
  }
  
  //generate_SMTP_GMAIL
  //generates the properties necessary for SMTP on GMail
  private static Properties generate_SMTP_GMAIL()
  {
    Properties p = new Properties();
    p.put("mail.smtp.auth", "true");
    p.put("mail.smtp.starttls.enable", "true");
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.port", "587");
    
    return p;
  }
  
  //generate_SMTP_YAHOO
  //generates the properties necessary for SMTP on Yahoo!
  private static Properties generate_SMTP_YAHOO()
  {
    Properties p = new Properties();
    p.put("mail.smtp.host", "smtp.mail.yahoo.com");
    p.put("mail.smtp.auth", "true");
    p.put("mail.debug", "false");
    p.put("mail.smtp.port", "587");
    
    return p;
  }
  
  //generate_SMTP_HOTMAIL
  //generates the properties necessary for SMTP on Hotmail
  //UNTESTED
  private static Properties generate_SMTP_HOTMAIL()
  {
    Properties p = new Properties();
    p.put("mail.smtp.host", "smtp.live.com");
    p.put("mail.smtp.starttls.enable", "true");
    p.put("mail.debug", "false");
    p.put("mail.smtp.port", "25");
    
    return p;
  }
  
}