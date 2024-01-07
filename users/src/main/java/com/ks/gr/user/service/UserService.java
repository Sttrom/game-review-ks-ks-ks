package com.ks.gr.user.service;

import com.ks.gr.user.entity.UserEntity;
import com.ks.gr.user.entity.dto.UserResponseDto;
import com.ks.gr.user.entity.dto.UserUpdateDto;
import com.ks.gr.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .steamLink(user.getSteamLink())
                .about(user.getAbout())
                .avatar(user.getAvatar())
                .build()).toList();
    }

    public UserResponseDto getUserById(long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id " + id + " not found!"));
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .steamLink(user.getSteamLink())
                .about(user.getAbout())
                .avatar(user.getAvatar())
                .build();
    }

    public UserResponseDto getUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() ->
                new EntityNotFoundException("User with username " + username + " not found!"));
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .steamLink(user.getSteamLink())
                .about(user.getAbout())
                .avatar(user.getAvatar())
                .build();
    }

    public UserResponseDto getUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException("User with email " + email + " not found!"));
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .steamLink(user.getSteamLink())
                .about(user.getAbout())
                .avatar(user.getAvatar())
                .build();
    }

    public UserResponseDto updateUser(long id, UserUpdateDto userUpdateDto) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("User with id " + id + " not found!");
        }
        UserEntity user = userRepository.save(UserEntity.builder()
                .id(userUpdateDto.id())
                .username(userUpdateDto.username())
                .email(userUpdateDto.email())
//                .password(passwordEncoder.encode(userUpdateDto.password()))
                .steamLink(userUpdateDto.steamLink())
                .about(userUpdateDto.about())
                .avatar(userUpdateDto.avatar())
                .build());
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .steamLink(user.getSteamLink())
                .about(user.getAbout())
                .avatar(user.getAvatar())
                .build();
    }

    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " is not found!");
        }
        userRepository.deleteById(id);
    }
}
