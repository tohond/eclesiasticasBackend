package pixelpulse.eclesiasticasbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "confirmacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Confirmacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idacta", referencedColumnName = "id", nullable = false)
    private Acta acta;
    
    @ManyToOne
    @JoinColumn(name = "idparroquia")
    private Parroquia parroquia;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idconfirmante")
    private Persona confirmante;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idmonsr")
    private Sacerdote monsr;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idsacerdote")
    private Sacerdote sacerdote;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "iddoyfe")
    private Sacerdote doyfe;
    
    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idpadrino")
    private Persona padrino;
    
    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idmadrina")
    private Persona madrina;

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

	public Parroquia getParroquia() {
		return parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}

	public Persona getConfirmante() {
		return confirmante;
	}

	public void setConfirmante(Persona confirmante) {
		this.confirmante = confirmante;
	}

	public Sacerdote getMonsr() {
		return monsr;
	}

	public void setMonsr(Sacerdote monsr) {
		this.monsr = monsr;
	}

	public Sacerdote getSacerdote() {
		return sacerdote;
	}

	public void setSacerdote(Sacerdote sacerdote) {
		this.sacerdote = sacerdote;
	}

	public Sacerdote getDoyfe() {
		return doyfe;
	}

	public void setDoyfe(Sacerdote doyfe) {
		this.doyfe = doyfe;
	}

	public Persona getPadrino() {
		return padrino;
	}

	public void setPadrino(Persona padrino) {
		this.padrino = padrino;
	}

	public Persona getMadrina() {
		return madrina;
	}

	public void setMadrina(Persona madrina) {
		this.madrina = madrina;
	}
    
    
}