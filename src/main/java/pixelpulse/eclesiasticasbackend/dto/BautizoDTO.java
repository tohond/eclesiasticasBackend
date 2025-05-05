package pixelpulse.eclesiasticasbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BautizoDTO {
    private UUID id;
    private UUID numeroActa;
    private UUID idBautizado;
    private UUID idSacerdote;
    private UUID idDoyfe;
    private UUID idLocalidad;
}
