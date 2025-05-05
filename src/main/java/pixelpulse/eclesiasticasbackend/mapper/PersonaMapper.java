package pixelpulse.eclesiasticasbackend.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.dto.PersonaDTO;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Persona;

@Component
public class PersonaMapper {
	 @Autowired
	    private ModelMapper modelMapper;
	 	
	 
	
	 
	 
	    public PersonaDTO toDto(Persona persona) {
	    	PersonaDTO p = modelMapper.map(persona, PersonaDTO.class);
	    	return p;
	    }

	    public List<PersonaDTO> toDtoList(List<Persona> personas) {
	        return personas.stream()
	                .map(this::toDto)
	                .collect(Collectors.toList());
	    }


	    public Persona fromDto(PersonaDTO personaDTO) {
	        return modelMapper.map(personaDTO, Persona.class);
	    }
}
