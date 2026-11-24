
package com.Handson.demo.service;

import com.Handson.demo.model.Department;
import com.Handson.demo.repo.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepo departmentRepo;

    public DepartmentService(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    // Add department
    public Department addDepartment(Department department) {
        return departmentRepo.save(department);
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    // Get department by ID
    public Department getDepartmentById(Long id) {
        return departmentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found with ID: " + id));
    }

    // Update department
    public Department updateDepartment(Long id, Department updatedDepartment) {

        Department existingDepartment = departmentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found with ID: " + id));

        existingDepartment.setName(updatedDepartment.getName());
        existingDepartment.setDescription(updatedDepartment.getDescription());

        return departmentRepo.save(existingDepartment);
    }

    // Delete department
    public void deleteDepartment(Long id) {

        if (!departmentRepo.existsById(id)) {
            throw new RuntimeException("Department not found with ID: " + id);
        }

        departmentRepo.deleteById(id);
    }

    // Count departments
    public long getDepartmentCount() {
        return departmentRepo.count();
    }
}
