package pixelpulse.eclesiasticasbackend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pixelpulse.eclesiasticasbackend.model.UserRequest;
import pixelpulse.eclesiasticasbackend.model.enums.RequestStatus;
import pixelpulse.eclesiasticasbackend.repository.UserRequestRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Autowired
    private FirebaseAuth firebaseAuth;

    @PostMapping("/{id}/approve")
    public ResponseEntity<?> approveUser(@PathVariable Long id, @AuthenticationPrincipal FirebaseToken adminToken) throws FirebaseAuthException {
        UserRequest request = userRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (request.getStatus() != RequestStatus.PENDING) {
            return ResponseEntity.badRequest().body("Already processed.");
        }

        // Set Firebase custom claim
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER");
        firebaseAuth.setCustomUserClaims(request.getFirebaseUid(), claims);

        request.setStatus(RequestStatus.APPROVED);
        request.setReviewedAt(LocalDateTime.now());
        request.setReviewedByAdminUid(adminToken.getUid());

        userRequestRepository.save(request);
        return ResponseEntity.ok("User approved.");
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<?> rejectUser(@PathVariable Long id, @AuthenticationPrincipal FirebaseToken adminToken) {
        UserRequest request = userRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (request.getStatus() != RequestStatus.PENDING) {
            return ResponseEntity.badRequest().body("Already processed.");
        }

        request.setStatus(RequestStatus.REJECTED);
        request.setReviewedAt(LocalDateTime.now());
        request.setReviewedByAdminUid(adminToken.getUid());

        userRequestRepository.save(request);
        return ResponseEntity.ok("User rejected.");
    }
}
