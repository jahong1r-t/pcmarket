package uz.app.pcmarket.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException e, Model model) {
        model.addAttribute("errorTitle", "Not Found");
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("status", 404);
        return "error";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleUniqueConstraintViolation(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("errorTitle", "Conflict");
        model.addAttribute("errorMessage", "This value already exists in the system.");
        model.addAttribute("status", 409);
        return "error";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, Model model) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        model.addAttribute("errorTitle", "Validation Error");
        model.addAttribute("errors", errors);
        model.addAttribute("status", 400);
        return "error";
    }

    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalArgumentException(IllegalStateException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("status", HttpStatus.CONFLICT);
        return "error";
    }
}
