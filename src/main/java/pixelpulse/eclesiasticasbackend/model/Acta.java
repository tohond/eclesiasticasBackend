package pixelpulse.eclesiasticasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "acta")
public class Acta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "numeroacta", length = 10)
    private String numeroActa;

    @Column(name = "folio", length = 4)
    private String folio;

    @Column(name = "libro", length = 4)
    private String libro;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "notamarginal", length = 200)
    private String notas;
    
    @Column (name = "tipo", length = 10)
    private String tipo;
    

    public String getTipo() {
		return tipo;
	}
    
    public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
    
    public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	/*
	// --- Relaciones OneToMany ---
    @OneToMany(mappedBy = "acta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Matrimonio> matrimonios;

    @OneToMany(mappedBy = "acta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bautizo> bautizos;

    @OneToMany(mappedBy = "acta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Confirmacion> confirmaciones;*/
}