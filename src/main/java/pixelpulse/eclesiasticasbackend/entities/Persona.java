package pixelpulse.eclesiasticasbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "persona")

public class Persona {
    
    @Id
    @Column(name = "id", length = 10)
    private String id;
    
    @Column(name = "nombre", length = 80)
    private String nombre;
    
    @Column(name = "telefono", length = 15)
    private String telefono;
    
    @Column(name = "direccion", length = 80)
    private String direccion;
    
    @ManyToOne
    @JoinColumn(name = "id_padre")
    private Persona padre;
    
    @ManyToOne
    @JoinColumn(name = "id_madre")
    private Persona madre;
}
