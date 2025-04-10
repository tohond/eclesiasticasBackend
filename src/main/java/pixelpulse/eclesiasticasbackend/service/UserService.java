package pixelpulse.eclesiasticasbackend.service;

import org.springframework.stereotype.Service;

import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.UpdateRequest;

import jakarta.mail.MessagingException;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final EmailServiceImpl emailServiceImpl;

    private final FirebaseAuth firebaseAuth;
    
    // This is a simplified example. In a real application, you would use a database.
    private final Map<String, String> users = new HashMap<>();
    
    public UserService(FirebaseAuth firebaseAuth, EmailServiceImpl emailServiceImpl) {
        // Add some test users (username -> password)
        users.put("user1@example.com", "password1");
        users.put("user2@example.com", "password2");
        this.firebaseAuth = firebaseAuth;
        this.emailServiceImpl = emailServiceImpl;
        
    }
    
    public boolean authenticateUser(String email, String password) {
        String storedPassword = users.get(email);
        return storedPassword != null && storedPassword.equals(password);
    }
    
   


  
        
        public UserRecord createUser(String email, String password, String displayname) throws FirebaseAuthException {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(password).setDisplayName(displayname);
                
            return firebaseAuth.createUser(request);
        }
        
        public void sendPasswordResetEmailLink(String targetEmail, String resetLink) throws FirebaseAuthException, MessagingException {
            ActionCodeSettings actionCodeSettings = ActionCodeSettings.builder()
                .setUrl("https://your-app.com/reset-password")
                .setHandleCodeInApp(true)
                .build();
                
            String displayName=firebaseAuth.getUserByEmail(targetEmail).getDisplayName();
            emailServiceImpl.sendPasswordResetEmailLink(displayName, targetEmail, resetLink);
            
        }
        
        public UserRecord confirmPasswordReset(String email,String oobCode, String newPassword) 
                throws FirebaseAuthException {
        	
        	UpdateRequest request = new UpdateRequest(firebaseAuth.getUserByEmail(email).getUid())
            .setPassword(newPassword);
        	return firebaseAuth.updateUser(request);
        	
        }
        
        public UserRecord getUserByEmail(String email) throws FirebaseAuthException {
        	return firebaseAuth.getUserByEmail(email);
        }
    
    
    // In a real application, you would have methods to register users, etc.
}