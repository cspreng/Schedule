package com.teste.Agenda.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailConstants {
    @Value("${spring.mail.to}")
    public String RECIPIENT; //destinatário


}

