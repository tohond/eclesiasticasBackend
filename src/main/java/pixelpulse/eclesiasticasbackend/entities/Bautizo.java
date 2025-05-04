package pixelpulse.eclesiasticasbackend.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "bautizo")

public class Bautizo {
    
    @Id
    @Column(name = "id", length = 10)
    private String id;
    
    @OneToOne
    @JoinColumn(name = "numeroActa")
    private Acta acta;
    
    @OneToOne
    @JoinColumn(name = "idBautizado")
    private Persona bautizado;
    
    @ManyToOne
    @JoinColumn(name = "idSacerdote")
    private Sacerdote sacerdote;
    
    @ManyToOne
    @JoinColumn(name = "idDoyfe")
    private Sacerdote doyfe;
    
    @Column(name = "idLocalidad", length = 10)
    private String idLocalidad;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Acta getActa() {
		return acta;
	}

	public void setActa(Acta acta) {
		this.acta = acta;
	}

	public Persona getBautizado() {
		return bautizado;
	}

	public void setBautizado(Persona bautizado) {
		this.bautizado = bautizado;
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

	public String getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

    
}
