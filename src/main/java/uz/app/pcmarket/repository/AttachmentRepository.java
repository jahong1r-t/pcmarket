package uz.app.pcmarket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.pcmarket.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
