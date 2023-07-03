package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    String generateDate (int dayToAdd, String pattern){
        return LocalDate.now().plusDays(dayToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldBeSuccessful(){
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Санкт-Петербург");
        //очистить поле дата
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        //ввести дату
        $("[placeholder='Дата встречи']").setValue(generateDate(5,"dd.MM.yyyy"));
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("[class='button__content'").click();
        String currentDate = generateDate(5,"dd.MM.yyyy");
        $(".notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15000)).shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));

    }
}
