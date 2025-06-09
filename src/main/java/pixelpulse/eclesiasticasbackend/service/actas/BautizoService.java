package pixelpulse.eclesiasticasbackend.service.actas;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pixelpulse.eclesiasticasbackend.dto.BautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.MatrimonioDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createBautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createMatrimonioDTO;
import pixelpulse.eclesiasticasbackend.dto.edit.EditBautizoDTO;
import pixelpulse.eclesiasticasbackend.mapper.BautizoMapper;
import pixelpulse.eclesiasticasbackend.mapper.MapStructMapper;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Bautizo;
import pixelpulse.eclesiasticasbackend.model.Confirmacion;
import pixelpulse.eclesiasticasbackend.model.Matrimonio;
import pixelpulse.eclesiasticasbackend.model.Persona;
import pixelpulse.eclesiasticasbackend.model.Sacerdote;
import pixelpulse.eclesiasticasbackend.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BautizoService {

   
	
	 @Autowired
    private final SacerdoteRepository sacerdoteRepository;

    @Autowired
    private BautizoRepository bautizoRepository;
    
    @Autowired
    private ActaRepository actaRepository;
    @Autowired
    private BautizoMapper bautizoMapper;

    
    public BautizoService( SacerdoteRepository sacerdoteRepository,
			BautizoRepository bautizoRepository, ActaRepository actaRepository, BautizoMapper bautizoMapper) {
		super();
		
		this.sacerdoteRepository = sacerdoteRepository;
		this.bautizoRepository = bautizoRepository;
		this.actaRepository = actaRepository;
		this.bautizoMapper = bautizoMapper;
	}

	public List<BautizoDTO> getAllBautizos() {
		List<Bautizo> confs = bautizoRepository.findAll();
		List<BautizoDTO> dtos = new ArrayList<>();
		for (Bautizo c : confs) {
			BautizoDTO dto = MapStructMapper.INSTANCE.bautizoToBautizoDTO(c);
			dtos.add(dto);
		}
		return dtos;
    }

    public BautizoDTO getBautizoById(Long id) {
    	Acta acta = actaRepository.findById(id)   .orElseThrow(() -> new EntityNotFoundException("Bautizo no encontrado con ID: " + id));
        ;
        Bautizo bautizo = bautizoRepository.findByActa(acta);
        BautizoDTO dto = MapStructMapper.INSTANCE.bautizoToBautizoDTO(bautizo);
		
                return dto;
        
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
		bautizado.setFechaNacimiento(dto.getFechaNacimiento());
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
    	b.setNombresmadrina(dto.getNombremadrinas());
    	b.setNombrespadrino(dto.getNombrepadrinos());
    	
    	
    	
    	
    	Bautizo savedbautizo = bautizoRepository.save(b);
        return savedbautizo;
    }

    public BautizoDTO updateBautizo(Long id, EditBautizoDTO bautizoDTO) {
        if (!bautizoRepository.existsById(id)) {
            throw new EntityNotFoundException("Bautizo no encontrado con ID: " + id);
        }
        
        Bautizo bautizo = bautizoRepository.findById(id).get();
        Acta a = bautizo.getActa();
        a.setNumeroActa(bautizoDTO.getNumeroActa());
        a.setFolio(bautizoDTO.getFolio());
        a.setLibro(bautizoDTO.getLibro());
        a.setFecha(bautizoDTO.getFecha());
        a.setNotas(bautizoDTO.getNotas());
        
        Persona p = bautizo.getIdBautizado();
        p.setNombre1(bautizoDTO.getNombre1());
        p.setNombre2(bautizoDTO.getNombre2());
        p.setApellido1(bautizoDTO.getApellido1());
        p.setApellido2(bautizoDTO.getApellido2());
        p.setFechaNacimiento(bautizoDTO.getFechaNacimiento());
        p.setCiudadnacimiento(bautizoDTO.getCiudadNacimiento());
        p.getPadre().setNombre1(bautizoDTO.getNombresPadre());
        p.getMadre().setNombre1(bautizoDTO.getNombresMadre());
        
        
        bautizo.setAbueloPaterno(bautizoDTO.getAbueloPaterno());
        bautizo.setAbuelaMaterna(bautizoDTO.getAbuelaMaterna());
        bautizo.setAbueloMaterno(bautizoDTO.getAbueloMaterno());
        bautizo.setAbuelaPaterna(bautizoDTO.getAbuelaPaterna());
        bautizo.setNombresmadrina(bautizoDTO.getNombremadrinas());
        bautizo.setNombrespadrino(bautizoDTO.getNombrepadrinos());
        
        // Actualizar sacerdote que da fe
        Sacerdote doyfe;
        if(bautizoDTO.getIdDoyFe() == null || bautizoDTO.getIdDoyFe().isBlank()) {
            if(bautizo.getIdDoyfe() == null) {
                doyfe = new Sacerdote();
                Persona p1 = new Persona();
                p1.setNombre1(bautizoDTO.getNombresDoyFe());
                doyfe.setPersona(p1);
            } else {
                doyfe = bautizo.getIdDoyfe();
                doyfe.getPersona().setNombre1(bautizoDTO.getNombresDoyFe());
            }
        } else {
            doyfe = sacerdoteRepository.findSacerdoteById(Long.valueOf(bautizoDTO.getIdDoyFe()));
        }

        // Actualizar sacerdote
        Sacerdote sacerdote;
        if(bautizoDTO.getIdSacerdote() == null || bautizoDTO.getIdSacerdote().isBlank()) {
            if(bautizo.getIdSacerdote() == null) {
                sacerdote = new Sacerdote();
                Persona p2 = new Persona();
                p2.setNombre1(bautizoDTO.getNombresSacerdote());
                sacerdote.setPersona(p2);
            } else {
                sacerdote = bautizo.getIdSacerdote();
                sacerdote.getPersona().setNombre1(bautizoDTO.getNombresSacerdote());
            }
        } else {
            sacerdote = sacerdoteRepository.findSacerdoteById(Long.valueOf(bautizoDTO.getIdSacerdote()));
        }
        
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
