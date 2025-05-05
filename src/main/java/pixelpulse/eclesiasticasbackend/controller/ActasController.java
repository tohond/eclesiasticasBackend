package pixelpulse.eclesiasticasbackend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;

import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.service.actas.ActaService;
import pixelpulse.eclesiasticasbackend.service.auth.FirebaseTokenService;
import pixelpulse.eclesiasticasbackend.service.others.EmailService;
import pixelpulse.eclesiasticasbackend.service.users.UserService;

@RestController
@RequestMapping("/api/actas")
public class ActasController {
	
	@Autowired
	private final FirebaseAuth firebaseAuth;
	private final ActaService actaService;
	
	
	
	public ActasController(FirebaseAuth firebaseAuth, ActaService actaservice) {
		
		this.firebaseAuth = firebaseAuth;
		this.actaService = actaservice;
	}
	
	
	@GetMapping("/all")
    public ResponseEntity<List<ActaDTO>> getAllActas() {
        return ResponseEntity.ok(actaService.getAllActas());
    }
	
	
	
	
}
