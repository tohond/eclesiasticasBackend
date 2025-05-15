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
    private String nombrespadrino;
    public String getNombrespadrino() {
		return nombrespadrino;
	}

	public void setNombrespadrino(String nombrespadrino) {
		this.nombrespadrino = nombrespadrino;
	}

	public String getNombresmadrina() {
		return nombresmadrina;
	}

	public void setNombresmadrina(String nombresmadrina) {
		this.nombresmadrina = nombresmadrina;
	}

	private String nombresmadrina;
    public String getAbueloPaterno() {
		return abueloPaterno;
	}

	public void setAbueloPaterno(String abueloPaterno) {
		this.abueloPaterno = abueloPaterno;
	}

	public String getAbueloMaterno() {
		return abueloMaterno;
	}

	public void setAbueloMaterno(String abueloMaterno) {
		this.abueloMaterno = abueloMaterno;
	}

	public String getAbuelaPaterna() {
		return abuelaPaterna;
	}

	public void setAbuelaPaterna(String abuelaPaterna) {
		this.abuelaPaterna = abuelaPaterna;
	}

	public String getAbuelaMaterna() {
		return abuelaMaterna;
	}

	public void setAbuelaMaterna(String abuelaMaterna) {
		this.abuelaMaterna = abuelaMaterna;
	}

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
