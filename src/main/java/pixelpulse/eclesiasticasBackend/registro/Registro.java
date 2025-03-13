package pixelpulse.eclesiasticasBackend.registro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class Registro {
	

	public Registro(String string) {
		nombres = string;
	}

	private String nombres;
}
