package pixelpulse.eclesiasticasbackend.service.actas;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pixelpulse.eclesiasticasbackend.EclesiasticasbackendApplication;
import pixelpulse.eclesiasticasbackend.repository.ActaRepository;
import pixelpulse.eclesiasticasbackend.repository.PersonaRepository;
import pixelpulse.eclesiasticasbackend.service.personas.PersonaService;
import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.mapper.ActaMapper;
import pixelpulse.eclesiasticasbackend.mapper.PersonaMapper;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Persona;


@Service
@RequiredArgsConstructor
public class ActaService {

	@Autowired
    private ActaRepository actaRepository;
	private ActaMapper actaMapper;

	private PersonaRepository personaRepository;
	private PersonaMapper personaMapper;


	/**
	 * Obtiene todas las actas
	 * @return Lista de todas las actas
	 */
	public List<ActaDTO> getAllActas() {
		return actaMapper.toDtoList(actaRepository.findAll());
	}
	
	/*
	public List<ActaDTO> getActasByName(String name) {
		Persona p = pMapper.fromDto(pService.getPersonaByNombre(name));
		List<ActaDTO> lista = new ArrayList<>();
        
    }*/

	/**
	 * Obtiene un acta por su ID
	 * @param id ID del acta
	 * @return DTO del acta encontrada
	 * @throws EntityNotFoundException si no se encuentra el acta
	 */
	public ActaDTO getActaById(UUID id) {
		Acta acta = actaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Acta no encontrada con ID: " + id));
		return actaMapper.toDto(acta);
	}

	/**
	 * Busca actas con filtros avanzados
	 * @param params Parámetros de búsqueda (libro, folio, acta, nombre, etc.)
	 * @return Lista de actas que coinciden con los criterios
	 */
	public List<ActaDTO> searchActas(Map<String, String> params) {
		// Implementación básica: buscar por libro, folio y número de acta
		String libro = params.get("libro");
		String folio = params.get("folio");
		String numeroActa = params.get("acta");
		String tipo = params.get("tipo");

		List<Acta> actas = actaRepository.findAll();

		// Aplicar filtros si están presentes
		if (libro != null && !libro.isEmpty()) {
			actas = actas.stream()
					.filter(a -> a.getLibro() != null && a.getLibro().contains(libro))
					.collect(Collectors.toList());
		}

		if (folio != null && !folio.isEmpty()) {
			actas = actas.stream()
					.filter(a -> a.getFolio() != null && a.getFolio().contains(folio))
					.collect(Collectors.toList());
		}

		if (numeroActa != null && !numeroActa.isEmpty()) {
			actas = actas.stream()
					.filter(a -> a.getNumeroActa() != null && a.getNumeroActa().contains(numeroActa))
					.collect(Collectors.toList());
		}

		if (tipo != null && !tipo.isEmpty()) {
			actas = actas.stream()
					.filter(a -> a.getTipo() != null && a.getTipo().equalsIgnoreCase(tipo))
					.collect(Collectors.toList());
		}

		return actaMapper.toDtoList(actas);
	}

	/**
	 * Crea una nueva acta
	 * @param actaDTO Datos del acta
	 * @return Acta creada
	 */
	public ActaDTO createActa(ActaDTO actaDTO) {
		Acta acta = actaMapper.fromDto(actaDTO);
		Acta savedActa = actaRepository.save(acta);
		return actaMapper.toDto(savedActa);
	}

	/**
	 * Actualiza un acta existente
	 * @param id ID del acta a actualizar
	 * @param actaDTO Nuevos datos del acta
	 * @return Acta actualizada
	 * @throws EntityNotFoundException si no se encuentra el acta
	 */
	public ActaDTO updateActa(UUID id, ActaDTO actaDTO) {
		if (!actaRepository.existsById(id)) {
			throw new EntityNotFoundException("Acta no encontrada con ID: " + id);
		}

		Acta acta = actaMapper.fromDto(actaDTO);
		acta.setId(id); // Asegurar que el ID sea el correcto

		Acta updatedActa = actaRepository.save(acta);
		return actaMapper.toDto(updatedActa);
	}

	/**
	 * Elimina un acta
	 * @param id ID del acta a eliminar
	 * @throws EntityNotFoundException si no se encuentra el acta
	 */
	public void deleteActa(UUID id) {
		if (!actaRepository.existsById(id)) {
			throw new EntityNotFoundException("Acta no encontrada con ID: " + id);
		}
		actaRepository.deleteById(id);
	}
    
   
    
    
}



