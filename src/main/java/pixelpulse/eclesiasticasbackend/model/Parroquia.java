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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(name = "nombre", length = 20, nullable = false)
    private String nombre;
    
    @Column(name = "direccion", length = 80)
    private String direccion;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
    
    
}