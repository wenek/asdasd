package project.calendar.dto;

import project.calendar.general.validation.Day;
import project.calendar.general.validation.Month;

public record HolidaysSearch(
        @Month Byte monthFrom,
        @Month Byte monthTo,
        @Day Byte dayFrom,
        @Day Byte dayTo) {}
