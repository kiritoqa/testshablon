package widgets;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static io.qameta.allure.Allure.step;

public class BasePage {
    String grand="https://grand-casino.com/";
    String azartzona ="https://azartzona.com/";
    String zeoncasino ="https://zeoncasino.com/";
    String bigazart ="https://bigazart.com/";
    String golden ="https://golden-game.com/";
    String xcasino ="https:/xcasino.com/";

    public String getUrl(String broker){
        switch (broker) {
            case "azartzona":
                return azartzona;
            case "zeoncasino":
                return zeoncasino;
            case "bigazart":
                return bigazart;
            case "golden":
                return golden;
            case "xcasino":
                return xcasino;
            default:
                return grand;
        }
    }
    public RegistrationPage open(String broker,String path){
        step("Открыть "+getUrl(broker), () -> {
            Selenide.open(getUrl(broker)+path);
        });
        return new RegistrationPage();
    }

    @Step("Сделать Скриншот")
    public void getScreen(String name){
        try {
            Allure.addAttachment(name, FileUtils.openInputStream(new File(Objects.requireNonNull(Selenide.screenshot(name+"png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
