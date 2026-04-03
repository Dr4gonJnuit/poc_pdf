package be.charleroi.ifapme.dab.jonasbette.poc_pdf.services;

import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import be.charleroi.ifapme.dab.jonasbette.poc_pdf.domain.Letter;
import be.charleroi.ifapme.dab.jonasbette.poc_pdf.exceptions.PdfCreationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PdfRenderer {

    public Path render(Letter letter) {

        try {
            Path outputPath = Path.of("letter.pdf");

            PdfWriter writer = new PdfWriter(outputPath.toString());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            Table table = new Table(UnitValue.createPercentArray(new float[] { 4, 1 })).useAllAvailableWidth();

            // Adresse (gauche)
            Cell addressCell = new Cell()
                    .add(new Paragraph(letter.getAddress().format()))
                    .setBorder(Border.NO_BORDER);

            // Logo (droite)
            Image logo = new Image(ImageDataFactory.create(letter.getLogoUrl()));
            logo.scaleToFit(100, 100);
            logo.setHorizontalAlignment(HorizontalAlignment.RIGHT);

            Cell logoCell = new Cell()
                    .add(logo)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT);

            // Ajout des cellules
            table.addCell(addressCell);
            table.addCell(logoCell);

            // Ajout au document
            document.add(table);

            // Contenu lettre
            List<IElement> contentElements = HtmlConverter.convertToElements(letter.getContent());

            Div content = new Div().setMarginTop(20);
            for (IElement element : contentElements) {
                if (element instanceof IBlockElement block) {
                    content.add(block);
                }
            }

            document.add(content);

            // Signature (alignée en bas à droite)
            PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            Paragraph signature = new Paragraph()
                    .add(new Text("Signature:").setFont(boldFont))
                    .add("\n")
                    .add(letter.getSignature().format())
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
