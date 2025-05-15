package pixelpulse.eclesiasticasbackend.model;

import java.time.LocalDate;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "nombre1", length = 100, nullable = false)
    private String nombre1;
    
    @Column(name = "nombre2", length = 100)
    private String nombre2;
    
    @Column(name = "apellido1", length = 100)
    private String apellido1;
    
    @Column(name = "apellido2", length = 100)
    private String apellido2;
    
    @Column(name = "fechanacimiento")
    private LocalDate fechaNacimiento;
    
    public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechanacimiento) {
		this.fechaNacimiento = fechanacimiento;
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

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
