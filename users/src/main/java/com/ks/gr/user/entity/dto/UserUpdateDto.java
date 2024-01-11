package com.ks.gr.user.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(@NotNull(message = "id must be present") long id,
                            @Size(min = 3, max = 20, message = "min username length 3, max 20") String username,

                            @Email(message = "email is invalid") String email,
                            String password,
                            String steamLink,
                            String about,
                            String avatar) { //FIXME avatar type
}
