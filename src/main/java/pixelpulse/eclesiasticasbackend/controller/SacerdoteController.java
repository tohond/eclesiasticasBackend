package pixelpulse.eclesiasticasbackend.controller;

import pixelpulse.eclesiasticasbackend.dto.SacerdoteDTO;
import pixelpulse.eclesiasticasbackend.dto.SacerdotePersonaDTO;
import pixelpulse.eclesiasticasbackend.service.users.SacerdoteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sacerdotes")
public class SacerdoteController {

    @Autowired
    private SacerdoteService sacerdoteService;

    @GetMapping
    public ResponseEntity<List<SacerdoteDTO>> getAllSacerdotes() {
        return ResponseEntity.ok(sacerdoteService.getAllSacerdotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SacerdoteDTO> getSacerdoteById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(sacerdoteService.getSacerdoteById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SacerdoteDTO> createSacerdote(@RequestBody SacerdoteDTO sacerdoteDTO) {
        return new ResponseEntity<>(sacerdoteService.createSacerdote(sacerdoteDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SacerdoteDTO> updateSacerdote(@PathVariable String id, @RequestBody SacerdoteDTO sacerdoteDTO) {
        try {
            return ResponseEntity.ok(sacerdoteService.updateSacerdote(id, sacerdoteDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSacerdote(@PathVariable String id) {
        try {
            sacerdoteService.deleteSacerdote(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/with-persona")
    public ResponseEntity<SacerdotePersonaDTO> createSacerdoteWithPersona(@RequestBody SacerdotePersonaDTO sacerdotePersonaDTO) {
        SacerdotePersonaDTO created = sacerdoteService.createSacerdoteWithPersona(sacerdotePersonaDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
