package pixelpulse.eclesiasticasbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SacerdotePersonaDTO {
    // Sacerdote fields
    private UUID id;

    // Persona fields
    private String nombre;
    private String telefono;
    private String direccion;
    private UUID idPadre;
    private UUID idMadre;

    // Additional Sacerdote-specific fields can be added here
    private String ordenadoEn;
    private String diocesis;
}
