package pixelpulse.eclesiasticasbackend.service.users;

import pixelpulse.eclesiasticasbackend.dto.SacerdoteDTO;
import pixelpulse.eclesiasticasbackend.dto.SacerdotePersonaDTO;
import pixelpulse.eclesiasticasbackend.mapper.SacerdoteMapper;
import pixelpulse.eclesiasticasbackend.model.Persona;
import pixelpulse.eclesiasticasbackend.model.Sacerdote;
import pixelpulse.eclesiasticasbackend.repository.PersonaRepository;
import pixelpulse.eclesiasticasbackend.repository.SacerdoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SacerdoteService {

    @Autowired
    private SacerdoteRepository sacerdoteRepository;
    
    @Autowired
    private SacerdoteMapper sacerdoteMapper;

    

    public List<SacerdoteDTO> getAllSacerdotes() {
        return sacerdoteRepository.findAll().stream()
                .map(this::sacerdoteMapper)
                .collect(Collectors.toList());
    }

    public SacerdoteDTO getSacerdoteById(String id) {
        Sacerdote sacerdote = sacerdoteRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Sacerdote no encontrado con ID: " + id));
        return convertToDTO(sacerdote);
    }

    public SacerdoteDTO createSacerdote(SacerdoteDTO sacerdoteDTO) {
        Sacerdote sacerdote = convertToEntity(sacerdoteDTO);
        Sacerdote savedSacerdote = sacerdoteRepository.save(sacerdote);
        return convertToDTO(savedSacerdote);
    }

    public SacerdoteDTO updateSacerdote(String id, SacerdoteDTO sacerdoteDTO) {
        if (!sacerdoteRepository.existsById(id)) {
            throw new EntityNotFoundException("Sacerdote no encontrado con ID: " + id);
        }
        
        Sacerdote sacerdote = convertToEntity(sacerdoteDTO);
        sacerdote.setId(id);
        Sacerdote updatedSacerdote = sacerdoteRepository.save(sacerdote);
        return convertToDTO(updatedSacerdote);
    }

    public void deleteSacerdote(String id) {
        if (!sacerdoteRepository.existsById(id)) {
            throw new EntityNotFoundException("Sacerdote no encontrado con ID: " + id);
        }
        sacerdoteRepository.deleteById(id);
    }

    
    /**
     * Create a new Sacerdote along with a new Persona in a single transaction
     */
   
    
    
    

    
}
