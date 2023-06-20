package com.airfranceklm.fasttrack.assignment.repositories;

import com.airfranceklm.fasttrack.assignment.resources.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {}
