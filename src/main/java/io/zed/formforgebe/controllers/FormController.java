package io.zed.formforgebe.controllers;

import io.zed.formforgebe.dtos.FormDto;
import io.zed.formforgebe.dtos.SaveFormRequest;
import io.zed.formforgebe.services.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

    @GetMapping("folders/{folderId}/forms")
    List<FormDto> forms(@PathVariable UUID folderId) {
        return formService.getFormsByFolder(folderId);
    }

    @GetMapping("forms/{formId}")
    FormDto getFormById(@PathVariable UUID formId, @RequestParam(required = false) boolean includeTopics) {
        return formService.getFormById(formId, includeTopics);
    }

    @PostMapping("folders/{folderId}/forms")
    UUID saveForm(@PathVariable UUID folderId, @RequestBody SaveFormRequest form) {
        return formService.saveForm(form, folderId);
    }

}
