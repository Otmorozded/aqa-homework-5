package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class AnotherDateTest {

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTestCorrectForm() {
        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.registrationInfo().getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.Registration.getVisitDate(3));
        $("[data-test-id='name'] input").setValue(DataGenerator.Registration.registrationInfo().getName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.Registration.registrationInfo().getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(12));
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.Registration.getVisitDate(3)));
        $("[data-test-id=success-notification] button").click();
    }

    @Test
    void shouldTestAnotherDate() {
        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.registrationInfo().getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.Registration.getVisitDate(3));
        $("[data-test-id='name'] input").setValue(DataGenerator.Registration.registrationInfo().getName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.Registration.registrationInfo().getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(12));
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.Registration.getVisitDate(3)));
        $("[data-test-id=success-notification] button").click();

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.Registration.getVisitDate(6));
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $$("[data-test-id='replan-notification'] button").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.Registration.getVisitDate(6)));
    }


    @Test
    void shouldFailWithWrongCity() {
        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.registrationFakeInfo().getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.Registration.getVisitDate(3));
        $("[data-test-id='name'] input").setValue(DataGenerator.Registration.registrationInfo().getName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.Registration.registrationInfo().getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(exactText("Доставка в выбранный город недоступна"));


    }

    @Test
    void shouldFailWithWrongName() {
        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.registrationInfo().getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.Registration.getVisitDate(3));
        $("[data-test-id='name'] input").setValue(DataGenerator.Registration.registrationFakeInfo().getName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.Registration.registrationInfo().getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFailWithoutAgreement() {
        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.registrationInfo().getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.Registration.getVisitDate(3));
        $("[data-test-id='name'] input").setValue(DataGenerator.Registration.registrationInfo().getName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.Registration.registrationInfo().getPhone());

        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=agreement].input_invalid .checkbox__text")
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldFailWithEmptyCity() {

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.Registration.getVisitDate(3));
        $("[data-test-id='name'] input").setValue(DataGenerator.Registration.registrationInfo().getName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.Registration.registrationInfo().getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldFailWithEmptyPhone() {
        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.registrationInfo().getCity());

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.Registration.getVisitDate(3));
        $("[data-test-id='name'] input").setValue(DataGenerator.Registration.registrationInfo().getName());

        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldFailWithEmptyName() {
        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.registrationInfo().getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.Registration.getVisitDate(3));
        $("[data-test-id='phone'] input").setValue(DataGenerator.Registration.registrationInfo().getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }



    //Тест упадет ,ошибка на форме. Форма принимает не полные телефонные номера

    @Test
    void shouldIncorrectPhone() {
        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.registrationInfo().getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.Registration.getVisitDate(3));
        $("[data-test-id='name'] input").setValue(DataGenerator.Registration.registrationInfo().getName());
        $("[data-test-id='phone'] input").setValue("+7975");
        $("[data-test-id='agreement']").click();
        $(withText("Запланировать")).click();
        $(byText("Номер телефона должен состоять из 11 цифр, начиная с +7")).shouldBe(visible);

    }








}
