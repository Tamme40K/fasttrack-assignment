package com.airfranceklm.fasttrack.assignment.resources;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

/**
 * A (to be) scheduled holiday of an employee (crew)
 * required: [ "holidayId", "holidayLabel", "employeeId", "status" ]
 */
@Entity
public class Holiday {

    /**
     * The unique identifier for a holiday
     */
    @Id
    private final UUID holidayId = UUID.randomUUID();

    /**
     * The label describing the holiday
     */
    private String label;

    /**
     * The unique identifier for a crew member
     * pattern: "^klm[0-9]{6}$"
     */
    private String employeeId;

    /**
     * The start date and time of the holiday (in UTC)
     */
    private Instant startOfHoliday;

    /**
     * The end date and time of the holiday (in UTC)
     */
    private Instant endOfHoliday;

    /**
     * The status of the holiday
     */
    private String status;

    public Holiday(String label, String employeeId, Instant startOfHoliday, Instant endOfHoliday, Status status) {
        this.label = label;
        this.employeeId = employeeId;
        this.startOfHoliday = startOfHoliday;
        this.endOfHoliday = endOfHoliday;
        this.status = status.toString();
    }

    public Holiday() {}

    public String getLabel() {
        return this.label;
    }

    public String getStatus() {
        return this.status;
    }
}
