package pixelpulse.eclesiasticasbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmacionDTO {
    private Long id;
    private Long idActa;
    private Long idParroquia;
    private Long idConfirmante;
    private Long idMonsr;
    private Long idSacerdote;
    private Long idDoyfe;
    private Long idPadrino;
    private Long idMadrina;
}
