package pixelpulse.eclesiasticasbackend.service.actas;

import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.model.*;
import pixelpulse.eclesiasticasbackend.repository.*;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<ConfirmacionDTO> getAllConfirmaciones() {
        return confirmacionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ConfirmacionDTO getConfirmacionById(String id) {
        Confirmacion confirmacion = confirmacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Confirmación no encontrada con ID: " + id));
        return convertToDTO(confirmacion);
    }

    public ConfirmacionDTO createConfirmacion(ConfirmacionDTO confirmacionDTO) {
        Confirmacion confirmacion = convertToEntity(confirmacionDTO);
        Confirmacion savedConfirmacion = confirmacionRepository.save(confirmacion);
        return convertToDTO(savedConfirmacion);
    }

    public ConfirmacionDTO updateConfirmacion(String id, ConfirmacionDTO confirmacionDTO) {
        if (!confirmacionRepository.existsById(id)) {
            throw new EntityNotFoundException("Confirmación no encontrada con ID: " + id);
        }
        
        Confirmacion confirmacion = convertToEntity(confirmacionDTO);
        confirmacion.setId(id);
        Confirmacion updatedConfirmacion = confirmacionRepository.save(confirmacion);
        return convertToDTO(updatedConfirmacion);
    }

    public void deleteConfirmacion(String id) {
        if (!confirmacionRepository.existsById(id)) {
            throw new EntityNotFoundException("Confirmación no encontrada con ID: " + id);
        }
        confirmacionRepository.deleteById(id);
    }

    private ConfirmacionDTO convertToDTO(Confirmacion confirmacion) {
        ConfirmacionDTO dto = new ConfirmacionDTO();
        dto.setId(confirmacion.getId());
        
        if (confirmacion.getActa() != null) {
            dto.setIdActa(confirmacion.getActa().getId());
        }
        
        if (confirmacion.getParroquia() != null) {
            dto.setIdParroquia(confirmacion.getParroquia().getId());
        }
        
        dto.setIdConfirmante(confirmacion.getIdConfirmante());
        dto.setIdMonsr(confirmacion.getIdMonsr());
        
        if (confirmacion.getSacerdote() != null) {
            dto.setIdSacerdote(confirmacion.getSacerdote().getId());
        }
        
        if (confirmacion.getDoyfe() != null) {
            dto.setIdDoyfe(confirmacion.getDoyfe().getId());
        }
        
        if (confirmacion.getPadrino() != null) {
            dto.setIdPadrino(confirmacion.getPadrino().getId());
        }
        
        if (confirmacion.getMadrina() != null) {
            dto.setIdMadrina(confirmacion.getMadrina().getId());
        }
        
        return dto;
    }

    private Confirmacion convertToEntity(ConfirmacionDTO dto) {
        Confirmacion confirmacion = new Confirmacion();
        confirmacion.setId(dto.getId());
        
        if (dto.getIdActa() != null) {
            Acta acta = actaRepository.findById(dto.getIdActa())
                    .orElseThrow(() -> new EntityNotFoundException("Acta no encontrada con ID: " + dto.getIdActa()));
            confirmacion.setActa(acta);
        }
        
        if (dto.getIdParroquia() != null) {
            Parroquia parroquia = parroquiaRepository.findById(dto.getIdParroquia())
                    .orElseThrow(() -> new EntityNotFoundException("Parroquia no encontrada con ID: " + dto.getIdParroquia()));
            confirmacion.setParroquia(parroquia);
        }
        
        confirmacion.setIdConfirmante(dto.getIdConfirmante());
        confirmacion.setIdMonsr(dto.getIdMonsr());
        
        if (dto.getIdSacerdote() != null) {
            Sacerdote sacerdote = sacerdoteRepository.findById(dto.getIdSacerdote())
                    .orElseThrow(() -> new EntityNotFoundException("Sacerdote no encontrado con ID: " + dto.getIdSacerdote()));
            confirmacion.setSacerdote(sacerdote);
        }
        
        if (dto.getIdDoyfe() != null) {
            Persona doyfe = personaRepository.findById(dto.getIdDoyfe())
                    .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + dto.getIdDoyfe()));
            confirmacion.setDoyfe(doyfe);
        }
        
        if (dto.getIdPadrino() != null) {
            Persona padrino = personaRepository.findById(dto.getIdPadrino())
                    .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + dto.getIdPadrino()));
            confirmacion.setPadrino(padrino);
        }
        
        if (dto.getIdMadrina() != null) {
            Persona madrina = personaRepository.findById(dto.getIdMadrina())
                    .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + dto.getIdMadrina()));
            confirmacion.setMadrina(madrina);
        }
        
        return confirmacion;
    }
}
