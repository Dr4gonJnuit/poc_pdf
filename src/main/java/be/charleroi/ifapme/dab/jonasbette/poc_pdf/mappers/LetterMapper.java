package be.charleroi.ifapme.dab.jonasbette.poc_pdf.mappers;

import org.springframework.stereotype.Service;

import be.charleroi.ifapme.dab.jonasbette.poc_pdf.domain.Address;
import be.charleroi.ifapme.dab.jonasbette.poc_pdf.domain.Letter;
import be.charleroi.ifapme.dab.jonasbette.poc_pdf.domain.Signature;
import be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto.commands.CreatePdfCommand;

@Service
public class LetterMapper {
    
    public Letter toDomain(CreatePdfCommand command) {
        Address address = new Address(command.getAddress());
        Signature signature = new Signature(command.getSignature());

        return Letter.builder()
                .address(address)
                .content(command.getContenu())
                .logoUrl(command.getLogoUrl().toString())
                .signature(signature)
                .build();
    }
    
}
