package com.ks.gr.user.service;

import com.ks.gr.user.entity.RefreshTokenEntity;
import com.ks.gr.user.entity.UserEntity;
import com.ks.gr.user.entity.dto.*;
import com.ks.gr.user.entity.enumeration.Roles;
import com.ks.gr.user.repository.UserRepository;
import com.ks.gr.user.security.ApplicationUserDetails;
import com.ks.gr.user.security.jwt.JwtUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    public AuthResponseDto authenticate(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.username(), loginRequestDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        ApplicationUserDetails userDetails = (ApplicationUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        RefreshTokenEntity refreshTokenEntity = refreshTokenService.createRefreshToken(userDetails.getId());
        return AuthResponseDto.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .accessToken(jwtUtils.generateAccessToken(userDetails))
                .refreshToken(refreshTokenEntity.getToken())
                .roles(roles)
                .build();
    }

    public void register(UserCreateDto userCreateDto) {
        if (userRepository.existsByUsername(userCreateDto.username())) {
            throw new RuntimeException("Username " + userCreateDto.username() + " already exists"); //TODO specialized exception
        }
        if ((userRepository.existsByEmail(userCreateDto.email()))) {
            throw new RuntimeException("Email " + userCreateDto.email() + " is already used"); //TODO specialized exception
        }
        userRepository.save(UserEntity.builder()
                .roles(Set.of(Roles.ROLE_USER))
                .username(userCreateDto.username())
                .email(userCreateDto.email())
                .password(passwordEncoder.encode(userCreateDto.password()))
                .steamLink(userCreateDto.steamLink())
                .about(userCreateDto.about())
                .avatar(userCreateDto.avatar())
                .build());
    }

    public TokenResponseDto refreshToken(RefreshTokenDto refreshTokenDto) {
        RefreshTokenEntity refreshToken = refreshTokenService.findByRefreshToken(refreshTokenDto.refreshToken());
        refreshTokenService.checkRefreshToken(refreshToken);
        UserEntity userEntity = userRepository.findById(refreshToken.getUserId()).orElseThrow(() ->
                new EntityNotFoundException("Exception getting refresh token for user " + refreshToken.getUserId()));
        refreshTokenService.deleteByUserId(refreshToken.getUserId());

        return TokenResponseDto.builder()
                .refreshToken(refreshTokenService.createRefreshToken(userEntity.getId()).getToken())
                .accessToken(jwtUtils.generateTokenFromUsername(userEntity.getUsername()))
                .build();
    }

    public void logout() {
        Object currentPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentPrincipal instanceof ApplicationUserDetails userDetails) {
            refreshTokenService.deleteByUserId(userDetails.getId());
        }
    }
}
