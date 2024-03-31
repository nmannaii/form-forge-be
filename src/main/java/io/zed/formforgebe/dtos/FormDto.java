package io.zed.formforgebe.dtos;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link io.zed.formforgebe.domains.Form}
 */
@Builder
public record FormDto(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UUID id,
        String name,
        String description,
        List<TopicDto> topics
) {
}
