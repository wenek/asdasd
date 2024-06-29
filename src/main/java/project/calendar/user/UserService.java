package project.calendar.user;

import static project.calendar.home.HolidayMapper.toIDs;
import static project.calendar.user.UserMapper.toEntity;
import static project.calendar.user.UserMapper.toUserDetails;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import project.calendar.dto.Holiday;
import project.calendar.dto.PasswordChange;
import project.calendar.dto.Registration;
import project.calendar.dto.UserCredentials;
import project.calendar.home.HolidayEntity;
import project.calendar.home.HomeRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final HomeRepository holidayRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public void register(Registration params) {
        if (userRepository.existsByEmail(params.email()))
            throw new RuntimeException("User already exists");

        userRepository.save(toEntity(params, encoder));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials credentials = userRepository.findByEmail(username, UserCredentials.class);

        if (null == credentials)
            throw new UsernameNotFoundException("Email not found");

        return toUserDetails(credentials);
    }

    @Transactional
    public void changePassword(String email, PasswordChange params) {
        UserEntity user = userRepository.findByEmail(email, UserEntity.class);

        if (!encoder.matches(params.currentPassword(), user.password))
            throw new RuntimeException("Provided password is incorrect");

        user.password = encoder.encode(params.newPassword());
    }

    public Set<Holiday> findUserHolidays(String email) {
        UserEntity user = userRepository.findByEmail(email, UserEntity.class);

        if (user.holidays.isEmpty())
            throw new RuntimeException("No holidays found");

        return holidayRepository.findByUser(toIDs(user.holidays));
    }

    @Transactional
    public void updateHolidays(String email, Set<Long> ids) {
        List<HolidayEntity> holidays = holidayRepository.findAllById(ids);
        UserEntity user = userRepository.findByEmail(email, UserEntity.class);
        user.holidays = holidays;
    }

}
