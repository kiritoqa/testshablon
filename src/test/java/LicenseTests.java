import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Step;
import org.testng.annotations.*;
import widgets.AboutProjec;
import widgets.BasePage;

import java.net.MalformedURLException;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.switchTo;
import static io.qameta.allure.Allure.step;

@Listeners({TestAllureListener.class})
public class LicenseTests extends BaseClass {
    BasePage base = new BasePage();
    AboutProjec aboutProjec = new AboutProjec();
    String env;

    @Parameters({"app", "config", "browser", "width", "height"})
    @BeforeMethod(description = "Настройки Окружения")
    @Step("Устройство: {0} Браузер:{2} Ширина: {3}  Высота: {4} ")
    public void BeforeClass(String app, String config, String browser, int width, int height) throws MalformedURLException {
        initialize_driver(config,browser,width,height);
        env = app;
    }

    @AfterMethod(description = "Закрыть Браузер")
    void afterMethod(){
        Selenide.closeWindow();
    }

    @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    void licenseShouldBeValid(Map<String, String> testData){
        String project = testData.get("project");
        String text = testData.get("text");
        String website = testData.get("website");
        AllureLifecycle lifecycle = Allure.getLifecycle();
        lifecycle.updateTestCase(testResult -> testResult.setName("Проверка лицензии на "+website));
        step("Открыть страницу О проекте",()->{
            base
                    .open(project)
                    .closeRegistrationPage()
                    .openMenu()
                    .selectAboutProject();
            getScreen("Окно: O проекте");

        });
        step("Открыть страницу Проверить Лицензию",()->{
            aboutProjec
                    .clickLicense()
                    .licenseShouldHaveText(text)
                    .clickCheckLicense();
        });
        step("Открыть страницу Подтверждение лицензии",()->{
            aboutProjec
                    .licenseShouldBeHaveWebsite(website)
                    .operatingStatusShouldBeValid();
        });
        getScreen("Окно Подтверждение лицензии");
    }

}
