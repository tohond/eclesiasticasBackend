package pixelpulse.eclesiasticasbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data

public class MatrimonioDTO {
    private Long id;
    private Long idActa;
    private Long idMadrina;
    private Long idPadrino;
    private Long personaA;
    private Long personaB;
	public MatrimonioDTO(Long id, Long idActa, Long idMadrina, Long idPadrino, Long personaA, Long personaB) {
		super();
		this.id = id;
		this.idActa = idActa;
		this.idMadrina = idMadrina;
		this.idPadrino = idPadrino;
		this.personaA = personaA;
		this.personaB = personaB;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdActa() {
		return idActa;
	}
	public void setIdActa(Long idActa) {
		this.idActa = idActa;
	}
	public Long getIdMadrina() {
		return idMadrina;
	}
	public void setIdMadrina(Long idMadrina) {
		this.idMadrina = idMadrina;
	}
	public Long getIdPadrino() {
		return idPadrino;
	}
	public void setIdPadrino(Long idPadrino) {
		this.idPadrino = idPadrino;
	}
	public Long getPersonaA() {
		return personaA;
	}
	public void setPersonaA(Long personaA) {
		this.personaA = personaA;
	}
	public Long getPersonaB() {
		return personaB;
	}
	public void setPersonaB(Long personaB) {
		this.personaB = personaB;
	}
}
