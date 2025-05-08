package pixelpulse.eclesiasticasbackend.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import pixelpulse.eclesiasticasbackend.model.Acta;

@Entity
@Table(name = "Matrimonio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matrimonio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // --- Relación con Acta ---
    @OneToOne(cascade = CascadeType.PERSIST)
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
    ///

}