package be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse {
    
    private Integer status;
    private String reasonPhrase;
    private String message;
    private String details;
    
}
