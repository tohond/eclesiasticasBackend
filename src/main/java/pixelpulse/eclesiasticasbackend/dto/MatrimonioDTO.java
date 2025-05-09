package pixelpulse.eclesiasticasbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatrimonioDTO {
    private UUID id;
    private UUID idActa;
    private UUID idMadrina;
    private UUID idPadrino;
    private UUID personaA;
    private UUID personaB;
	public MatrimonioDTO(UUID id, UUID idActa, UUID idMadrina, UUID idPadrino, UUID personaA, UUID personaB) {
		super();
		this.id = id;
		this.idActa = idActa;
		this.idMadrina = idMadrina;
		this.idPadrino = idPadrino;
		this.personaA = personaA;
		this.personaB = personaB;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getIdActa() {
		return idActa;
	}
	public void setIdActa(UUID idActa) {
		this.idActa = idActa;
	}
	public UUID getIdMadrina() {
		return idMadrina;
	}
	public void setIdMadrina(UUID idMadrina) {
		this.idMadrina = idMadrina;
	}
	public UUID getIdPadrino() {
		return idPadrino;
	}
	public void setIdPadrino(UUID idPadrino) {
		this.idPadrino = idPadrino;
	}
	public UUID getPersonaA() {
		return personaA;
	}
	public void setPersonaA(UUID personaA) {
		this.personaA = personaA;
	}
	public UUID getPersonaB() {
		return personaB;
	}
	public void setPersonaB(UUID personaB) {
		this.personaB = personaB;
	}
}
