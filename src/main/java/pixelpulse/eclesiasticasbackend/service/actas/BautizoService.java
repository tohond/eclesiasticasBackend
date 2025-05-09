package pixelpulse.eclesiasticasbackend.service.actas;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pixelpulse.eclesiasticasbackend.controller.ActaExportController;
import pixelpulse.eclesiasticasbackend.dto.BautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.MatrimonioDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createBautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createMatrimonioDTO;
import pixelpulse.eclesiasticasbackend.mapper.BautizoMapper;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Bautizo;
import pixelpulse.eclesiasticasbackend.model.Matrimonio;
import pixelpulse.eclesiasticasbackend.model.Persona;
import pixelpulse.eclesiasticasbackend.model.Sacerdote;
import pixelpulse.eclesiasticasbackend.repository.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BautizoService {

    private final ActaExportController actaExportController;
	
	 @Autowired
    private final SacerdoteRepository sacerdoteRepository;

    @Autowired
    private BautizoRepository bautizoRepository;
    
    @Autowired
    private BautizoMapper bautizoMapper;

    BautizoService(SacerdoteRepository sacerdoteRepository, ActaExportController actaExportController) {
        this.sacerdoteRepository = sacerdoteRepository;
        this.actaExportController = actaExportController;
    }

    public List<BautizoDTO> getAllBautizos() {
        return bautizoMapper.toDtoList(bautizoRepository.findAll());
    }

    public BautizoDTO getBautizoById(UUID id) {
        Bautizo bautizo = bautizoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bautizo no encontrado con ID: " + id));
        return bautizoMapper.toDto(bautizo);
    }

    public BautizoDTO createMatrimonio(createBautizoDTO dto) {
    	Acta a = new Acta(null, dto.getNumeroActa(), dto.getFolio(), dto.getLibro(), dto.getFecha(), dto.getNotas(), dto.getTipo());
		
		Persona padre = new Persona();
		
		padre.setNombre(dto.getNombresPadre());
		Persona madre = new Persona();
		madre.setNombre(dto.getNombresMadre());
		
		Persona bautizado = new Persona();
		bautizado.setMadre(madre);
		bautizado.setPadre(padre);
		bautizado.setCiudadnacimiento(dto.getCiudadNacimiento());
		bautizado.setNombre(dto.getNombre1()+" "+dto.getNombre2()+" "+dto.getNombre3()+" "+dto.getNombre4()+" ");
		
		
		
		Sacerdote doyfe = new Sacerdote();
		Sacerdote s = new Sacerdote();
		
    	if(dto.getIdDoyFe()==null||dto.getIdDoyFe().isBlank() ) {
    		
    		Persona p1 = new Persona();
    		p1.setNombre(dto.getNombresDoyFe());
    		doyfe.setPersona(p1);
    		
    	}
    	else {
    		doyfe=sacerdoteRepository.findSacerdoteById(UUID.fromString(dto.getIdDoyFe()));
    	}
    	
    	if(dto.getIdSacerdote()==null||dto.getIdSacerdote().isBlank() ) {
    		
    		Persona p2 = new Persona();
    		p2.setNombre(dto.getNombresSacerdote());
    		s.setPersona(p2);
    		
    	}
    	else {
    		s=sacerdoteRepository.findSacerdoteById(UUID.fromString(dto.getIdSacerdote()));
    	}
    	
    	
    	Bautizo b = new Bautizo();
    	b.setIdBautizado(bautizado);
    	b.setIdDoyfe(doyfe);
    	b.setIdSacerdote(s);
    	b.setActa(a);
    	b.setAbueloPaterno(dto.getAbueloPaterno());
    	b.setAbueloMaterno(dto.getAbueloMaterno());
    	b.setAbuelaMaterna(dto.getAbuelaMaterna());
    	b.setAbuelaPaterna(dto.getAbuelaPaterna());
    	
    	
    	
    	
    	
    	
    	Bautizo savedbautizo = bautizoRepository.save(b);
        return bautizoMapper.toDto(savedbautizo);
    }

    public BautizoDTO updateBautizo(UUID id, BautizoDTO bautizoDTO) {
        if (!bautizoRepository.existsById(id)) {
            throw new EntityNotFoundException("Bautizo no encontrado con ID: " + id);
        }
        
        Bautizo bautizo = bautizoMapper.fromDto(bautizoDTO);

        Bautizo updatedBautizo = bautizoRepository.save(bautizo);
        return bautizoMapper.toDto(updatedBautizo);
    }

    public void deleteBautizo(UUID id) {
        if (!bautizoRepository.existsById(id)) {
            throw new EntityNotFoundException("Bautizo no encontrado con ID: " + id);
        }
        bautizoRepository.deleteById(id);
    }

    

   
}
