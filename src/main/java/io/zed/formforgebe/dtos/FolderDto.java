package io.zed.formforgebe.dtos;

import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link io.zed.formforgebe.domains.Folder}
 */
@Builder
public record FolderDto(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime visitedAt,
        UUID id,
        String name,
        String description,
        String color
) {
}
