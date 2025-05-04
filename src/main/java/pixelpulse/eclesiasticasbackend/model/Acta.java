package pixelpulse.eclesiasticasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public abstract class Acta {
    @Id
    @Column(name = "id", length = 10)
    private String id;

    @Column(name = "numeroActa", length = 10)
    private String numeroActa;

    @Column(name = "folio", length = 4)
    private String folio;

    @Column(name = "libro", length = 4)
    private String libro;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "notas", length = 200)
    private String notas;

    // --- Relaciones OneToMany ---
    @OneToMany(mappedBy = "acta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Matrimonio> matrimonios;

    @OneToMany(mappedBy = "acta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bautizo> bautizos;

    @OneToMany(mappedBy = "acta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Confirmacion> confirmaciones;
}