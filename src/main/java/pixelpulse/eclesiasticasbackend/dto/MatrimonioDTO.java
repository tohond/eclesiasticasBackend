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
    private SacerdoteDTO idSacerdote;
    public SacerdoteDTO getIdSacerdote() {
		return idSacerdote;
	}
	public void setIdSacerdote(SacerdoteDTO idSacerdote) {
		this.idSacerdote = idSacerdote;
	}
	private SacerdoteDTO idDoyFe;
    public SacerdoteDTO getIdDoyfe() {
		return idDoyFe;
	}
	public void setIdDoyFe(SacerdoteDTO idDoyfe) {
		this.idDoyFe = idDoyfe;
	}
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
