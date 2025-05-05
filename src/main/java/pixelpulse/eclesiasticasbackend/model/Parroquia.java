package pixelpulse.eclesiasticasbackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "parroquia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parroquia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;
    
    @Column(name = "nombre", length = 20)
    private String nombre;
    
    @Column(name = "direccion", length = 80)
    private String direccion;
}