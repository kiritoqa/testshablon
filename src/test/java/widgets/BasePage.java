package widgets;
import com.codeborne.selenide.Selenide;
import static io.qameta.allure.Allure.step;

public class BasePage {
    String url ="";
    String url1 ="";
    String url2 ="";

    public String getUrl(String broker){
        switch (broker) {
            case "broker":
                return url1;
            case "broker1":
                return url2;
            default:
                return url;
        }
    }
    public void open(String broker){
        step("Open "+getUrl(broker), () -> {
            Selenide.open(getUrl(broker));
        });}
}
