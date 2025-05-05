package pixelpulse.eclesiasticasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Confirmacion")
@Getter
@Setter
public class Confirmacion extends Acta {

    @Id
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    // --- Relación con Acta ---
    @ManyToOne
    @JoinColumn(name = "idActa", referencedColumnName = "id", nullable = false)
    private Acta acta;

    @Column(name = "idParroquia", nullable = false, length = 10)
    private String idParroquia;

    @Column(name = "idConfirmante", nullable = false, length = 10)
    private String idConfirmante;

    @Column(name = "idMonsr", nullable = false, length = 10)
    private String idMonsr; // Persona que da fe

    @Column(name = "idSacerdote", nullable = false, length = 10)
    private String idSacerdote;

    @Column(name = "idDoyfe", nullable = false, length = 10)
    private String idDoyfe;

    @Column(name = "idPadrino", nullable = false, length = 10)
    private String idPadrino;

    @Column(name = "idMadrina", nullable = false, length = 10)
    private String idMadrina;
}