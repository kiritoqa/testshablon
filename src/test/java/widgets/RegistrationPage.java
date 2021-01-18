package widgets;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class RegistrationPage extends BasePage{
    @Step("Закрыть окно Регистрация")
    public MainPage closeRegistrationPage()  {
        switchTo().frame(0);
        $("#initial_modal .close_modal").click();
    return new MainPage();
    }
}
