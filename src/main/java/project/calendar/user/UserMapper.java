package project.calendar.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import project.calendar.dto.Registration;
import project.calendar.dto.UserCredentials;

public final class UserMapper {

    public static UserEntity toEntity(Registration dto, PasswordEncoder encoder) {
        UserEntity user = new UserEntity();
        user.email = dto.email();
        user.password = encoder.encode(dto.password());
        return user;
    }

    public static UserDetails toUserDetails(UserCredentials credentials) {
        return User.builder()
                .username(credentials.email())
                .password(credentials.password())
                .build();
    }

    public static Authentication toAuthentication(UserCredentials credentials) {
        return new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());
    }

}
