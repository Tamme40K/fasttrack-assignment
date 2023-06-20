package com.airfranceklm.fasttrack.assignment.controller;

import java.util.List;

import com.airfranceklm.fasttrack.assignment.service.HolidaysService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airfranceklm.fasttrack.assignment.resources.Holiday;

@Controller
@RequestMapping("/holidays")
public class HolidaysApi {

    private final HolidaysService holidaysService;

    public HolidaysApi(HolidaysService holidaysService) {
        this.holidaysService = holidaysService;
    }

    @GetMapping
    public ResponseEntity<List<Holiday>> getHolidays() {
        return new ResponseEntity<>(holidaysService.list(), HttpStatus.OK);
    }

}
