package com.mitrais.rms.datasource.entity;

public class Car {

    private Long id;
    private String registrationNo;
    private String colour;

    public Car(Long id, String registrationNo, String colour){
        this.id = id;
        this.registrationNo = registrationNo;
        this.colour = colour;
    }

    public Car(String registrationNo, String colour) {
        this.registrationNo = registrationNo;
        this.colour = colour;
    }

    public Long getId() {
        return id;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
