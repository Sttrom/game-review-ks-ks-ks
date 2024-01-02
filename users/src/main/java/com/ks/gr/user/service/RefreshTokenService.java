package com.ks.gr.user.service;

import com.ks.gr.user.entity.RefreshTokenEntity;
import com.ks.gr.user.repository.RefreshTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${application.jwt.refresh-token-duration}")
    private Duration refreshTokenDuration;

    public RefreshTokenEntity findByRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException("Token does not exist or expired"));
    }

    public RefreshTokenEntity createRefreshToken(Long userId) {
        deleteByUserId(userId);
        return refreshTokenRepository.save(
                RefreshTokenEntity.builder()
                        .userId(userId)
                        .expirationDate(Instant.now().plusMillis(refreshTokenDuration.toMillis()))
                        .token(UUID.randomUUID().toString())
                        .build());
    }

    public RefreshTokenEntity checkRefreshToken(RefreshTokenEntity token) {
        if (token.getExpirationDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired");
        }

        return token;
    }

    public void deleteByUserId(Long userId) {
        Optional<RefreshTokenEntity> refreshToken = refreshTokenRepository.findByUserId(userId);
        refreshToken.ifPresent(refreshTokenRepository::delete);
    }

}
