package widgets;
import com.codeborne.selenide.Selenide;
import static io.qameta.allure.Allure.step;

public class BasePage {
    String url1="https://grand-casino.com/initial/registration/";
    String url2 ="https://azartzona.com/azartzona/";
    String url3 ="https://zeoncasino.com/initial/registration/";

    public String getUrl(String broker){
        switch (broker) {
            case "grand":
                return url1;
            case "azartzona":
                return url2;
            case "zeoncasino":
                return url3;
            default:
                return url1;
        }
    }
    public void open(String broker){
        step("Open "+getUrl(broker), () -> {
            Selenide.open(getUrl(broker));
        });}
}
