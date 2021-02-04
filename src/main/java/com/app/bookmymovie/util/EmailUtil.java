package com.app.bookmymovie.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.app.bookmymovie.pojo.Ticket;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Component
public class EmailUtil {

//	private static JavaMailSender emailSender;
	
	public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}

	public static void sendTicketViaEmail(Ticket ticket) throws IOException, Exception {

		JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
		emailSender.setHost("smtp.gmail.com");
		emailSender.setPort(587);

		emailSender.setUsername("kewalramanitanay48@gmail.com");
		emailSender.setPassword("ib23ATYl1WG48");

		Properties props = emailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		File qrFile = new File("ticket-QR/" + ticket.getId() + ".jpg");
		ImageIO.write(generateQRCodeImage("id:" + ticket.getId() + " seats:" + ticket.getSeats()), "jpg", qrFile);

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(ticket.getUser().getEmail());
		helper.setSubject("Ticket");
		String body = "Tickets for " + ticket.getShow().getMovie() + " at " + ticket.getShow().getTheatre().getName()
				+ " on " + ticket.getShow().getDate() + " at " + ticket.getShow().getTime() + "\n" + "Seat numbesr : "
				+ ticket.getSeats().toString();
		helper.setText(body);
		helper.addAttachment("ticket", qrFile);
		emailSender.send(message);
	}
}
