package pixelpulse.eclesiasticasbackend.mapper;

import pixelpulse.eclesiasticasbackend.dto.SacerdoteDTO;
import pixelpulse.eclesiasticasbackend.model.Sacerdote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PersonaMapper.class)
public interface SacerdoteMapper {

    @Mapping(source = "persona.id", target = "idPersona")
    SacerdoteDTO toDto(Sacerdote sacerdote);

    @Mapping(source = "idPersona", target = "persona.id")
    Sacerdote toEntity(SacerdoteDTO sacerdoteDTO);
}
