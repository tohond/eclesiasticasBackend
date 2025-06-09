package pixelpulse.eclesiasticasbackend.service.actas;

import pixelpulse.eclesiasticasbackend.dto.BautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.SacerdoteDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createBautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.edit.EditConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.mapper.ConfirmacionMapper;
import pixelpulse.eclesiasticasbackend.mapper.MapStructMapper;
import pixelpulse.eclesiasticasbackend.mapper.PersonaMapper;
import pixelpulse.eclesiasticasbackend.model.*;
import pixelpulse.eclesiasticasbackend.repository.*;
import pixelpulse.eclesiasticasbackend.service.personas.PersonaService;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

	public ConfirmacionService(ConfirmacionRepository confirmacionRepository, ActaRepository actaRepository,
			ParroquiaRepository parroquiaRepository, PersonaRepository personaRepository,
			SacerdoteRepository sacerdoteRepository, PersonaService pService, PersonaMapper pMapper,
			ConfirmacionMapper mapper) {
		super();
		this.confirmacionRepository = confirmacionRepository;
		this.actaRepository = actaRepository;
		this.parroquiaRepository = parroquiaRepository;
		this.personaRepository = personaRepository;
		this.sacerdoteRepository = sacerdoteRepository;
		this.pService = pService;
		this.pMapper = pMapper;
		this.mapper = mapper;
	}

	public List<ConfirmacionDTO> getAllConfirmaciones() {
		List<Confirmacion> confs = confirmacionRepository.findAll();
		List<ConfirmacionDTO> dtos = new ArrayList<>();
		for (Confirmacion c : confs) {
			ConfirmacionDTO dto = MapStructMapper.INSTANCE.confirmacionToConfirmacionDTO(c);
			dtos.add(dto);
		}
		return dtos;
	}
	/*
	 * public ConfirmacionDTO getConfirmacionByNombre(String nombre) { Persona p =
	 * pMapper.fromDto(pService.getPersonaByNombre(nombre)); Confirmacion
	 * confirmacion = confirmacionRepository.findByPersona(p); return
	 * mapper.toDto(confirmacion); }
	 */

	public Confirmacion createConfirmacion(createConfirmacionDTO dto) {
		Acta a = new Acta(null, dto.getNumeroActa(), dto.getFolio(), dto.getLibro(), dto.getFecha(),
				dto.getNotaMarginal(), dto.getTipo());

		Persona padre = new Persona();

		padre.setNombre1(dto.getNombresPadre());
		Persona madre = new Persona();
		madre.setNombre1(dto.getNombresMadre());
		Persona madrina = new Persona();

		madrina.setNombre1(dto.getNombresmadrina());

		Persona padrino = new Persona();
		padrino.setNombre1(dto.getNombrespadrino());
		Persona confirmado = new Persona();
		confirmado.setMadre(madre);
		confirmado.setPadre(padre);
		confirmado.setCiudadnacimiento(dto.getCiudadNacimiento());
		confirmado.setNombre1(dto.getNombre1());
		confirmado.setNombre2(dto.getNombre2());
		confirmado.setApellido1(dto.getApellido1());
		confirmado.setApellido2(dto.getApellido2());
		Sacerdote monsr = new Sacerdote();
		// s, el parroco de la confirmacion
		Sacerdote s = new Sacerdote();
		Sacerdote doyfe = new Sacerdote();

		Parroquia parroquia = null;
		if (dto.getIdParroquia() != null) {
			parroquia = new Parroquia();
			parroquia.setId(Long.valueOf(dto.getIdParroquia()));
		}

		if (dto.getIdDoyFe() == null || dto.getIdDoyFe().isBlank()) {

			Persona p1 = new Persona();
			p1.setNombre1(dto.getNombresDoyFe());
			doyfe.setPersona(p1);

		} else {
			doyfe = sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdDoyFe()));
		}

		if (dto.getIdSacerdote() == null || dto.getIdSacerdote().isBlank()) {

			Persona p2 = new Persona();
			p2.setNombre1(dto.getNombresSacerdote());
			s.setPersona(p2);

		} else {
			s = sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdSacerdote()));
		}

		if (dto.getIdmonsr() == null || dto.getIdmonsr().isBlank()) {

			Persona p3 = new Persona();
			p3.setNombre1(dto.getNombresSacerdote());
			monsr.setPersona(p3);

		} else {
			monsr = sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdSacerdote()));
		}

		Confirmacion b = new Confirmacion();
		b.setConfirmante(confirmado);
		b.setParroquia(parroquia);
		b.setDoyfe(doyfe);
		b.setSacerdote(s);
		b.setMonsr(monsr);
		b.setActa(a);
		b.setMadrina(madrina);
		b.setPadrino(padrino);

		Confirmacion savedconf = confirmacionRepository.save(b);
		return savedconf;
	}

	public Confirmacion updateConfirmacion(Long id, EditConfirmacionDTO dto) {
	    // Buscar la confirmación existente
	    Confirmacion existingConfirmacion = confirmacionRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Confirmación no encontrada con id: " + id));

	    // Actualizar datos del acta
	    Acta acta = existingConfirmacion.getActa();
	    acta.setNumeroActa(dto.getNumeroActa());
	    acta.setFolio(dto.getFolio());
	    acta.setLibro(dto.getLibro());
	    acta.setFecha(dto.getFecha());
	    acta.setNotas(dto.getNotaMarginal());
	    acta.setTipo(dto.getTipo());

	    // Actualizar datos del padre
	    Persona padre = existingConfirmacion.getConfirmante().getPadre();
	    padre.setNombre1(dto.getNombresPadre());

	    // Actualizar datos de la madre
	    Persona madre = existingConfirmacion.getConfirmante().getMadre();
	    madre.setNombre1(dto.getNombresMadre());

	    // Actualizar datos de la madrina
	    Persona madrina = existingConfirmacion.getMadrina();
	    madrina.setNombre1(dto.getNombresmadrina());

	    // Actualizar datos del padrino
	    Persona padrino = existingConfirmacion.getPadrino();
	    padrino.setNombre1(dto.getNombrespadrino());

	    // Actualizar datos del confirmado
	    Persona confirmado = existingConfirmacion.getConfirmante();
	    confirmado.setCiudadnacimiento(dto.getCiudadNacimiento());
	    confirmado.setNombre1(dto.getNombre1());
	    confirmado.setNombre2(dto.getNombre2());
	    confirmado.setApellido1(dto.getApellido1());
	    confirmado.setApellido2(dto.getApellido2());

	    // Actualizar parroquia
	    if (dto.getIdParroquia() != null) {
	        Parroquia parroquia = new Parroquia();
	        parroquia.setId(Long.valueOf(dto.getIdParroquia()));
	        existingConfirmacion.setParroquia(parroquia);
	    } else {
	        existingConfirmacion.setParroquia(null);
	    }

	    // Actualizar sacerdote que da fe
	    Sacerdote doyfe;
	    if (dto.getIdDoyFe() == null || dto.getIdDoyFe().isBlank()) {
	        if (existingConfirmacion.getDoyfe() == null) {
	            doyfe = new Sacerdote();
	            Persona p1 = new Persona();
	            p1.setNombre1(dto.getNombresDoyFe());
	            doyfe.setPersona(p1);
	        } else {
	            doyfe = existingConfirmacion.getDoyfe();
	            doyfe.getPersona().setNombre1(dto.getNombresDoyFe());
	        }
	    } else {
	        doyfe = sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdDoyFe()));
	    }

	    // Actualizar sacerdote
	    Sacerdote sacerdote;
	    if (dto.getIdSacerdote() == null || dto.getIdSacerdote().isBlank()) {
	        if (existingConfirmacion.getSacerdote() == null) {
	            sacerdote = new Sacerdote();
	            Persona p2 = new Persona();
	            p2.setNombre1(dto.getNombresSacerdote());
	            sacerdote.setPersona(p2);
	        } else {
	            sacerdote = existingConfirmacion.getSacerdote();
	            sacerdote.getPersona().setNombre1(dto.getNombresSacerdote());
	        }
	    } else {
	        sacerdote = sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdSacerdote()));
	    }

	    // Actualizar monseñor
	    Sacerdote monsr;
	    if (dto.getIdmonsr() == null || dto.getIdmonsr().isBlank()) {
	        if (existingConfirmacion.getMonsr() == null) {
	            monsr = new Sacerdote();
	            Persona p3 = new Persona();
	            p3.setNombre1(dto.getNombresmonsr());
	            monsr.setPersona(p3);
	        } else {
	            monsr = existingConfirmacion.getMonsr();
	            monsr.getPersona().setNombre1(dto.getNombresmonsr());
	        }
	    } else {
	        monsr = sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdmonsr()));
	    }

	    // Actualizar campos de la confirmación
	    existingConfirmacion.setDoyfe(doyfe);
	    existingConfirmacion.setSacerdote(sacerdote);
	    existingConfirmacion.setMonsr(monsr);
	    existingConfirmacion.setMadrina(madrina);
	    existingConfirmacion.setPadrino(padrino);

	    // Guardar los cambios
	    return confirmacionRepository.save(existingConfirmacion);
	}

	public void deleteConfirmacion(String id) {
		if (!confirmacionRepository.existsById(Long.valueOf(id))) {
			throw new EntityNotFoundException("Confirmación no encontrada con ID: " + id);
		}
		confirmacionRepository.deleteById(Long.valueOf(id));
	}

	public ConfirmacionDTO getConfirmacionById(String id) {
		// TODO Auto-generated method stub
		Acta acta = actaRepository.findById(Long.valueOf(id)).orElseThrow(() -> new EntityNotFoundException("Bautizo no encontrado con ID: " + id) );
        ;
		Confirmacion c = confirmacionRepository.findByActa(acta);
		ConfirmacionDTO dto = MapStructMapper.INSTANCE.confirmacionToConfirmacionDTO(c);
			
		return dto;
				
	}

}
