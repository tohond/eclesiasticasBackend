package pixelpulse.eclesiasticasbackend.dto;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class ActaDTO {
    private String id;
    private String numeroActa;
    private String folio;
    private String libro;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern="dd-MM-yy")
    private LocalDate fecha;
    private String notas;
    private String tipo;
    
	
	public ActaDTO(String id, String numeroActa, String folio, String libro, LocalDate fecha, String notas, String tipo) {
		super();
		this.id = id;
		this.numeroActa = numeroActa;
		this.folio = folio;
		this.libro = libro;
		this.fecha = fecha;
		this.notas = notas;
		this.tipo = tipo;
	}
	
	public ActaDTO() {
		
	};
	
	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
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
	



}

