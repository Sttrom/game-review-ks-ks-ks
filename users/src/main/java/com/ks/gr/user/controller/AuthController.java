package com.ks.gr.user.controller;

import com.ks.gr.user.entity.dto.*;
import com.ks.gr.user.service.SecurityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final SecurityService securityService;

    @GetMapping("/all")
    public BaseResponseDto all() {
        return new BaseResponseDto("all auth");
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return securityService.authenticate(loginRequestDto);
    }

    @PostMapping("/register")
    public BaseResponseDto register(@RequestBody @Valid UserCreateDto userCreateDto) {
        securityService.register(userCreateDto);
        return new BaseResponseDto("Succefully registered new user " + userCreateDto.username());
    }

    @PostMapping("/refresh")
    public TokenResponseDto refreshTokens(@RequestBody RefreshTokenDto refreshTokenDto) {
        return securityService.refreshToken(refreshTokenDto);
    }

    @PostMapping("/logout")
    public BaseResponseDto logout(@AuthenticationPrincipal UserDetails userDetails) {
        securityService.logout();
        return new BaseResponseDto("Successfully logged out");
    }

}
