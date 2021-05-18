package com.WorkerProvider.Services;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.WorkerProvider.DAO.HelperDao;
import com.WorkerProvider.Entities.Worker;

@Service
public class EmailService {
	Random random = new Random(100000);

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private HelperDao helperDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// method for sending email
	public boolean sendEmail(String subject, String message, String to) {

		boolean f = false;
		String from = "workersprovider24@gmail.com";
		String host = "smtp.gmail.com";

		// get rhwe systrem property

		Properties properties = System.getProperties();
		System.out.println("prop" + " " + properties);

		// setting the important information to properties object

		// host set

		properties.put("mail.smtp.host", host);
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.port", 465);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// step -1 get the session object

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("workersprovider24@gmail.com", "Worker@24");
			}
		});

		System.out.println(session);

		session.setDebug(true);

		// step -2 compose message[text,multimedia]
		MimeMessage mimeMessage = new MimeMessage(session);
		System.out.println("before try block");
		try {
			// from email id

			mimeMessage.setFrom(from);

			// adding recipient to message
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to msg
			mimeMessage.setSubject(subject);

			// adding text message
			// mimeMessage.setText(message);

			mimeMessage.setContent(message, "text/html");

			// step -3 send the msg using transport class

			Transport.send(mimeMessage);
			System.out.println("send successfuly");
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	// send otp for worker registeration varification

	public boolean sendOTPWorker(HttpSession session) {
		// System.out.println(email);

		// genratin otp of 6 degit
		boolean f = false;

		Worker worker = (Worker) session.getAttribute("workerDataFromForm");

		int otp = random.nextInt(999999);
		System.out.println(otp);

		int length = (int) (Math.log10(otp) + 1);

		System.out.println(length);

		// code for sending otp to email
		String subject = "OTP from Workers Provider";
		String message = "Your " + length + " digits one time password for Email verification is :" + otp + "";
		String to = worker.getEmail();
		boolean flag = this.emailService.sendEmail(subject, message, to);

		if (flag) {

			session.setAttribute("myWorkerOtp", otp);
			// session.setAttribute("email", email);

			f = true;
			return f;
			// return "change_password";
		} else {
			// session.setAttribute("otpSendingError", "OTP not Sent");

			// m.addAttribute("otpSendingError", "OTP not sent..Something went wrong.");
			return f;
		}

	}

	// method for checking email is exist in db or not

	public boolean isEmailAlreadyInDb(String email) {
		boolean f = helperDao.isEmailExist(email);
		
		return f;
		
		

	}

}
