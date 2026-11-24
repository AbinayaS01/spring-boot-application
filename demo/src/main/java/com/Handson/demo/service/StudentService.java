
package com.Handson.demo.service;

import com.Handson.demo.model.Student;
import com.Handson.demo.model.Department;
import com.Handson.demo.repo.StudentRepo;
import com.Handson.demo.repo.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private final DepartmentRepo departmentRepo;

    public StudentService(StudentRepo studentRepo,
                          DepartmentRepo departmentRepo) {
        this.studentRepo = studentRepo;
        this.departmentRepo = departmentRepo;
    }

    // Add Student
    public Student addStudent(Student student) {

        if (student.getDepartment() == null ||
                student.getDepartment().getId() == null) {
            throw new RuntimeException("Department ID is required");
        }

        Long departmentId = student.getDepartment().getId();

        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() ->
                        new RuntimeException("Department not found with ID: " + departmentId));

        student.setDepartment(department);

        return studentRepo.save(student);
    }

    // Get All Students
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // Get Student By ID
    public Student getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with ID: " + id));
    }

    // Update Student
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = studentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with ID: " + id));

        if (updatedStudent.getDepartment() == null ||
                updatedStudent.getDepartment().getId() == null) {
            throw new RuntimeException("Department ID is required");
        }

        Long departmentId = updatedStudent.getDepartment().getId();

        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() ->
                        new RuntimeException("Department not found with ID: " + departmentId));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setDepartment(department);

        return studentRepo.save(existingStudent);
    }

    // Delete Student
    public void deleteStudent(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with ID: " + id));

        studentRepo.delete(student);
    }

    // Delete All Students
    public void deleteAllStudents() {
        studentRepo.deleteAll();
    }

    // Get Student Count
    public long getStudentCount() {
        return studentRepo.count();
    }
}
