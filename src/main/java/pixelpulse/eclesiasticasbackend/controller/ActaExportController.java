package pixelpulse.eclesiasticasbackend.controller;

import pixelpulse.eclesiasticasbackend.service.PDFGeneratorService;
import pixelpulse.eclesiasticasbackend.entities.ActaMatrimonio;
import pixelpulse.eclesiasticasbackend.repository.ActaMatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/export")
public class ActaExportController {

    @Autowired
    private PDFGeneratorService pdfService;

    @Autowired
    private ActaMatrimonioRepository actaRepository;

    @GetMapping("/matrimonio/{id}")
    public ResponseEntity<String> exportMatrimonioToPdf(@PathVariable Long id) {
        try {
            ActaMatrimonio acta = actaRepository.findById(id).orElseThrow();
            String filePath = pdfService.generarActaMatrimonio(acta);
            return ResponseEntity.ok("PDF generado en: " + filePath);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}