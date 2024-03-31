package io.zed.formforgebe.respositories;

import io.zed.formforgebe.domains.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {
    int countByFormId(UUID formId);

    List<Topic> findAllByFormIdOrderByElementOrderAsc(UUID formId);
}
