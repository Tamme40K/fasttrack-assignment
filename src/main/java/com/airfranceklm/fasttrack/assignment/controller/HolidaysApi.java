package com.airfranceklm.fasttrack.assignment.controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.airfranceklm.fasttrack.assignment.service.HolidaysService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.airfranceklm.fasttrack.assignment.resources.Holiday;

@Controller
@RequestMapping("/holidays")
public class HolidaysApi {

    private final HolidaysService holidaysService;

    public HolidaysApi(HolidaysService holidaysService) {
        this.holidaysService = holidaysService;
    }

    @GetMapping("/overview")
    public ResponseEntity<List<Holiday>> getHolidaysByEmployee(@RequestParam String employeeId) {
        return new ResponseEntity<>(holidaysService.findHolidaysByEmployeeId(employeeId), HttpStatus.OK);
    }

    @PostMapping("/schedule")
    @ResponseBody
    public ResponseEntity<Holiday> scheduleHoliday(@RequestBody Map<String, String> json) {
        String label = json.get("label");
        String employeeId = json.get("employeeId");
        String startOfHoliday = json.get("startOfHoliday");
        String endOfHoliday = json.get("endOfHoliday");

        return new ResponseEntity<>(holidaysService.scheduleHoliday(label, employeeId, Instant.parse(startOfHoliday), Instant.parse(endOfHoliday)), HttpStatus.OK);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<Holiday> cancelHoliday(@RequestParam String holidayId) {
        return new ResponseEntity<>(holidaysService.cancelHoliday(holidayId), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> editHoliday() {
        return new ResponseEntity<>("FIXME", HttpStatus.OK);
    }

}
