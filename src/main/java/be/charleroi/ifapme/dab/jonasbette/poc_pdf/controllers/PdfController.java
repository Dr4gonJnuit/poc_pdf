package be.charleroi.ifapme.dab.jonasbette.poc_pdf.controllers;

import java.nio.file.Path;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto.CreatedPdfResponse;
import be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto.commands.CreatePdfCommand;
import be.charleroi.ifapme.dab.jonasbette.poc_pdf.services.PdfService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/pdf")
@AllArgsConstructor
public class PdfController {

    // @Slf4j equivalent:
    // private final Logger logger = LoggerFactory.getLogger(PdfController.class);

    private final PdfService pdfService;

    /**
     * 
     * @param command
     * @return
     */
    @PostMapping
    public ResponseEntity<CreatedPdfResponse> createPdf(@RequestBody @Valid CreatePdfCommand command) {

        Path pdfPath = pdfService.generateLetter(command);

        CreatedPdfResponse response = CreatedPdfResponse.builder()
                .path(pdfPath.toString())
                // .autres champs...
                .build();

        return ResponseEntity.ok(response);
    }

}
