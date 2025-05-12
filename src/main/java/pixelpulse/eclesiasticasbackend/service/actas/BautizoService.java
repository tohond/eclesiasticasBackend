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

    public BautizoDTO getBautizoById(Long id) {
        Bautizo bautizo = bautizoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bautizo no encontrado con ID: " + id));
        return bautizoMapper.toDto(bautizo);
    }

    public Bautizo createBautizo(createBautizoDTO dto) {
    	Acta a = new Acta(null, dto.getNumeroActa(), dto.getFolio(), dto.getLibro(), dto.getFecha(), dto.getNotaMarginal(), dto.getTipo());
		
		Persona padre = new Persona();
		
		padre.setNombre1(dto.getNombresPadre());
		Persona madre = new Persona();
		madre.setNombre1(dto.getNombresMadre());
		
		Persona bautizado = new Persona();
		bautizado.setMadre(madre);
		bautizado.setPadre(padre);
		bautizado.setCiudadnacimiento(dto.getCiudadNacimiento());
		bautizado.setNombre1(dto.getNombre1());
		bautizado.setNombre2(dto.getNombre2());
		bautizado.setApellido1(dto.getApellido1());
		bautizado.setApellido2(dto.getApellido2());
		Sacerdote doyfe = new Sacerdote();
		Sacerdote s = new Sacerdote();
		
    	if(dto.getIdDoyFe()==null||dto.getIdDoyFe().isBlank() ) {
    		
    		Persona p1 = new Persona();
    		p1.setNombre1(dto.getNombresDoyFe());
    		doyfe.setPersona(p1);
    		
    	}
    	else {
    		doyfe=sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdDoyFe()));
    	}
    	
    	if(dto.getIdSacerdote()==null||dto.getIdSacerdote().isBlank() ) {
    		
    		Persona p2 = new Persona();
    		p2.setNombre1(dto.getNombresSacerdote());
    		s.setPersona(p2);
    		
    	}
    	else {
    		s=sacerdoteRepository.findSacerdoteById(Long.valueOf(dto.getIdSacerdote()));
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
        return savedbautizo;
    }

    public BautizoDTO updateBautizo(Long id, BautizoDTO bautizoDTO) {
        if (!bautizoRepository.existsById(id)) {
            throw new EntityNotFoundException("Bautizo no encontrado con ID: " + id);
        }
        
        Bautizo bautizo = bautizoMapper.fromDto(bautizoDTO);

        Bautizo updatedBautizo = bautizoRepository.save(bautizo);
        return bautizoMapper.toDto(updatedBautizo);
    }

    public void deleteBautizo(Long id) {
        if (!bautizoRepository.existsById(id)) {
            throw new EntityNotFoundException("Bautizo no encontrado con ID: " + id);
        }
        bautizoRepository.deleteById(id);
    }

    

   
}
