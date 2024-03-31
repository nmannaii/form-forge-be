package io.zed.formforgebe.services;

import io.zed.formforgebe.domains.Folder;
import io.zed.formforgebe.domains.Form;
import io.zed.formforgebe.dtos.FormDto;
import io.zed.formforgebe.dtos.SaveFormRequest;
import io.zed.formforgebe.dtos.TopicDto;
import io.zed.formforgebe.respositories.FolderRepository;
import io.zed.formforgebe.respositories.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;
    private final FolderRepository folderRepository;

    public UUID saveForm(SaveFormRequest form, UUID folderId) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found"));
        return formRepository.save(
                Form.builder()
                        .name(form.name())
                        .description(form.description())
                        .folder(folder)
                        .build()
        ).getId();
    }

    public List<FormDto> getFormsByFolder(UUID folderId) {
        return formRepository.findAllByFolderId(folderId)
                .stream()
                .map(f -> FormDto.builder()
                        .createdAt(f.getCreatedAt())
                        .updatedAt(f.getUpdatedAt())
                        .id(f.getId())
                        .name(f.getName())
                        .description(f.getDescription())
                        .topics(f.getTopics().stream()
                                .map(t -> TopicDto.builder()
                                        .createdAt(t.getCreatedAt())
                                        .updatedAt(t.getUpdatedAt())
                                        .id(t.getId())
                                        .name(t.getName())
                                        .description(t.getDescription())
                                        .elementOrder(t.getElementOrder())
                                        .build())
                                .toList())
                        .build()
                ).toList();
    }

    public FormDto getFormById(UUID formId, boolean includeTopics) {
        return formRepository.findById(formId)
                .map(f -> {
                    var formDto = FormDto.builder()
                            .createdAt(f.getCreatedAt())
                            .updatedAt(f.getUpdatedAt())
                            .id(f.getId())
                            .name(f.getName())
                            .description(f.getDescription())
                            .topics(new ArrayList<>())
                            .build();
                    if (includeTopics) {
                        formDto.topics().addAll(
                                f.getTopics().stream()
                                        .map(t -> TopicDto.builder()
                                                .createdAt(t.getCreatedAt())
                                                .updatedAt(t.getUpdatedAt())
                                                .id(t.getId())
                                                .name(t.getName())
                                                .description(t.getDescription())
                                                .elementOrder(t.getElementOrder())
                                                .build())
                                        .toList()
                        );
                    }

                    return formDto;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Form not found"));
    }
}
