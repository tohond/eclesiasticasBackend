package pixelpulse.eclesiasticasbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pixelpulse.eclesiasticasbackend.model.enums.RequestStatus;

import java.time.LocalDateTime;

@Data
@Entity
public class UserRequest {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String firebaseUid;
    private String email;
    private String displayName;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime reviewedAt;
    private String reviewedByAdminUid;

}
