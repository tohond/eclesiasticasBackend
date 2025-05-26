package pixelpulse.eclesiasticasbackend.service.others;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import pixelpulse.eclesiasticasbackend.model.Matrimonio;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import pixelpulse.eclesiasticasbackend.dto.PdfRequestDTO;

@Service
public class PdfService {

    public byte[] generarPdfDesdePlantilla(PdfRequestDTO request) throws IOException {
        // 1. Cargar plantilla Word
        String plantillaPath = "plantillas_actas/" + request.getTipoDocumento()+ request.getTipoPdf()+ ".docx";

        try (InputStream plantilla = getClass().getClassLoader().getResourceAsStream(plantillaPath)) {
            if (plantilla == null) throw new FileNotFoundException("No se encontró la plantilla");

            XWPFDocument doc = new XWPFDocument(plantilla);

            // 2. Reemplazar los parámetros en el texto
            for (XWPFParagraph p : doc.getParagraphs()) {
                StringBuilder fullText = new StringBuilder();
                for (XWPFRun run : p.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        fullText.append(text);
                    }
                }

                String replaced = fullText.toString();
                for (Map.Entry<String, String> entry : request.getParametros().entrySet()) {
                    replaced = replaced.replace("${" + entry.getKey() + "}", entry.getValue());
                }

                // Limpia los runs originales
                int numRuns = p.getRuns().size();
                for (int i = numRuns - 1; i >= 0; i--) {
                    p.removeRun(i);
                }

                // Crea un nuevo run con el texto reemplazado
                XWPFRun newRun = p.createRun();
                newRun.setText(replaced);
            }

            // 3. Convertir DOCX a PDF
            try (ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream()) {
                PdfOptions options = PdfOptions.create();
                PdfConverter.getInstance().convert(doc, pdfOutputStream, options);
                return pdfOutputStream.toByteArray();
            }
        }
    }
}
