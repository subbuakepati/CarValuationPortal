package pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefs.WebDriverSingleton;

import java.time.Duration;

public class CarFullDetailsPage {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[1]/wbac-app/div[1]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[1]/div[2]")
    private WebElement registrationNumber;

    @FindBy(xpath = "//*[@id='wbac-app-container']/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[1]/div[2]")
    private WebElement make;

    @FindBy(xpath = "//*[@id='wbac-app-container']/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[2]/div[2]")
    private WebElement model;

    @FindBy(xpath = "//*[@id='wbac-app-container']/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[3]/div[2]")
    private WebElement year;

    @FindBy(id = "btn-back")
    private WebElement back;

    @FindBy(css = "div h1")
    private WebElement notFoundError;

    public CarFullDetailsPage() {
        this.driver = WebDriverSingleton.instantiateWebDriverInstance();
        PageFactory.initElements(driver, this);
    }

    public String getVehicleRegistrationNumber() {
        try {
            WaitTillElementPresent(registrationNumber);
            return registrationNumber.getText();
        } catch (NoSuchElementException e) {
           return notFoundError.getText();
        }
    }

    public String getVehicleModel() {
        WaitTillElementPresent(model);
        return model.getText();
    }

    public String getVehicleYear() {
        WaitTillElementPresent(year);
        return year.getText();
    }

    public String getCarMake() {
        WaitTillElementPresent(make);
        return make.getText();
    }

    private void WaitTillElementPresent(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void navigateBackToSearchPage() {
        back.click();
    }

    public String getNotFoundMessage() {
        return notFoundError.getText();
    }
}
