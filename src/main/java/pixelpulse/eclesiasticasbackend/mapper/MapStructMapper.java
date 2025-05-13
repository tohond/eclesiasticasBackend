package pixelpulse.eclesiasticasbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.dto.BautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.MatrimonioDTO;
import pixelpulse.eclesiasticasbackend.dto.PersonaDTO;
import pixelpulse.eclesiasticasbackend.dto.SacerdoteDTO;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Bautizo;
import pixelpulse.eclesiasticasbackend.model.Confirmacion;
import pixelpulse.eclesiasticasbackend.model.Matrimonio;
import pixelpulse.eclesiasticasbackend.model.Persona;
import pixelpulse.eclesiasticasbackend.model.Sacerdote;

@Mapper
public interface MapStructMapper {
	
	MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);
    
	@Mapping(source = "padre.nombre1", target = "nombresPadre")
    @Mapping(source = "madre.nombre1", target = "nombresMadre")

	PersonaDTO personaToPersonaDTO(Persona persona);
	
	@Mapping(source = "persona.nombre1", target = "nombre")
	SacerdoteDTO sacerdoteToSacerdoteDTO(Sacerdote sacerdote);
	
	ActaDTO actaToActaDTO (Acta acta);
	
	@Mapping(source = "conf.acta", target = "idActa")
	@Mapping(source = "conf.confirmante", target = "idConfirmante")
	@Mapping(source = "conf.sacerdote", target = "idSacerdote")
	@Mapping(source = "conf.monsr", target = "idMonsr")
	@Mapping(source = "conf.doyfe", target = "idDoyfe")
	@Mapping(source = "conf.padrino", target = "idPadrino")
	@Mapping(source = "conf.madrina", target = "idMadrina")
	ConfirmacionDTO confirmacionToConfirmacionDTO (Confirmacion conf);
	
	
	@Mapping(source = "bautizo.acta", target = "idActa")
	@Mapping(source = "bautizo.idBautizado", target = "idBautizado")
	@Mapping(source = "bautizo.idSacerdote", target = "idSacerdote")
	@Mapping(source = "bautizo.idDoyfe", target = "idDoyfe")
	BautizoDTO bautizoToBautizoDTO (Bautizo bautizo);
	
	@Mapping(source = "mat.acta", target = "idActa")
	@Mapping(source = "mat.personaA", target = "personaA")
	@Mapping(source = "mat.personaB", target = "personaB")
	@Mapping(source = "mat.idpadrea", target = "idpadrea")
	@Mapping(source = "mat.idmadrea", target = "idmadrea")
	@Mapping(source = "mat.idpadreb", target = "idpadreb")
	@Mapping(source = "mat.idmadreb", target = "idmadreb")
	MatrimonioDTO matrimoniooToMatrimonioDTO (Matrimonio mat);
}
