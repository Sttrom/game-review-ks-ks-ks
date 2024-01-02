package com.ks.gr.commons.entity.dto;

import lombok.Builder;

@Builder
public record ImageDto(byte[] picture) {
}
