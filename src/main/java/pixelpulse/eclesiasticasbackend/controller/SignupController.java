package pixelpulse.eclesiasticasbackend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pixelpulse.eclesiasticasbackend.model.UserRequest;
import pixelpulse.eclesiasticasbackend.model.enums.RequestStatus;
import pixelpulse.eclesiasticasbackend.repository.UserRequestRepository;

import java.time.LocalDateTime;
import java.util.Optional;
@RestController
@RequestMapping("/api/signup")
public class SignupController {

    public SignupController(UserRequestRepository repo) {
        this.userRequestRepository = repo;
    }

    @Autowired
    private UserRequestRepository userRequestRepository;

    @PostMapping("/request")
    public ResponseEntity<?> requestSignup(@AuthenticationPrincipal FirebaseToken token) {
        String uid = token.getUid();

        if (userRequestRepository.existsByFirebaseUid(uid)) {
            return ResponseEntity.status(409).body("Solicitud ya enviada");
        }

        UserRequest req = new UserRequest();
        req.setFirebaseUid(uid);
        req.setEmail(token.getEmail());
        req.setDisplayName((String) token.getClaims().get("name"));
        req.setCreatedAt(LocalDateTime.now());
        req.setStatus(RequestStatus.PENDING);

        userRequestRepository.save(req);
        return ResponseEntity.ok("Solicitud enviada");
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approve(@PathVariable Long id,
                                     @AuthenticationPrincipal FirebaseToken adminToken) {
        var request = userRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));

        if (request.getStatus() != RequestStatus.PENDING)
            return ResponseEntity.badRequest().body("Ya procesado");

        FirebaseAuth.getInstance().setCustomUserClaimsAsync(request.getFirebaseUid(),
                java.util.Map.of("role", "USER"));

        request.setStatus(RequestStatus.APPROVED);
        request.setReviewedAt(LocalDateTime.now());
        request.setReviewedByAdminUid(adminToken.getUid());

        userRequestRepository.save(request);
        return ResponseEntity.ok("Aprobado");
    }

    @PostMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> reject(@PathVariable Long id,
                                    @AuthenticationPrincipal FirebaseToken adminToken) {
        var request = userRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));

        request.setStatus(RequestStatus.REJECTED);
        request.setReviewedAt(LocalDateTime.now());
        request.setReviewedByAdminUid(adminToken.getUid());

        userRequestRepository.save(request);
        return ResponseEntity.ok("Rechazado");
    }
}
