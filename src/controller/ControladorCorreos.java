package controller;

import java.io.FileInputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import view.ContenidoHTML;

public class ControladorCorreos {
	private String host;
	private String puerto;
	private String usuario;
	private String auth;
	private String contrasena;

	public ControladorCorreos() {
		datosConexion();
	}

	public void datosConexion() {
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("data/configuracion.ini"));
			host = propiedades.getProperty("host");
			puerto = propiedades.getProperty("puerto");
			usuario = propiedades.getProperty("usuario");
			auth = propiedades.getProperty("auth");
			contrasena = propiedades.getProperty("contrasena");
			
		} catch (Exception e) {
			System.out.println("Error al intentar conectarse: " + e.getMessage());
		}

	}

	public MimeMultipart construccionMensaje() {
		MimeMultipart multiParte = new MimeMultipart();
		try {
			BodyPart texto = new MimeBodyPart();
			texto.setContent(new ContenidoHTML().contenido(), "text/html");

			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource("./RSK000204.pdf")));
			adjunto.setFileName("RSK000204.pdf");

			multiParte.addBodyPart(texto);
			multiParte.addBodyPart(adjunto);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return multiParte;
	}

	public Session conexion() {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", puerto);
		props.setProperty("mail.smtp.user", usuario);
		props.setProperty("mail.smtp.auth", auth);

		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		return session;
	}

	public void enviarCorreos() {
		try {
			MimeMessage message = new MimeMessage(conexion());
			message.setFrom(new InternetAddress(usuario));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("davidsp302@gmail.com"));
			message.setSubject("Asunto del mensaje");
			message.setContent(construccionMensaje());

			Transport t = conexion().getTransport("smtp");
			t.connect(usuario, contrasena);
			t.sendMessage(message, message.getAllRecipients());
			t.close();

		} catch (MessagingException e) {
			e.getMessage();
		}

	}

}
