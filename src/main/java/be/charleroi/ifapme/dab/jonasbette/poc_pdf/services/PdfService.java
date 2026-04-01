package be.charleroi.ifapme.dab.jonasbette.poc_pdf.services;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto.commands.CreatePdfCommand;
import be.charleroi.ifapme.dab.jonasbette.poc_pdf.exceptions.PdfCreationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PdfService {

    // Créer un POC pour générer un fichier PDF directement en Java, à partir d’un
    // texte et d’une adresse (par exemple, une lettre avec l’adresse d’expédition
    // et le contenu du message). Avoir un petit logo afficher dans le coin et une
    // signature en bas.

    public Path generateLetter(CreatePdfCommand command) {

        try {
            Path outputPath = Path.of("letter.pdf");

            PdfWriter writer = new PdfWriter(outputPath.toString());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Logo
            Image logo = new Image(ImageDataFactory.create(command.getLogoUrl().toString()));
            logo.scaleToFit(150, 200)
                    .setFixedPosition(400, 700); // Positionner le logo en haut à gauche
            document.add(logo);

            StringBuilder addressBuilder = new StringBuilder()
                    .append(command.getSignature().getFirstName())
                    .append(" ")
                    .append(command.getSignature().getLastName())
                    .append("\n")
                    .append(command.getAddress().getStreet())
                    .append(", ")
                    .append(command.getAddress().getHouseNumber())
                    .append("\n")
                    .append(command.getAddress().getPostalCode())
                    .append(" - ")
                    .append(command.getAddress().getCity())
                    .append("\n")
                    .append(command.getAddress().getCountry());

            // Adresse
            Paragraph address = new Paragraph()
                    .add(addressBuilder.toString());

            document.add(address);

            // Contenu lettre
            Paragraph content = new Paragraph()
                    .add(command.getContenu())
                    .setMarginTop(50);
            document.add(content);

            // Signature (alignée en bas à droite)
            StringBuilder signatureBuilder = new StringBuilder()
                    .append(command.getSignature().getTitle().isBlank()
                            ? ""
                            : command.getSignature().getTitle() + " ")
                    .append(command.getSignature().getFirstName())
                    .append(" ")
                    .append(command.getSignature().getLastName());

            Paragraph signature = new Paragraph("Signature:")
                    .add("\n")
                    .add(signatureBuilder.toString())
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(50);
            document.add(signature);

            document.close();

            log.info("PDF generated successfully at path: {}", outputPath.toAbsolutePath());

            return outputPath.toAbsolutePath();
        } catch (Exception e) {
            // Log the exception (using a logger, e.g., SLF4J)
            log.error("Error generating PDF", e);
            throw new PdfCreationException("An error occurred while creating the PDF", e);
        }
    }
}
