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
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fecha;
    private String notas;
    private String tipo;
    private String idSacerdote;
	private String nombresSacerdote;
	private String idDoyFe;
	private String nombresDoyFe;
	
	private String esposonombre1;
	private String esposonombre2;
	private String esposoapellido1;
	private String esposoapellido2;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaNacimientoEsposo;
	private String lugarNacimientoEsposo;
	
	public String getLugarNacimientoEsposo() {
		return lugarNacimientoEsposo;
	}

	public void setLugarNacimientoEsposo(String lugarNacimientoEsposo) {
		this.lugarNacimientoEsposo = lugarNacimientoEsposo;
	}

	public String getLugarNacimientoEsposa() {
		return lugarNacimientoEsposa;
	}

	public void setLugarNacimientoEsposa(String lugarNacimientoEsposa) {
		this.lugarNacimientoEsposa = lugarNacimientoEsposa;
	}

	private String esposanombre1;
	private String esposanombre2;
	private String esposaapellido1;
	private String esposaapellido2;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaNacimientoEsposa;
	private String lugarNacimientoEsposa;

    
    

    // --- Datos Anexos ---
    private String nombresPadreEsposo;
    private String nombresMadreEsposo;
    private String nombresPadreEsposa;
    private String nombresMadreEsposa;
    
    
    private String nombrestestigo1;
    private String nombrestestigo2;
    private String nombrestestigo3;
    private String nombrestestigo4;
    
    

    
    public String getNombrestestigo1() {
		return nombrestestigo1;
	}

	public void setNombrestestigo1(String nombrestestigo1) {
		this.nombrestestigo1 = nombrestestigo1;
	}

	public String getNombrestestigo2() {
		return nombrestestigo2;
	}

	public void setNombrestestigo2(String nombrestestigo2) {
		this.nombrestestigo2 = nombrestestigo2;
	}

	public String getNombrestestigo3() {
		return nombrestestigo3;
	}

	public void setNombrestestigo3(String nombrestestigo3) {
		this.nombrestestigo3 = nombrestestigo3;
	}

	public String getNombrestestigo4() {
		return nombrestestigo4;
	}

	public void setNombrestestigo4(String nombrestestigo4) {
		this.nombrestestigo4 = nombrestestigo4;
	}

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

	public String getEsposonombre1() {
		return esposonombre1;
	}

	public void setEsposonombre1(String esposonombre1) {
		this.esposonombre1 = esposonombre1.toLowerCase();
	}

	public String getEsposonombre2() {
		return esposonombre2;
	}

	public void setEsposonombre2(String esposonombre2) {
		this.esposonombre2 = esposonombre2.toLowerCase();
	}

	public String getEsposoapellido1() {
		return esposoapellido1;
	}

	public void setEsposoapellido1(String esposoapellido1) {
		this.esposoapellido1 = esposoapellido1.toLowerCase();
	}

	public String getEsposoapellido2() {
		return esposoapellido2;
	}

	public void setEsposoapellido2(String esposoapellido2) {
		this.esposoapellido2 = esposoapellido2.toLowerCase();
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
		this.esposanombre1 = esposanombre1.toLowerCase();
	}

	public String getEsposanombre2() {
		return esposanombre2;
	}

	public void setEsposanombre2(String esposanombre2) {
		this.esposanombre2 = esposanombre2.toLowerCase();
	}

	public String getEsposaapellido1() {
		return esposaapellido1;
	}

	public void setEsposaapellido1(String esposaapellido1) {
		this.esposaapellido1 = esposaapellido1.toLowerCase();
	}

	public String getEsposaapellido2() {
		return esposaapellido2;
	}

	public void setEsposaapellido2(String esposaapellido2) {
		this.esposaapellido2 = esposaapellido2.toLowerCase();
	}

	public LocalDate getFechaNacimientoEsposa() {
		return fechaNacimientoEsposa;
	}

	public void setFechaNacimientoEsposa(LocalDate fechaNacimientoEsposa) {
		this.fechaNacimientoEsposa = fechaNacimientoEsposa;
	}


	
	public String getNombresPadreEsposo() {
		return nombresPadreEsposo;
	}

	public void setNombresPadreEsposo(String nombresPadreEsposo) {
		this.nombresPadreEsposo = nombresPadreEsposo.toLowerCase();
	}

	public String getNombresMadreEsposo() {
		return nombresMadreEsposo;
	}

	public void setNombresMadreEsposo(String nombresMadreEsposo) {
		this.nombresMadreEsposo = nombresMadreEsposo.toLowerCase();
	}

	public String getNombresPadreEsposa() {
		return nombresPadreEsposa;
	}

	public void setNombresPadreEsposa(String nombresPadreEsposa) {
		this.nombresPadreEsposa = nombresPadreEsposa.toLowerCase();
	}

	public String getNombresMadreEsposa() {
		return nombresMadreEsposa;
	}

	public void setNombresMadreEsposa(String nombresMadreEsposa) {
		this.nombresMadreEsposa = nombresMadreEsposa.toLowerCase();
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
