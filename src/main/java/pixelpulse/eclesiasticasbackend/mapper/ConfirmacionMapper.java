package pixelpulse.eclesiasticasbackend.mapper;

import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Confirmacion;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component

public class ConfirmacionMapper {
	 @Autowired
	    private ModelMapper modelMapper;
	 	
	 
	
	 
	 
	    public ConfirmacionDTO toDto(Confirmacion acta) {
	    	ConfirmacionDTO a = modelMapper.map(acta, ConfirmacionDTO.class);
	    	return a;
	    }

	    public List<ConfirmacionDTO> toDtoList(List<Confirmacion> actas) {
	        return actas.stream()
	                .map(this::toDto)
	                .collect(Collectors.toList());
	    }


	    public Confirmacion fromDto(ConfirmacionDTO ActaDTO) {
	        return modelMapper.map(ActaDTO, Confirmacion.class);
	    }
}
