package be.charleroi.ifapme.dab.jonasbette.poc_pdf.domain;

import be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto.commands.SignatureCommand;

public class Signature {

    private String title;
    private String firstName;
    private String lastName;

    public String format() {
        StringBuilder signatureBuilder = new StringBuilder();

        if (title != null && !title.isEmpty()) {
            signatureBuilder.append(title).append(" ");
        }

        signatureBuilder.append(firstName).append(" ").append(lastName);

        return signatureBuilder.toString();
    }

    public Signature(SignatureCommand command) {
        this.title = command.getTitle();
        this.firstName = command.getFirstName();
        this.lastName = command.getLastName();
    }

}
