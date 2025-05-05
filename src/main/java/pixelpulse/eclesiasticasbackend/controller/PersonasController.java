package pixelpulse.eclesiasticasbackend.controller;


import jakarta.persistence.EntityNotFoundException;
import pixelpulse.eclesiasticasbackend.dto.PersonaDTO;
import pixelpulse.eclesiasticasbackend.service.personas.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonasController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> getAllPersonas() {
        return ResponseEntity.ok(personaService.getAllPersonas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> getPersonaById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(personaService.getPersonaById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> createPersona(@RequestBody PersonaDTO personaDTO) {
        return new ResponseEntity<>(personaService.createPersona(personaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> updatePersona(@PathVariable String id, @RequestBody PersonaDTO personaDTO) {
        try {
            return ResponseEntity.ok(personaService.updatePersona(id, personaDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable String id) {
        try {
            personaService.deletePersona(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

