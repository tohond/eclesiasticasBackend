package pixelpulse.eclesiasticasbackend.controller;

import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.service.actas.ConfirmacionService;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/confirmaciones")
public class ConfirmacionController {

    @Autowired
    private ConfirmacionService confirmacionService;

    @GetMapping
    public ResponseEntity<List<ConfirmacionDTO>> getAllConfirmaciones() {
        return ResponseEntity.ok(confirmacionService.getAllConfirmaciones());
    }
    /*
    @GetMapping("/{id}")
    public ResponseEntity<ConfirmacionDTO> getConfirmacionById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(confirmacionService.getConfirmacionById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }*/

    @PostMapping
    public ResponseEntity<ConfirmacionDTO> createConfirmacion(@RequestBody ConfirmacionDTO confirmacionDTO) {
        return null;
        		//new ResponseEntity<>(confirmacionService.createConfirmacion(confirmacionDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfirmacionDTO> updateConfirmacion(@PathVariable String id, @RequestBody ConfirmacionDTO confirmacionDTO) {
        try {
            return ResponseEntity.ok(confirmacionService.updateConfirmacion(id, confirmacionDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConfirmacion(@PathVariable String id) {
        try {
            confirmacionService.deleteConfirmacion(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
