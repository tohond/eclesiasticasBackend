package pixelpulse.eclesiasticasbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pixelpulse.eclesiasticasbackend.model.Persona;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
    private String id;
    private String nombre;
    private String telefono;
    private String direccion;
    @JsonProperty("padre")
    private Persona padre;
    @JsonProperty("madre")
    private Persona madre;
}
