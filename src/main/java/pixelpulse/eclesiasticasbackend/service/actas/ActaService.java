package pixelpulse.eclesiasticasbackend.service.actas;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pixelpulse.eclesiasticasbackend.EclesiasticasbackendApplication;
import pixelpulse.eclesiasticasbackend.repository.ActaRepository;
import pixelpulse.eclesiasticasbackend.repository.BautizoRepository;
import pixelpulse.eclesiasticasbackend.repository.ConfirmacionRepository;
import pixelpulse.eclesiasticasbackend.repository.MatrimonioRepository;
import pixelpulse.eclesiasticasbackend.service.personas.PersonaService;
import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.mapper.ActaMapper;
import pixelpulse.eclesiasticasbackend.mapper.PersonaMapper;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Bautizo;
import pixelpulse.eclesiasticasbackend.model.Confirmacion;
import pixelpulse.eclesiasticasbackend.model.Matrimonio;
import pixelpulse.eclesiasticasbackend.model.Persona;


@Service
public class ActaService {

    private final EclesiasticasbackendApplication eclesiasticasbackendApplication;
	@Autowired
    private ActaRepository actaRepository;
	@Autowired
	private ActaMapper actaMapper;
	
	private MatrimonioRepository matrimonioRepository;
	private ConfirmacionRepository confirmacionRepository;
	private BautizoRepository bautizoRepository;
	private PersonaService pService;
	private PersonaMapper pMapper;
    
	
    


	public ActaService(EclesiasticasbackendApplication eclesiasticasbackendApplication, ActaRepository actaRepository,
			ActaMapper actaMapper, MatrimonioRepository matrimonioRepository,
			ConfirmacionRepository confirmacionRepository, BautizoRepository bautizoRepository, PersonaService pService,
			PersonaMapper pMapper) {
		super();
		this.eclesiasticasbackendApplication = eclesiasticasbackendApplication;
		this.actaRepository = actaRepository;
		this.actaMapper = actaMapper;
		this.matrimonioRepository = matrimonioRepository;
		this.confirmacionRepository = confirmacionRepository;
		this.bautizoRepository = bautizoRepository;
		this.pService = pService;
		this.pMapper = pMapper;
	}





	public List<ActaDTO> getAllActas() {
		
		
		List<Acta> actas = actaRepository.findAll();
		List<ActaDTO> dtos = new ArrayList<>();
		for (Acta acta : actas) {
			ActaDTO nuevo = new ActaDTO();
			Acta original = null;
			Persona p = null;
			if(acta.getTipo().equals("matrimonio")) {
				Matrimonio m = matrimonioRepository.findByActa(acta);
				original = m.getActa();
				p = m.getPersonaA();
			}
			if(acta.getTipo().equals("confirmacion")) {
				Confirmacion m = confirmacionRepository.findByActa(acta);
				original = m.getActa();
				p = m.getConfirmante();
			}
			if(acta.getTipo().equals("bautizo")) {
				Bautizo m = bautizoRepository.findByActa(acta);
				original = m.getActa();
				p = m.getIdBautizado();
			}
			nuevo.setNumeroActa(original.getNumeroActa());
			nuevo.setId(""+original.getId());
			nuevo.setLibro(original.getLibro());
			nuevo.setFolio(original.getFolio());
			nuevo.setTipo(original.getTipo());
			nuevo.setFecha(original.getFecha());
			nuevo.setNombre1(p.getNombre1());
			nuevo.setNombre2(p.getNombre2());
			nuevo.setApellido1(p.getApellido1());
			nuevo.setApellido2(p.getApellido2());
			nuevo.setNotas(original.getNotas());
			dtos.add(nuevo);
			
		}
		
		
        return dtos;
    }
	

	
	/*
	public List<ActaDTO> getActasByName(String name) {
		Persona p = pMapper.fromDto(pService.getPersonaByNombre(name));
		List<ActaDTO> lista = new ArrayList<>();
        
    }*/
    
   
    
    
}



