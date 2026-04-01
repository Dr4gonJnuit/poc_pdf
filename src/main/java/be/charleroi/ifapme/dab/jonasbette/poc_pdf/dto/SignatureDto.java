package be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignatureDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String title; // e.g., "Dr.", "Ing.", "Mr.", "Ms."

    // private String signatureImageUrl; // URL or path to the signature image

}
