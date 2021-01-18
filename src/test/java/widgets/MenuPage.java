package widgets;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class MenuPage extends BasePage{
    @Step("Выбрать "+'"'+"O проекте"+'"')
    public AboutProjec selectAboutProject() {
        $(".content > .item:nth-child(10) > span:nth-child(2)").click();
        switchTo().defaultContent();
       return new AboutProjec();
    }
}
