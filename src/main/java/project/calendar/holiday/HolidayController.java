package project.calendar.holiday;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static project.calendar.Constants.API_PATH;

import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import project.calendar.dto.Holiday;
import project.calendar.dto.HolidaysSearch;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_PATH + "/calendar")
public class HolidayController {

    private final HolidayService holidayService;

    @ResponseStatus(OK)
    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public Set<Holiday> find(@Valid @RequestBody HolidaysSearch params) {
        return holidayService.find(params);
    }

}
