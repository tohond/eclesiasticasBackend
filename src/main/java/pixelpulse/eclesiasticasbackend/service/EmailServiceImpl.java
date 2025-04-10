package pixelpulse.eclesiasticasbackend.service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
 
@Service
public class EmailServiceImpl implements EmailService {
	@Value("${spring.mail.username}")
	private String sourceEmail;
	
	
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private TemplateEngine templateEngine;
 
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
	public void sendPasswordResetEmailLink(String name,String targetEmail, String link) throws MessagingException {
		// TODO Auto-generated method stub
		
		Map<String, Object> variables = Map.of(
	            "name", name,
	            "confirmationLink", link
	        );
	        
	        sendHtmlEmail(targetEmail, "Reestablecer contraseña", "reset-password-email", variables);
	}
	
	
	
	private void sendHtmlEmail(String to, String subject, String templateName, Map<String, Object> variables) throws MessagingException {
        // Prepare the evaluation context
        final Context context = new Context();
        context.setVariables(variables);
        
        // Create the HTML body using Thymeleaf
        final String htmlContent = templateEngine.process(templateName, context);
        
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, 
                                           true, StandardCharsets.UTF_8.name());
        
        message.setSubject(subject);
        message.setFrom(sourceEmail);
        message.setTo(to);
        
        // Add the HTML content
        message.setText(htmlContent, true);
        
        // Add the logo
        message.addInline("logo", new ClassPathResource("/static/images/logo.png"));
        
        // Send message
        mailSender.send(mimeMessage);
    }
}