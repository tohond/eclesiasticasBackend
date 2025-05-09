package pixelpulse.eclesiasticasbackend.dto.create;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class createMatrimonioDTO{
	
	private int numero_formulario;
	private String numeroActa;
    private String folio;
    private String libro;
    @JsonFormat(pattern="dd-MM-yy")
    private Date fecha;
    private String notas;
    private String tipo;
    private String idSacerdote;
	private String nombresSacerdote;
	private String idDoyFe;
	private String nombresDoyFe;
	
	private String esposonombre1;
	private String esposonombre2;
	private String esposonombre3;
	private String esposonombre4;
	
	@JsonFormat(pattern="dd-MM-yy")
    private Date fechaNacimientoEsposo;
	
	private String esposanombre1;
	private String esposanombre2;
	private String esposanombre3;
	private String esposanombre4;
	
	@JsonFormat(pattern="dd-MM-yy")
    private Date fechaNacimientoEsposa;

    private String nombreCiudad;
    
    private String lugarNacimiento;
    
    
    private String ciudadNacimiento;

    // --- Datos Anexos ---
    private String nombresPadre;
    private String nombresMadre;
    private String abueloPaterno;
    private String abuelaPaterna;
    private String abueloMaterno;
    private String abuelaMaterna;
    
    
    private String nombrepadrinos;
    private String nombremadrinas;
    
    private String notaMarginal;

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
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

	public String getEsposonombre1() {
		return esposonombre1;
	}

	public void setEsposonombre1(String esposonombre1) {
		this.esposonombre1 = esposonombre1;
	}

	public String getEsposonombre2() {
		return esposonombre2;
	}

	public void setEsposonombre2(String esposonombre2) {
		this.esposonombre2 = esposonombre2;
	}

	public String getEsposonombre3() {
		return esposonombre3;
	}

	public void setEsposonombre3(String esposonombre3) {
		this.esposonombre3 = esposonombre3;
	}

	public String getEsposonombre4() {
		return esposonombre4;
	}

	public void setEsposonombre4(String esposonombre4) {
		this.esposonombre4 = esposonombre4;
	}

	public Date getFechaNacimientoEsposo() {
		return fechaNacimientoEsposo;
	}

	public void setFechaNacimientoEsposo(Date fechaNacimientoEsposo) {
		this.fechaNacimientoEsposo = fechaNacimientoEsposo;
	}

	public String getEsposanombre1() {
		return esposanombre1;
	}

	public void setEsposanombre1(String esposanombre1) {
		this.esposanombre1 = esposanombre1;
	}

	public String getEsposanombre2() {
		return esposanombre2;
	}

	public void setEsposanombre2(String esposanombre2) {
		this.esposanombre2 = esposanombre2;
	}

	public String getEsposanombre3() {
		return esposanombre3;
	}

	public void setEsposanombre3(String esposanombre3) {
		this.esposanombre3 = esposanombre3;
	}

	public String getEsposanombre4() {
		return esposanombre4;
	}

	public void setEsposanombre4(String esposanombre4) {
		this.esposanombre4 = esposanombre4;
	}

	public Date getFechaNacimientoEsposa() {
		return fechaNacimientoEsposa;
	}

	public void setFechaNacimientoEsposa(Date fechaNacimientoEsposa) {
		this.fechaNacimientoEsposa = fechaNacimientoEsposa;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
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

	public String getAbueloPaterno() {
		return abueloPaterno;
	}

	public void setAbueloPaterno(String abueloPaterno) {
		this.abueloPaterno = abueloPaterno;
	}

	public String getAbuelaPaterna() {
		return abuelaPaterna;
	}

	public void setAbuelaPaterna(String abuelaPaterna) {
		this.abuelaPaterna = abuelaPaterna;
	}

	public String getAbueloMaterno() {
		return abueloMaterno;
	}

	public void setAbueloMaterno(String abueloMaterno) {
		this.abueloMaterno = abueloMaterno;
	}

	public String getAbuelaMaterna() {
		return abuelaMaterna;
	}

	public void setAbuelaMaterna(String abuelaMaterna) {
		this.abuelaMaterna = abuelaMaterna;
	}

	public String getNombrepadrinos() {
		return nombrepadrinos;
	}

	public void setNombrepadrinos(String nombrepadrinos) {
		this.nombrepadrinos = nombrepadrinos;
	}

	public String getNombremadrinas() {
		return nombremadrinas;
	}

	public void setNombremadrinas(String nombremadrinas) {
		this.nombremadrinas = nombremadrinas;
	}

	public String getNotaMarginal() {
		return notaMarginal;
	}

	public void setNotaMarginal(String notaMarginal) {
		this.notaMarginal = notaMarginal;
	}

	public createMatrimonioDTO(int numero_formulario, String numeroActa, String folio, String libro, Date fecha,
			String notas, String tipo, String idSacerdote, String nombresSacerdote, String idDoyFe, String nombresDoyFe,
			String esposonombre1, String esposonombre2, String esposonombre3, String esposonombre4,
			Date fechaNacimientoEsposo, String esposanombre1, String esposanombre2, String esposanombre3,
			String esposanombre4, Date fechaNacimientoEsposa, String nombreCiudad, String lugarNacimiento,
			String ciudadNacimiento, String nombresPadre, String nombresMadre, String abueloPaterno,
			String abuelaPaterna, String abueloMaterno, String abuelaMaterna, String nombrepadrinos,
			String nombremadrinas, String notaMarginal) {
		super();
		this.numero_formulario = numero_formulario;
		this.numeroActa = numeroActa;
		this.folio = folio;
		this.libro = libro;
		this.fecha = fecha;
		this.notas = notas;
		this.tipo = tipo;
		this.idSacerdote = idSacerdote;
		this.nombresSacerdote = nombresSacerdote;
		this.idDoyFe = idDoyFe;
		this.nombresDoyFe = nombresDoyFe;
		this.esposonombre1 = esposonombre1;
		this.esposonombre2 = esposonombre2;
		this.esposonombre3 = esposonombre3;
		this.esposonombre4 = esposonombre4;
		this.fechaNacimientoEsposo = fechaNacimientoEsposo;
		this.esposanombre1 = esposanombre1;
		this.esposanombre2 = esposanombre2;
		this.esposanombre3 = esposanombre3;
		this.esposanombre4 = esposanombre4;
		this.fechaNacimientoEsposa = fechaNacimientoEsposa;
		this.nombreCiudad = nombreCiudad;
		this.lugarNacimiento = lugarNacimiento;
		this.ciudadNacimiento = ciudadNacimiento;
		this.nombresPadre = nombresPadre;
		this.nombresMadre = nombresMadre;
		this.abueloPaterno = abueloPaterno;
		this.abuelaPaterna = abuelaPaterna;
		this.abueloMaterno = abueloMaterno;
		this.abuelaMaterna = abuelaMaterna;
		this.nombrepadrinos = nombrepadrinos;
		this.nombremadrinas = nombremadrinas;
		this.notaMarginal = notaMarginal;
	}
    
	
}
