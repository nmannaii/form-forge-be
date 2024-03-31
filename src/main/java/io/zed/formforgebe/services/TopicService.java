package io.zed.formforgebe.services;

import io.zed.formforgebe.domains.Form;
import io.zed.formforgebe.domains.Topic;
import io.zed.formforgebe.dtos.SaveTopicRequest;
import io.zed.formforgebe.dtos.TopicDto;
import io.zed.formforgebe.respositories.FormRepository;
import io.zed.formforgebe.respositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final FormRepository formRepository;

    public UUID saveTopic(SaveTopicRequest topicRequest, UUID formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Form not found"));
        int topicsCount = topicRepository
                .countByFormId(formId);
        Topic topic = Topic.builder()
                .name(topicRequest.name())
                .elementOrder(topicsCount + 1)
                .description(topicRequest.description())
                .form(form)
                .build();
        return topicRepository.save(topic).getId();
    }

    public List<TopicDto> getTopicsByFormId(UUID formId) {
        return topicRepository.findAllByFormIdOrderByElementOrderAsc(formId)
                .stream()
                .map(topic -> TopicDto
                        .builder()
                        .id(topic.getId())
                        .elementOrder(topic.getElementOrder())
                        .name(topic.getName())
                        .description(topic.getDescription())
                        .createdAt(topic.getCreatedAt())
                        .updatedAt(topic.getUpdatedAt())
                        .build()
                ).toList();
    }
}
