package pixelpulse.eclesiasticasbackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import jakarta.mail.MessagingException;
import pixelpulse.eclesiasticasbackend.controller.AuthController.FirebaseSignInRequest;
import pixelpulse.eclesiasticasbackend.controller.AuthController.FirebaseSignInResponse;
import pixelpulse.eclesiasticasbackend.dto.requests.LoginRequestDTO;
import pixelpulse.eclesiasticasbackend.service.auth.FirebaseTokenService;
import pixelpulse.eclesiasticasbackend.service.others.EmailService;
import pixelpulse.eclesiasticasbackend.service.users.UserService;



@RestController
@RequestMapping("/api/user")
public class UserController {

	
	@Value("${firebase.webapikey}")
	private String webApiKey;

	private final UserService userService;
	private final FirebaseTokenService firebaseTokenService;
	private final EmailService emailService;
	private final FirebaseAuth firebaseAuth; 
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	public UserController(UserService userService, FirebaseTokenService firebaseTokenService,
			FirebaseAuth firebaseAuth, EmailService emailService) {
		this.userService = userService;
		this.firebaseTokenService = firebaseTokenService;
		this.firebaseAuth = firebaseAuth;
		this.emailService= emailService;
	}


	/*

	@PostMapping("/update-profile")
	public ResponseEntity<?> editProfile(@RequestHeader("Authorization") String token,
			@RequestBody Map<String, String> requestBody) {
		try {
			String uid = firebaseAuth.verifyIdToken(token.replace("Bearer ", "")).getUid();
	        String email = requestBody.get("email");
	        String nombre = requestBody.get("nombre");
	        String apellido = requestBody.get("apellido");
		String newPassword = requestBody.get("password");
	        
	        // Corregir línea crítica (setEmail)
	        UserRecord.UpdateRequest updateRequest = new UserRecord.UpdateRequest(uid)
	            .setDisplayName(nombre + " " + apellido)
	            .setEmail(email)
			.setPassword(newPassword); // ← Aquí estaba el error
	        
	        UserRecord user = firebaseAuth.updateUser(updateRequest);
	        
	        Map<String, String> response = new HashMap<>();
	        response.put("email", email);
	        response.put("nombre", nombre);
	        response.put("apellido", apellido);
		response.put("password", newPassword);
	        
	        return ResponseEntity.ok(response);

		} 
		
		catch (FirebaseAuthException e) {
			logger.error("Error actualizando perfil: {}", e.getMessage(), e);
	        Map<String, String> response = new HashMap<>();
	        response.put("error", "Error actualizando perfil");
	        return ResponseEntity.internalServerError().body(response);

		}
	}
	*/

	@PostMapping("/update-profile")
public ResponseEntity<?> editProfile(
        @RequestHeader("Authorization") String token,
        @RequestBody Map<String, String> requestBody) {
    try {
        String uid = firebaseAuth.verifyIdToken(token.replace("Bearer ", "")).getUid();
        String email = requestBody.get("email");
        String nombre = requestBody.get("nombre");
        String apellido = requestBody.get("apellido");
        String newPassword = requestBody.get("password");
        
        // Armar la petición a Firebase para actualizar el usuario
        UserRecord.UpdateRequest updateRequest = new UserRecord.UpdateRequest(uid)
            .setDisplayName(nombre + " " + apellido)
            .setEmail(email);
        
        // Solo seteamos la contraseña si en la petición viene "password"
        if (newPassword != null && !newPassword.isBlank()) {
            updateRequest.setPassword(newPassword);
        }
        
        UserRecord user = firebaseAuth.updateUser(updateRequest);
        
        // Devolvemos en el body la info que se actualizó (sin el password, por seguridad)
        Map<String, String> response = new HashMap<>();
        response.put("email", user.getEmail());
        response.put("nombre", nombre);
        response.put("apellido", apellido);
        // NO agregamos el password en la respuesta de vuelta.
        
        return ResponseEntity.ok(response);

    } catch (FirebaseAuthException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error actualizando perfil: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

	
	
	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> requestBody) {
		String email = requestBody.get("email");
		
		if (email == null || email.trim().isEmpty()) {
			logger.warn("Password reset requested with empty email");
			return ResponseEntity.badRequest().body("Email is required");
		}

		try {
			logger.info("Sending password reset email to: {}", email);

			// Use Firebase Admin SDK to send password reset email
			// Note: This requires the Firebase project to have Email/Password
			// authentication enabled
			String resetLink = firebaseAuth.generatePasswordResetLink(email);
			
			userService.sendPasswordResetEmailLink(email, resetLink);

			Map<String, String> response = new HashMap<>();
			response.put("message", "Password reset email sent successfully");

			logger.info("Password reset link generated successfully for: {}", email);
			return ResponseEntity.ok(response);

		} catch (FirebaseAuthException e) {
			logger.error("Failed to send password reset email: {}", e.getMessage(), e);

			// Don't expose detailed error information to clients in production
			Map<String, String> response = new HashMap<>();
			response.put("error", "Failure in firebase password reset request");

			return ResponseEntity.internalServerError().body(response);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			Map<String, String> response = new HashMap<>();
			response.put("error", "Failed to send password reset email");

			return ResponseEntity.internalServerError().body(response);
		}
	}

}
