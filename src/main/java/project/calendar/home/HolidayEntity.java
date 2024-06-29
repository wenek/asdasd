package project.calendar.home;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import project.calendar.general.validation.Month;
import project.calendar.user.UserEntity;

@Entity
@Table(name = "holidays")
public class HolidayEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Long id;

    @NotBlank
    @Size(max = 500)
    public String name;

    @Min(1)
    public byte dayValue;

    @Month
    public byte monthValue;

    @ManyToMany(mappedBy = "holidays")
    public List<UserEntity> users = new ArrayList<>();

}
