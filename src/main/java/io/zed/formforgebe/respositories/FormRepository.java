package io.zed.formforgebe.respositories;

import io.zed.formforgebe.domains.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FormRepository extends JpaRepository<Form, UUID> {
    List<Form> findAllByFolderId(UUID folderId);
}
