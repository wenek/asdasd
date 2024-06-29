package project.calendar.holiday;

import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.calendar.dto.Holiday;
import project.calendar.dto.HolidaysSearch;

@Service
@RequiredArgsConstructor
public class HolidayService {

    private final HolidayRepository holidayRepository;

    public Set<Holiday> find(HolidaysSearch params) {
        return holidayRepository.find(
                params.monthFrom(),
                params.monthTo(),
                params.dayFrom(),
                params.dayTo());
    }

}
