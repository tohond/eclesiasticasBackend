package pixelpulse.eclesiasticasbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


public class BautizoDTO {
    private UUID id;
    private UUID numeroActa;
    private UUID idBautizado;
    private UUID idSacerdote;
    private UUID idDoyfe;
    private UUID idLocalidad;
	public BautizoDTO(UUID id, UUID numeroActa, UUID idBautizado, UUID idSacerdote, UUID idDoyfe, UUID idLocalidad) {
		super();
		this.id = id;
		this.numeroActa = numeroActa;
		this.idBautizado = idBautizado;
		this.idSacerdote = idSacerdote;
		this.idDoyfe = idDoyfe;
		this.idLocalidad = idLocalidad;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getNumeroActa() {
		return numeroActa;
	}
	public void setNumeroActa(UUID numeroActa) {
		this.numeroActa = numeroActa;
	}
	public UUID getIdBautizado() {
		return idBautizado;
	}
	public void setIdBautizado(UUID idBautizado) {
		this.idBautizado = idBautizado;
	}
	public UUID getIdSacerdote() {
		return idSacerdote;
	}
	public void setIdSacerdote(UUID idSacerdote) {
		this.idSacerdote = idSacerdote;
	}
	public UUID getIdDoyfe() {
		return idDoyfe;
	}
	public void setIdDoyfe(UUID idDoyfe) {
		this.idDoyfe = idDoyfe;
	}
	public UUID getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(UUID idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
    
    
}
