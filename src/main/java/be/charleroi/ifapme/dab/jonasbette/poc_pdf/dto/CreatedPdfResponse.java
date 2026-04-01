package be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatedPdfResponse {

    private String path;
    
}
