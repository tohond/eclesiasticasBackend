package pixelpulse.eclesiasticasbackend.service;


public interface EmailService {
    void sendPasswordResetEmail(String targetEmail, String token);
    void sendPasswordResetEmailLink(String targetEmail, String link);
}