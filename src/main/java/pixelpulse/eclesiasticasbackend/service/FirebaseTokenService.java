package pixelpulse.eclesiasticasbackend.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class FirebaseTokenService {
    
    /**
     * Creates a custom Firebase token for the given user ID
     * 
     * @param uid The user ID to create a token for
     * @return The custom token
     * @throws FirebaseAuthException If token creation fails
     */
    public String createCustomToken(String uid) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().createCustomToken(uid);
    }
    
    /**
     * Creates a custom Firebase token with additional claims
     * 
     * @param uid The user ID to create a token for
     * @param additionalClaims Additional claims to include in the token
     * @return The custom token
     * @throws FirebaseAuthException If token creation fails
     */
    public String createCustomTokenWithClaims(String uid, Map<String, Object> additionalClaims) 
            throws FirebaseAuthException {
        return FirebaseAuth.getInstance().createCustomToken(uid, additionalClaims);
    }
}