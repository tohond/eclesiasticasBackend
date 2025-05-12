package pixelpulse.eclesiasticasbackend.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;

import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createActaDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createBautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createMatrimonioDTO;
import pixelpulse.eclesiasticasbackend.service.actas.ActaService;
import pixelpulse.eclesiasticasbackend.service.actas.BautizoService;
import pixelpulse.eclesiasticasbackend.service.actas.ConfirmacionService;
import pixelpulse.eclesiasticasbackend.service.actas.MatrimonioService;
import pixelpulse.eclesiasticasbackend.service.auth.FirebaseTokenService;
import pixelpulse.eclesiasticasbackend.service.others.EmailService;
import pixelpulse.eclesiasticasbackend.service.personas.PersonaService;
import pixelpulse.eclesiasticasbackend.service.personas.PersonaService.PersonaSearchResult;
import pixelpulse.eclesiasticasbackend.service.users.UserService;

@RestController
@RequestMapping("/api/actas")
public class ActasController {

    private final ConfirmacionService confirmacionService;

    private final BautizoService bautizoService;

    
	
	@Autowired
	private final FirebaseAuth firebaseAuth;
	private final ActaService actaService;
	private final PersonaService personaService;
	private final MatrimonioService matrimonioService;
	
	
	
	public ActasController(FirebaseAuth firebaseAuth, ActaService actaservice,PersonaService personaService, MatrimonioService matrimonioService, BautizoService bautizoService, ConfirmacionService confirmacionService) {
		
		this.firebaseAuth = firebaseAuth;
		this.actaService = actaservice;
		this.personaService = personaService;
		this.matrimonioService = matrimonioService;
		this.bautizoService = bautizoService;
		this.confirmacionService = confirmacionService;
	}
	
	
	@GetMapping("/all")
    public ResponseEntity<List<ActaDTO>> getAllActas() {
        return ResponseEntity.ok(actaService.getAllActas());
    }
	
	
	@GetMapping("/nombre")
    public ResponseEntity<?> searchByName(
        @RequestParam("nombre") String name
    ) {
        List<ActaDTO> results = personaService.searchByName(name);
        return ResponseEntity.ok(results);
    }
	
	@DeleteMapping("/")
	public ResponseEntity<?> editProfile(
			@PathVariable String id ){
		UUID uuid = UUID.fromString(id);
		//actaService.deleteActaById(uuid);
		return null;
		
	}
	@PostMapping("/batch")
	public ResponseEntity<?> createActaBatch(
			@RequestBody List<Map<String,String>> batchActas ){
		ObjectMapper mapper = new ObjectMapper();
		
		List<ActaDTO> actas = new ArrayList<>();
		for (Map<String, String> map : batchActas) {
		
			String tipo = map.get("tipo").toLowerCase();
			map.replace("tipo", tipo);
			if ( tipo.equals("matrimonio") ){
				createMatrimonioDTO dto = mapper.convertValue(map, createMatrimonioDTO.class);
				matrimonioService.createMatrimonio(dto);
			}
			else if (tipo.equals("confirmacion") ){
				createConfirmacionDTO dto =mapper.convertValue(map, createConfirmacionDTO.class);
				confirmacionService.createConfirmacion(dto);
			}
			else if (tipo.equals("bautizo") ){
				createBautizoDTO dto =mapper.convertValue(map, createBautizoDTO.class);
				bautizoService.createBautizo(dto);
			}
		}
		
		
		//actaService.deleteActaById(uuid);
		return null;
		
	}
	
}
