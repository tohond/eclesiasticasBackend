package pixelpulse.eclesiasticasbackend.service.actas;

import pixelpulse.eclesiasticasbackend.dto.BautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.MatrimonioDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createMatrimonioDTO;
import pixelpulse.eclesiasticasbackend.mapper.MatrimonioMapper;
import pixelpulse.eclesiasticasbackend.mapper.PersonaMapper;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Bautizo;
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

    

    public MatrimonioService(SacerdoteRepository sacerdoteRepository, MatrimonioRepository matrimonioRepository,
			ActaRepository actaRepository, PersonaRepository personaRepository, PersonaService pService,
			PersonaMapper pMapper, MatrimonioMapper mapper) {
		super();
		this.sacerdoteRepository = sacerdoteRepository;
		this.matrimonioRepository = matrimonioRepository;
		this.actaRepository = actaRepository;
		this.personaRepository = personaRepository;
		this.pService = pService;
		this.pMapper = pMapper;
		this.mapper = mapper;
	}


	public List<MatrimonioDTO> getAllMatrimonios() {
        return mapper.toDtoList(matrimonioRepository.findAll());
        		
    }

   
    public MatrimonioDTO getMatrimonioById(Long id) {
    	Acta acta = actaRepository.findById(id)   .orElseThrow(() -> new EntityNotFoundException("Bautizo no encontrado con ID: " + id));
        ;
        Matrimonio bautizo = matrimonioRepository.findByActa(acta);
             
        return mapper.toDto(bautizo);
        
    }
    public MatrimonioDTO createMatrimonio(createMatrimonioDTO dto) {
    	Acta a = new Acta(null, dto.getNumeroActa(), dto.getFolio(), dto.getLibro(), dto.getFecha(), dto.getNotas(), dto.getTipo());
		Sacerdote doyfe = new Sacerdote();
		Sacerdote s = new Sacerdote();
		Persona personaA = new Persona();
		Persona personaB = new Persona();
		
		personaA.setNombre1(dto.getEsposanombre1());
		personaA.setNombre2(dto.getEsposanombre2());
		personaA.setApellido1(dto.getEsposaapellido1());
		personaA.setApellido2(dto.getEsposaapellido2());
		
		personaB.setNombre1(dto.getEsposonombre1());
		personaB.setNombre2(dto.getEsposonombre2());
		personaB.setApellido1(dto.getEsposoapellido1());
		personaB.setApellido2(dto.getEsposoapellido2());
		
		Persona madrePersonaA = new Persona();
		madrePersonaA.setNombre1(dto.getNombresMadreEsposa());
		Persona padrePersonaA = new Persona();
		padrePersonaA.setNombre1(dto.getNombresPadreEsposa());
		
		Persona madrePersonaB = new Persona();
		madrePersonaA.setNombre1(dto.getNombresMadreEsposo());
		Persona padrePersonaB = new Persona();
		padrePersonaA.setNombre1(dto.getNombresPadreEsposo());

		
		
    	if(dto.getIdDoyFe()==null||dto.getIdDoyFe().isBlank() ) {
    		
    		Persona p1 = new Persona();
    		p1.setNombre1(dto.getNombresDoyFe());
    		doyfe.setPersona(p1);
    		
    	}
    	else {
    		doyfe=sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdDoyFe()));
    	}
    	
    	if(dto.getIdSacerdote()==null||dto.getIdSacerdote().isBlank() ) {
    		
    		Persona p2 = new Persona();
    		p2.setNombre1(dto.getNombresSacerdote());
    		s.setPersona(p2);
    		
    	}
    	else {
    		s=sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdSacerdote()));
    	}
    	
    	
    	
    	Matrimonio m = new Matrimonio();
    	m.setActa(a);
    	m.setIdDoyFe(doyfe);
    	m.setPersonaA(personaA);
    	m.setPersonaB(personaB);
    	m.setIdSacerdote(s);
    	m.setIdmadrea(madrePersonaA);
    	m.setIdpadrea(padrePersonaA);
    	m.setIdmadreb(madrePersonaB);
    	m.setIdpadreb(padrePersonaB);
    	m.setTestigo1(dto.getNombrestestigo1());
    	m.setTestigo2(dto.getNombrestestigo2());
    	m.setTestigo3(dto.getNombrestestigo3());
    	m.setTestigo4(dto.getNombrestestigo4());
    	
    	
    	Matrimonio savedMatrimonio = matrimonioRepository.save(m);
        return mapper.toDto(savedMatrimonio);
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
