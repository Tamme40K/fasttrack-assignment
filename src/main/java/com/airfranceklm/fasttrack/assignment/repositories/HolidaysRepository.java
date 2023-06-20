package com.airfranceklm.fasttrack.assignment.repositories;

import com.airfranceklm.fasttrack.assignment.resources.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidaysRepository extends JpaRepository<Holiday, Long> {}
