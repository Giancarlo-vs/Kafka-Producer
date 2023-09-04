package com.co.animalcentral.modulo2.models;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor

public class PetCredit {
    private Integer idPet;
    private Boolean isActive;
    private Double totalAmount;
    private Double amountPaid;
    private String description;

    @Override
    public String toString() {
        return "{" +
                "'idPet':" + idPet +
                ", 'isActive':'" + isActive + '\'' +
                ", 'totalAmount':" + totalAmount +
                ", 'amountPaid':" + amountPaid +
                ", 'description':" + description +
                '}';
    }

}
