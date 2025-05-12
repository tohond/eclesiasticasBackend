package pixelpulse.eclesiasticasbackend.mapper;

import pixelpulse.eclesiasticasbackend.dto.PersonaDTO;
import pixelpulse.eclesiasticasbackend.dto.SacerdoteDTO;
import pixelpulse.eclesiasticasbackend.model.Persona;
import pixelpulse.eclesiasticasbackend.model.Sacerdote;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public class SacerdoteMapper {

	

	@Autowired
    private ModelMapper modelMapper;
 	
 

 
 
    public SacerdoteDTO toDto(Sacerdote persona) {
    	SacerdoteDTO dto = new SacerdoteDTO();
    	dto.setId(persona.getId());
    	dto.setNombre(persona.getPersona().getNombre1());
    	return dto;
    }

    public List<SacerdoteDTO> toDtoList(List<Sacerdote> personas) {
        return personas.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public Sacerdote fromDto(SacerdoteDTO SacerdoteDTO) {
        return modelMapper.map(SacerdoteDTO, Sacerdote.class);
    }
}
