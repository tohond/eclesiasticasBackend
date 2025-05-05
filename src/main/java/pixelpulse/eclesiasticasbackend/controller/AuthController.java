package pixelpulse.eclesiasticasbackend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;

import jakarta.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import pixelpulse.eclesiasticasbackend.dto.requests.LoginRequestDTO;
import pixelpulse.eclesiasticasbackend.dto.requests.SignInRequestDTO;
import pixelpulse.eclesiasticasbackend.security.FirebaseAuthenticationFilter;
import pixelpulse.eclesiasticasbackend.service.auth.FirebaseTokenService;
import pixelpulse.eclesiasticasbackend.service.others.EmailService;
import pixelpulse.eclesiasticasbackend.service.users.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	

	record FirebaseSignInRequest(String email, String password, boolean returnSecureToken) {
	}

	record FirebaseSignInResponse(String idToken, String refreshToken) {
	}
	record FirebaseRegisterResponse(String code, String message ) {}

	@Autowired
	private final FirebaseAuth firebaseAuth;
	@Autowired
	private final UserService userService;
	@Autowired
	private final FirebaseTokenService firebaseTokenService;

	private static final String API_KEY_PARAM = "key";
	private static final String INVALID_CREDENTIALS_ERROR = "INVALID_LOGIN_CREDENTIALS";
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	private static final String SIGN_IN_BASE_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword";
	private static final String DUPLICATE_ACCOUNT_ERROR = "EMAIL_EXISTS";
	
	@Value("${firebase.webapikey}")
	private String webApiKey;

	@Autowired
	public AuthController(UserService userService, FirebaseTokenService firebaseTokenService,
			FirebaseAuth firebaseAuth, EmailService emailService) {
		this.userService = userService;
		this.firebaseTokenService = firebaseTokenService;
		this.firebaseAuth = firebaseAuth;
	}

	//@SuppressWarnings("finally")

	
	/**
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
			// For a complete backend solution, you would need to implement custom token
			// verification

			// For demonstration purposes only - in production you would use a more secure
			// approach

			return ResponseEntity.ok(request);

		} catch (Exception e) {
			logger.error("Failed to confirm password reset: {}", e.getMessage(), e);

			Map<String, String> response = new HashMap<>();
			response.put("error", "Failed to reset password");

			return ResponseEntity.internalServerError().body(response);
		}
	}**/

	
	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody SignInRequestDTO request) throws FirebaseAuthException {
		try {
			UserRecord user = userService.createUser(request.getEmail(), request.getPassword(), request.getNombre()+" "+request.getApellido());
			return ResponseEntity.ok(user);
		} catch (FirebaseAuthException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) throws FirebaseAuthException {

		// For testing, we'll accept any username/password
		// In a real app, you would validate against your database
		logger.info("apikey:" + webApiKey);
		// Generate a custom token using the email as the user ID
		String uid = request.getEmail();
		// String customToken = FirebaseAuth.getInstance().createCustomToken(uid);

		/*
		 * Map<String, String> response = new HashMap<>(); response.put("token",
		 * customToken);
		 */

		FirebaseSignInRequest requestBody = new FirebaseSignInRequest(uid, request.getPassword(), true);
		UserRecord user = userService.getUserByEmail(uid);
		FirebaseSignInResponse sResponse = sendSignInRequest(requestBody);
		
		Map<String, String> response = new HashMap<>();
		response.put("email", requestBody.email());
		response.put("displayName", user.getDisplayName());
		response.put("idToken",sResponse.idToken());
		response.put("refreshToken", sResponse.refreshToken());

		try{
			return ResponseEntity.ok(response);
		}
		catch (HttpClientErrorException e) {
			
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getResponseBodyAsString());
		}

	}

	// Request model
	private FirebaseSignInResponse sendSignInRequest (FirebaseSignInRequest firebaseSignInRequest) throws HttpClientErrorException {
        	
    	
            
            	
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
