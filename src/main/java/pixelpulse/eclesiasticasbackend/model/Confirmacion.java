package pixelpulse.eclesiasticasbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "confirmacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Confirmacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "idacta")
    private Acta acta;
    
    @ManyToOne
    @JoinColumn(name = "idparroquia")
    private Parroquia parroquia;
    
    @OneToOne
    @JoinColumn(name = "idconfirmante")
    private Persona confirmante;
    
    @ManyToOne
    @JoinColumn(name = "idmonsr")
    private Sacerdote monsr;
    
    @ManyToOne
    @JoinColumn(name = "idsacerdote")
    private Sacerdote sacerdote;
    
    @ManyToOne
    @JoinColumn(name = "iddoyfe")
    private Sacerdote doyfe;
    
    @ManyToOne
    @JoinColumn(name = "idpadrino")
    private Persona padrino;
    
    @ManyToOne
    @JoinColumn(name = "idmadrina")
    private Persona madrina;
}