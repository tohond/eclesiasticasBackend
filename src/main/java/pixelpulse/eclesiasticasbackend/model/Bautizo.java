package pixelpulse.eclesiasticasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Bautizo")
@Getter
@Setter
public class Bautizo extends Acta {

    @Id
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    // --- Relación con Acta ---
    @ManyToOne
    @JoinColumn(name = "idActa", referencedColumnName = "id", nullable = false)
    private Acta acta;

    @Column(name = "idBautizado", nullable = false, length = 10)
    private String idBautizado;

    @Column(name = "idSacerdote", nullable = false, length = 10)
    private String idSacerdote;

    @Column(name = "idDoyfe", nullable = false, length = 10)
    private String idDoyfe;

    @Column(name = "idLocalidad", nullable = false, length = 10)
    private String idLocalidad;
}