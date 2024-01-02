package com.ks.gr.user.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(@NotNull long id,
                            @Size(min = 3, max = 20) String username,
                            @Email String email,
                            String password,
                            String steamLink,
                            String about,
                            String avatar) { //FIXME avatar type
}
