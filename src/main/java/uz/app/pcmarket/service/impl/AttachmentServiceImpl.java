package uz.app.pcmarket.service.impl;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.app.pcmarket.entity.Attachment;
import uz.app.pcmarket.repository.AttachmentRepository;
import uz.app.pcmarket.service.AttachmentService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Value("${file.upload.base-path}")
    private String UPLOAD_DIR;

    @SneakyThrows
    public void download(UUID fileId, HttpServletResponse resp) {
        Attachment attachment = attachmentRepository.findById(fileId)
                .orElseThrow(() -> new NoSuchElementException("File not found with id: " + fileId));

        String path = UPLOAD_DIR + attachment.getId() + attachment.getSuffix();

        File file = new File(path);

        if (!file.exists()) {
            throw new NoSuchElementException("File not found at path: " + path);
        }

        resp.setHeader("Content-disposition", "attachment; filename=\"" + attachment.getName() + "\"");
        resp.setContentType("application/octet-stream");

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] content = new byte[fileInputStream.available()];
            fileInputStream.read(content);
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.write(content);
        }
    }

    @Override
    @Transactional
    public Attachment upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String[] split = file.getOriginalFilename().split("\\.");
        String suffix = "." + split[split.length - 1];

        Attachment build = Attachment.builder()
                .name(file.getOriginalFilename())
                .suffix(suffix)
                .fileSize(file.getSize())
                .path(UPLOAD_DIR)
                .build();

        Attachment attachment = attachmentRepository.save(build);

        String path = UPLOAD_DIR + attachment.getId().toString().concat(suffix);

        try (OutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(file.getInputStream().readAllBytes());
            fileOutputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage(), e);
        }

        return attachment;
    }
}
