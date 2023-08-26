package faang.school.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventStartEventDto {
    private long id;
    private String title;
    private List<Long> userIds;
    private EventCountdown eventCountdown;
}