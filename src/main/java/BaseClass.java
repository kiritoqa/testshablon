import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static io.qameta.allure.Allure.step;

public class BaseClass {
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }
    public WebDriver initialize_driver(String config,String env, int width, int height) throws MalformedURLException{
        switch(config) {
            case "docker":
                WebDriver driver;
                final String url = "http://localhost:4444/wd/hub";
                if (env.equals("firefox")){
                     driver = new RemoteWebDriver(new URL(url), DesiredCapabilities.firefox());}
                else {
                     driver = new RemoteWebDriver(new URL(url), DesiredCapabilities.chrome()); }
                driver.manage().window().setSize(new Dimension(width,height));
                setWebDriver(driver);
                tdriver.set(driver);
                return getDriver();
            case "local":
                if (width==350){
                    System.setProperty("chromeoptions.mobileEmulation", "deviceName=Nexus 5");}
                Configuration.browserSize=width+"x"+height;
                Configuration.browser = env;
                return getDriver();}
        return null;
    }

    public void script_click(By locator){
        Selenide.executeJavaScript("arguments[0].click();", $(locator));
    }

    public void script_over(By locator){
        Selenide.executeJavaScript("mouseOverScript", $(locator));
    }

    public void highLighterMethod(By element){
        Selenide.executeJavaScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", $(element));
    }

    public String offsetTop(By firstElement, By secondElement){
        return String.valueOf($(secondElement).getRect().getY()-$(firstElement).getRect().getY()-$(firstElement).getRect().getHeight());
    }

    public String offsetRight(By firstElement, By secondElement){
        return String.valueOf($(secondElement).getRect().getX()-$(firstElement).getRect().getX()-$(firstElement).getRect().getWidth());
    }

    @Step("Сделать Сркиншот {0}")
    public void getScreen(String name){
        Selenide.sleep(3000);
        try {
            Allure.addAttachment(name, FileUtils.openInputStream(new File(Objects.requireNonNull(Selenide.screenshot(name+"png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEnvironment(String env){
        String soft = "";
        if (env.equals("PC")){
            soft = "expectedPc";}
        if (env.equals("Tablet")){
            soft = "expectedTablet"; }
        if (env.equals("Mobile")){
            soft = "expectedMobile";}
        return soft;
    }

    void checkTopDistanceBetweenTwoElements(String description1,String description2,By element1, By element2, String expectedResult){
        AllureLifecycle lifecycle = Allure.getLifecycle();
        String error = "Incorrect distance between "+description1+" and "+description2;
        lifecycle.updateTestCase(testResult -> testResult.setName("Check distance between "+description1+" and "+description2));
        highLighterMethod(element1);
        highLighterMethod(element2);
        step("Check distance between " +element1+" and " +element2, () -> {
            Assert.assertEquals(offsetTop(element1, element2), expectedResult, error);
        });}

    void checkRightDistanceBetweenTwoElements(String description1,String description2,By element1, By element2, String expectedResult){
        AllureLifecycle lifecycle = Allure.getLifecycle();
        String error = "Incorrect distance between "+description1+" and "+description2;
        lifecycle.updateTestCase(testResult -> testResult.setName("Check distance between "+description1+" and "+description2));
        highLighterMethod(element1);
        highLighterMethod(element2);
        step("Check distance between " +element1+" and " +element2, () -> {
            Assert.assertEquals(offsetRight(element1, element2), expectedResult, error);
        });}
}
