package uz.app.pcmarket.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.app.pcmarket.service.AttachmentService;

import java.util.UUID;

@Controller
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @GetMapping("/download/{fileId}")
    public void downloadAttachment(@PathVariable UUID fileId, HttpServletResponse resp) {
        attachmentService.download(fileId, resp);
    }
}
