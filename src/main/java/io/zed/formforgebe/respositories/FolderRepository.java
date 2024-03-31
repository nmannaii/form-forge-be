package io.zed.formforgebe.respositories;

import io.zed.formforgebe.domains.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FolderRepository extends JpaRepository<Folder, UUID> {
    List<Folder> findAllByOrderByVisitedAtDesc();
}
