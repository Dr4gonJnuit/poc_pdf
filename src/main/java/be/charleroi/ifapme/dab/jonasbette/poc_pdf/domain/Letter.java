package be.charleroi.ifapme.dab.jonasbette.poc_pdf.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Letter {

    private Address address;
    private String content;
    private String logoUrl;
    private Signature signature;

}
