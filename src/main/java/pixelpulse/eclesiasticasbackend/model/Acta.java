package pixelpulse.eclesiasticasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "acta")
public class Acta {
	
    

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numeroacta", length = 10)
    private String numeroActa;

    @Column(name = "folio", length = 10)
    private String folio;

    @Column(name = "libro", length = 10)
    private String libro;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "notamarginal", length = 200)
    private String notas;
    
    @Column (name = "tipo", length = 20)
    private String tipo;
    
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "acta")
    private Matrimonio matrimonio;
    
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "acta")
    private Confirmacion confirmacion;
    
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "acta")
    private Bautizo bautizo;
    
    
    
    public Acta() {
    	
    }
    public Acta(Long  id, String numeroActa, String folio, String libro, LocalDate fecha, String notas, String tipo) {
		super();
		this.id = id;
		this.numeroActa = numeroActa;
		this.folio = folio;
		this.libro = libro;
		this.fecha = fecha;
		this.notas = notas;
		this.tipo = tipo;
	}
    
    

	public String getTipo() {
		return tipo;
	}
    
    public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
    
    public Long  getId() {
		return id;
	}

	public void setId(Long id) {
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

	
	
}