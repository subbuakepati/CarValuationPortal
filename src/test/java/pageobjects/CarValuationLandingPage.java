package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefs.TestUtils;
import stepdefs.WebDriverSingleton;

import java.time.Duration;

public class CarValuationLandingPage {

    private WebDriver driver;

    public CarValuationLandingPage() {
        this.driver = WebDriverSingleton.instantiateWebDriverInstance();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "vehicleReg")
    private WebElement vehicleReg;

    @FindBy(id = "Mileage")
    private WebElement mileage;

    @FindBy(id = "btn-go")
    private WebElement getValuation;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookies;

    public void enterCarRegistrationNumber(String regNumber) throws Exception {
        WaitTillElementPresent(vehicleReg);
        vehicleReg.clear();
        vehicleReg.sendKeys(regNumber);

        WaitTillElementPresent(mileage);
        mileage.clear();
        mileage.sendKeys(TestUtils.randomMileageGenerator());
        getValuation.click();
        Thread.sleep(3000);

    }
    private void WaitTillElementPresent(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void openCarTaxWebSite(){
        driver.navigate().to("https://www.webuyanycar.com/");
        acceptCookies();
    }

    private void acceptCookies() {
        WaitTillElementPresent(acceptCookies);
        acceptCookies.click();
    }

}
