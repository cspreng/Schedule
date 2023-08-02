package com.teste.Agenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService {
	
	 @Autowired
	    private JavaMailSender javaMailSender;

	    public void sendMailWithAttachment(String toEmail,
	                                       String body,
	                                       String subject,
	                                       String attachment) throws MessagingException {
	        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
	        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
	        mimeMessageHelper.setFrom("carlos.spreg@gmail.com");
	        mimeMessageHelper.setTo(toEmail);
	        mimeMessageHelper.setText(body);
	        mimeMessageHelper.setSubject(subject);

	        FileSystemResource fileSystemResource=
	                new FileSystemResource(new File(attachment));
	        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),
	                fileSystemResource);
	        javaMailSender.send(mimeMessage);
	        System.out.printf("E-mail com anexo enviado com sucesso!");

	    }
	    
	    @Scheduled(cron = "0 00 06 * * *") // Executes at 6:00 AM daily
	    public void scheduleMailSending() throws MessagingException {
	        String toEmail = "teste@gmail.com";
	        String body = "Segue em anexo relatório diario de clientes.";
	        String subject = "Relatório diario";
	        String attachmentPath = "C:/Users/carlo/Desktop/Trampo/agendaApp/Agenda/clientes.xlsx"; // Update this with the actual attachment file path
	        sendMailWithAttachment(toEmail, body, subject, attachmentPath);
	    }
}
