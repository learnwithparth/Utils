import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailTLS {

    public static String sendOTPSms(String mailFrom, String pwd, String mailTo, String sub, String msg) {
        final String username = mailFrom;
        final String password = pwd;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));

            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);

            return ("Done");

        } catch (MessagingException e) {
            return ("Error");
        }

    }

    public static void main(String[] args) {
        String pwd = "lugxhijjuzjruxfa";
        String mailFrom = "parthshah.ce@charusat.ac.in";
        String mailTo = "hod.it@charusat.ac.in";
        String sub = "OTP for CHARUSAT Admission Merit No.";
        String msg = "1234 is your One Time Password(OTP) for Merit Access. This OTP is valid till 120 sec - CHARUSAT";

        System.out.println( sendOTPSms(mailFrom, pwd, mailTo, sub, msg));

    }
}
