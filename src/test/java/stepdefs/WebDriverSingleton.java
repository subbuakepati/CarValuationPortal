package stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSingleton {

    static WebDriver driver;

    private WebDriverSingleton() {

    }

    public static WebDriver instantiateWebDriverInstance() {

        if (driver == null) {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir")
                            + "/src/test/resources/chromedriver");
//            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        return driver;
    }

}
