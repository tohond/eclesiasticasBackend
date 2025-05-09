package pixelpulse.eclesiasticasbackend.model;

import java.util.UUID;

import org.hibernate.annotations.Cascade;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idbautizado", referencedColumnName = "id",nullable = false)
    private Persona idBautizado;
    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idsacerdote",referencedColumnName = "id",nullable = false)
    private Sacerdote idSacerdote;
    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "iddoyfe",referencedColumnName = "id",nullable = false)
    private Sacerdote idDoyfe;
    
    @Column (name = "abuelopaterno", length = 100)
    private String abueloPaterno;
    
    @Column (name = "abuelomaterno", length = 100)
    private String abueloMaterno;
    
    @Column (name = "abuelapaterna", length = 100)
    private String abuelaPaterna;
    
    @Column (name = "abuelamaterna", length = 100)
    private String abuelaMaterna;
    
	public String getAbueloPaterno() {
		return abueloPaterno;
	}
	public void setAbueloPaterno(String abueloPaterno) {
		this.abueloPaterno = abueloPaterno;
	}
	public String getAbueloMaterno() {
		return abueloMaterno;
	}
	public void setAbueloMaterno(String abueloMaterno) {
		this.abueloMaterno = abueloMaterno;
	}
	public String getAbuelaPaterna() {
		return abuelaPaterna;
	}
	public void setAbuelaPaterna(String abuelaPaterna) {
		this.abuelaPaterna = abuelaPaterna;
	}
	public String getAbuelaMaterna() {
		return abuelaMaterna;
	}
	public void setAbuelaMaterna(String abuelaMaterna) {
		this.abuelaMaterna = abuelaMaterna;
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