package com.airfranceklm.fasttrack.assignment.service;

import com.airfranceklm.fasttrack.assignment.repositories.HolidaysRepository;
import com.airfranceklm.fasttrack.assignment.resources.Holiday;
import com.airfranceklm.fasttrack.assignment.resources.Status;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class HolidaysService {

    private final HolidaysRepository holidaysRepository;

    public HolidaysService(HolidaysRepository holidaysRepository) {
        this.holidaysRepository = holidaysRepository;

        /* Populate the Holiday table with some initial holidays */
        addHoliday(
                "Summer holiday",
                "klm123456",
                Instant.now(),
                Instant.now().plus(5, ChronoUnit.DAYS),
                Status.REQUESTED
        );
        addHoliday(
                "Medical leave",
                "klm567099",
                Instant.parse("2023-12-23T00:00:00.00Z"),
                Instant.parse("2023-12-23T00:00:00.00Z").plus(5, ChronoUnit.DAYS),
                Status.SCHEDULED
        );
    }

    /**
     * Intended for initial population of the table, do not use outside of testing.
     */
    private void addHoliday(String label, String employeeId, Instant startOfHoliday, Instant endOfHoliday, Status status) {
        holidaysRepository.save(new Holiday(label, employeeId, startOfHoliday, endOfHoliday, status));
    }

    /**
     * Will attempt to schedule a holiday, observing the following business rules:
     * - There should be a gap of at least 3 working days between holidays.
     * - A holiday must be planned at least 5 working days before the start date.
     * - A holiday must be cancelled at least 5 working days before the start date.
     * - Holidays must not overlap (for the sake of this assignment also not between different crew members).
     *
     * @param label = The label applied to the holiday such as "summer holiday"
     * @param employeeId = Id of the employee requesting the holiday
     * @param startOfHoliday = Starting date of the holiday
     * @param endOfHoliday = Ending date of the holiday
     *
     * @return A string representation of the success or failure of the operation
     */
    public Holiday scheduleHoliday(String label, String employeeId, Instant startOfHoliday, Instant endOfHoliday) {
        boolean noFailedChecks = true;

        List<Holiday> holidays = findHolidaysByEmployeeId(employeeId);

        /* Check for at least three working days between the proposed holiday and the scheduled ones.
        * If the checks are true for all holidays, the rule is satisfied. */
        for (Holiday holiday : holidays) {
            boolean startGap = startOfHoliday.isAfter(holiday.getEndOfHoliday().plus(3, ChronoUnit.DAYS));
            boolean endGap = endOfHoliday.isBefore(holiday.getStartOfHoliday().minus(3, ChronoUnit.DAYS));

            if (!startGap && !endGap) {
                noFailedChecks = false;
                break;
            }
        }

        /* Check for at least 5 working days before the start of the holiday */
        if (startOfHoliday.isBefore(Instant.now().plus(5, ChronoUnit.DAYS))) {
            noFailedChecks = false;
        }

        /* If no checks were failed, the holiday can be scheduled normally */
        if (noFailedChecks) {
            Holiday scheduled = new Holiday(label, employeeId, startOfHoliday, endOfHoliday, Status.SCHEDULED);
            holidaysRepository.save(scheduled);
            return scheduled;
        } else {
            return new Holiday(label, employeeId, startOfHoliday, endOfHoliday, Status.ARCHIVED);
        }
    }

    public List<Holiday> findHolidaysByEmployeeId(String employeeId) {
        return holidaysRepository.findByEmployeeId(employeeId);
    }
}
