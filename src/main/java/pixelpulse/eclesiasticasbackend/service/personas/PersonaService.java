package pixelpulse.eclesiasticasbackend.service.personas;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;
import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.dto.PersonaDTO;
import pixelpulse.eclesiasticasbackend.mapper.ActaMapper;
import pixelpulse.eclesiasticasbackend.mapper.PersonaMapper;
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
@RequiredArgsConstructor
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;
    private PersonaMapper personaMapper;
    private MatrimonioRepository matrimonioRepository;
    private BautizoRepository bautizoRepository;
    private ConfirmacionRepository confirmacionRepository;

    public List<PersonaDTO> getAllPersonas() {
        return personaMapper.toDtoList(personaRepository.findAll());
    }
    
    /*
    public PersonaSearchResult searchByName(String name) {
        // Find personas by name (case-insensitive, partial match)
        Persona p = personaRepository.findPersonaByNombre(name);


        // Collect all related records for each persona
                List<Matrimonio> matrimonios = matrimonioRepository.findAllByPersonaInAnyRole(p);
                List<Bautizo> bautizos = bautizoRepository.findByIdBautizado(p);
                List<Confirmacion> confirmaciones = confirmacionRepository.findAllByPersonaInAnyRole(p);
                return new PersonaSearchResult(p, matrimonios, bautizos, confirmaciones);
            
    }
     */

    //Busca personas por nombre completo
    public PersonaSearchResult searchByName(String name) {
        // Find personas by name (case-insensitive, partial match)
        Persona p = personaRepository.findPersonaByNombre(name);

        if (p == null) {
            return new PersonaSearchResult(
                    null,
                    "No se encontró ninguna persona con ese nombre",
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>()
            );
        }

        // Collect all related records for each persona
        List<Matrimonio> matrimonios = matrimonioRepository.findAllByPersonaInAnyRole(p);
        List<Bautizo> bautizos = bautizoRepository.findByIdBautizado(p);
        List<Confirmacion> confirmaciones = confirmacionRepository.findAllByPersonaInAnyRole(p);
        return new PersonaSearchResult(p, matrimonios, bautizos, confirmaciones);
    }

    //Busca personas por combinación de nombres y apellidos
    public PersonaSearchResult searchByFullName(
            String primerNombre,
            String segundoNombre,
            String primerApellido,
            String segundoApellido) {

        // Construir el nombre completo para la búsqueda
        StringBuilder nombreBuilder = new StringBuilder();

        if (primerNombre != null && !primerNombre.trim().isEmpty()) {
            nombreBuilder.append(primerNombre.trim());
        }

        if (segundoNombre != null && !segundoNombre.trim().isEmpty()) {
            if (nombreBuilder.length() > 0) {
                nombreBuilder.append(" ");
            }
            nombreBuilder.append(segundoNombre.trim());
        }

        if (primerApellido != null && !primerApellido.trim().isEmpty()) {
            if (nombreBuilder.length() > 0) {
                nombreBuilder.append(" ");
            }
            nombreBuilder.append(primerApellido.trim());
        }

        if (segundoApellido != null && !segundoApellido.trim().isEmpty()) {
            if (nombreBuilder.length() > 0) {
                nombreBuilder.append(" ");
            }
            nombreBuilder.append(segundoApellido.trim());
        }

        String nombreCompleto = nombreBuilder.toString();

        if (nombreCompleto.isEmpty()) {
            return new PersonaSearchResult(
                    null,
                    "Debe proporcionar al menos un nombre o apellido para la búsqueda",
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>()
            );
        }

        // Buscar personas que coincidan con el nombre completo
        List<Persona> personas = personaRepository.findByNombreContaining(nombreCompleto);

        if (personas.isEmpty()) {
            return new PersonaSearchResult(
                    null,
                    "No se encontraron personas con los criterios de búsqueda proporcionados",
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>()
            );
        }

        // Para simplificar, tomamos la primera persona encontrada
        // En una implementación más completa, se podría devolver una lista de resultados
        Persona personaEncontrada = personas.get(0);

        // Recopilar todos los registros relacionados con la persona
        List<Matrimonio> matrimonios = matrimonioRepository.findAllByPersonaInAnyRole(personaEncontrada);
        List<Bautizo> bautizos = bautizoRepository.findByIdBautizado(personaEncontrada);
        List<Confirmacion> confirmaciones = confirmacionRepository.findAllByPersonaInAnyRole(personaEncontrada);

        return new PersonaSearchResult(personaEncontrada, matrimonios, bautizos, confirmaciones);
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


    public PersonaDTO getPersonaByNombre(String nombre) {
        Persona persona = personaRepository.findPersonaByNombre(nombre);
        return personaMapper.toDto(persona);
    }

    public PersonaDTO createPersona(PersonaDTO personaDTO) {
        Persona persona = personaMapper.fromDto(personaDTO);
        Persona savedPersona = personaRepository.save(persona);
        return personaMapper.toDto(savedPersona);
    }

    public PersonaDTO updatePersona(String id, PersonaDTO personaDTO) {
        if (!personaRepository.existsById(UUID.fromString(id))) {
            throw new EntityNotFoundException("Persona no encontrada con ID: " + id);
        }

        Persona persona = personaMapper.fromDto(personaDTO);

        Persona updatedPersona = personaRepository.save(persona);
        return personaMapper.toDto(updatedPersona);
    }

    public void deletePersona(String id) {
        if (!personaRepository.existsById(UUID.fromString(id))) {
            throw new EntityNotFoundException("Persona no encontrada con ID: " + id);
        }
        personaRepository.deletePersonaById(UUID.fromString(id));
    }

    public record PersonaSearchResult(
            UUID personaId,
            String personaName,
            List<Matrimonio> matrimonios,
            List<Bautizo> bautizos,
            List<Confirmacion> confirmaciones
    ) {
        public PersonaSearchResult(Persona persona, List<Matrimonio> matrimonios, List<Bautizo> bautizos, List<Confirmacion> confirmaciones) {
            this(
                    persona.getId(),
                    persona.getNombre(),
                    matrimonios,
                    bautizos,
                    confirmaciones
            );
        }

        public PersonaSearchResult(UUID personaId, String personaName, List<Matrimonio> matrimonios, List<Bautizo> bautizos, List<Confirmacion> confirmaciones) {
            this.personaId = personaId;
            this.personaName = personaName;
            this.matrimonios = matrimonios;
            this.bautizos = bautizos;
            this.confirmaciones = confirmaciones;
        }
    }


}