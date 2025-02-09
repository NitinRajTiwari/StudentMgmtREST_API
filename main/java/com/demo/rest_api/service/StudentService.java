package com.demo.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.rest_api.entity.Student;
import com.demo.rest_api.repo.StudentRepo;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudentData(){
        return studentRepo.findAll();
    }
    public void saveStudent(Student student){
        studentRepo.save(student);
    }

    public boolean deleteStudent(Integer id){
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()) {
            studentRepo.delete(student.get());  // Delete the student
            return true;
        } else {
            return false;  // Student not found
        }
    }
    public Student getStudentById(Integer id) {
        Optional<Student> byId = studentRepo.findById(id);
        Student student = byId.get();
        return student;
    }

    public Student updateStudent(Integer id, Student student) {
        Optional<Student> studentById = studentRepo.findById(id);
        Student stud = null;
        if(studentById.isPresent())
        {
            Student dbStudentObject = studentById.get();
            dbStudentObject.setAge(student.getAge());
            dbStudentObject.setEmail(student.getEmail());
            dbStudentObject.setName(student.getName());
            stud= studentRepo.save(dbStudentObject);
        }
        else {
            System.out.println("Data not present in db....");
        }
        return stud;
    }

}
