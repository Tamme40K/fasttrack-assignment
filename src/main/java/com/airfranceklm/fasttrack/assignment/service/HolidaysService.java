package com.airfranceklm.fasttrack.assignment.service;

import com.airfranceklm.fasttrack.assignment.repositories.HolidaysRepository;
import com.airfranceklm.fasttrack.assignment.resources.Holiday;
import com.airfranceklm.fasttrack.assignment.resources.Status;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class HolidaysService {

    private final HolidaysRepository holidaysRepository;

    public HolidaysService(HolidaysRepository holidaysRepository) {
        this.holidaysRepository = holidaysRepository;
    }

    /**
     * Will attempt to schedule a holiday, observing the following business rules:
     * - There should be a gap of at least 3 working days between holidays.
     * - A holiday must be planned at least 5 working days before the start date.
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
            boolean startGap = startOfHoliday.getEpochSecond() >= holiday.getEndOfHoliday().plus(3, ChronoUnit.DAYS).getEpochSecond();
            boolean endGap = endOfHoliday.getEpochSecond() <= holiday.getStartOfHoliday().minus(3, ChronoUnit.DAYS).getEpochSecond();

            if (!startGap && !endGap) {
                noFailedChecks = false;
                break;
            }
        }

        /* Check for at least 5 working days before the start of the holiday */
        if (startOfHoliday.getEpochSecond() <= Instant.now().plus(5, ChronoUnit.DAYS).getEpochSecond()) {
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

    /**
     * Will attempt to cancel the holiday indicated by the label as long as the following business rule is observed:
     * - A holiday must be cancelled at least 5 working days before the start date.
     * @param holidayId Identifier for the holiday
     * @return The holiday with an updated status
     */
    public Holiday cancelHoliday(String holidayId) {

        Holiday holiday = findHolidayByUuid(holidayId);

        /* Check for at least 5 working days before the start of the holiday */
        if (holiday.getStartOfHoliday().getEpochSecond() > Instant.now().plus(5, ChronoUnit.DAYS).getEpochSecond()) {
            holidaysRepository.delete(holiday);
            holiday.setStatus(Status.ARCHIVED.toString());
        }

        return holiday;
    }

    public List<Holiday> findHolidaysByEmployeeId(String employeeId) {
        return holidaysRepository.findByEmployeeId(employeeId);
    }

    public Holiday findHolidayByUuid(String holidayId) {
        return holidaysRepository.findByHolidayId(UUID.fromString(holidayId));
    }
}
