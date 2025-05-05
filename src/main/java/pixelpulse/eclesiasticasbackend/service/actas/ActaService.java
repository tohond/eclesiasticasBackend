package pixelpulse.eclesiasticasbackend.service.actas;

import java.util.List;

import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import pixelpulse.eclesiasticasbackend.repository.ActaRepository;
import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.mapper.ActaMapper;
import pixelpulse.eclesiasticasbackend.model.Acta;


@Service
public class ActaService {
	@Autowired
    private ActaRepository actaRepository;
	@Autowired
	private ActaMapper actaMapper;


    
	
    public ActaService(ActaMapper actaMapper) {
		this.actaMapper = actaMapper;
	}


	public List<ActaDTO> getAllActas() {
        return actaMapper.toDtoList(actaRepository.findAll());
    }
    
    
   
    
    
}



