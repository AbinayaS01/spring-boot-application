package com.Handson.demo.service;

import com.Handson.demo.model.Student;
import com.Handson.demo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

//    @Autowired
//    private StudentRepo studentRepo;

    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo){
        this.studentRepo=studentRepo;
    }

    public Student addStudent(Student student){
        return studentRepo.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Optional<Student> getStudentById(Long id){
        return studentRepo.findById(id);
    }

    public Student updateStudent(Long id, Student updatedStudent){
        Student existingStudent=studentRepo.findById(id).orElseThrow(()->new RuntimeException("Student not found with ID: "+id));
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setAge(updatedStudent.getAge());
        return studentRepo.save(existingStudent);
    }

    public void deleteStudent(Long id){
        if(!studentRepo.existsById(id)){
            throw new RuntimeException("student not found with ID: "+id);
        }
        studentRepo.deleteById(id);
    }
    public void deleteAllStudents(){
        studentRepo.deleteAll();
    }

    public long getStudentCount(){
        return studentRepo.count();
    }

    
}
