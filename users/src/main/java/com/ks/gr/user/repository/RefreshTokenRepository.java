package com.ks.gr.user.repository;

import com.ks.gr.user.entity.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findByToken(String token);
    Optional<RefreshTokenEntity> findByUserId(Long userID);
}
