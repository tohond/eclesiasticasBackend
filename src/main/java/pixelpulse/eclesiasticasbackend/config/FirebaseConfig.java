package pixelpulse.eclesiasticasbackend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
	
	@Value("${FIREBASE_TYPE:service_account}")
    private String type;

    @Value("${FIREBASE_PROJECT_ID}")
    private String projectId;

    @Value("${FIREBASE_PRIVATE_KEY_ID}")
    private String privateKeyId;

    @Value("${FIREBASE_PRIVATE_KEY}")
    private String privateKey;
    
    @Value("${FIREBASE_CLIENT_EMAIL}")
    private String clientEmail;
    
    @Value("${FIREBASE_CLIENT_ID:}")
    private String clientId;
    
    @Value("${FIREBASE_AUTH_URI:https://accounts.google.com/o/oauth2/auth}")
    private String authUri;
    
    @Value("${FIREBASE_TOKEN_URI:https://oauth2.googleapis.com/token}")
    private String tokenUri;
    
    @Value("${FIREBASE_AUTH_PROVIDER_X509_CERT_URL:https://www.googleapis.com/oauth2/v1/certs}")
    private String authProviderX509CertUrl;
    
    @Value("${FIREBASE_CLIENT_X509_CERT_URL:}")
    private String clientX509CertUrl;
    
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
    	
    	String firebaseCredentials = String.format("""
                {
                  "type": "%s",
                  "project_id": "%s",
                  "private_key_id": "%s",
                  "private_key": "%s",
                  "client_email": "%s",
                  "client_id": "%s",
                  "auth_uri": "%s",
                  "token_uri": "%s",
                  "auth_provider_x509_cert_url": "%s",
                  "client_x509_cert_url": "%s"
                }
                """, 
                type,
                projectId, 
                privateKeyId,
                privateKey.replace("\\\\n", "\\n").replace("\\n", "\n"), // Handle double escaping in Docker env vars
                clientEmail,
                clientId,
                authUri,
                tokenUri,
                authProviderX509CertUrl,
                clientX509CertUrl.isEmpty() ? 
                    String.format("https://www.googleapis.com/robot/v1/metadata/x509/%s", 
                                 clientEmail.replace("@", "%40")) : 
                    clientX509CertUrl);
        // Load the service account key JSON file
    	 InputStream credentialsStream = new ByteArrayInputStream(firebaseCredentials.getBytes());
         
         // Create GoogleCredentials from the InputStream
         GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
         
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();
        
        // Initialize the Firebase Admin SDK
        if (FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.initializeApp(options);
        }
        return FirebaseApp.getInstance();
    }
    
    @Bean
    public FirebaseAuth firebaseAuth(FirebaseApp firebaseApp) {
        return FirebaseAuth.getInstance(firebaseApp);
    }
}

