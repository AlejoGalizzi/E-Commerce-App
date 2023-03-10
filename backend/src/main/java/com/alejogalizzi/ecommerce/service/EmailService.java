package com.alejogalizzi.ecommerce.service;

import com.alejogalizzi.ecommerce.service.abstraction.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import javax.swing.text.html.HTML;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

  @Autowired
  private final JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String emailFrom;

  @Override
  public void sendWelcomeMessage(String emailTo, String user)
      throws IOException, MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
    helper.setTo(emailTo);
    helper.setSubject(String.format("Welcome %s to our e-commerce", user));
    helper.setFrom(emailFrom);
    InputStream inputStream = new ClassPathResource("static/WelcomeMessage.html").getInputStream();
    String htmlBody = new String(StreamUtils.copyToByteArray(inputStream), StandardCharsets.UTF_8);
    helper.setText(htmlBody, true);
    mailSender.send(message);
  }
}
