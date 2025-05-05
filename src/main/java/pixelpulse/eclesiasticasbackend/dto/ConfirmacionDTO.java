package pixelpulse.eclesiasticasbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmacionDTO {
    private UUID id;
    private UUID idActa;
    private UUID idParroquia;
    private UUID idConfirmante;
    private UUID idMonsr;
    private UUID idSacerdote;
    private UUID idDoyfe;
    private UUID idPadrino;
    private UUID idMadrina;
}
