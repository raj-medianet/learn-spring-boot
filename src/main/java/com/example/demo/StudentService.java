package com.example.demo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	public List<Student> retrieveAllStudents(){
		return studentRepo.findAll();
	}
	
	public Student getStudentById(Long id) {
		return studentRepo.findById(id).orElse(null);
	}
	
	public Student createStudent(Student student) {
		Student savedStudent =  studentRepo.save(student);
		return savedStudent;
	}
	
	public Student updateStudent(Student student, Long id) {
		Student oldStudent = studentRepo.findById(id).orElse(null);
		if (oldStudent==null)
			return null;
		student.setId(id);
		return studentRepo.save(student);
	}
	
	public boolean deleteStudent(Long id) {
		Student oldStudent = studentRepo.findById(id).orElse(null);
		if (oldStudent==null)
			return false;
		studentRepo.deleteById(id);
		return true;
	}
	
}
