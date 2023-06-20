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
        employeeRepository.save(new Employee("klm123456", "Clarissa"));
    }

    public List<Employee> list() {
        return employeeRepository.findAll();
    }
}
