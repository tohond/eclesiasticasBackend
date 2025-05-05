package pixelpulse.eclesiasticasbackend.service.actas;

import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.MatrimonioDTO;
import pixelpulse.eclesiasticasbackend.mapper.MatrimonioMapper;
import pixelpulse.eclesiasticasbackend.mapper.PersonaMapper;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Confirmacion;
import pixelpulse.eclesiasticasbackend.model.Matrimonio;
import pixelpulse.eclesiasticasbackend.model.Persona;
import pixelpulse.eclesiasticasbackend.repository.ActaRepository;
import pixelpulse.eclesiasticasbackend.repository.MatrimonioRepository;
import pixelpulse.eclesiasticasbackend.repository.PersonaRepository;
import pixelpulse.eclesiasticasbackend.service.personas.PersonaService;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MatrimonioService {

    @Autowired
    private MatrimonioRepository matrimonioRepository;
    
    @Autowired
    private ActaRepository actaRepository;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private PersonaService pService;
    
    private PersonaMapper pMapper;
    
    @Autowired
    private MatrimonioMapper mapper;

    public List<MatrimonioDTO> getAllMatrimonios() {
        return mapper.toDtoList(matrimonioRepository.findAll());
        		
    }

    public MatrimonioDTO getMatrimonioByNombre(String nombre) {
    	Persona p = pMapper.fromDto(pService.getPersonaByNombre(nombre)); 
        Matrimonio confirmacion = matrimonioRepository.findByPersonaA(p);
                 return mapper.toDto(confirmacion);
    }

    public MatrimonioDTO createConfirmacion(MatrimonioDTO confirmacionDTO) {
    	Matrimonio confirmacion = mapper.fromDto(confirmacionDTO);
    	Matrimonio savedConfirmacion = matrimonioRepository.save(confirmacion);
        return mapper.toDto(savedConfirmacion);
    }

    public MatrimonioDTO updateConfirmacion(String id, MatrimonioDTO confirmacionDTO) {
        if (!matrimonioRepository.existsById(UUID.fromString(id))) {
            throw new EntityNotFoundException("Confirmación no encontrada con ID: " + id);
        }
        
        Matrimonio confirmacion = mapper.fromDto(confirmacionDTO);
        
        Matrimonio updatedConfirmacion = matrimonioRepository.save(confirmacion);
        return mapper.toDto(updatedConfirmacion);
    }

    public void deleteConfirmacion(String id) {
        if (!matrimonioRepository.existsById(UUID.fromString(id))) {
            throw new EntityNotFoundException("Confirmación no encontrada con ID: " + id);
        }
        matrimonioRepository.deleteById(UUID.fromString(id));
    }
    
}
