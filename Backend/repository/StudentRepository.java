package rva.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import rva.jpa.Departman;

import rva.jpa.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	//za prosledjni departman pronaci ce sve studente 
	Collection<Student> findByDepartman(Departman d);

//	Collection<Student> findByBrojIndeksaContainingIgnoreCase(String brIndeksa);

}
