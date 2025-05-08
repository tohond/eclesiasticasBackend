package pixelpulse.eclesiasticasbackend.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Bautizo")

public class Bautizo  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // --- Relación con Acta ---
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idacta", referencedColumnName = "id", nullable = false)
    private Acta acta;

    @ManyToOne
    @JoinColumn(name = "idbautizado")
    private Persona idBautizado;
    @ManyToOne
    @JoinColumn(name = "idsacerdote")
    private Sacerdote idSacerdote;
    @ManyToOne
    @JoinColumn(name = "iddoyfe")
    private Sacerdote idDoyfe;
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
	public Persona getIdBautizado() {
		return idBautizado;
	}
	public void setIdBautizado(Persona idBautizado) {
		this.idBautizado = idBautizado;
	}
	public Sacerdote getIdSacerdote() {
		return idSacerdote;
	}
	public void setIdSacerdote(Sacerdote idSacerdote) {
		this.idSacerdote = idSacerdote;
	}
	public Sacerdote getIdDoyfe() {
		return idDoyfe;
	}
	public void setIdDoyfe(Sacerdote idDoyfe) {
		this.idDoyfe = idDoyfe;
	}
    
    
   
}