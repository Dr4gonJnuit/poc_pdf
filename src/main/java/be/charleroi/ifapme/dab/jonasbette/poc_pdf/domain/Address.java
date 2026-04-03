package be.charleroi.ifapme.dab.jonasbette.poc_pdf.domain;

import be.charleroi.ifapme.dab.jonasbette.poc_pdf.dto.commands.AddressCommand;

public class Address {

    private String destFirstName;
    private String destLastName;
    private String street;
    private Integer houseNumber;
    private String city;
    private Integer postalCode;
    private String country;

    public String format() {
        StringBuilder addressBuilder = new StringBuilder();

        if (destFirstName != null && !destFirstName.isEmpty()) {
            addressBuilder.append(destFirstName).append(" ");
        }

        if (destLastName != null && !destLastName.isEmpty()) {
            addressBuilder.append(destLastName).append("\n");
        }

        addressBuilder
                .append(street).append(", ").append(houseNumber)
                .append("\n")
                .append(postalCode).append(" - ").append(city)
                .append("\n")
                .append(country);

        return addressBuilder.toString();
    }

    public Address(AddressCommand command) {
        this.destFirstName = command.getDestFirstName();
        this.destLastName = command.getDestLastName();
        this.street = command.getStreet();
        this.houseNumber = command.getHouseNumber();
        this.city = command.getCity();
        this.postalCode = command.getPostalCode();
        this.country = command.getCountry();
    }

}
