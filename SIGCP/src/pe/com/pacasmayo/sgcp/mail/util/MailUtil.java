package pe.com.pacasmayo.sgcp.mail.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import pe.com.pacasmayo.sgcp.mail.bean.MailBean;
import pe.com.pacasmayo.sgcp.mail.bean.MailConfig;

public class MailUtil {

	public MailUtil() {
	}

	private static Session getSession(MailConfig mailCfg) throws Exception {
		// flog.info("Intentando obtener Session .");
		Properties prop = System.getProperties();
		prop.put(mailCfg.getMailTransportProtocol(), mailCfg.getTransportProtocol());
		prop.put(mailCfg.getMailTransportHost(), mailCfg.getTransportHost());
		prop.put(mailCfg.getMailTransportAuth(), mailCfg.getAuthorization());
		prop.put(mailCfg.getMailConnectionTimeout(), mailCfg.getConnectionTimeout());
		prop.put(mailCfg.getMailTimeout(), mailCfg.getTimeout());
		// if (flog.isDebugEnabled()) {
		// flog.debug(mailCfg.getMailTransportProtocol() + " : " +
		// mailCfg.getTransportProtocol());
		// flog.debug(mailCfg.getMailTransportHost() + " : " +
		// mailCfg.getTransportHost());
		// flog.debug(mailCfg.getMailTransportAuth() + " : " +
		// mailCfg.getAuthorization());
		// flog.debug("Content Type : " + mailCfg.getContentType());
		// }
		final String usr = mailCfg.getUser();
		final String pwd = mailCfg.getPswd();
		pa = new Authenticator() {

			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usr, pwd);
			}

		};
		Session session = Session.getInstance(prop, pa);
		if (session == null) {
			throw new Exception("No se pudo obtener una sesion para el env\355o de mail con los datos proporcionados.");
		} else {
			// flog.info("Session obtenida.");
			return session;
		}
	}

	public static void send(MailBean mailBean, MailConfig config, byte[] bytearray, Map mapa) throws Exception {
		session = getSession(config);
		message = new MimeMessage(session);
		fromAddress = new InternetAddress(mailBean.getFrom());
		toAddresses = InternetAddress.parse(mailBean.getTo());
		ccAddresses = InternetAddress.parse(mailBean.getToCC());
		bccAddresses = InternetAddress.parse(mailBean.getToBCC());
		message.setFrom(fromAddress);
		message.setRecipients(javax.mail.Message.RecipientType.TO, toAddresses);
		message.setRecipients(javax.mail.Message.RecipientType.CC, ccAddresses);
		message.setRecipients(javax.mail.Message.RecipientType.BCC, bccAddresses);
		message.setSubject(mailBean.getSubject());
		// message.setText(mailBean.getText(), "ISO-8859-1");

		// String tempString = new String(mailBean.getText().getBytes(),
		// "ISO-8859-1");

		// flog.info("Procediendo al env\355o del mail...");
		MimeMultipart multiParte = new MimeMultipart();

		body = new MimeBodyPart();

		InputStream is = null;
		BufferedReader bfReader = null;
		String mensaje = "";
		try {
			is = new ByteArrayInputStream(bytearray);
			bfReader = new BufferedReader(new InputStreamReader(is));
			String temp = null;
			while ((temp = bfReader.readLine()) != null) {
				mensaje += temp;
				System.out.println(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {

			}
		}
		String tempString = new String(bytearray, "ISO-8859-1");
		body.setContent(new String(tempString.getBytes("ISO-8859-1")), agregaCharset("text/html; charset=UTF-8", "ISO-8859-1"));

		for (Iterator it = mapa.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Entry) it.next();
			String imageName = (String) entry.getKey();
			byte[] imageData = (byte[]) entry.getValue();
			// attach imageData using imageName as Content-ID
			System.out.println("-->" + imageName);

			MimeBodyPart imagePart = new MimeBodyPart();
			imagePart.setHeader("Content-ID", "<" + imageName + ">");
			// imagePart.setDisposition(MimeBodyPart.INLINE);

			DataSource ds;
			ds = new ByteArrayDataSource(imageData, "image/*");

			imagePart.setDataHandler(new DataHandler(ds));
			// // String imageFilePath = mapInlineImages.get(contentId);
			// try {
			// FileOutputStream fileOuputStream = null;
			// try {
			// fileOuputStream = new FileOutputStream("filename");
			// fileOuputStream.write(imageData);
			// } finally {
			// fileOuputStream.close();
			// }
			// imagePart.setContent(imageData);
			// imagePart.attachFile(file);
			//
			// } catch (IOException ex) {
			// ex.printStackTrace();
			// }

			multiParte.addBodyPart(imagePart);
		}

		// body.setText(mensaje);

		BodyPart texto = new MimeBodyPart();
		texto.setText("ORUENBAASAssS");

		multiParte.addBodyPart(texto);
		multiParte.addBodyPart(body);
		// message.setContent(new String(tempString.getBytes("ISO-8859-1")),
		// agregaCharset(config.getContentType(), "ISO-8859-1"));
		message.setContent(multiParte);
		Transport.send(message);
		// flog.info("Mail enviado.");
	}

	public static InternetAddress[] obtenerListaemail(String cadenacorreo) throws AddressException {
		String recipienta = cadenacorreo;
		String[] recipientList = recipienta.split(";");
		InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
		int counter = 0;
		for (String recipient : recipientList) {
			recipientAddress[counter] = new InternetAddress(recipient.trim());
			counter++;
		}

		return recipientAddress;
	}

	public static void send(MailBean mailBean, MailConfig config, DataSource archivo, String nombreArchivo) throws Exception {
		session = getSession(config);
		message = new MimeMessage(session);
		fromAddress = new InternetAddress(mailBean.getFrom());
		toAddresses = null;
		bccAddresses = null;
		ccAddresses = null;

		if (mailBean != null && mailBean.getTo() != null) {

			// toAddresses = InternetAddress.parse(mailBean.getTo());
			toAddresses = obtenerListaemail(mailBean.getTo());
		}
		if (mailBean != null && mailBean.getToCC() != null) {
			ccAddresses = InternetAddress.parse(mailBean.getToCC());
		}
		if (mailBean != null && mailBean.getToBCC() != null) {
			bccAddresses = InternetAddress.parse(mailBean.getToBCC());
		}

		message.setFrom(fromAddress);

		if (toAddresses != null) {
			message.setRecipients(javax.mail.Message.RecipientType.TO, toAddresses);
		}
		if (ccAddresses != null) {
			message.setRecipients(javax.mail.Message.RecipientType.CC, ccAddresses);
		}
		if (bccAddresses != null) {
			message.setRecipients(javax.mail.Message.RecipientType.BCC, bccAddresses);
		}

		message.setSubject(mailBean.getSubject());

		MimeMultipart multiParte = new MimeMultipart();

		body = new MimeBodyPart();
		body.setDataHandler(new DataHandler(archivo));
		body.setFileName(nombreArchivo);

		BodyPart texto = new MimeBodyPart();
		texto.setText(mailBean.getText());

		multiParte.addBodyPart(texto);
		multiParte.addBodyPart(body);

		message.setContent(multiParte);
		Transport.send(message);

	}

	public static void send(List mailList, MailConfig config) throws Exception {
		session = getSession(config);
		MailBean mailBean = null;
		int size = mailList.size();
		// flog.info("Procediendo al env\355o de los mails masivos....");
		for (int i = 0; i < size; i++) {
			mailBean = (MailBean) mailList.get(i);
			message = new MimeMessage(session);
			fromAddress = new InternetAddress(mailBean.getFrom());
			toAddresses = InternetAddress.parse(mailBean.getTo());
			ccAddresses = InternetAddress.parse(mailBean.getToCC());
			bccAddresses = InternetAddress.parse(mailBean.getToBCC());
			message.setFrom(fromAddress);
			message.setRecipients(javax.mail.Message.RecipientType.TO, toAddresses);
			message.setRecipients(javax.mail.Message.RecipientType.CC, ccAddresses);
			message.setRecipients(javax.mail.Message.RecipientType.BCC, bccAddresses);
			message.setSubject(mailBean.getSubject());
			message.setText(mailBean.getText(), "ISO-8859-1");
			String tempString = new String(mailBean.getText().getBytes(), "ISO-8859-1");
			message.setContent(new String(tempString.getBytes("ISO-8859-1")),
					agregaCharset(config.getContentType(), "ISO-8859-1"));
			Transport.send(message);
			// flog.info(" ** Mail Nro " + i + " enviado.");
		}

		// flog.info("Fin del proceso de env\355o de mails masivos.");
	}

	public static void send(Session session, MailBean mailBean, String contentType) throws Exception {
		message = new MimeMessage(session);
		fromAddress = new InternetAddress(mailBean.getFrom());
		toAddresses = InternetAddress.parse(mailBean.getTo());
		ccAddresses = InternetAddress.parse(mailBean.getToCC());
		bccAddresses = InternetAddress.parse(mailBean.getToBCC());
		message.setFrom(fromAddress);
		message.setRecipients(javax.mail.Message.RecipientType.TO, toAddresses);
		message.setRecipients(javax.mail.Message.RecipientType.CC, ccAddresses);
		message.setRecipients(javax.mail.Message.RecipientType.BCC, bccAddresses);
		message.setSubject(mailBean.getSubject());
		message.setText(mailBean.getText(), "ISO-8859-1");
		String tempString = new String(mailBean.getText().getBytes(), "ISO-8859-1");
		message.setContent(new String(tempString.getBytes("ISO-8859-1")), agregaCharset(contentType, "ISO-8859-1"));
		Transport.send(message);
	}

	private static String agregaCharset(String contentType, String charset) throws Exception {
		String resultado = "";
		if (contentType != null)
			resultado = contentType + "; charset=\"" + charset + "\"";
		return resultado;
	}

	private static Properties properties = null;
	private static Authenticator pa = null;
	private static Session session = null;
	private static MimeMessage message = null;

	private static Address fromAddress = null;
	private static Address toAddresses[] = null;
	private static Address ccAddresses[] = null;
	private static Address bccAddresses[] = null;
	private static BodyPart body = null;

}
