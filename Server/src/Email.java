import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

class Email {

    public final static String from = "me@me.com";

    // public static void send(String recipient, String subject, String message) {
        
    //  Properties props = new Properties();
    //  props.put("mail.smtp.host", "smtp.myisp.com");
    //  Session session = Session.getDefaultInstance(props, null);
        
        
    //  Message msg = new MimeMessage(session);
    //  try {
    //      msg.setFrom(new InternetAddress(from));
    //      msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    //      msg.setSubject(subject);
    //      msg.setText(message);
        
    //      Transport.send(msg);
    //  } catch (MessagingException e) {
    //      // TODO: Logger.
    //  }
    // }

    public static void send(String[] emails, String subject, String msg, boolean[] notifyAll, boolean priority) throws Exception {
        
        String from = "notify@serverborey-vladislavoff1.rhcloud.com";
        String host = "localhost";
        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        for (int i = 0; i < emails.length; i++) {
            // try {

                if (!priority && !notifyAll[i]) {
                    continue;
                }
                
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));

                message.setSubject(subject);
                message.setText(msg);

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(emails[i]));
                Transport.send(message);

            // } catch (Exception e) {
            //     e.printStackTrace();
            // }
        }
    }
}