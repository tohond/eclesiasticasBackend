package pixelpulse.eclesiasticasbackend.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pixelpulse.eclesiasticasbackend.dto.LoginRequest;
import pixelpulse.eclesiasticasbackend.dto.LoginResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Service
public class FirebaseAuthService {

	private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    
    @Value("${firebase.privatekey}")
    private String firebaseApiKey;
    
    private static final String FIREBASE_AUTH_URL = 
            "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=";
    
    public FirebaseAuthService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }
    
    public LoginResponse loginWithEmailPassword(LoginRequest loginRequest) {
        try {
            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            // Prepare request body
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("email", loginRequest.getEmail());
            requestBody.put("password", loginRequest.getPassword());
            requestBody.put("returnSecureToken", "true");
            
            // Create HTTP entity with headers and body
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
            
            // Make the request to Firebase Auth REST API
            ResponseEntity<String> response = restTemplate.postForEntity(
                    FIREBASE_AUTH_URL + firebaseApiKey, 
                    entity, 
                    String.class);
            
            // Parse the response
            JsonNode root = objectMapper.readTree(response.getBody());
            
            // Create and return the login response
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setIdToken(root.path("idToken").asText());
            loginResponse.setEmail(root.path("email").asText());
            loginResponse.setRefreshToken(root.path("refreshToken").asText());
            loginResponse.setExpiresIn(root.path("expiresIn").asText());
            loginResponse.setLocalId(root.path("localId").asText());
            loginResponse.setRegistered(root.path("registered").asBoolean());
            
            return loginResponse;
            
        } catch (Exception e) {
            LoginResponse errorResponse = new LoginResponse();
            errorResponse.setError("Authentication failed: " + e.getMessage());
            return errorResponse;
        }
    }
}