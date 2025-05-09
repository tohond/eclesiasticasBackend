package pixelpulse.eclesiasticasbackend.mapper;

import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.MatrimonioDTO;
import pixelpulse.eclesiasticasbackend.model.Confirmacion;
import pixelpulse.eclesiasticasbackend.model.Matrimonio;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public class MatrimonioMapper {
	@Autowired
    private ModelMapper modelMapper;
 	
 

	 
    public MatrimonioDTO toDto(Matrimonio acta) {
    	MatrimonioDTO a = modelMapper.map(acta, MatrimonioDTO.class);
    	return a;
    }

    public List<MatrimonioDTO> toDtoList(List<Matrimonio> actas) {
        return actas.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public Matrimonio fromDto(MatrimonioDTO ActaDTO) {
        return modelMapper.map(ActaDTO, Matrimonio.class);
    }
}
