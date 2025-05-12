package pixelpulse.eclesiasticasbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


public class BautizoDTO {
    private Long id;
    private Long numeroActa;
    private Long idBautizado;
    private Long idSacerdote;
    private Long idDoyfe;
    private Long idLocalidad;
	public BautizoDTO(Long id, Long numeroActa, Long idBautizado, Long idSacerdote, Long idDoyfe, Long idLocalidad) {
		super();
		this.id = id;
		this.numeroActa = numeroActa;
		this.idBautizado = idBautizado;
		this.idSacerdote = idSacerdote;
		this.idDoyfe = idDoyfe;
		this.idLocalidad = idLocalidad;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumeroActa() {
		return numeroActa;
	}
	public void setNumeroActa(Long numeroActa) {
		this.numeroActa = numeroActa;
	}
	public Long getIdBautizado() {
		return idBautizado;
	}
	public void setIdBautizado(Long idBautizado) {
		this.idBautizado = idBautizado;
	}
	public Long getIdSacerdote() {
		return idSacerdote;
	}
	public void setIdSacerdote(Long idSacerdote) {
		this.idSacerdote = idSacerdote;
	}
	public Long getIdDoyfe() {
		return idDoyfe;
	}
	public void setIdDoyfe(Long idDoyfe) {
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
