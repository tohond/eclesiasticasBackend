package pixelpulse.eclesiasticasbackend.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name = "persona")
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id")
@JsonIdentityReference(alwaysAsId = true)*/

public class Persona {
    
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "nombre1", length = 100)
    private String nombre;
  
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_padre", referencedColumnName = "id")
    private Persona padre;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_madre", referencedColumnName = "id")
    private Persona madre;

    @OneToMany (mappedBy = "padre")
    private List<Persona> hijosPadre;
    
    @OneToMany (mappedBy = "madre")
    private List<Persona> hijosMadre;

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
