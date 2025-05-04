package pixelpulse.eclesiasticasbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sacerdote")

public class Sacerdote {
    
    @Id
    @Column(name = "id", length = 10)
    private String id;
    
    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;
}
