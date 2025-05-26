package pixelpulse.eclesiasticasbackend.dto;

import java.util.Map;

import lombok.Data;
@Data
public class PdfRequestDTO {
    private String tipoPdf;        // e.g. "constanciaBautismo"
    private String tipoDocumento;  // e.g. "acta"
    private Map<String, String> parametros;
}
