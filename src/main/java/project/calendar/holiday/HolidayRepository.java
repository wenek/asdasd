package project.calendar.holiday;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.calendar.dto.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<HolidayEntity, Long> {

    @Query("""
            SELECT new project.calendar.dto.Holiday(h.id, h.name, h.monthValue, h.dayValue)
            FROM HolidayEntity h
            WHERE (:monthFrom IS NULL OR h.monthValue >= :monthFrom)
            AND (:monthTo IS NULL OR h.monthValue <= :monthTo)
            AND (:dayFrom IS NULL OR h.dayValue >= :dayFrom)
            AND (:dayTo IS NULL OR h.dayValue <= :dayTo)
            ORDER BY h.monthValue, h.dayValue""")
    Set<Holiday> find(
            @Param("monthFrom") Byte monthFrom,
            @Param("monthTo") Byte monthTo,
            @Param("dayFrom") Byte dayFrom,
            @Param("dayTo") Byte dayTo);

    @Query("""
            SELECT new project.calendar.dto.Holiday(h.id, h.name, h.monthValue, h.dayValue)
            FROM HolidayEntity h
            WHERE h.id IN (:ids)
            ORDER BY h.monthValue, h.dayValue""")
    Set<Holiday> findByUser(@Param("ids") Set<Long> userHolidaysIDs);

}
