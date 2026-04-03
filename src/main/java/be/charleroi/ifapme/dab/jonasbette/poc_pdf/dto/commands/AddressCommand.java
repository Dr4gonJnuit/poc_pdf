package be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto.commands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddressCommand {

    private String destFirstName;
    private String destLastName;

    @Positive
    private Integer houseNumber;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @Min(0)
    private Integer postalCode;

    @NotBlank
    private String country;

}
