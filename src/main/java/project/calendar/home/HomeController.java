package project.calendar.home;

import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import project.calendar.dto.Holiday;
import project.calendar.dto.HolidaysSearch;
import project.calendar.dto.Registration;
import project.calendar.general.validation.Day;
import project.calendar.general.validation.Month;
import project.calendar.user.UserService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;
    private final UserService userService;

    @GetMapping
    public String home(
            @Valid @RequestParam(required = false) @Month Byte monthFrom,
            @Valid @RequestParam(required = false) @Month Byte monthTo,
            @Valid @RequestParam(required = false) @Day Byte dayFrom,
            @Valid @RequestParam(required = false) @Day Byte dayTo,
            Model model) {
        HolidaysSearch parameters = new HolidaysSearch(monthFrom, monthTo, dayFrom, dayTo);
        Set<Holiday> foundHolidays = homeService.find(parameters);
        model.addAttribute("holidays", foundHolidays);
        return "index";
    }

    @GetMapping(value = "/saved")
    public String findUserHolidays(Authentication authentication, Model model) {
        Set<Holiday> savedHolidays = userService.findUserHolidays(authentication.getName());
        model.addAttribute("holidays", savedHolidays);
        return "index";
    }

    @GetMapping(value = "/register")
    public String registration(Model model) {
        model.addAttribute("registration", Registration.empty());
        return "registration";
    }

    @PostMapping(value = "/register")
    public String register(@Valid @ModelAttribute("registration") Registration params, BindingResult validation) {
        if (validation.hasErrors())
            return "registration";

        userService.register(params);
        return "redirect:/login";
    }

}
