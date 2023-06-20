package com.airfranceklm.fasttrack.assignment.resources;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A record of an employee (crew)
 */
@Entity
public class Employee {

    /**
     * The unique identifier for a crew member (klm012345)
     * pattern: "^klm[0-9]{6}$"
     */
    @Id
    private String employeeId;

    /**
     * The name of the employee
     */
    private String name;

    public Employee(String employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public Employee() {}

}
