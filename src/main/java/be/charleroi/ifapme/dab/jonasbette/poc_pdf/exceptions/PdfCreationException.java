package be.charleroi.ifapme.dab.jonasbette.poc_pdf.exceptions;

public class PdfCreationException extends RuntimeException {

    public PdfCreationException(String message) {
        super(message);
    }

    public PdfCreationException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
