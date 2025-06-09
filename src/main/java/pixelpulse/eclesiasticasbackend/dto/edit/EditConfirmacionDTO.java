package pixelpulse.eclesiasticasbackend.dto.edit;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class EditConfirmacionDTO{
	private long id;
	private int numero_formulario;
	private String numeroActa;
    private String folio;
    private String libro;
    
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fecha;
    
    private String tipo;
    private String idSacerdote;
	private String nombresSacerdote;
	private String idDoyFe;
	private String nombresDoyFe;

	private String nombresmonsr;
	private String idParroquia;
	
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	

    // --- Fecha de Nacimiento ---
	 @JsonDeserialize(using = LocalDateDeserializer.class)
	    @JsonSerialize(using = LocalDateSerializer.class)
	    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaNacimiento;
    
    
    private String lugarNacimiento;
    
    
    private String ciudadNacimiento;

    // --- Datos Anexos ---
    private String nombresPadre;
    private String nombresMadre;
    
    
    
    private String nombrespadrino;
    private String nombresmadrina;
    
    private String notaMarginal;
    
    public EditConfirmacionDTO() {
    	
    }

	public int getNumero_formulario() {
		return numero_formulario;
	}

	public void setNumero_formulario(int numero_formulario) {
		this.numero_formulario = numero_formulario;
	}

	public String getNumeroActa() {
		return numeroActa;
	}

	public void setNumeroActa(String numeroActa) {
		this.numeroActa = numeroActa.toLowerCase();
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo.toLowerCase();
	}

	public String getIdSacerdote() {
		return idSacerdote;
	}

	public void setIdSacerdote(String idSacerdote) {
		this.idSacerdote = idSacerdote;
	}

	public String getNombresSacerdote() {
		return nombresSacerdote;
	}

	public void setNombresSacerdote(String nombresSacerdote) {
		this.nombresSacerdote = nombresSacerdote.toLowerCase();
	}

	public String getIdDoyFe() {
		return idDoyFe;
	}

	public void setIdDoyFe(String idDoyFe) {
		this.idDoyFe = idDoyFe;
	}

	public String getNombresDoyFe() {
		return nombresDoyFe;
	}

	public void setNombresDoyFe(String nombresDoyFe) {
		this.nombresDoyFe = nombresDoyFe.toLowerCase();
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1.toLowerCase();
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2.toLowerCase();
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1.toLowerCase();
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2.toLowerCase();
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getCiudadNacimiento() {
		return ciudadNacimiento;
	}

	public void setCiudadNacimiento(String ciudadNacimiento) {
		this.ciudadNacimiento = ciudadNacimiento;
	}

	public String getNombresPadre() {
		return nombresPadre;
	}

	public void setNombresPadre(String nombresPadre) {
		this.nombresPadre = nombresPadre.toLowerCase();
	}

	public String getNombresMadre() {
		return nombresMadre;
	}

	public void setNombresMadre(String nombresMadre) {
		this.nombresMadre = nombresMadre.toLowerCase();
	}

	

	public String getNombrespadrino() {
		return nombrespadrino;
	}

	public void setNombrespadrino(String nombrespadrino) {
		this.nombrespadrino = nombrespadrino.toLowerCase();
	}

	public String getNombresmadrina() {
		return nombresmadrina;
	}

	public void setNombresmadrina(String nombresmadrina) {
		this.nombresmadrina = nombresmadrina.toLowerCase();
	}

	public String getNotaMarginal() {
		return notaMarginal;
	}

	public void setNotaMarginal(String notaMarginal) {
		this.notaMarginal = notaMarginal;
	}



	public String getNombresmonsr() {
		return nombresmonsr;
	}

	public void setNombresmonsr(String nombresmonsr) {
		this.nombresmonsr = nombresmonsr.toLowerCase();
	}

	public String getIdParroquia() {
		return idParroquia;
	}

	public void setIdParroquia(String idParroquia) {
		this.idParroquia = idParroquia;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
