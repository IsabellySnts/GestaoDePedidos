package com.cantinagomes.gestaopedidos.restcontroller;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import com.cantinagomes.gestaopedidos.model.Cliente;


public class JavaMailApp {

	 public static void main(String[] args) {
	}	

	public void sendEmail(Cliente cliente) {
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Yahoo */
		props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols=TLSv1.2", "true");
        props.put("mail.smtp.ssl.trust", "*");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ds.isabelly05@yahoo.com", "yahoostarqueza10");
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ds.isabelly05@yahoo.com")); // Remetente
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("isabellyd429@gmail.com")); // Destinatário(s)
			message.setSubject("Alteração de senha ");// Assunto
			message.setText("Esse é seu código de verificação" + cliente.getCodigo());
			/** Método para enviar a mensagem criada */
			Transport.send(message);

			System.out.println("Feito!!!");
			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}