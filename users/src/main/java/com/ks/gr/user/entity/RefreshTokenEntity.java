package com.ks.gr.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("refresh_token")
public class RefreshTokenEntity {

    @Id
    @Indexed
    private Long id;

    @Indexed
    private Long userId;

    @Indexed
    private String token;

    private Instant expirationDate;

}
