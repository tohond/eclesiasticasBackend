package pixelpulse.eclesiasticasbackend.entities;

import jakarta.persistence.*;


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
