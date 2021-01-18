package widgets;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class AboutProjec extends BasePage{
    @Step("Нажать на текст <<лицензию>>")
    public AboutProjec clickLicense() {
        $("p:nth-child(1) > a").click();
        switchTo().window(1);
        return this;
    }

    @Step("На новой странице должен быть текст - {0}")
    public AboutProjec licenseShouldHaveText(String text) {
        $(".content-license__text:nth-child(2)").shouldHave(Condition.text(text+" работает в соответствии с законами об азартных" +
                " играх государства Кюрасао. Оператором сайта является Nadontil Limited, 25 Voukourestiou, " +
                "NEPTUNE HOUSE, 1st floor, Flat 11, 3045, Limassol, Cyprus; Quirfad Limited, 25 Voukourestiou," +
                " NEPTUNE HOUSE, 1st floor, Flat 11, 3045, Limassol, Cyprus; и имеет лицензию, полностью принадлежащую " +
                "Overcan N.V. Heelsumstraat 51, E-Commerce Park, Curacao."));
        getScreen("Вторая страница");
        return this;
    }
    @Step("Нажать Проверить лицензию")
    public AboutProjec clickCheckLicense() {
        $(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/p[2]/a[1]")).click();
        switchTo().window(2);
        return this;
    }
    @Step("Поле 'Registered Website(s)' должно содержать {0}")
    public AboutProjec licenseShouldBeHaveWebsite(String website) {
        $("span").shouldHave(Condition.text(website));
        return this;
    }
    @Step("Operating status должен содержать текст Valid")
    public void operatingStatusShouldBeValid() {
        $("#operating-status").shouldHave(Condition.text("Operating status: VALID"));
        getScreen("Operating status");
    }
}
