package hand_dot.famicon_mini.Service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MailService {

	Properties props = new Properties();

	final String from = props.getProperty("mail.smtp.from");

	final String user = props.getProperty("mail.smtp.user");

	final String host = props.getProperty("mail.smtp.host");

	final String port = props.getProperty("mail.smtp.port");

	final String starttls = props.getProperty("mail.smtp.starttls");
	/** Google account mail address*/
	final String username = props.getProperty("mail.smtp.username");
	/** Google App password*/
	final String password = props.getProperty("mail.smtp.password");

	final String charset = "UTF-8";

	final String encoding = "base64";

	/**
	 * mail.propertiesから取得した情報と
	 * 引数でメールを送ります。
	 * @param subject 題名
	 * @param content 本文
	 * @param to 送り先
	 */
	public void send(String subject, String content, String to) {
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.smtp.connectiontimeout", "10000");
		props.put("mail.smtp.timeout", "10000");
		props.put("mail.debug", "true");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			// Set From:
			message.setFrom(new InternetAddress(from, user));
			// Set ReplyTo:
			message.setReplyTo(new Address[] { new InternetAddress(from) });
			// Set To:
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject, charset);
			message.setText(content, charset);
			message.setHeader("Content-Transfer-Encoding", encoding);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

	}

}