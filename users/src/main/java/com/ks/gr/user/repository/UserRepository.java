package com.ks.gr.user.repository;

import com.ks.gr.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername (String username);
    Optional<UserEntity> findByEmail (String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);



}
