package com.ks.gr.user.entity.dto;

import lombok.Builder;

@Builder
public record TokenResponseDto(String accessToken,
                               String refreshToken) {
}
