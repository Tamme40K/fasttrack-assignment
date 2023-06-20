package com.airfranceklm.fasttrack.assignment.service;

import com.airfranceklm.fasttrack.assignment.repositories.HolidaysRepository;
import com.airfranceklm.fasttrack.assignment.resources.Holiday;
import com.airfranceklm.fasttrack.assignment.resources.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class HolidaysServiceTest {

    @Autowired
    private HolidaysService holidaysService;
    @MockBean
    private HolidaysRepository holidaysRepository;

    @BeforeEach
    void init() {

        Holiday summer = new Holiday (
                "Summer holiday",
                "klm567099",
                Instant.parse("3023-06-20T00:00:00.00Z"),
                Instant.parse("3023-06-25T00:00:00.00Z"),
                Status.REQUESTED
        );
        Holiday winter = new Holiday (
                "Christmas",
                "klm567099",
                Instant.parse("3023-12-23T00:00:00.00Z"),
                Instant.parse("3023-12-28T00:00:00.00Z"),
                Status.SCHEDULED
        );

        when(holidaysRepository.findByEmployeeId("klm567099")).thenReturn(Arrays.asList(summer, winter));
    }

    /**
     * Test if holidays are correctly found based on employeeId
     */
    @Test
    void findHolidays() {
        List<Holiday> holidayList = holidaysService.findHolidaysByEmployeeId("klm567099");
        assertEquals(2, holidayList.size());
    }

    /**
     * Test if a series of valid holidays can be scheduled
     */
    @Test
    void scheduleHolidayJustEnoughTimeBetweenHolidays() {

        Holiday scheduled = holidaysService.scheduleHoliday(
                "Pls I really wanna see a movie",
                "klm567099",
                Instant.parse("3023-12-17T00:00:00.00Z"),
                Instant.parse("3023-12-20T00:00:00.00Z")
        );

        assertEquals(Status.SCHEDULED.toString(), scheduled.getStatus(), "End date should not be close enough to the following holiday's start date for a failure!");
    }


    @Test
    void scheduleHolidayJustFarEnoughFromHolidayStartDate() {

        Holiday scheduled = holidaysService.scheduleHoliday(
                "Pls I really wanna see a movie",
                "klm567099",

                // Offset of five seconds is allowed since Instant.now() is called in the function after this
                Instant.now().plus(5, ChronoUnit.DAYS).plus(5, ChronoUnit.SECONDS),
                Instant.now().plus(7, ChronoUnit.DAYS)
        );

        assertEquals(Status.SCHEDULED.toString(), scheduled.getStatus(), "End date should not be close enough to the following holiday's start date for a failure!");
    }

    /**
     * Test if a series of invalid holidays can't be scheduled
     */
    @Test
    void scheduleHolidayNotEnoughTimeBetweenHolidays() {

        Holiday scheduled = holidaysService.scheduleHoliday(
                "Pls I really wanna see a movie",
                "klm567099",
                Instant.parse("3023-12-17T00:00:00.00Z"),
                Instant.parse("3023-12-20T00:01:00.00Z")
        );

        assertEquals(Status.ARCHIVED.toString(), scheduled.getStatus(), "End date should be too close to the following holiday's start date for a success!");
    }

    @Test
    void scheduleHolidayTooCloseToHolidayStartDate() {

        Holiday scheduled = holidaysService.scheduleHoliday(
                "Pls I really wanna see a movie",
                "klm567099",
                Instant.now().plus(4, ChronoUnit.DAYS),
                Instant.now().plus(7, ChronoUnit.DAYS)
        );

        assertEquals(Status.ARCHIVED.toString(), scheduled.getStatus(), "Start date should be too close to current date for a success!");
    }
}