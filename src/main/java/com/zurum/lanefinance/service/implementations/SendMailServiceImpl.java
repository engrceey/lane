package com.zurum.lanefinance.service.implementations;

import com.zurum.lanefinance.dtos.request.EmailDto;
import com.zurum.lanefinance.exceptions.CustomException;
import com.zurum.lanefinance.service.SendMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;
import java.util.concurrent.Future;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {
    private final JavaMailSender mailSender;

    @Async
    public Future<String> sendEmail(EmailDto emailDto) {
        log.info("inside Send email, building mail!!");
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(emailDto.getSender());
            mimeMessageHelper.setTo(emailDto.getRecipient());
            mimeMessageHelper.setSubject(emailDto.getSubject());
            mimeMessageHelper.setText(emailDto.getBody());
        };

        try {
            mailSender.send(mimeMessagePreparator);
            log.info("email has sent!!");
            return new AsyncResult<>("email sent");
        }catch (MailException exception) {
            log.error("Exception occurred when sending mail {}",exception.getMessage());
            throw new CustomException("Exception occurred when sending mail to " + emailDto.getRecipient(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Async
    public Future<String> sendEmailWithAttachment(EmailDto emailDto) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("noreply@gmail.com");
        mimeMessageHelper.setTo(emailDto.getRecipient());
        mimeMessageHelper.setSubject(emailDto.getSubject());
        mimeMessageHelper.setText(emailDto.getBody());

        try {
            FileSystemResource fileSystemResource =
                    new FileSystemResource(new File(emailDto.getAttachment()));

            mimeMessageHelper
                    .addAttachment(Objects
                            .requireNonNull(fileSystemResource.getFilename()),fileSystemResource);

            mailSender.send(mimeMessage);
            log.info("email has sent!!");
            return new AsyncResult<>("email sent");
        } catch (MailException exception) {
            log.error("Exception occurred when sending mail {}",exception.getMessage());
            throw new CustomException("Exception occurred when sending mail to " + emailDto.getRecipient(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
