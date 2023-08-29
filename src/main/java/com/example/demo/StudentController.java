package com.example.demo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService=studentService;
	}
	
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.retrieveAllStudents();
    }
    
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
    	Student student = studentService.getStudentById(id);
    	if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
    	Student savedStudent = studentService.createStudent(student);
    	URI loc = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId())
				.toUri();
		return ResponseEntity.created(loc).build();
    }
	
    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {
    	Student savedStudent = studentService.updateStudent(student, id);
    	if(savedStudent==null)
    		return ResponseEntity.notFound().build();
    	return ResponseEntity.ok(savedStudent);
    }
    
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable long id) {
    	boolean deleted = studentService.deleteStudent(id);
    	if(!deleted)
    		return ResponseEntity.notFound().build();
    	return ResponseEntity.noContent().build();
    }
}
