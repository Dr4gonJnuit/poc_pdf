package be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreatePdfCommand {

    @NotNull
    private AddressCommand address;

    @NotBlank // ni null ni vide -> "", " ", null n'est pas accepté mais "abc" ou "123" sont acceptés
    private String contenu;

    @NotBlank
    private String logoUrl;

    /**
     * A simple signature field, but in a real application, this could be more
     * complex (e.g., an image or a digital signature).
     */
    @NotNull
    private SignatureCommand signature;

}
