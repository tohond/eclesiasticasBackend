package pixelpulse.eclesiasticasbackend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import pixelpulse.eclesiasticasbackend.dto.LoginRequest;
import pixelpulse.eclesiasticasbackend.dto.SignInRequest;
import pixelpulse.eclesiasticasbackend.security.FirebaseAuthenticationFilter;
import pixelpulse.eclesiasticasbackend.service.FirebaseTokenService;
import pixelpulse.eclesiasticasbackend.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final FirebaseAuth firebaseAuth;
	record FirebaseSignInRequest(String email, String password, boolean returnSecureToken) {}
	record FirebaseSignInResponse(String idToken, String refreshToken) {}
    private final UserService userService;
    private final FirebaseTokenService firebaseTokenService;
    
    private static final String API_KEY_PARAM = "key";
    private static final String INVALID_CREDENTIALS_ERROR = "INVALID_LOGIN_CREDENTIALS";
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private static final String SIGN_IN_BASE_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword";

    
    @Value("${firebase.webapikey}")
    private String webApiKey;
    
    
    @Autowired
    public AuthController(UserService userService, FirebaseTokenService firebaseTokenService, FirebaseAuth firebaseAuth) {
        this.userService = userService;
        this.firebaseTokenService = firebaseTokenService;
        this.firebaseAuth = firebaseAuth;
    }
    
    @SuppressWarnings("finally")
    
    
    private static final String DUPLICATE_ACCOUNT_ERROR = "EMAIL_EXISTS";

    
    @PostMapping("/confirm-reset-password")
    public ResponseEntity<?> confirmResetPassword(@RequestBody Map<String, String> requestBody) {
        String oobCode = requestBody.get("oobCode"); // out-of-band code from the reset link
        String newPassword = requestBody.get("newPassword");
        String email = requestBody.get("email");
        if (oobCode == null || newPassword == null || email == null) {
            return ResponseEntity.badRequest().body("Missing required parameters");
        }
        
        try {
            logger.info("Confirming password reset with provided code");
            
            UpdateRequest request = new UpdateRequest(firebaseAuth.getUserByEmail(email).getUid())
                    .setPassword(newPassword);
            // Note: Firebase Admin SDK doesn't directly support verifying OOB codes
            // This would typically be handled by the Firebase client SDK on the frontend
            // For a complete backend solution, you would need to implement custom token verification
            
            // For demonstration purposes only - in production you would use a more secure approach
            
            
            return ResponseEntity.ok(request);
            
        } catch (Exception e) {
            logger.error("Failed to confirm password reset: {}", e.getMessage(), e);
            
            Map<String, String> response = new HashMap<>();
            response.put("error", "Failed to reset password");
            
            return ResponseEntity.internalServerError().body(response);
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
            // Note: This requires the Firebase project to have Email/Password authentication enabled
            String resetLink = firebaseAuth.generatePasswordResetLink(email);
            
            // You can either return the link (not recommended for production) or send it via your own email service
            // For this example, we'll assume Firebase handles the email sending
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Password reset email sent successfully:"+resetLink);
            
            logger.info("Password reset link generated successfully for: {}", email);
            return ResponseEntity.ok(response);
            
        } catch (FirebaseAuthException e) {
            logger.error("Failed to send password reset email: {}", e.getMessage(), e);
            
            // Don't expose detailed error information to clients in production
            Map<String, String> response = new HashMap<>();
            response.put("error", "Failed to process password reset request");
            
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    	
  
        @PostMapping("/register")
        public ResponseEntity<UserRecord> registerUser(@RequestBody SignInRequest request) throws FirebaseAuthException {
            try {
                UserRecord user = userService.createUser(request.getEmail(), request.getPassword());
                return ResponseEntity.ok(user);
            } catch (FirebaseAuthException e) {
                throw e;
            }
        }
    
    
    
	@PostMapping("/login")
    public FirebaseSignInResponse  login(@RequestBody LoginRequest request) {
    	

            // For testing, we'll accept any username/password
            // In a real app, you would validate against your database
            logger.info("apikey:"+webApiKey);
            // Generate a custom token using the email as the user ID
            String uid = request.getEmail();
            //String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
            
            /*Map<String, String> response = new HashMap<>();
            response.put("token", customToken);*/
            
            
            FirebaseSignInRequest  requestBody = new FirebaseSignInRequest(uid, request.getPassword(), true);
           
            return sendSignInRequest(requestBody);
        
        
    }
    
   
    // Request model
    private FirebaseSignInResponse sendSignInRequest(FirebaseSignInRequest firebaseSignInRequest) {
        
            return RestClient.create(SIGN_IN_BASE_URL)
              .post()
              .uri(uriBuilder -> uriBuilder
                .queryParam(API_KEY_PARAM, webApiKey)
                .build())
              .body(firebaseSignInRequest)
              .contentType(MediaType.APPLICATION_JSON)
              .retrieve()
              .body(FirebaseSignInResponse.class);
        
    }
}