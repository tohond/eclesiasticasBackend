package pixelpulse.eclesiasticasbackend.service.actas;

import pixelpulse.eclesiasticasbackend.dto.BautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.MatrimonioDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createMatrimonioDTO;
import pixelpulse.eclesiasticasbackend.dto.edit.EditMatrimonioDTO;
import pixelpulse.eclesiasticasbackend.mapper.MapStructMapper;
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
        
        MatrimonioDTO dto = MapStructMapper.INSTANCE.matrimoniooToMatrimonioDTO(bautizo);
		
             
        return dto;
        
    }
    public MatrimonioDTO createMatrimonio(createMatrimonioDTO dto) {
    	Acta a = new Acta(null, dto.getNumeroActa(), dto.getFolio(), dto.getLibro(), dto.getFecha(), dto.getNotaMarginal(), dto.getTipo());
		Sacerdote doyfe = new Sacerdote();
		Sacerdote s = new Sacerdote();
		Persona personaA = new Persona();
		Persona personaB = new Persona();
		
		personaA.setNombre1(dto.getEsposanombre1());
		personaA.setNombre2(dto.getEsposanombre2());
		personaA.setApellido1(dto.getEsposaapellido1());
		personaA.setApellido2(dto.getEsposaapellido2());
		personaA.setFechaNacimiento(dto.getFechaNacimientoEsposa());
		personaA.setCiudadnacimiento(dto.getLugarNacimientoEsposa());
		
		
		personaB.setNombre1(dto.getEsposonombre1());
		personaB.setNombre2(dto.getEsposonombre2());
		personaB.setApellido1(dto.getEsposoapellido1());
		personaB.setApellido2(dto.getEsposoapellido2());
		personaB.setFechaNacimiento(dto.getFechaNacimientoEsposo());
		personaB.setCiudadnacimiento(dto.getLugarNacimientoEsposo());
		
		
		Persona madrePersonaA = new Persona();
		madrePersonaA.setNombre1(dto.getNombresMadreEsposa());
		Persona padrePersonaA = new Persona();
		padrePersonaA.setNombre1(dto.getNombresPadreEsposa());
		
		Persona madrePersonaB = new Persona();
		madrePersonaB.setNombre1(dto.getNombresMadreEsposo());
		Persona padrePersonaB = new Persona();
		padrePersonaB.setNombre1(dto.getNombresPadreEsposo());

		personaA.setMadre(madrePersonaA);
		personaA.setPadre(padrePersonaA);
		personaB.setMadre(madrePersonaB);
		personaB.setPadre(padrePersonaB);
		
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
    	
    	m.setTestigo1(dto.getNombrestestigo1());
    	m.setTestigo2(dto.getNombrestestigo2());
    	m.setTestigo3(dto.getNombrestestigo3());
    	m.setTestigo4(dto.getNombrestestigo4());
    	
    	
    	Matrimonio savedMatrimonio = matrimonioRepository.save(m);
        return mapper.toDto(savedMatrimonio);
    }

    public MatrimonioDTO updateMatrimonio(Long id, EditMatrimonioDTO dto) {
        // Buscar el matrimonio existente
        Matrimonio existingMatrimonio = matrimonioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Matrimonio no encontrado con id: " + id));

        // Actualizar datos del acta
        Acta acta = existingMatrimonio.getActa();
        acta.setNumeroActa(dto.getNumeroActa());
        acta.setFolio(dto.getFolio());
        acta.setLibro(dto.getLibro());
        acta.setFecha(dto.getFecha());
        acta.setNotas(dto.getNotaMarginal());
        acta.setTipo(dto.getTipo());

        // Actualizar datos de la esposa (personaA)
        Persona personaA = existingMatrimonio.getPersonaA();
        personaA.setNombre1(dto.getEsposanombre1());
        personaA.setNombre2(dto.getEsposanombre2());
        personaA.setApellido1(dto.getEsposaapellido1());
        personaA.setApellido2(dto.getEsposaapellido2());
        personaA.setFechaNacimiento(dto.getFechaNacimientoEsposa());
        personaA.setCiudadnacimiento(dto.getLugarNacimientoEsposa());

        // Actualizar datos del esposo (personaB)
        Persona personaB = existingMatrimonio.getPersonaB();
        personaB.setNombre1(dto.getEsposonombre1());
        personaB.setNombre2(dto.getEsposonombre2());
        personaB.setApellido1(dto.getEsposoapellido1());
        personaB.setApellido2(dto.getEsposoapellido2());
        personaB.setFechaNacimiento(dto.getFechaNacimientoEsposo());
        personaB.setCiudadnacimiento(dto.getLugarNacimientoEsposo());

        // Actualizar padres de la esposa
        Persona madrePersonaA = personaA.getMadre();
        madrePersonaA.setNombre1(dto.getNombresMadreEsposa());
        Persona padrePersonaA = personaA.getPadre();
        padrePersonaA.setNombre1(dto.getNombresPadreEsposa());

        // Actualizar padres del esposo
        Persona madrePersonaB = personaB.getMadre();
        madrePersonaB.setNombre1(dto.getNombresMadreEsposo());
        Persona padrePersonaB = personaB.getPadre();
        padrePersonaB.setNombre1(dto.getNombresPadreEsposo());

        // Actualizar sacerdote que da fe
        Sacerdote doyfe;
        if(dto.getIdDoyFe() == null || dto.getIdDoyFe().isBlank()) {
            if(existingMatrimonio.getIdDoyFe() == null) {
                doyfe = new Sacerdote();
                Persona p1 = new Persona();
                p1.setNombre1(dto.getNombresDoyFe());
                doyfe.setPersona(p1);
            } else {
                doyfe = existingMatrimonio.getIdDoyFe();
                doyfe.getPersona().setNombre1(dto.getNombresDoyFe());
            }
        } else {
            doyfe = sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdDoyFe()));
        }

        // Actualizar sacerdote
        Sacerdote sacerdote;
        if(dto.getIdSacerdote() == null || dto.getIdSacerdote().isBlank()) {
            if(existingMatrimonio.getIdSacerdote() == null) {
                sacerdote = new Sacerdote();
                Persona p2 = new Persona();
                p2.setNombre1(dto.getNombresSacerdote());
                sacerdote.setPersona(p2);
            } else {
                sacerdote = existingMatrimonio.getIdSacerdote();
                sacerdote.getPersona().setNombre1(dto.getNombresSacerdote());
            }
        } else {
            sacerdote = sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdSacerdote()));
        }

        // Actualizar campos adicionales del matrimonio
        existingMatrimonio.setIdDoyFe(doyfe);
        existingMatrimonio.setIdSacerdote(sacerdote);
        existingMatrimonio.setTestigo1(dto.getNombrestestigo1());
        existingMatrimonio.setTestigo2(dto.getNombrestestigo2());
        existingMatrimonio.setTestigo3(dto.getNombrestestigo3());
        existingMatrimonio.setTestigo4(dto.getNombrestestigo4());

        // Guardar los cambios
        Matrimonio updatedMatrimonio = matrimonioRepository.save(existingMatrimonio);
        return mapper.toDto(updatedMatrimonio);
    }

    public void deleteConfirmacion(String id) {
        if (!matrimonioRepository.existsById(Long.valueOf(id))) {
            throw new EntityNotFoundException("Confirmación no encontrada con ID: " + id);
        }
        matrimonioRepository.deleteById(Long.valueOf(id));
    }
    
}
