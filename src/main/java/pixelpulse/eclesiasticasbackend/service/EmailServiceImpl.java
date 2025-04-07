package pixelpulse.eclesiasticasbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
 
@Service
public class EmailServiceImpl implements EmailService {
	@Value("${spring.mail.username}")
	private String sourceEmail;
	
	
    @Autowired
    private JavaMailSender mailSender;
 
    @Override
    public void sendPasswordResetEmail(String targetEmail, String token) {
        String resetLink = "http://localhost:8080/reset-password?token=" + token;
 
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(targetEmail);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the link below:\n" + resetLink);
        message.setFrom(sourceEmail);
 
        mailSender.send(message);
    }
    
	@Override
	public void sendPasswordResetEmailLink(String targetEmail, String link) {
		// TODO Auto-generated method stub
		 SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(targetEmail);
	        message.setSubject("Solicitud de recuperación de cuenta");
	        message.setText("Para recuperar su cuenta, inserte una nueva contraseña en el siguiente enlace:" + link);
	        message.setFrom(sourceEmail);
	 
	        mailSender.send(message);
	}
}