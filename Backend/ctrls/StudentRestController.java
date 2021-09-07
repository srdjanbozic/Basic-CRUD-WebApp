package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Departman;

import rva.jpa.Student;
import rva.repository.StudentRepository;
import rva.repository.DepartmanRepository;


@RestController
@CrossOrigin
@Api(tags = {"Student CRUD operacija"})
public class StudentRestController {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private DepartmanRepository departmanRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*izlistava sve iz statusa*/
	@GetMapping("student") 
	@ApiOperation(value = "Vraca kolekciju svih studenata iz baze podataka")
	public Collection<Student>getStudent() {
		return studentRepository.findAll();
	}
	
	@GetMapping("student/{id}")
	@ApiOperation(value = "Vraca studente iz baze podataka ciji je ID prosledjen kao path varijabla")
	public ResponseEntity<Student> getStudent(@PathVariable("id") Integer id) {
		Student student = studentRepository.getOne(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	//za prosledjeni departman uzimamo studente
	@GetMapping("studentZaDepartman/{id}")
	@ApiOperation(value = "Za prosledjeni id departmana izlistava sve studente iz baze podataka")
	public Collection<Student> studentPoDepartmanu(@PathVariable("id") int id) {
		Departman d = departmanRepository.getOne(id);
		return studentRepository.findByDepartman(d);
	}

	//INSERT
	@PostMapping("student")
	@CrossOrigin
	@ApiOperation(value = "Upisuje studenta u bazu podataka")
		public ResponseEntity<Student> insertStudent(@RequestBody Student student) {
			if(!studentRepository.existsById(student.getId())) {
				studentRepository.save(student);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/* UPDATE */
	@PutMapping("student")
	@CrossOrigin
	@ApiOperation(value = "Modifikuje studenta u bazi podataka")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		if(!studentRepository.existsById(student.getId())) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		studentRepository.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//DELETE
	@DeleteMapping("student/{id}")
	@ApiOperation(value = "Brise studenta iz baze podataka ciji je id prosledjen kao path varijabla")
	public ResponseEntity<Student> deleteProjekat(@PathVariable ("id") Integer id) {
		if(!studentRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		studentRepository.deleteById(id);
		if (id== -100) {
			jdbcTemplate.execute(" INSERT INTO \"student\" (\"id\", \"ime\", \"prezime\", \"broj_indeksa\", \"status\", \"departman\")"
					+ " VALUES (-100, 'Ime Test delete', 'Prezime Test delete', 'Index Test', 2, 2) ");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
