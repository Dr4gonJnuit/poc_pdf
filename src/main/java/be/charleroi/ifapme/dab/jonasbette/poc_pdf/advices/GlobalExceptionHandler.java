package be.charleroi.ifapme.dab.jonasbette.poc_pdf.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto.ApiResponse;
import be.charleroi.ifapme.dab.jonasbette.poc_pdf.exceptions.PdfCreationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // Handle validation errors

        log.error("Validation error: {}", ex.getMessage(), ex);

        ApiResponse errorResponse = ApiResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed")
                .details(ex.getBindingResult().getFieldErrors().stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                        .orElse("No details"))
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(PdfCreationException.class)
    public ResponseEntity<ApiResponse> handlePdfCreationException(PdfCreationException ex) {
        // Handle PDF creation errors

        log.error("PDF creation error: {}", ex.getMessage(), ex);

        ApiResponse errorResponse = ApiResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .reasonPhrase(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .details("An error occurred while creating the PDF")
                .build();

        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
