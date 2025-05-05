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
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idacta", referencedColumnName = "id", nullable = false)
    private Acta acta;
    /*
    // --- Campos específicos ---
    @Column(name = "personaA", nullable = false, length = 10)
    //private Persona personaA;

    @Column(name = "personaB", nullable = false, length = 10)
    //private Persona personaB;

    @Column(name = "idMadrina", nullable = false, length = 10)
    //private Persona idMadrina;

    @Column(name = "idPadrino", nullable = false, length = 10)
    //private Persona idPadrino;
    ///*/

}