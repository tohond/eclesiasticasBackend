package pixelpulse.eclesiasticasbackend.controller;

import pixelpulse.eclesiasticasbackend.dto.MatrimonioDTO;
import pixelpulse.eclesiasticasbackend.service.actas.MatrimonioService;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matrimonios")

public class MatrimonioController {

    @Autowired
    private MatrimonioService matrimonioService;

    @GetMapping
    public ResponseEntity<List<MatrimonioDTO>> getAllMatrimonios() {
        return ResponseEntity.ok(matrimonioService.getAllMatrimonios());
    }
    /*
    @GetMapping("/{id}")
    public ResponseEntity<MatrimonioDTO> getMatrimonioById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(matrimonioService.getMatrimonioById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MatrimonioDTO> createMatrimonio(@RequestBody MatrimonioDTO matrimonioDTO) {
        return new ResponseEntity<>(matrimonioService.createMatrimonio(matrimonioDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatrimonioDTO> updateMatrimonio(@PathVariable String id, @RequestBody MatrimonioDTO matrimonioDTO) {
        try {
            return ResponseEntity.ok(matrimonioService.updateMatrimonio(id, matrimonioDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatrimonio(@PathVariable String id) {
        try {
            matrimonioService.deleteMatrimonio(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }*/
}
