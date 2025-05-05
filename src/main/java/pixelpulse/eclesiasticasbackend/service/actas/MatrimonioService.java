package pixelpulse.eclesiasticasbackend.service.actas;

import pixelpulse.eclesiasticasbackend.dto.MatrimonioDTO;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Matrimonio;
import pixelpulse.eclesiasticasbackend.model.Persona;
import pixelpulse.eclesiasticasbackend.repository.ActaRepository;
import pixelpulse.eclesiasticasbackend.repository.MatrimonioRepository;
import pixelpulse.eclesiasticasbackend.repository.PersonaRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatrimonioService {

    @Autowired
    private MatrimonioRepository matrimonioRepository;
    
    @Autowired
    private ActaRepository actaRepository;
    
    @Autowired
    private PersonaRepository personaRepository;

    public List<MatrimonioDTO> getAllMatrimonios() {
        return matrimonioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MatrimonioDTO getMatrimonioById(String id) {
        Matrimonio matrimonio = matrimonioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Matrimonio no encontrado con ID: " + id));
        return convertToDTO(matrimonio);
    }

    public MatrimonioDTO createMatrimonio(MatrimonioDTO matrimonioDTO) {
        Matrimonio matrimonio = convertToEntity(matrimonioDTO);
        Matrimonio savedMatrimonio = matrimonioRepository.save(matrimonio);
        return convertToDTO(savedMatrimonio);
    }

    public MatrimonioDTO updateMatrimonio(String id, MatrimonioDTO matrimonioDTO) {
        if (!matrimonioRepository.existsById(id)) {
            throw new EntityNotFoundException("Matrimonio no encontrado con ID: " + id);
        }
        
        Matrimonio matrimonio = convertToEntity(matrimonioDTO);
        matrimonio.setId(id);
        Matrimonio updatedMatrimonio = matrimonioRepository.save(matrimonio);
        return convertToDTO(updatedMatrimonio);
    }

    public void deleteMatrimonio(String id) {
        if (!matrimonioRepository.existsById(id)) {
            throw new EntityNotFoundException("Matrimonio no encontrado con ID: " + id);
        }
        matrimonioRepository.deleteById(id);
    }

    private MatrimonioDTO convertToDTO(Matrimonio matrimonio) {
        MatrimonioDTO dto = new MatrimonioDTO();
        dto.setId(matrimonio.getId());
        
        if (matrimonio.getActa() != null) {
            dto.setIdActa(matrimonio.getActa().getId());
        }
        
        if (matrimonio.getMadrina() != null) {
            dto.setIdMadrina(matrimonio.getMadrina().getId());
        }
        
        if (matrimonio.getPadrino() != null) {
            dto.setIdPadrino(matrimonio.getPadrino().getId());
        }
        
        if (matrimonio.getPersonaA() != null) {
            dto.setPersonaA(matrimonio.getPersonaA().getId());
        }
        
        if (matrimonio.getPersonaB() != null) {
            dto.setPersonaB(matrimonio.getPersonaB().getId());
        }
        
        return dto;
    }

    private Matrimonio convertToEntity(MatrimonioDTO dto) {
        Matrimonio matrimonio = new Matrimonio();
        matrimonio.setId(dto.getId());
        
        if (dto.getIdActa() != null) {
            Acta acta = actaRepository.findById(dto.getIdActa())
                    .orElseThrow(() -> new EntityNotFoundException("Acta no encontrada con ID: " + dto.getIdActa()));
            matrimonio.setActa(acta);
        }
        
        if (dto.getIdMadrina() != null) {
            Persona madrina = personaRepository.findById(dto.getIdMadrina())
                    .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + dto.getIdMadrina()));
            matrimonio.setMadrina(madrina);
        }
        
        if (dto.getIdPadrino() != null) {
            Persona padrino = personaRepository.findById(dto.getIdPadrino())
                    .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + dto.getIdPadrino()));
            matrimonio.setPadrino(padrino);
        }
        
        if (dto.getPersonaA() != null) {
            Persona personaA = personaRepository.findById(dto.getPersonaA())
                    .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + dto.getPersonaA()));
            matrimonio.setPersonaA(personaA);
        }
        
        if (dto.getPersonaB() != null) {
            Persona personaB = personaRepository.findById(dto.getPersonaB())
                    .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + dto.getPersonaB()));
            matrimonio.setPersonaB(personaB);
        }
        
        return matrimonio;
    }
}
