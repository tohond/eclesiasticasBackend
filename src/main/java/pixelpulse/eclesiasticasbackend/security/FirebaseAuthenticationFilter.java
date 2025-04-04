package pixelpulse.eclesiasticasbackend.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Extract the token from the Authorization header
        String token = extractToken(request)
                .orElse(null);
        
        // If no token is found, continue to the next filter (authentication will fail)
        if (token == null) {
            logger.debug("No token found in request");
            filterChain.doFilter(request, response);
            return;
        }
        
        try {
            // Verify the token
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            
            // Create authentication object with user details and authorities
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                decodedToken.getUid(),
                decodedToken,
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            );
            
            // Set the authentication in the context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug("Successfully authenticated user: {}", decodedToken.getUid());
            
        } catch (FirebaseAuthException e) {
            logger.error("Firebase authentication failed: {}", e.getMessage());
            // Don't set any authentication - Spring Security will reject the request
        }
        
        filterChain.doFilter(request, response);
    }
    
    private Optional<String> extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }
}