package be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

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
