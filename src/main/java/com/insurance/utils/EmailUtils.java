package com.insurance.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail( String subject, String body, String toAddress, File file) 
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
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
