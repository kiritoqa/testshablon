package widgets;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    @Step("Навести мышку на кнопку Меню")
    public MenuPage openMenu() {
        $(".menu_button > .combined_label").hover();
        return new MenuPage();
    }
}
