package com.zurum.lanefinance.service;

import com.zurum.lanefinance.dtos.request.EmailDto;

import javax.mail.MessagingException;
import java.util.concurrent.Future;

public interface SendMailService {
    Future<String> sendEmail(EmailDto emailDto);
    Future<String> sendEmailWithAttachment(EmailDto emailDto) throws MessagingException;
}
