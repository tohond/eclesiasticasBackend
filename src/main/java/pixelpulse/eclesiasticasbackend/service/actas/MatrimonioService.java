package pixelpulse.eclesiasticasbackend.service.actas;

import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.MatrimonioDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createMatrimonioDTO;
import pixelpulse.eclesiasticasbackend.mapper.MatrimonioMapper;
import pixelpulse.eclesiasticasbackend.mapper.PersonaMapper;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Confirmacion;
import pixelpulse.eclesiasticasbackend.model.Matrimonio;
import pixelpulse.eclesiasticasbackend.model.Persona;
import pixelpulse.eclesiasticasbackend.model.Sacerdote;
import pixelpulse.eclesiasticasbackend.repository.ActaRepository;
import pixelpulse.eclesiasticasbackend.repository.MatrimonioRepository;
import pixelpulse.eclesiasticasbackend.repository.PersonaRepository;
import pixelpulse.eclesiasticasbackend.repository.SacerdoteRepository;
import pixelpulse.eclesiasticasbackend.service.personas.PersonaService;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MatrimonioService {

    private final SacerdoteRepository sacerdoteRepository;

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

    MatrimonioService(SacerdoteRepository sacerdoteRepository) {
        this.sacerdoteRepository = sacerdoteRepository;
    }

    public List<MatrimonioDTO> getAllMatrimonios() {
        return mapper.toDtoList(matrimonioRepository.findAll());
        		
    }

    public MatrimonioDTO getMatrimonioByNombre(String nombre) {
    	Persona p = pMapper.fromDto(pService.getPersonaByNombre(nombre)); 
        Matrimonio confirmacion = matrimonioRepository.findByPersonaA(p);
                 return mapper.toDto(confirmacion);
    }

    public MatrimonioDTO createMatrimonio(createMatrimonioDTO dto) {
    	Acta a = new Acta(null, dto.getNumeroActa(), dto.getFolio(), dto.getLibro(), dto.getFecha(), dto.getNotas(), dto.getTipo());
		Sacerdote doyfe = new Sacerdote();
		Sacerdote s = new Sacerdote();
		Persona personaA = new Persona();
		
    	if(dto.getIdDoyFe()==null||dto.getIdDoyFe().isBlank() ) {
    		
    		Persona p1 = new Persona();
    		p1.setNombre(dto.getNombresDoyFe());
    		doyfe.setPersona(p1);
    		
    	}
    	else {
    		doyfe=sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdDoyFe()));
    	}
    	
    	if(dto.getIdSacerdote()==null||dto.getIdSacerdote().isBlank() ) {
    		
    		Persona p2 = new Persona();
    		p2.setNombre(dto.getNombresSacerdote());
    		s.setPersona(p2);
    		
    	}
    	else {
    		s=sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdSacerdote()));
    	}
    	
    	
    	
    	
    	
    	
    	
    	Matrimonio savedConfirmacion = matrimonioRepository.save(null);
        return mapper.toDto(savedConfirmacion);
    }

    public MatrimonioDTO updateConfirmacion(String id, MatrimonioDTO confirmacionDTO) {
        if (!matrimonioRepository.existsById(Long.valueOf(id))) {
            throw new EntityNotFoundException("Confirmación no encontrada con ID: " + id);
        }
        
        Matrimonio confirmacion = mapper.fromDto(confirmacionDTO);
        
        Matrimonio updatedConfirmacion = matrimonioRepository.save(confirmacion);
        return mapper.toDto(updatedConfirmacion);
    }

    public void deleteConfirmacion(String id) {
        if (!matrimonioRepository.existsById(Long.valueOf(id))) {
            throw new EntityNotFoundException("Confirmación no encontrada con ID: " + id);
        }
        matrimonioRepository.deleteById(Long.valueOf(id));
    }
    
}
