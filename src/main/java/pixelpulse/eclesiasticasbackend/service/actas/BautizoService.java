package pixelpulse.eclesiasticasbackend.service.actas;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pixelpulse.eclesiasticasbackend.dto.BautizoDTO;
import pixelpulse.eclesiasticasbackend.mapper.BautizoMapper;
import pixelpulse.eclesiasticasbackend.model.Bautizo;
import pixelpulse.eclesiasticasbackend.repository.ActaRepository;
import pixelpulse.eclesiasticasbackend.repository.BautizoRepository;
import pixelpulse.eclesiasticasbackend.repository.ParroquiaRepository;
import pixelpulse.eclesiasticasbackend.repository.PersonaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BautizoService {

    @Autowired
    private BautizoRepository bautizoRepository;
    
    @Autowired
    private BautizoMapper bautizoMapper;

    public List<BautizoDTO> getAllBautizos() {
        return bautizoMapper.toDtoList(bautizoRepository.findAll());
    }

    public BautizoDTO getBautizoById(UUID id) {
        Bautizo bautizo = bautizoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bautizo no encontrado con ID: " + id));
        return bautizoMapper.toDto(bautizo);
    }

    public BautizoDTO createBautizo(BautizoDTO bautizoDTO) {
        Bautizo bautizo = bautizoMapper.fromDto(bautizoDTO);
        Bautizo savedBautizo = bautizoRepository.save(bautizo);
        return bautizoMapper.toDto(savedBautizo);
    }

    public BautizoDTO updateBautizo(UUID id, BautizoDTO bautizoDTO) {
        if (!bautizoRepository.existsById(id)) {
            throw new EntityNotFoundException("Bautizo no encontrado con ID: " + id);
        }
        
        Bautizo bautizo = bautizoMapper.fromDto(bautizoDTO);

        Bautizo updatedBautizo = bautizoRepository.save(bautizo);
        return bautizoMapper.toDto(updatedBautizo);
    }

    public void deleteBautizo(UUID id) {
        if (!bautizoRepository.existsById(id)) {
            throw new EntityNotFoundException("Bautizo no encontrado con ID: " + id);
        }
        bautizoRepository.deleteById(id);
    }

    

   
}
