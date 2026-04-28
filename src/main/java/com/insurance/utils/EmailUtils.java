package com.insurance.utils;

import java.io.File;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.insurance.exception.EmailSendingException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	
	private JavaMailSender mailSender;
	
	 public EmailUtils(JavaMailSender mailSender) {
	        this.mailSender = mailSender;
	    }
	
	public void sendEmail( String subject, String body, String toAddress, File file) 
	{
		try {
		MimeMessage mimeMessage = mailSender.createMimeMessage();		
		MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,true);
		helper.setSubject(subject);
		helper.setText(body, true);
		helper.setTo(toAddress);
		helper.addAttachment("InsrusencePlan", file);
		mailSender.send(mimeMessage);
		
		}
	    catch (MessagingException | MailException e) {
         throw new EmailSendingException("Failed to send email", e);
         
     }
	}

}
