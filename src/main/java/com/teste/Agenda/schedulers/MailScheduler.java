package com.teste.Agenda.schedulers;

import com.teste.Agenda.service.EmailSenderService;
import com.teste.Agenda.service.ExcelService;
import com.teste.Agenda.utils.MailConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class MailScheduler {

    @Autowired
    ExcelService excelService;

    @Autowired
    EmailSenderService emailSenderService;

        @Scheduled(cron = "0 55 05 * * *") // Executes at 5:55 AM daily
//    @Scheduled(cron = "0 * * * * *") // Executa uma vez a cada minuto para testes
    private void scheduler() throws MessagingException {
        excelService.exportDataToExcel();
        emailSenderService.scheduleMailSending();

    }

}
