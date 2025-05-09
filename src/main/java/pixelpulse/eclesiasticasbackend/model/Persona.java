package pixelpulse.eclesiasticasbackend.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name = "persona")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//property = "id")
//@JsonIdentityReference(alwaysAsId = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Persona {
    
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column (name = "ciudadnacimiento", length = 20)
    private String ciudadnacimiento;
    
    public String getCiudadnacimiento() {
		return ciudadnacimiento;
	}

	public void setCiudadnacimiento(String ciudadnacimiento) {
		this.ciudadnacimiento = ciudadnacimiento;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_padre", referencedColumnName = "id")
    private Persona padre;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_madre", referencedColumnName = "id")
    private Persona madre;
    
    
    public List<Persona> getHijosPadre() {
		return hijosPadre;
	}

	public void setHijosPadre(List<Persona> hijosPadre) {
		this.hijosPadre = hijosPadre;
	}

	public List<Persona> getHijosMadre() {
		return hijosMadre;
	}

	public void setHijosMadre(List<Persona> hijosMadre) {
		this.hijosMadre = hijosMadre;
	}

	public List<Bautizo> getBautizo() {
		return bautizo;
	}

	public void setBautizo(List<Bautizo> bautizo) {
		this.bautizo = bautizo;
	}

	public List<Matrimonio> getMatrimonio() {
		return matrimonio;
	}

	public void setMatrimonio(List<Matrimonio> matrimonio) {
		this.matrimonio = matrimonio;
	}

	public List<Confirmacion> getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(List<Confirmacion> confirmacion) {
		this.confirmacion = confirmacion;
	}
	
	@OneToMany (mappedBy = "padre")
    private List<Persona> hijosPadre;
    
    @OneToMany (mappedBy = "madre")
    
    private List<Persona> hijosMadre;
    
    @OneToMany (mappedBy = "idBautizado",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Bautizo> bautizo;
    
    @OneToMany (mappedBy = "personaA",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Matrimonio> matrimonio;
    
    @OneToMany (mappedBy = "confirmante",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Confirmacion> confirmacion;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
	public Persona getPadre() {
		return padre;
	}

	public void setPadre(Persona padre) {
		this.padre = padre;
	}

	public Persona getMadre() {
		return madre;
	}

	public void setMadre(Persona madre) {
		this.madre = madre;
	}
    
    
}
