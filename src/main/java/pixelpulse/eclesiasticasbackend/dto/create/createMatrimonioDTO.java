package pixelpulse.eclesiasticasbackend.dto.create;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class createMatrimonioDTO{
	
	private int numero_formulario;
	private String numeroActa;
    private String folio;
    private String libro;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern="dd-MM-yy")
    private LocalDate fecha;
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
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern="dd-MM-yy")
    private LocalDate fechaNacimientoEsposo;
	
	private String esposanombre1;
	private String esposanombre2;
	private String esposanombre3;
	private String esposanombre4;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
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
    
    
    private String nombrespadrino;
    private String nombresmadrina;
    
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
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

	public LocalDate getFechaNacimientoEsposo() {
		return fechaNacimientoEsposo;
	}

	public void setFechaNacimientoEsposo(LocalDate fechaNacimientoEsposo) {
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
		return nombrespadrino;
	}

	public void setNombrepadrinos(String nombrepadrinos) {
		this.nombrespadrino = nombrepadrinos;
	}

	public String getNombremadrinas() {
		return nombresmadrina;
	}

	public void setNombremadrinas(String nombremadrinas) {
		this.nombresmadrina = nombremadrinas;
	}

	public String getNotaMarginal() {
		return notaMarginal;
	}

	public void setNotaMarginal(String notaMarginal) {
		this.notaMarginal = notaMarginal;
	}

	public createMatrimonioDTO() {
		
	}
    
	
}
