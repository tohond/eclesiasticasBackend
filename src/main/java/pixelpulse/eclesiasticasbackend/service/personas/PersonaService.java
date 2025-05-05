package pixelpulse.eclesiasticasbackend.service.personas;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.dto.PersonaDTO;
import pixelpulse.eclesiasticasbackend.mapper.ActaMapper;
import pixelpulse.eclesiasticasbackend.mapper.PersonaMapper;
import pixelpulse.eclesiasticasbackend.model.Persona;
import pixelpulse.eclesiasticasbackend.repository.ActaRepository;
import pixelpulse.eclesiasticasbackend.repository.PersonaRepository;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;
    private PersonaMapper personaMapper;

    public List<PersonaDTO> getAllPersonas() {
        return personaMapper.toDtoList(personaRepository.findAll());
    }

    public PersonaDTO getPersonaById(String id) {
        Persona persona = personaRepository.findPersonaById(UUID.fromString(id));
                return personaMapper.toDto(persona);
    }

    public PersonaDTO createPersona(PersonaDTO personaDTO) {
        Persona persona = personaMapper.fromDto(personaDTO);
        Persona savedPersona = personaRepository.save(persona);
        return  personaMapper.toDto(savedPersona);
    }

    public PersonaDTO updatePersona(String id, PersonaDTO personaDTO) {
        if (!personaRepository.existsById(UUID.fromString(id))) {
            throw new EntityNotFoundException("Persona no encontrada con ID: " + id);
        }
        
        Persona persona = personaMapper.fromDto(personaDTO);
        
        Persona updatedPersona = personaRepository.save(persona);
        return personaMapper.toDto(updatedPersona);
    }

    public void deletePersona(String id) {
    	if (!personaRepository.existsById(UUID.fromString(id))) {
            throw new EntityNotFoundException("Persona no encontrada con ID: " + id);
        }
        personaRepository.deletePersonaById(UUID.fromString(id));
    }

    
}