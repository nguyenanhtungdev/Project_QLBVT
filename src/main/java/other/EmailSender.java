package other;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.List;
import java.util.Properties;

public class EmailSender {
	public static void sendPromotionEmail(String subject, String body, List<String> recipientEmails) {
        final String fromEmail = "luungoccaoson1712@gmail.com";
        final String password = "t v r z f v v j p e h c e j f c";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            for (String email : recipientEmails) {
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(fromEmail, "Promotion Team"));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
                msg.setSubject(subject, "UTF-8");
                msg.setText(body, "UTF-8");
                msg.setSentDate(new java.util.Date());
                Transport.send(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
