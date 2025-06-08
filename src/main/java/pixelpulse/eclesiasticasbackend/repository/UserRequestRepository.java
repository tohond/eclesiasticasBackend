package pixelpulse.eclesiasticasbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pixelpulse.eclesiasticasbackend.model.UserRequest;

import java.util.Optional;

public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {
    boolean existsByFirebaseUid(String uid);
    Optional<UserRequest> findByFirebaseUid(String uid);
}