package pixelpulse.eclesiasticasbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "sacerdote")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sacerdote {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona", referencedColumnName = "id", nullable = false)
    private Persona persona;

	public Sacerdote(UUID id, Persona persona) {
		super();
		this.id = id;
		this.persona = persona;
	}
	
	public Sacerdote() {
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
    
    
    
    // Additional fields
    // private String ordenadoEn;
    // private String diocesis;
}
