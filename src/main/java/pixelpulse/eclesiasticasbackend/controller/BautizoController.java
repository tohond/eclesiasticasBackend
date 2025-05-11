package pixelpulse.eclesiasticasbackend.controller;

import pixelpulse.eclesiasticasbackend.dto.BautizoDTO;
import pixelpulse.eclesiasticasbackend.service.actas.BautizoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bautizos")

public class BautizoController {

    @Autowired
    private BautizoService bautizoService;

    @GetMapping
    public ResponseEntity<List<BautizoDTO>> getAllBautizos() {
        return ResponseEntity.ok(bautizoService.getAllBautizos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BautizoDTO> getBautizoById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bautizoService.getBautizoById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BautizoDTO> createBautizo(@RequestBody BautizoDTO bautizoDTO) {
        return new ResponseEntity<>(bautizoService.createConfirmacion(bautizoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BautizoDTO> updateBautizo(@PathVariable UUID id, @RequestBody BautizoDTO bautizoDTO) {
        try {
            return ResponseEntity.ok(bautizoService.updateBautizo(id, bautizoDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBautizo(@PathVariable UUID id) {
        try {
            bautizoService.deleteBautizo(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
