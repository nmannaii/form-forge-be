package io.zed.formforgebe.dtos;

import java.util.UUID;

public record SaveTopicRequest(
        UUID id,
        String name,
        String description
) {
}
