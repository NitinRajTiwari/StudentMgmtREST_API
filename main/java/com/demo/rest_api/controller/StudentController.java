package com.demo.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rest_api.entity.Student;
import com.demo.rest_api.service.StudentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/std")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/data")
    public List<Student> getAllStudents() {
        System.out.println("running");
        List<Student> list = service.getAllStudentData();
        return list;
    }

    @PostMapping("/save")
    public ResponseEntity<String> postMethodName( @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age) {
            
        service.saveStudent(new Student(name, email, age));  // Save the student data
        return ResponseEntity.status(HttpStatus.CREATED).body("Student data saved");  // Return response
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        boolean isDeleted = service.deleteStudent(id);  // Delete student logic
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        Student student=service.getStudentById(id);
        return student;
    }
    
     @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable Integer id,@RequestBody Student student)
    {
        Student updatedStudent = service.updateStudent(id, student);
        return updatedStudent;
    }

    
}
