package com.ks.gr.user.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(@NotBlank @Size(min = 3, max = 20) String username,
                              @NotBlank String password) {
}
