package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Status;
import rva.repository.StatusRepository;

@RestController
@CrossOrigin
@Api(tags = {"Status CRUD operacija"})
public class StatusRestController {
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*izlistava sve iz statusa*/
	@GetMapping("status") 
	@ApiOperation(value = "Vraca kolekciju svih statusa iz baze podataka")
	public Collection<Status>getStatus() {
		return statusRepository.findAll();
	}
	
	@GetMapping("status/{id}")
	@ApiOperation(value = "Vraca status iz baze podataka ciji je ID prosledjen kao path varijabla")
	public Status getStatus(@PathVariable("id") Integer id) {
		return statusRepository.getOne(id);
	}
	
	@GetMapping("statusNaziv/{naziv}")
	@ApiOperation(value = "Vraca status iz baze podataka ciji je naziv prosledjen kao path varijabla")
	public Collection<Status> getStatusByNaziv(@PathVariable("naziv") String naziv) {
		return statusRepository.findByNazivContainingIgnoreCase(naziv);
		}
	//metoda za dodavanje novog statusa u bazu INSERT
	@PostMapping("status")
	@CrossOrigin
	@ApiOperation(value = "Upisuje status u bazu podataka")
	public ResponseEntity<Status> insertStatus(@RequestBody Status status) {
		if(!statusRepository.existsById(status.getId())) {
			statusRepository.save(status);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	//metoda za update UPDATE
	@PutMapping("status")
	@CrossOrigin
	@ApiOperation(value = "Modifikuje status u bazi podataka")
	public ResponseEntity<Status> updateStatus(@RequestBody Status status) {
		if(!statusRepository.existsById(status.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		statusRepository.save(status);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//metoda za brisanje statusa DELETE
	@DeleteMapping("status/{id}")
	@ApiOperation(value = "Brise status iz baze podataka ciji je id prosledjen kao path varijabla")
	public ResponseEntity<Status> deleteStatus(@PathVariable ("id") Integer id) {
		if(!statusRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		statusRepository.deleteById(id);
		if(id== -100) {
			jdbcTemplate.execute(
					" INSERT INTO \"status\" (\"id\", \"naziv\", \"oznaka\")"
					+ " VALUES (-100, 'Naziv Test', 'OT') ");
		}
		return new ResponseEntity<>(HttpStatus.OK);

	}
	

 }
