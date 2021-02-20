package ru.netology;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class RegistrationFakeInfo {

    private final String city;
    private final String name;

    /*public RegistrationFakeInfo(String city, String name) {
        this.city = city;
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }*/
}

