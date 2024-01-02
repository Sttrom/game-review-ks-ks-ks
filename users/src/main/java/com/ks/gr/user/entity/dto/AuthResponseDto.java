package com.ks.gr.user.entity.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AuthResponseDto(Long id,
                              String username,
                              String email,
                              String accessToken,
                              String refreshToken,
                              List<String> roles) {
}
