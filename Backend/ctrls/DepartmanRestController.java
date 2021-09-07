package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
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
import rva.jpa.Departman;
import rva.jpa.Status;
import rva.repository.DepartmanRepository;

@RestController
@CrossOrigin
@Api(tags = {"Departman CRUD operacija"})
public class DepartmanRestController {
	
	@Autowired
	private DepartmanRepository departmanRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("departman")
	@ApiOperation(value = "Vraca kolekciju svih departmana iz baze podataka")
	public Collection<Departman>getDepartman(){
		return departmanRepository.findAll();
		}
	
	@GetMapping("departman/{id}")
	@ApiOperation(value = "Vraca departman iz baze podataka ciji je ID prosledjen kao path varijabla")
	public Departman getDepartman(@PathVariable("id") Integer id) {
		return departmanRepository.getOne(id);
	}
	
	
	@GetMapping("departmanNaziv/{naziv}")
	@ApiOperation(value = "Vraca kolekciju departmana koji u nazivu sadrze string prosledjen kao path varijabla")
	public Collection<Departman> getDepartmanByNaziv(@PathVariable("naziv") String naziv) {
		return departmanRepository.findByNazivContainingIgnoreCase(naziv);
		}
	
	@PostMapping("departman")
	@CrossOrigin
	@ApiOperation(value = "Upisuje departman u bazu podataka")
	public ResponseEntity<Departman> insertDepartman(@RequestBody Departman departman) {
		if(!departmanRepository.existsById(departman.getId())) {
			departmanRepository.save(departman);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	//metoda za update
	@PutMapping("departman")
	@CrossOrigin
	@ApiOperation(value = "Modifikuje departman u bazi podataka")
	public ResponseEntity<Departman> updateDepartman(@RequestBody Departman departman) {
		if(!departmanRepository.existsById(departman.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		departmanRepository.save(departman);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//metoda za brisanje statusa DELETE
		@DeleteMapping("departman/{id}")
		@ApiOperation(value = "Brise departman iz baze podataka ciji je id prosledjen kao path varijabla")
		public ResponseEntity<Departman> deleteDepartman(@PathVariable ("id") Integer id) {
			if(!departmanRepository.existsById(id))
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			jdbcTemplate.execute("delete from student where departman = " +id);
			departmanRepository.deleteById(id);
			
			if(id== -100) {
				jdbcTemplate.execute(
						" INSERT INTO \"departman\" (\"id\", \"naziv\", \"oznaka\", \"fakultet\" )"
						+ " VALUES (-100, 'Naziv Test', 'OT', 1) ");
			}
			return new ResponseEntity<>(HttpStatus.OK);

		}
		
}
