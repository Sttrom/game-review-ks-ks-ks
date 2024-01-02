package com.ks.gr.user.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDto(@NotBlank @Size(min = 3, max = 20) String username,
                            @Email String email,
                            @NotBlank String password,
                            String steamLink,
                            String about,
                            String avatar) { //FIXME avatar type
}
