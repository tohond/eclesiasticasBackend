package pixelpulse.eclesiasticasbackend.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Bautizo")
@Getter
@Setter
public class Bautizo  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // --- Relación con Acta ---
    @ManyToOne
    @JoinColumn(name = "idacta", referencedColumnName = "id", nullable = false)
    private Acta acta;

    @ManyToOne
    @JoinColumn(name = "idbautizado")
    private Persona idBautizado;
    @ManyToOne
    @JoinColumn(name = "idsacerdote")
    private Sacerdote idSacerdote;
    @ManyToOne
    @JoinColumn(name = "iddoyfe")
    private Sacerdote idDoyfe;
   
}