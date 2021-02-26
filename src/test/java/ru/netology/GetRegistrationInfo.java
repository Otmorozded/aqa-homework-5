package ru.netology;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class GetRegistrationInfo {

    private final String city;
    private final String name;
    private final String phone;


}
