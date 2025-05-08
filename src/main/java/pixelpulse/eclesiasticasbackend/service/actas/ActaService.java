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
import pixelpulse.eclesiasticasbackend.service.personas.PersonaService;
import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.mapper.ActaMapper;
import pixelpulse.eclesiasticasbackend.mapper.PersonaMapper;
import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Persona;


@Service
public class ActaService {

    private final EclesiasticasbackendApplication eclesiasticasbackendApplication;
	@Autowired
    private ActaRepository actaRepository;
	@Autowired
	private ActaMapper actaMapper;

	private PersonaService pService;
	private PersonaMapper pMapper;
    
	
    public ActaService(ActaMapper actaMapper, EclesiasticasbackendApplication eclesiasticasbackendApplication) {
		this.actaMapper = actaMapper;
		this.eclesiasticasbackendApplication = eclesiasticasbackendApplication;
	}


	public List<ActaDTO> getAllActas() {
        return actaMapper.toDtoList(actaRepository.findAll());
    }
	

	
	/*
	public List<ActaDTO> getActasByName(String name) {
		Persona p = pMapper.fromDto(pService.getPersonaByNombre(name));
		List<ActaDTO> lista = new ArrayList<>();
        
    }*/
    
   
    
    
}



