package pixelpulse.eclesiasticasbackend.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pixelpulse.eclesiasticasbackend.model.Acta;

@Entity
@Table(name = "Matrimonio")
@Getter
@Setter
public class Matrimonio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    // --- Relación con Acta ---
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idacta", referencedColumnName = "id", nullable = false)
    private Acta acta;
    
    // --- Campos específicos ---
    @ManyToOne
    @JoinColumn(name = "personaa", nullable = false)
    private Persona personaA;
    
    @ManyToOne
    @JoinColumn(name = "personab", nullable = false)
    private Persona personaB;
    @ManyToOne
    @JoinColumn(name = "idpadrea", nullable = false)
    private Persona idpadrea;
    @ManyToOne
    @JoinColumn(name = "idmadrea", nullable = false)
    private Persona idmadrea;
    @ManyToOne
    @JoinColumn(name = "idpadreb", nullable = false)
    private Persona idpadreb;
    @ManyToOne
    @JoinColumn(name = "idmadreb", nullable = false)
    private Persona idmadreb;
    
    @Column(name = "testigo1", length = 100)
    private String testigo1;
    @Column(name = "testigo2", length = 100)
    private String testigo2;
    @Column(name = "testigo3", length = 100)
    private String testigo3;
    @Column(name = "testigo4", length = 100)
    private String testigo4;
    
    @ManyToOne
    @JoinColumn(name = "iddoyfe", nullable = false)
    private Sacerdote idDoyFe;
    
    @ManyToOne
    @JoinColumn(name = "idsacerdote", nullable = false)
    private Sacerdote idSacerdote;
    
    ///

	public Sacerdote getIdDoyFe() {
		return idDoyFe;
	}

	public void setIdDoyFe(Sacerdote idDoyFe) {
		this.idDoyFe = idDoyFe;
	}

	public Sacerdote getIdSacerdote() {
		return idSacerdote;
	}

	public void setIdSacerdote(Sacerdote idSacerdote) {
		this.idSacerdote = idSacerdote;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Acta getActa() {
		return acta;
	}

	public void setActa(Acta acta) {
		this.acta = acta;
	}

	public Persona getPersonaA() {
		return personaA;
	}

	public void setPersonaA(Persona personaA) {
		this.personaA = personaA;
	}

	public Persona getPersonaB() {
		return personaB;
	}

	public void setPersonaB(Persona personaB) {
		this.personaB = personaB;
	}

	public Persona getIdpadrea() {
		return idpadrea;
	}

	public void setIdpadrea(Persona idpadrea) {
		this.idpadrea = idpadrea;
	}

	public Persona getIdmadrea() {
		return idmadrea;
	}

	public void setIdmadrea(Persona idmadrea) {
		this.idmadrea = idmadrea;
	}

	public Persona getIdpadreb() {
		return idpadreb;
	}

	public void setIdpadreb(Persona idpadreb) {
		this.idpadreb = idpadreb;
	}

	public Persona getIdmadreb() {
		return idmadreb;
	}

	public void setIdmadreb(Persona idmadreb) {
		this.idmadreb = idmadreb;
	}

    
}