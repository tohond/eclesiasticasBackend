package pixelpulse.eclesiasticasbackend.controller;

import java.awt.PageAttributes.MediaType;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;

import pixelpulse.eclesiasticasbackend.dto.ActaDTO;
import pixelpulse.eclesiasticasbackend.dto.PdfRequestDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createActaDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createBautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.create.createMatrimonioDTO;
import pixelpulse.eclesiasticasbackend.dto.edit.EditBautizoDTO;
import pixelpulse.eclesiasticasbackend.dto.edit.EditConfirmacionDTO;
import pixelpulse.eclesiasticasbackend.dto.edit.EditMatrimonioDTO;
import pixelpulse.eclesiasticasbackend.service.actas.ActaService;
import pixelpulse.eclesiasticasbackend.service.actas.BautizoService;
import pixelpulse.eclesiasticasbackend.service.actas.ConfirmacionService;
import pixelpulse.eclesiasticasbackend.service.actas.MatrimonioService;
import pixelpulse.eclesiasticasbackend.service.auth.FirebaseTokenService;
import pixelpulse.eclesiasticasbackend.service.others.EmailService;
import pixelpulse.eclesiasticasbackend.service.others.PdfService;
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
	
	private final PdfService pdfservice;
	
	
	
	public ActasController(PdfService pdf,FirebaseAuth firebaseAuth, ActaService actaservice,PersonaService personaService, MatrimonioService matrimonioService, BautizoService bautizoService, ConfirmacionService confirmacionService) {
		
		this.firebaseAuth = firebaseAuth;
		this.actaService = actaservice;
		this.personaService = personaService;
		this.matrimonioService = matrimonioService;
		this.bautizoService = bautizoService;
		this.confirmacionService = confirmacionService;
		this.pdfservice = pdf;
	}
	
	
	@GetMapping("/all")
    public ResponseEntity<List<ActaDTO>> getAllActas() {
        return ResponseEntity.ok(actaService.getAllActas());
    }
	
	
	@GetMapping("/buscaravanzado")
    public ResponseEntity<?> searchByNames(
        @RequestParam("nombre1") String nombre1,
        @RequestParam("nombre2") String nombre2,
        @RequestParam("apellido1") String apellido1,
        @RequestParam("apellido2") String apellido2
    ) {
        List<ActaDTO> results = personaService.searchByNameAvanzado(nombre1, nombre2, apellido1, apellido2);
        return ResponseEntity.ok(results);
    }
	
	@GetMapping("/buscar")
    public ResponseEntity<?> searchByName(
        @RequestParam("nombre") String name
    ) {
        List<ActaDTO> results = personaService.searchByName(name);
        return ResponseEntity.ok(results);
    }
	
	@PostMapping("/pdf")
	public ResponseEntity<?> generarPdf(@RequestBody PdfRequestDTO request) {
        try {
            byte[] pdf = pdfservice.generarPdfDesdePlantilla(request);

            // Configurar headers para forzar descarga
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment()
                .filename("documento_generado.pdf")
                .build());

            return new ResponseEntity<>(pdf, headers, HttpStatus.SC_ACCEPTED);

        } catch (FileNotFoundException e) {
        	
        	Map<String,String> error = new HashMap<>();
        	error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(error);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
	@PutMapping("/editar")
	public ResponseEntity<?> editProfile(
			@RequestBody Map<String,String> map ){
		Long uid = Long.valueOf(map.get("id"));
		if (uid==null) {
			Map<String,String> error = new HashMap<>();
        	error.put("error", "No ID:"+map.get("id"));
			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(error);
		}
		ObjectMapper mapper = new ObjectMapper();
		
		String tipo = map.get("tipo");
		
		if ( tipo.equals("matrimonio") ){
			EditMatrimonioDTO dto = mapper.convertValue(map, EditMatrimonioDTO.class);
			return  ResponseEntity.ok(matrimonioService.updateMatrimonio(uid, dto));
		}
		else if (tipo.equals("confirmacion") ){
			EditConfirmacionDTO dto =mapper.convertValue(map, EditConfirmacionDTO.class);
			return  ResponseEntity.ok(confirmacionService.updateConfirmacion(uid,dto));
		}
		else if (tipo.equals("bautizo") ){
			EditBautizoDTO dto =mapper.convertValue(map, EditBautizoDTO.class);
			return  ResponseEntity.ok(bautizoService.updateBautizo(uid,dto));
		}
		
		
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
				return  ResponseEntity.ok(matrimonioService.createMatrimonio(dto));
			}
			else if (tipo.equals("confirmacion") ){
				createConfirmacionDTO dto =mapper.convertValue(map, createConfirmacionDTO.class);
				return  ResponseEntity.ok(confirmacionService.createConfirmacion(dto));
			}
			else if (tipo.equals("bautizo") ){
				createBautizoDTO dto =mapper.convertValue(map, createBautizoDTO.class);
				return  ResponseEntity.ok(bautizoService.createBautizo(dto)) ;
			}
		}
		return null;
		
		//actaService.deleteActaById(uuid);
		
		
	}
	
}
