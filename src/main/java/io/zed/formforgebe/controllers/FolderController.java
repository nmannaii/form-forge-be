package io.zed.formforgebe.controllers;

import io.zed.formforgebe.dtos.FolderDto;
import io.zed.formforgebe.dtos.SaveFolderRequest;
import io.zed.formforgebe.services.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @GetMapping("folders")
    List<FolderDto> folders() {
        return folderService.getFolders();
    }

    @PostMapping("folders")
    UUID saveFolder(@RequestBody SaveFolderRequest folder) {
        return folderService.saveFolder(folder);
    }

    @PatchMapping("folders/{folderId}/visited-at")
    void updateVisitedAt(@PathVariable UUID folderId) {
        folderService.updateVisitedAt(folderId);
    }
}
