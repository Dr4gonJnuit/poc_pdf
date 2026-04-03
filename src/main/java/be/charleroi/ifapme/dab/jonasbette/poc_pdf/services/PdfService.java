package be.charleroi.ifapme.dab.jonasbette.poc_pdf.services;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

import be.charleroi.ifapme.dab.jonasbette.poc_pdf.domain.Letter;
import be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto.commands.CreatePdfCommand;
import be.charleroi.ifapme.dab.jonasbette.poc_pdf.mappers.LetterMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class PdfService {

        private final LetterMapper letterMapper;
        private final PdfRenderer pdfRenderer;
    
    // Créer un POC pour générer un fichier PDF directement en Java, à partir d’un
    // texte et d’une adresse (par exemple, une lettre avec l’adresse d’expédition
    // et le contenu du message). Avoir un petit logo afficher dans le coin et une
    // signature en bas.

    public Path generateLetter(CreatePdfCommand command) {

        Letter letter = letterMapper.toDomain(command);

        return pdfRenderer.render(letter);
    }
}
