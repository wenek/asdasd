package project.calendar.holiday;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public final class HolidayMapper {

    public static Set<Long> toIDs(Collection<HolidayEntity> entities) {
        return entities.stream().map(entity -> entity.id).collect(Collectors.toSet());
    }

}
