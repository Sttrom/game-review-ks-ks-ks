package com.ks.gr.user.event;

import com.ks.gr.user.entity.RefreshTokenEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisExpirationEvent {

    @EventListener
    public void handleKeyExpiredEvent(RedisKeyExpiredEvent<RefreshTokenEntity> event) {
        RefreshTokenEntity expiredRefreshToken = (RefreshTokenEntity) event.getValue();

        if (expiredRefreshToken == null) {
            throw new RuntimeException("Refresh token is null, can not be deleted");
        }

        log.debug("Refresh token with key: {} expired", expiredRefreshToken.getId());
    }
}
