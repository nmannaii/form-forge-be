package io.zed.formforgebe.services;

import io.zed.formforgebe.domains.Folder;
import io.zed.formforgebe.dtos.FolderDto;
import io.zed.formforgebe.dtos.SaveFolderRequest;
import io.zed.formforgebe.respositories.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FolderService {
    private final FolderRepository folderRepository;

    public UUID saveFolder(SaveFolderRequest saveFolderRequest) {
        Folder folder = Folder.builder()
                .name(saveFolderRequest.name())
                .description(saveFolderRequest.description())
                .color(saveFolderRequest.color())
                .build();
        return folderRepository.save(folder).getId();
    }

    public List<FolderDto> getFolders() {
        return folderRepository.findAllByOrderByVisitedAtDesc()
                .stream()
                .map(u -> FolderDto.builder()
                        .id(u.getId())
                        .name(u.getName())
                        .description(u.getDescription())
                        .visitedAt(u.getVisitedAt())
                        .color(u.getColor())
                        .createdAt(u.getCreatedAt())
                        .updatedAt(u.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());
    }

    public void updateVisitedAt(UUID folderId) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found"));
        folder.setVisitedAt(LocalDateTime.now());
        folderRepository.save(folder);
    }
}
