package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageobjects.CarValuationLandingPage;
import pageobjects.CarFullDetailsPage;

import java.util.List;
import java.util.Map;

public class TestStepDefinitions {

    private static WebDriver driver;
    private CarValuationLandingPage carValuationLandingPage = new CarValuationLandingPage();
    private CarFullDetailsPage carFullDetailsPage = new CarFullDetailsPage();

    private List<String> inputVehicleRegNumbers;
    private Map<String, CsvFileReading> listOfOuptVehicleDetails;

    @Given("^I have list of Car Registration Numbers in the \"([^\"]*)\"$")
    public void vehicleDetailsAreThereInThe(String filePath) throws Throwable {
        String fileContent = TestUtils.readInputFileAsString(filePath);
        inputVehicleRegNumbers = TestUtils.getDataOnRegx(fileContent);
    }

    @When("^I have expected car details are there in the \"([^\"]*)\"$")
    public void vehicleDetailsAreVerifiedOnTheCartaxCheckWebsite(String outputFile) throws Throwable {
        listOfOuptVehicleDetails = TestUtils.getOutPutDataFromCSV(outputFile);
    }

    @Then("^Actual vehicle details are verified with expected details$")
    public void vehicleDetailsShouldMatchTheDetailsInThe() throws Throwable {
        getActualVechicleDataAndAssertWithOutputData(listOfOuptVehicleDetails);
    }

    @Then("^I check car details in the webuyanycar website$")
    public void navigateCarCheckSite() {
        driver = WebDriverSingleton.instantiateWebDriverInstance();
        carValuationLandingPage.openCarTaxWebSite();
    }

    // Read Vehicle Details From the OutPut File and Assert with displayed full car details page
    private void getActualVechicleDataAndAssertWithOutputData(Map<String, CsvFileReading> map) throws Exception {

        for (String regNumbFromInputText : inputVehicleRegNumbers) {

            CsvFileReading csvData = map.get(regNumbFromInputText);
            if (csvData == null) {
                System.out.println("No vehicle output data found for Vehicle Reg :  " + regNumbFromInputText);
            } else {
                carValuationLandingPage.enterCarRegistrationNumber(regNumbFromInputText);
            // we can apply wait condition better way than using thread.sleep  but because of time constrain applied this mechanisem
               Thread.sleep(3000);

                if (driver.getCurrentUrl().contains("/vehicle/details")) {
                    // Reading Vehicle details from OutPut Text

                    String carRegNumbFromOutputText = csvData.getVARIANT_REG();
                    String carMake = csvData.getMAKE();
                    String carModel = csvData.getMODEL();
                    String carRegistrationYear = csvData.getYEAR();

                    String retrievedRegistrationNumberFromFullVehicleDetailsPage = carFullDetailsPage.getVehicleRegistrationNumber();
                    String retrieveMakeFromFullVehicleDetailsPage = carFullDetailsPage.getCarMake();
                    String retrieveModelFromFullVehicleDetailsPage = carFullDetailsPage.getVehicleModel();
                    String retrieveYearFromFullVehicleDetailsPage = carFullDetailsPage.getVehicleYear();

                    // Assert Registration Number in the full Vehicle Details Page
                    Assert.assertEquals("Registration Number Mismatch", carRegNumbFromOutputText, retrievedRegistrationNumberFromFullVehicleDetailsPage);

                    // Assert Car Make Value in the full Vehicle Details Page
                    Assert.assertEquals("Make Verification", carMake, retrieveMakeFromFullVehicleDetailsPage);

                    // Assert Car Model Value in the full Vehicle Details Page
                    Assert.assertEquals("Model Verification", carModel, retrieveModelFromFullVehicleDetailsPage);

                    // Assert Car Year Value in the full Vehicle Details Page
                    Assert.assertEquals("Year Verification", carRegistrationYear, retrieveYearFromFullVehicleDetailsPage);

                    if (regNumbFromInputText != null) carFullDetailsPage.navigateBackToSearchPage();
                } else {
                    String notFoundErrorMsg = carFullDetailsPage.getNotFoundMessage();
                    Assert.assertEquals("Year Verification", "Sorry, we couldn't find your car", notFoundErrorMsg);
                }
            }
        }
    }

    @After
    public void before_or_after() {
        driver.quit();
    }
}
