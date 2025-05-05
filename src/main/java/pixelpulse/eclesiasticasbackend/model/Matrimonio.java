package pixelpulse.eclesiasticasbackend.model;

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
    @Column(name = "id", length = 10)
    private String id;

    // --- Relación con Acta ---
    @ManyToOne
    @JoinColumn(name = "idActa", referencedColumnName = "id", nullable = false)
    private Acta acta;

    // --- Campos específicos ---
    @Column(name = "personaA", nullable = false, length = 10)
    private String personaA;

    @Column(name = "personaB", nullable = false, length = 10)
    private String personaB;

    @Column(name = "idMadrina", nullable = false, length = 10)
    private String idMadrina;

    @Column(name = "idPadrino", nullable = false, length = 10)
    private String idPadrino;
}