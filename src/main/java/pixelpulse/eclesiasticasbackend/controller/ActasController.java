package pixelpulse.eclesiasticasbackend.controller;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.firebase.auth.FirebaseAuth;

import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.dto.BautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.ConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.MatrimonioDTO;
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
@RequiredArgsConstructor
@RequestMapping("/api/actas")
public class ActasController {

	private final FirebaseAuth firebaseAuth;
	private final ActaService actaService;
	private final PersonaService personaService;
	private final BautizoService bautizoService;
	private final ConfirmacionService confirmacionService;
	private final MatrimonioService matrimonioService;

	
	@GetMapping("/all")
    public ResponseEntity<List<ActaDTO>> getAllActas() {
        return ResponseEntity.ok(actaService.getAllActas());
    }
	
	
	@GetMapping("/nombre")
    public ResponseEntity<PersonaSearchResult> searchByName(
        @RequestParam("nombre") String name
    ) {
        PersonaSearchResult results = personaService.searchByName(name);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/buscar-nombre-completo")
    public ResponseEntity<PersonaSearchResult> searchByFullName(
            @RequestParam(value = "primerNombre", required = false) String primerNombre,
            @RequestParam(value = "segundoNombre", required = false) String segundoNombre,
            @RequestParam(value = "primerApellido", required = false) String primerApellido,
            @RequestParam(value = "segundoApellido", required = false) String segundoApellido
    ) {
        PersonaSearchResult results = personaService.searchByFullName(
                primerNombre, segundoNombre, primerApellido, segundoApellido);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/bautismo")
    public ResponseEntity<BautizoDTO> createBautizo(@RequestBody BautizoDTO bautizoDTO) {
        BautizoDTO createdBautizo = bautizoService.createBautizo(bautizoDTO);
        return new ResponseEntity<>(createdBautizo, HttpStatus.CREATED);
    }

    @PostMapping("/confirmacion")
    public ResponseEntity<ConfirmacionDTO> createConfirmacion(@RequestBody ConfirmacionDTO confirmacionDTO) {
        ConfirmacionDTO createdConfirmacion = confirmacionService.createConfirmacion(confirmacionDTO);
        return new ResponseEntity<>(createdConfirmacion, HttpStatus.CREATED);
    }

    @PostMapping("/matrimonio")
    public ResponseEntity<MatrimonioDTO> createMatrimonio(@RequestBody MatrimonioDTO matrimonioDTO) {
        MatrimonioDTO createdMatrimonio = matrimonioService.createMatrimonio(matrimonioDTO);
        return new ResponseEntity<>(createdMatrimonio, HttpStatus.CREATED);
    }

}
