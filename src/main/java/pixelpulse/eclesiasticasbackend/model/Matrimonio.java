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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
    @JoinColumn(name = "idmadrina", nullable = false)
    private Persona idMadrina;
    @ManyToOne
    @JoinColumn(name = "idpadrino", nullable = false)
    private Persona idPadrino;
    
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

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public Persona getIdMadrina() {
		return idMadrina;
	}

	public void setIdMadrina(Persona idMadrina) {
		this.idMadrina = idMadrina;
	}

	public Persona getIdPadrino() {
		return idPadrino;
	}

	public void setIdPadrino(Persona idPadrino) {
		this.idPadrino = idPadrino;
	}
    
}