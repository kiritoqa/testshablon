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

    @Parameters({"app", "config", "browser", "width", "height"})
    @BeforeClass(description = "Environment Configs")
    @Step("Application: {0} Browser:{2} width: {3}  height: {4} ")
    public void BeforeClass(String app, String config, String browser, int width, int height) throws MalformedURLException {
        initialize_driver(config,browser,width,height);
        env = app;
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
        base.open(data);
    }

    @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    void methodName2(Map<String, String> testData){
        String data = testData.get("data");
        AllureLifecycle lifecycle = Allure.getLifecycle();
        lifecycle.updateTestCase(testResult -> testResult.setName("Name of the test"));
        base.open(data);
    }
}
