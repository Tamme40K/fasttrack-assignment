package com.airfranceklm.fasttrack.assignment.service;

import com.airfranceklm.fasttrack.assignment.repositories.EmployeeRepository;
import com.airfranceklm.fasttrack.assignment.resources.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

        /* Populate the Employee table with some employees */
        addEmployee("klm123456", "Clarissa");
        addEmployee("klm654321", "Anita");
        addEmployee("klm666666", "Myrtle");
        addEmployee("klm567099", "Cloud");
    }

    public void addEmployee(String employeeId, String name) {
        employeeRepository.save(new Employee(employeeId, name));
    }

    public List<Employee> list() {
        return employeeRepository.findAll();
    }
}
