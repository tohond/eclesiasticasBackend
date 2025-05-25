package pixelpulse.eclesiasticasbackend.service.personas;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;
import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.dto.PersonaDTO;
import pixelpulse.eclesiasticasbackend.mapper.ActaMapper;
import pixelpulse.eclesiasticasbackend.mapper.MapStructMapper;
import pixelpulse.eclesiasticasbackend.mapper.PersonaMapper;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Bautizo;
import pixelpulse.eclesiasticasbackend.model.Confirmacion;
import pixelpulse.eclesiasticasbackend.model.Matrimonio;
import pixelpulse.eclesiasticasbackend.model.Persona;
import pixelpulse.eclesiasticasbackend.repository.ActaRepository;
import pixelpulse.eclesiasticasbackend.repository.BautizoRepository;
import pixelpulse.eclesiasticasbackend.repository.ConfirmacionRepository;
import pixelpulse.eclesiasticasbackend.repository.MatrimonioRepository;
import pixelpulse.eclesiasticasbackend.repository.PersonaRepository;

@Service
public class PersonaService {

    private final ActaMapper actaMapper;

    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaMapper personaMapper;
    @Autowired
    private  MatrimonioRepository matrimonioRepository;
    @Autowired
    private BautizoRepository bautizoRepository;
    @Autowired
    private ConfirmacionRepository confirmacionRepository;

    
    
    
    public PersonaService(PersonaRepository personaRepository, PersonaMapper personaMapper,
			MatrimonioRepository matrimonioRepository, BautizoRepository bautizoRepository,
			ConfirmacionRepository confirmacionRepository, ActaMapper actaMapper) {
		super();
		this.personaRepository = personaRepository;
		this.personaMapper = personaMapper;
		this.matrimonioRepository = matrimonioRepository;
		this.bautizoRepository = bautizoRepository;
		this.confirmacionRepository = confirmacionRepository;
		this.actaMapper = actaMapper;
	}


	public List<PersonaDTO> getAllPersonas() {
		List<Persona> personas = personaRepository.findAll();
		List<PersonaDTO> dtos = new ArrayList<>();
		for (Persona persona : personas) {
			PersonaDTO dto = MapStructMapper.INSTANCE.personaToPersonaDTO(persona);
			dtos.add(dto);
		}
		return dtos;
    }
    
    
    public List<ActaDTO> searchByName(String name) {
        // Find personas by name (case-insensitive, partial match)
        List<Persona> p = personaRepository.findByFullNameContaining(name);
        MapStructMapper mapper = MapStructMapper.INSTANCE;

        // Collect all related records for each persona
        List<ActaDTO> actasByPerson = new ArrayList<>();
        for (Persona persona : p) {
        	 matrimonioRepository.findAllByPersonaInAnyRole(persona).forEach(e -> {
        		 Acta a = e.getActa();
        		 ActaDTO dto=mapper.actaToActaDTO(a);
        		 dto.setNombre1(e.getPersonaA().getNombre1());
        		 dto.setNombre2(e.getPersonaA().getNombre2());
        		 dto.setApellido1(e.getPersonaA().getApellido1());
        		 dto.setApellido2(e.getPersonaA().getApellido2());
        		 actasByPerson.add(dto);
        		 } );
             bautizoRepository.findByIdBautizado(persona).forEach(e -> 
             {
            	 
            	 Acta a = e.getActa();
        		 ActaDTO dto=mapper.actaToActaDTO(a);
        		 dto.setNombre1(e.getIdBautizado().getNombre1());
        		 dto.setNombre2(e.getIdBautizado().getNombre2());
        		 dto.setApellido1(e.getIdBautizado().getApellido1());
        		 dto.setApellido2(e.getIdBautizado().getApellido2());
        		 actasByPerson.add(dto);
             } )  ;
             confirmacionRepository.findAllByPersonaInAnyRole(persona).forEach(e -> {
            	 Acta a = e.getActa();
        		 ActaDTO dto=mapper.actaToActaDTO(a);
        		 dto.setNombre1(e.getConfirmante().getNombre1());
        		 dto.setNombre2(e.getConfirmante().getNombre2());
        		 dto.setApellido1(e.getConfirmante().getApellido1());
        		 dto.setApellido2(e.getConfirmante().getApellido2());
        		 actasByPerson.add(dto);
            	 
            	 
             } );
             
		}
                
                return actasByPerson;
            
    
}
    
    
    public List<ActaDTO> searchByNameAvanzado(String nombre1,String nombre2,String apellido1, String apellido2) {
        // Find personas by name (case-insensitive, partial match)
    	MapStructMapper mapper = MapStructMapper.INSTANCE;
    	
    	List<Persona> p = personaRepository.findPersonaByNombre1AndNombre2AndApellido1AndApellido2
        		(nombre1, nombre2, apellido1, apellido2);


        // Collect all related records for each persona
        List<ActaDTO> actasByPerson = new ArrayList<>();
        for (Persona persona : p) {
       	 matrimonioRepository.findAllByPersonaInAnyRole(persona).forEach(e -> {
       		 Acta a = e.getActa();
       		 ActaDTO dto=mapper.actaToActaDTO(a);
       		 dto.setNombre1(e.getPersonaA().getNombre1());
       		 dto.setNombre2(e.getPersonaA().getNombre2());
       		 dto.setApellido1(e.getPersonaA().getApellido1());
       		 dto.setApellido2(e.getPersonaA().getApellido2());
       		 actasByPerson.add(dto);
       		 } );
            bautizoRepository.findByIdBautizado(persona).forEach(e -> 
            {
           	 
           	 Acta a = e.getActa();
       		 ActaDTO dto=mapper.actaToActaDTO(a);
       		 dto.setNombre1(e.getIdBautizado().getNombre1());
       		 dto.setNombre2(e.getIdBautizado().getNombre2());
       		 dto.setApellido1(e.getIdBautizado().getApellido1());
       		 dto.setApellido2(e.getIdBautizado().getApellido2());
       		 actasByPerson.add(dto);
            } )  ;
            confirmacionRepository.findAllByPersonaInAnyRole(persona).forEach(e -> {
           	 Acta a = e.getActa();
       		 ActaDTO dto=mapper.actaToActaDTO(a);
       		 dto.setNombre1(e.getConfirmante().getNombre1());
       		 dto.setNombre2(e.getConfirmante().getNombre2());
       		 dto.setApellido1(e.getConfirmante().getApellido1());
       		 dto.setApellido2(e.getConfirmante().getApellido2());
       		 actasByPerson.add(dto);
           	 
           	 
            } );
            
		}
                
                return actasByPerson;
            
    
}
    
    public List<Matrimonio> getMatrimoniosForPersona(Persona persona) {
        return matrimonioRepository.findAllByPersonaInAnyRole(persona);
    }

    public List<Bautizo> getBautizosForPersona(Persona persona) {
        return bautizoRepository.findByIdBautizado(persona);
    }

    public List<Confirmacion> getConfirmacionesForPersona(Persona persona) {
        return confirmacionRepository.findAllByPersonaInAnyRole(persona);
    }
    

    public List<PersonaDTO> getPersonaByNombreSimple(String nombre) {
    	List<PersonaDTO> list = new ArrayList<>();
    	List<Persona> personas = personaRepository.findByFullNameContaining(nombre);
    	for (Persona persona : personas) {
    		list.add(personaMapper.toDto(persona)); 
		}
                return list;
    }

    public PersonaDTO createPersona(PersonaDTO personaDTO) {
        Persona persona = personaMapper.fromDto(personaDTO);
        Persona savedPersona = personaRepository.save(persona);
        return  personaMapper.toDto(savedPersona);
    }

    public PersonaDTO updatePersona(String id, PersonaDTO personaDTO) {
        if (!personaRepository.existsById(Long.valueOf(id))) {
            throw new EntityNotFoundException("Persona no encontrada con ID: " + id);
        }
        
        Persona persona = personaMapper.fromDto(personaDTO);
        
        Persona updatedPersona = personaRepository.save(persona);
        return personaMapper.toDto(updatedPersona);
    }

    public void deletePersona(String id) {
    	if (!personaRepository.existsById(Long.valueOf(id))) {
            throw new EntityNotFoundException("Persona no encontrada con ID: " + id);
        }
        personaRepository.deletePersonaById(Long.valueOf(id));
    }

    
    
    public record PersonaSearchResult(
    	    Long personaId,
    	    String personaName,
    	    List<Acta> actas
    	) {
    	    public PersonaSearchResult(Persona persona, List<Acta> actas) {
    	        this(
    	            persona.getId(),
    	            persona.getNombre1(),
    	            actas
    	        );
    	    }
    	}
}
