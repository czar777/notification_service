package faang.school.notificationservice.messaging.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.notificationservice.client.service.UserServiceClient;
import faang.school.notificationservice.dto.redis.FollowerEvent;
import faang.school.notificationservice.messaging.message_builder.MessageBuilder;
import faang.school.notificationservice.service.NotificationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class FollowerEventListener extends AbstractEventListener<FollowerEvent> implements MessageListener {

    @Autowired
    public FollowerEventListener(ObjectMapper objectMapper, UserServiceClient userServiceClient,
                                 MessageBuilder<FollowerEvent> messageBuilders,
                                 List<NotificationService> notificationServices) {
        super(objectMapper, userServiceClient, messageBuilders, notificationServices);
    }

    @Override
    public void onMessage(@NonNull Message message, byte[] pattern) {
        FollowerEvent event = mapEvent(message, FollowerEvent.class);
        String text = getMessage(event, Locale.getDefault());
        sendNotification(event.getFolloweeId(), text);
    }
}