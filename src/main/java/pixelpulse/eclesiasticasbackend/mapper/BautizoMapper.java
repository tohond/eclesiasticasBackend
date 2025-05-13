package pixelpulse.eclesiasticasbackend.mapper;

import pixelpulse.eclesiasticasbackend.dto.BautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.PersonaDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createBautizoDTO;
import pixelpulse.eclesiasticasbackend.model.Bautizo;
import pixelpulse.eclesiasticasbackend.model.Persona;

import java.beans.JavaBean;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BautizoMapper {

	@Autowired
    private ModelMapper modelMapper;
 	
 

 
    public BautizoDTO toDto(Bautizo Bautizo) {
    	BautizoDTO p = modelMapper.map(Bautizo, BautizoDTO.class);
    	return p;
    }

    public List<BautizoDTO> toDtoList(List<Bautizo> Bautizos) {
        return Bautizos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public Bautizo fromDto(BautizoDTO BautizoDTO) {
        return modelMapper.map(BautizoDTO, Bautizo.class);
    }
	
	
    


    
 
    
    
    
}
