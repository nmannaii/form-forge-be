package io.zed.formforgebe.dtos;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link io.zed.formforgebe.domains.Topic}
 */
@Builder
public record TopicDto(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UUID id,
        String name,
        String description,
        int elementOrder
) {
}
