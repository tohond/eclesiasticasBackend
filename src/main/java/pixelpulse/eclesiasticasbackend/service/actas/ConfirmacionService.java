package pixelpulse.eclesiasticasbackend.service.actas;

import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.mapper.ConfirmacionMapper;
import pixelpulse.eclesiasticasbackend.mapper.PersonaMapper;
import pixelpulse.eclesiasticasbackend.model.*;
import pixelpulse.eclesiasticasbackend.repository.*;
import pixelpulse.eclesiasticasbackend.service.personas.PersonaService;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConfirmacionService {

    @Autowired
    private ConfirmacionRepository confirmacionRepository;
    
    @Autowired
    private ActaRepository actaRepository;
    
    @Autowired
    private ParroquiaRepository parroquiaRepository;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private SacerdoteRepository sacerdoteRepository;
    
    @Autowired
    private PersonaService pService;
    
    private PersonaMapper pMapper;
    
    
    private ConfirmacionMapper mapper;

    public List<ConfirmacionDTO> getAllConfirmaciones() {
        return mapper.toDtoList(confirmacionRepository.findAll());
        		
    }
    /*
    public ConfirmacionDTO getConfirmacionByNombre(String nombre) {
    	Persona p = pMapper.fromDto(pService.getPersonaByNombre(nombre)); 
        Confirmacion confirmacion = confirmacionRepository.findByPersona(p);
                 return mapper.toDto(confirmacion);
    }*/

    public ConfirmacionDTO createConfirmacion(ConfirmacionDTO confirmacionDTO) {
        Confirmacion confirmacion = mapper.fromDto(confirmacionDTO);
        Confirmacion savedConfirmacion = confirmacionRepository.save(confirmacion);
        return mapper.toDto(savedConfirmacion);
    }

    public ConfirmacionDTO updateConfirmacion(String id, ConfirmacionDTO confirmacionDTO) {
        if (!confirmacionRepository.existsById(UUID.fromString(id))) {
            throw new EntityNotFoundException("Confirmación no encontrada con ID: " + id);
        }
        
        Confirmacion confirmacion = mapper.fromDto(confirmacionDTO);
        
        Confirmacion updatedConfirmacion = confirmacionRepository.save(confirmacion);
        return mapper.toDto(updatedConfirmacion);
    }

    public void deleteConfirmacion(String id) {
        if (!confirmacionRepository.existsById(UUID.fromString(id))) {
            throw new EntityNotFoundException("Confirmación no encontrada con ID: " + id);
        }
        confirmacionRepository.deleteById(UUID.fromString(id));
    }

   

  
}
