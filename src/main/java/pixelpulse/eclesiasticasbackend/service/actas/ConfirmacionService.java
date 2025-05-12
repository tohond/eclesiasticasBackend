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

	public ConfirmacionDTO updateConfirmacion(String id, ConfirmacionDTO confirmacionDTO) {
		if (!confirmacionRepository.existsById(Long.valueOf(id))) {
			throw new EntityNotFoundException("Confirmación no encontrada con ID: " + id);
		}

		Confirmacion confirmacion = mapper.fromDto(confirmacionDTO);

		Confirmacion updatedConfirmacion = confirmacionRepository.save(confirmacion);
		return mapper.toDto(updatedConfirmacion);
	}

	public void deleteConfirmacion(String id) {
		if (!confirmacionRepository.existsById(Long.valueOf(id))) {
			throw new EntityNotFoundException("Confirmación no encontrada con ID: " + id);
		}
		confirmacionRepository.deleteById(Long.valueOf(id));
	}

	public ConfirmacionDTO getConfirmacionById(String id) {
		// TODO Auto-generated method stub
		Confirmacion c = confirmacionRepository.findById(Long.valueOf(id))  .orElseThrow(() -> new EntityNotFoundException("Bautizo no encontrado con ID: " + id));
        ;
		return mapper.toDto(c);
				
	}

}
