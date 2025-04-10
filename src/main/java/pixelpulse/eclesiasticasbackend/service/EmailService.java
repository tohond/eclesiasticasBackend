package pixelpulse.eclesiasticasbackend.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendPasswordResetEmail(String targetEmail, String token);
    void sendPasswordResetEmailLink(String name,String targetEmail, String link) throws MessagingException;
}