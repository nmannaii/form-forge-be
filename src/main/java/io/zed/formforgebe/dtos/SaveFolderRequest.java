package io.zed.formforgebe.dtos;

import java.util.UUID;

public record SaveFolderRequest(UUID id, String name, String description, String color) {
}
