package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    private DataGenerator() {
    }



    public static class Registration {
        private Registration() {
        }


        public static GetRegistrationInfo registrationInfo() {
            Faker faker = new Faker(new Locale("ru"));
            return new GetRegistrationInfo(faker.address().city(), faker.name().fullName(), faker.phoneNumber().phoneNumber());

        }

        public static GetRegistrationFakeInfo registrationFakeInfo() {
            Faker fakerEng = new Faker(new Locale("eng"));
            return new GetRegistrationFakeInfo(fakerEng.address().city(), fakerEng.name().fullName());

        }



        public static String getVisitDate(int plusDays) {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate newDate=today.plusDays(plusDays);
            return formatter.format(newDate);
        }

    }




}
