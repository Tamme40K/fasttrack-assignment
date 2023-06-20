package com.airfranceklm.fasttrack.assignment.repositories;

import com.airfranceklm.fasttrack.assignment.resources.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidaysRepository extends JpaRepository<Holiday, Long> {
    List<Holiday> findByEmployeeId(String employeeId);
}
