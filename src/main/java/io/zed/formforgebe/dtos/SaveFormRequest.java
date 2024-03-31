package io.zed.formforgebe.dtos;

import java.util.UUID;

public record SaveFormRequest(UUID id, String name, String description) {
}
