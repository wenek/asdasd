package project.calendar.user;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import project.calendar.general.validation.Password;
import project.calendar.home.HolidayEntity;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Long id;

    @Email
    @NotNull
    @Size(max = 254)
    public String email;

    @NotBlank
    @Password
    public String password;

    @ManyToMany(
            cascade = REMOVE)
    @JoinTable(
            name = "users_holidays",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "holiday_id", referencedColumnName = "id"))
    List<HolidayEntity> holidays = new ArrayList<>();

}
