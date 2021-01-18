import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Step;
import org.testng.annotations.*;
import widgets.BasePage;

import java.net.MalformedURLException;
import java.util.Map;

@Listeners({TestAllureListener.class})
public class PageTests extends BaseClass {
    BasePage base = new BasePage();
    String env;
    String language;

    @Parameters({"app", "config", "browser","lang", "width", "height"})
    @BeforeMethod(description = "Настройки Окружения")
    @Step("Устройство: {0} Браузер:{2} Язык {3} Ширина: {4}  Высота: {5} ")
    public void BeforeClass(String app, String config, String browser, String lang, int width, int height) throws MalformedURLException {
        initialize_driver(config,browser, lang, width,height);
        env = app;
        language = lang;
    }

    @AfterMethod(description = "Close Browser")
    void afterMethod(){
        Selenide.clearBrowserLocalStorage();
        Selenide.clearBrowserCookies();
    }

    @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    void methodName(Map<String, String> testData){
        String data = testData.get("data");
        AllureLifecycle lifecycle = Allure.getLifecycle();
        lifecycle.updateTestCase(testResult -> testResult.setName("Name of the test"));
        base.open(data,"path");
        getScreen(data);
    }

}
