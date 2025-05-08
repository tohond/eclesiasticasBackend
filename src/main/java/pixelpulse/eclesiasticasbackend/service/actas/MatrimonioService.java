package pixelpulse.eclesiasticasbackend.service.actas;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
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


    public MatrimonioDTO createMatrimonio(MatrimonioDTO matrimonioDTO) {
    	Matrimonio matrimonio = mapper.fromDto(matrimonioDTO);
    	Matrimonio savedConfirmacion = matrimonioRepository.save(matrimonio);
        return mapper.toDto(savedConfirmacion);
    }



    /*
    /**
     * Crea un nuevo matrimonio
     * @param matrimonioDTO Datos del matrimonio
     * @return Matrimonio creado
     */
    /*
    @Transactional
    public MatrimonioDTO createMatrimonio(MatrimonioDTO matrimonioDTO) {
        // Crear o recuperar las personas involucradas
        Persona personaA = getOrCreatePersona(matrimonioDTO.getPersonaA());
        Persona personaB = getOrCreatePersona(matrimonioDTO.getPersonaB());
        Persona padrino = getOrCreatePersona(matrimonioDTO.getIdPadrino());
        Persona madrina = getOrCreatePersona(matrimonioDTO.getIdMadrina());

        // Crear el acta asociada
        Acta acta = new Acta();
        acta.setTipo("Matrimonio");
        // Aquí se pueden establecer más propiedades del acta
        acta = actaRepository.save(acta);

        // Crear el matrimonio
        Matrimonio matrimonio = new Matrimonio();
        matrimonio.setActa(acta);
        matrimonio.setPersonaA(personaA);
        matrimonio.setPersonaB(personaB);
        matrimonio.setIdPadrino(padrino);
        matrimonio.setIdMadrina(madrina);

        Matrimonio savedMatrimonio = matrimonioRepository.save(matrimonio);
        return mapper.toDto(savedMatrimonio);
    }

     */


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
