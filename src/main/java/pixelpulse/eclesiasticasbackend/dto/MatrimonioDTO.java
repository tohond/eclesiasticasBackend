package pixelpulse.eclesiasticasbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatrimonioDTO {
    private UUID id;
    private UUID idActa;
    private UUID idMadrina;
    private UUID idPadrino;
    private UUID personaA;
    private UUID personaB;
}
