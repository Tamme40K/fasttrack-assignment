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
        holidaysRepository.save(new Holiday("Summer holiday", "klm123456", Instant.now(), Instant.now().plus(1, ChronoUnit.DAYS), Status.REQUESTED));
    }

    public List<Holiday> list() {
        return holidaysRepository.findAll();
    }
}
