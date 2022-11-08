package ru.netology;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @Test
    void test1(){
        String planningDate = generateDate(15);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $x("//input[@placeholder='Город']").setValue("Екатеринбург");
        $x("//input [@placeholder='Дата встречи']").doubleClick();
        $x("//input [@placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $x("//input[@placeholder = 'Дата встречи']").setValue(planningDate);
        $x("//input[@name = 'name']").setValue("Трапезникова Кристина");
        $x("//input [@name ='phone']").setValue("+79227280798");
        $("[data-test-id='agreement']").click();
        $("[type='button'] .button__text").click();
        $x("//*[contains(text(),'Успешно!')]").should(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);



    }

    
}
