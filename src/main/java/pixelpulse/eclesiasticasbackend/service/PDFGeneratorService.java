package pixelpulse.eclesiasticasbackend.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pixelpulse.eclesiasticasbackend.model.Matrimonio;

@Service
public class PDFGeneratorService {

    @Autowired
    private FileStorageService fileStorageService;

    public String generarActaMatrimonio(Matrimonio acta) throws Exception {
        String docsPath = fileStorageService.crearDirectorioActas();
        String fileName = fileStorageService.generarRutaArchivo(
            "ACTA_DE_MATRIMONIO_",
            acta.getPersonaA().replace(" ", ""),
            acta.getPersonaB().replace(" ", ""),
            acta.getFecha().toString()
        );

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("ACTA DE MATRIMONIO");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Contrayentes: " + acta.getPersonaA() + " y " + acta.getPersonaB());
            contentStream.endText();
            contentStream.close();

            document.save(docsPath + fileName);
        }

        return docsPath + fileName;
    }
}