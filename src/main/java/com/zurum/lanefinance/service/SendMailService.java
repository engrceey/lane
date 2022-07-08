package com.zurum.lanefinance.service;

import com.zurum.lanefinance.dtos.request.EmailDto;

import javax.mail.MessagingException;

public interface SendMailService {
    void sendEmail(EmailDto emailDto);
    void sendEmailWithAttachment(EmailDto emailDto) throws MessagingException;
}
