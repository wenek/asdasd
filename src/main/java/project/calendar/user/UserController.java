package project.calendar.user;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
import static project.calendar.Constants.API_PATH;
import static project.calendar.user.UserMapper.toAuthentication;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import project.calendar.dto.Holiday;
import project.calendar.dto.PasswordChange;
import project.calendar.dto.Registration;
import project.calendar.dto.UserCredentials;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_PATH + "/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final LogoutHandler logoutHandler;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void register(@Valid @RequestBody Registration params) {
        userService.register(params);
    }

    @ResponseStatus(NO_CONTENT)
    @PostMapping(value = "/session", consumes = APPLICATION_JSON_VALUE)
    public void login(@Valid @RequestBody UserCredentials params, HttpServletRequest request) {
        Authentication authenticationRequest = toAuthentication(params);
        Authentication authentication = authenticationManager.authenticate(authenticationRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        request.getSession(true).setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(value = "/session")
    public void logout(
            Authentication authentication,
            HttpServletRequest request,
            HttpServletResponse response) {
        logoutHandler.logout(request, response, authentication);
    }

    @ResponseStatus(NO_CONTENT)
    @PatchMapping(value = "/password", consumes = APPLICATION_JSON_VALUE)
    public void changePassword(
            @Valid @RequestBody PasswordChange params,
            Authentication authentication) {
        userService.changePassword(authentication.getName(), params);
    }

    @ResponseStatus(OK)
    @GetMapping(value = "/holiday", produces = APPLICATION_JSON_VALUE)
    public Set<Holiday> findUserHolidays(Authentication authentication) {
        return userService.findUserHolidays(authentication.getName());
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping(
            value = "/holiday",
            consumes = APPLICATION_JSON_VALUE)
    public void updateHolidays(
            @Valid @RequestBody @NotEmpty Set<Long> ids,
            Authentication authentication) {
        userService.updateHolidays(authentication.getName(), ids);
    }

}
