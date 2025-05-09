package pixelpulse.eclesiasticasbackend.service.actas;

import pixelpulse.eclesiasticasbackend.dto.BautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createBautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createConfirmacionDTO;
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

    public Confirmacion createBautizo(createConfirmacionDTO dto) {
    	Acta a = new Acta(null, dto.getNumeroActa(), dto.getFolio(), dto.getLibro(), dto.getFecha(), dto.getNotaMarginal(), dto.getTipo());
		
		Persona padre = new Persona();
		
		padre.setNombre(dto.getNombresPadre());
		Persona madre = new Persona();
		madre.setNombre(dto.getNombresMadre());
		Persona madrina = new Persona();
		
		madrina.setNombre(dto.getNombremadrinas());
		
		Persona padrino = new Persona();
		padrino.setNombre(dto.getNombrepadrinos());
		
		
		Persona confirmado = new Persona();
		confirmado.setMadre(madre);
		confirmado.setPadre(padre);
		confirmado.setCiudadnacimiento(dto.getCiudadNacimiento());
		confirmado.setNombre(dto.getNombre1()+" "+dto.getNombre2()+" "+dto.getNombre3()+" "+dto.getNombre4()+" ");
	
		
		
		Sacerdote monsr = new Sacerdote();
		//s, el parroco de la confirmacion
		Sacerdote s = new Sacerdote();
		Sacerdote doyfe = new Sacerdote();
		
    	if(dto.getIdDoyFe()==null||dto.getIdDoyFe().isBlank() ) {
    		
    		Persona p1 = new Persona();
    		p1.setNombre(dto.getNombresDoyFe());
    		doyfe.setPersona(p1);
    		
    	}
    	else {
    		doyfe=sacerdoteRepository.findSacerdoteById(UUID.fromString(dto.getIdDoyFe()));
    	}
    	
    	if(dto.getIdSacerdote()==null||dto.getIdSacerdote().isBlank() ) {
    		
    		Persona p2 = new Persona();
    		p2.setNombre(dto.getNombresSacerdote());
    		s.setPersona(p2);
    		
    	}
    	else {
    		s=sacerdoteRepository.findSacerdoteById(UUID.fromString(dto.getIdSacerdote()));
    	}
    	
    	if(dto.getIdmonsr()==null||dto.getIdmonsr().isBlank() ) {
    		
    		Persona p3 = new Persona();
    		p3.setNombre(dto.getNombresSacerdote());
    		monsr.setPersona(p3);
    		
    	}
    	else {
    		monsr=sacerdoteRepository.findSacerdoteById(UUID.fromString(dto.getIdSacerdote()));
    	}
    	
    	
    	Confirmacion b = new Confirmacion();
    	b.setConfirmante(confirmado);
    	b.setDoyfe(doyfe);
    	b.setSacerdote(s);
    	b.setMonsr(monsr);
    	b.setActa(a);
    	b.setMadrina(madrina);
    	b.setPadrino(padrino);
    	
    	
    	
    	
  
    	Confirmacion savedconf = confirmacionRepository.save(b);
        return savedconf;
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
