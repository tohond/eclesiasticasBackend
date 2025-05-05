package pixelpulse.eclesiasticasbackend.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.model.Acta;

@Component
public class ActaMapper {
	 @Autowired
	    private ModelMapper modelMapper;
	 	
	 
	
	 
	 
	    public ActaDTO toDto(Acta acta) {
	    	ActaDTO a = modelMapper.map(acta, ActaDTO.class);
	    	return a;
	    }

	    public List<ActaDTO> toDtoList(List<Acta> actas) {
	        return actas.stream()
	                .map(this::toDto)
	                .collect(Collectors.toList());
	    }


	    public Acta fromDto(ActaDTO ActaDTO) {
	        return modelMapper.map(ActaDTO, Acta.class);
	    }
}
