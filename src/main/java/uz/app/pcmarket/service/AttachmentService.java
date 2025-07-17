package uz.app.pcmarket.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import uz.app.pcmarket.entity.Attachment;

import java.util.UUID;

public interface AttachmentService {
    void download(UUID fileId, HttpServletResponse resp);

    Attachment upload(MultipartFile file);
}
