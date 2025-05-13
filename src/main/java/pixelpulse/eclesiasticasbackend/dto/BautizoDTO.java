package pixelpulse.eclesiasticasbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


public class BautizoDTO {
    private Long id;
    private ActaDTO idActa;
    private PersonaDTO idBautizado;
    private SacerdoteDTO idSacerdote;
    private SacerdoteDTO idDoyfe;
    private Long idLocalidad;
    private String abueloPaterno;
    private String abueloMaterno;
    private String abuelaPaterna;
    private String abuelaMaterna;
	
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

	public PersonaDTO getIdBautizado() {
		return idBautizado;
	}

	public void setIdBautizado(PersonaDTO idBautizado) {
		this.idBautizado = idBautizado;
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

	public Long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(Long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public BautizoDTO() {
    	
    }
}
