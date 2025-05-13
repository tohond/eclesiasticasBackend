package pixelpulse.eclesiasticasbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


public class ConfirmacionDTO {
    private Long id;
    private ActaDTO idActa;
    private Long idParroquia;
    private PersonaDTO idConfirmante;
    private SacerdoteDTO idMonsr;
    private SacerdoteDTO idSacerdote;
    private SacerdoteDTO idDoyfe;
    private PersonaDTO idPadrino;
    private PersonaDTO idMadrina;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ActaDTO getIdActa() {
		return idActa;
	}
	public void setIdActa(ActaDTO idActa) {
		this.idActa = idActa;
	}
	public Long getIdParroquia() {
		return idParroquia;
	}
	public void setIdParroquia(Long idParroquia) {
		this.idParroquia = idParroquia;
	}
	public PersonaDTO getIdConfirmante() {
		return idConfirmante;
	}
	public void setIdConfirmante(PersonaDTO idConfirmante) {
		this.idConfirmante = idConfirmante;
	}
	public SacerdoteDTO getIdMonsr() {
		return idMonsr;
	}
	public void setIdMonsr(SacerdoteDTO idMonsr) {
		this.idMonsr = idMonsr;
	}
	public SacerdoteDTO getIdSacerdote() {
		return idSacerdote;
	}
	public void setIdSacerdote(SacerdoteDTO idSacerdote) {
		this.idSacerdote = idSacerdote;
	}
	public SacerdoteDTO getIdDoyfe() {
		return idDoyfe;
	}
	public void setIdDoyfe(SacerdoteDTO idDoyfe) {
		this.idDoyfe = idDoyfe;
	}
	public PersonaDTO getIdPadrino() {
		return idPadrino;
	}
	public void setIdPadrino(PersonaDTO idPadrino) {
		this.idPadrino = idPadrino;
	}
	public PersonaDTO getIdMadrina() {
		return idMadrina;
	}
	public void setIdMadrina(PersonaDTO idMadrina) {
		this.idMadrina = idMadrina;
	}
    
    public ConfirmacionDTO() {
    	
    }
}
