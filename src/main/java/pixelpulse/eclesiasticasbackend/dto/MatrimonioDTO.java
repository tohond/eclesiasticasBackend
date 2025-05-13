package pixelpulse.eclesiasticasbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pixelpulse.eclesiasticasbackend.model.Persona;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Data

public class MatrimonioDTO {
    private Long id;
    private ActaDTO idActa;
    private PersonaDTO personaA;
    private PersonaDTO personaB;
    private PersonaDTO idpadrea;
    private PersonaDTO idmadrea;
   private PersonaDTO idpadreb;
   private PersonaDTO idmadreb;
    private String testigo1;
     private String testigo2;
    private String testigo3;
    private String testigo4;
	public MatrimonioDTO() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ActaDTO getIdActa() {
		return idActa;
	}
	public void setIdActa(ActaDTO idActa) {
		this.idActa = idActa;
	}
	
	public PersonaDTO getPersonaA() {
		return personaA;
	}
	public void setPersonaA(PersonaDTO personaA) {
		this.personaA = personaA;
	}
	public PersonaDTO getPersonaB() {
		return personaB;
	}
	public void setPersonaB(PersonaDTO personaB) {
		this.personaB = personaB;
	}
	public PersonaDTO getIdpadrea() {
		return idpadrea;
	}
	public void setIdpadrea(PersonaDTO idpadrea) {
		this.idpadrea = idpadrea;
	}
	public PersonaDTO getIdmadrea() {
		return idmadrea;
	}
	public void setIdmadrea(PersonaDTO idmadrea) {
		this.idmadrea = idmadrea;
	}
	public PersonaDTO getIdpadreb() {
		return idpadreb;
	}
	public void setIdpadreb(PersonaDTO idpadreb) {
		this.idpadreb = idpadreb;
	}
	public PersonaDTO getIdmadreb() {
		return idmadreb;
	}
	public void setIdmadreb(PersonaDTO idmadreb) {
		this.idmadreb = idmadreb;
	}
	public String getTestigo1() {
		return testigo1;
	}
	public void setTestigo1(String testigo1) {
		this.testigo1 = testigo1;
	}
	public String getTestigo2() {
		return testigo2;
	}
	public void setTestigo2(String testigo2) {
		this.testigo2 = testigo2;
	}
	public String getTestigo3() {
		return testigo3;
	}
	public void setTestigo3(String testigo3) {
		this.testigo3 = testigo3;
	}
	public String getTestigo4() {
		return testigo4;
	}
	public void setTestigo4(String testigo4) {
		this.testigo4 = testigo4;
	}
	
}
