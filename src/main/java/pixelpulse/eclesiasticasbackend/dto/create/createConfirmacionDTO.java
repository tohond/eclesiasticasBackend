package pixelpulse.eclesiasticasbackend.dto.create;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class createConfirmacionDTO{
	
	private int numero_formulario;
	private String numeroActa;
    private String folio;
    private String libro;
    @JsonFormat(pattern="dd-MM-yy")
    private LocalDate fecha;
    
    private String tipo;
    private String idSacerdote;
	private String nombresSacerdote;
	private String idDoyFe;
	private String nombresDoyFe;
	private String idmonsr;
	private String nombresmonsr;
	private String idParroquia;
	
	private String nombre1;
	private String nombre2;
	private String nombre3;
	private String nombre4;
	

    // --- Fecha de Nacimiento ---
    @JsonFormat(pattern="dd-MM-yy")
    private LocalDate fechaNacimiento;
    
    
    private String lugarNacimiento;
    
    
    private String ciudadNacimiento;

    // --- Datos Anexos ---
    private String nombresPadre;
    private String nombresMadre;
    
    
    
    private String nombrespadrino;
    private String nombresmadrina;
    
    private String notaMarginal;
    
    public createConfirmacionDTO() {
    	
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
		this.numeroActa = numeroActa;
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
		this.tipo = tipo;
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
		this.nombresSacerdote = nombresSacerdote;
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
		this.nombresDoyFe = nombresDoyFe;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getNombre3() {
		return nombre3;
	}

	public void setNombre3(String nombre3) {
		this.nombre3 = nombre3;
	}

	public String getNombre4() {
		return nombre4;
	}

	public void setNombre4(String nombre4) {
		this.nombre4 = nombre4;
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
		this.nombresPadre = nombresPadre;
	}

	public String getNombresMadre() {
		return nombresMadre;
	}

	public void setNombresMadre(String nombresMadre) {
		this.nombresMadre = nombresMadre;
	}

	

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

	public String getNotaMarginal() {
		return notaMarginal;
	}

	public void setNotaMarginal(String notaMarginal) {
		this.notaMarginal = notaMarginal;
	}

	public String getIdmonsr() {
		return idmonsr;
	}

	public void setIdmonsr(String idmonsr) {
		this.idmonsr = idmonsr;
	}

	public String getNombresmonsr() {
		return nombresmonsr;
	}

	public void setNombresmonsr(String nombresmonsr) {
		this.nombresmonsr = nombresmonsr;
	}

	public String getIdParroquia() {
		return idParroquia;
	}

	public void setIdParroquia(String idParroquia) {
		this.idParroquia = idParroquia;
	}
	
	
	
}
