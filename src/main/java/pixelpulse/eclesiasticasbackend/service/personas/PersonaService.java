package pixelpulse.eclesiasticasbackend.service.personas;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;
import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.dto.PersonaDTO;
import pixelpulse.eclesiasticasbackend.mapper.ActaMapper;
import pixelpulse.eclesiasticasbackend.mapper.PersonaMapper;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Bautizo;
import pixelpulse.eclesiasticasbackend.model.Confirmacion;
import pixelpulse.eclesiasticasbackend.model.Matrimonio;
import pixelpulse.eclesiasticasbackend.model.Persona;
import pixelpulse.eclesiasticasbackend.repository.ActaRepository;
import pixelpulse.eclesiasticasbackend.repository.BautizoRepository;
import pixelpulse.eclesiasticasbackend.repository.ConfirmacionRepository;
import pixelpulse.eclesiasticasbackend.repository.MatrimonioRepository;
import pixelpulse.eclesiasticasbackend.repository.PersonaRepository;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaMapper personaMapper;
    @Autowired
    private  MatrimonioRepository matrimonioRepository;
    @Autowired
    private BautizoRepository bautizoRepository;
    @Autowired
    private ConfirmacionRepository confirmacionRepository;

    
    
    
    public PersonaService(PersonaRepository personaRepository, PersonaMapper personaMapper,
			MatrimonioRepository matrimonioRepository, BautizoRepository bautizoRepository,
			ConfirmacionRepository confirmacionRepository) {
		super();
		this.personaRepository = personaRepository;
		this.personaMapper = personaMapper;
		this.matrimonioRepository = matrimonioRepository;
		this.bautizoRepository = bautizoRepository;
		this.confirmacionRepository = confirmacionRepository;
	}


	public List<PersonaDTO> getAllPersonas() {
        return personaMapper.toDtoList(personaRepository.findAll());
    }
    
    
    public PersonaSearchResult searchByName(String name) {
        // Find personas by name (case-insensitive, partial match)
        Persona p = personaRepository.findPersonaByNombre(name);


        // Collect all related records for each persona
        List<Acta> actasByPerson = new ArrayList<>();
                 matrimonioRepository.findAllByPersonaInAnyRole(p).forEach(e -> actasByPerson.add(e.getActa()) );
                bautizoRepository.findByIdBautizado(p).forEach(e -> actasByPerson.add(e.getActa() ) ) ;
                //confirmacionRepository.findBy(p).forEach(e -> actasByPerson.add(e.getActa() ) );
                return new PersonaSearchResult(p, actasByPerson);
            
    
}
    
    public List<Matrimonio> getMatrimoniosForPersona(Persona persona) {
        return matrimonioRepository.findAllByPersonaInAnyRole(persona);
    }

    public List<Bautizo> getBautizosForPersona(Persona persona) {
        return bautizoRepository.findByIdBautizado(persona);
    }

    public List<Confirmacion> getConfirmacionesForPersona(Persona persona) {
        return confirmacionRepository.findAllByPersonaInAnyRole(persona);
    }
    

    public PersonaDTO getPersonaByNombre(String nombre) {
        Persona persona = personaRepository.findPersonaByNombre(nombre);
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

    
    
    public record PersonaSearchResult(
    	    UUID personaId,
    	    String personaName,
    	    List<Acta> actas
    	) {
    	    public PersonaSearchResult(Persona persona, List<Acta> actas) {
    	        this(
    	            persona.getId(),
    	            persona.getNombre(),
    	            actas
    	        );
    	    }
    	}
}