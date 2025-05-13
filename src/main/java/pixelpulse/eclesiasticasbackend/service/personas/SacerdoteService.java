package pixelpulse.eclesiasticasbackend.service.personas;

import pixelpulse.eclesiasticasbackend.dto.PersonaDTO;
import pixelpulse.eclesiasticasbackend.dto.SacerdoteDTO;
import pixelpulse.eclesiasticasbackend.dto.SacerdotePersonaDTO;
import pixelpulse.eclesiasticasbackend.mapper.MapStructMapper;
import pixelpulse.eclesiasticasbackend.mapper.SacerdoteMapper;
import pixelpulse.eclesiasticasbackend.model.Persona;
import pixelpulse.eclesiasticasbackend.model.Sacerdote;
import pixelpulse.eclesiasticasbackend.repository.PersonaRepository;
import pixelpulse.eclesiasticasbackend.repository.SacerdoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SacerdoteService {

    @Autowired
    private SacerdoteRepository sacerdoteRepository;
    
    private SacerdoteMapper sacerdoteMapper;

    
    
    public SacerdoteService(SacerdoteRepository sacerdoteRepository, SacerdoteMapper sacerdoteMapper) {
		super();
		this.sacerdoteRepository = sacerdoteRepository;
		this.sacerdoteMapper = sacerdoteMapper;
	}

	public List<SacerdoteDTO> getAllSacerdotes() {
		List<Sacerdote> sacer = sacerdoteRepository.findAll();
		List<SacerdoteDTO> dtos = new ArrayList<>();
		for (Sacerdote s : sacer) {
			SacerdoteDTO dto = MapStructMapper.INSTANCE.sacerdoteToSacerdoteDTO(s);
			dtos.add(dto);
		}
		return dtos;
        		
        		
    }

    public SacerdoteDTO getSacerdoteById(String id) {
        Sacerdote sacerdote = sacerdoteRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Sacerdote no encontrado con ID: " + id));
        return sacerdoteMapper.toDto(sacerdote);
    }

    public SacerdoteDTO createSacerdote(SacerdoteDTO sacerdoteDTO) {
        Sacerdote sacerdote = sacerdoteMapper.fromDto(sacerdoteDTO);
        Sacerdote savedSacerdote = sacerdoteRepository.save(sacerdote);
        return sacerdoteMapper.toDto(savedSacerdote);
    }

    public SacerdoteDTO updateSacerdote(String id, SacerdoteDTO sacerdoteDTO) {
        if (!sacerdoteRepository.existsById(Long.valueOf(id))) {
            throw new EntityNotFoundException("Sacerdote no encontrado con ID: " + id);
        }
        
        Sacerdote sacerdote = sacerdoteMapper.fromDto(sacerdoteDTO);
       
        Sacerdote updatedSacerdote = sacerdoteRepository.save(sacerdote);
        return sacerdoteMapper.toDto(updatedSacerdote);
    }

    public void deleteSacerdote(String id) {
        if (!sacerdoteRepository.existsById(Long.valueOf(id))) {
            throw new EntityNotFoundException("Sacerdote no encontrado con ID: " + id);
        }
        sacerdoteRepository.deleteById(Long.valueOf(id));
    }

    
    /**
     * Create a new Sacerdote along with a new Persona in a single transaction
     */
   
    
    
    

    
}
