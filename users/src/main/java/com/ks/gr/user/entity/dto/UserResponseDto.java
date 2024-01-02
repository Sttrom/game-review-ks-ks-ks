package com.ks.gr.user.entity.dto;

import com.ks.gr.user.entity.enumeration.Roles;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserResponseDto(Long id,
                              String username,
                              String email,
                              Set<Roles> roles,
                              String steamLink,
                              String about,
                              String avatar) { //FIXME avatar type
}
