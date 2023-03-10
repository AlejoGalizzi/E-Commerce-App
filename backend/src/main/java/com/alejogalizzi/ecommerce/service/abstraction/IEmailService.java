package com.alejogalizzi.ecommerce.service.abstraction;

import jakarta.mail.MessagingException;
import java.io.IOException;

public interface IEmailService {

  void sendWelcomeMessage(String emailTo, String user) throws IOException, MessagingException;
}
