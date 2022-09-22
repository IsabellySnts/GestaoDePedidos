package com.cantinagomes.gestaopedidos.restcontroller;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.cantinagomes.gestaopedidos.model.Cliente;

@Service
public class JavaMailApp {

	

	public void sendEmail() {
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Yahoo */
		props.put("mail.smtp.host", "smtp.mail.yahoo.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "25");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ds.isabelly05@yahoo.com", "yahoostarqueza10");
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Random random = null;
			Cliente cliente = null;
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ds.isabelly05@yahoo.com")); // Remetente
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cliente.getEmail())); // Destinatário(s)
			message.setSubject("Enviando email com JavaMail");// Assunto
			message.setText("Enviei este email utilizando JavaMail com minha conta Yahoo!" + random);
			/** Método para enviar a mensagem criada */
			Transport.send(message);

			System.out.println("Feito!!!");
			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}