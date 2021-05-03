package Steps;


import static Steps.WebConnector.driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class MyStepdefs {
    String featureName = "Feature ";
    @Before
    private void getFeatureFileNameFromScenarioId(Scenario scenario) {
        String rawFeatureName = scenario.getId().split(";")[0].replace("-"," ");
        featureName = featureName + rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1);
    }
    WebConnector webConnector = new WebConnector();

    @Given("^I open web browser$")
    public void iOpenWebBrowser(DataTable dataTable) throws Throwable {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);
        webConnector.setUpDriver(table.get(0).get("Browser"),featureName);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://altestal.activelylearn.com/account.html#/");
    }


    @When("I login to AL account")
    public void iLoginToALAccount(DataTable dataTable) throws Throwable{
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);
        String username = table.get(0).get("username");
        String password = table.get(0).get("password");
        webConnector.PerformActionOnElement("xpath,//input[@id='username']", "Type", username);
        webConnector.PerformActionOnElement("xpath,//input[@id='password']", "Type", password);
        webConnector.PerformActionOnElement("xpath,//button[.//text()='Log in']", "Click", "");
    }

    @After
    public void closeBrowser(){
        driver.close();
    }

    @Then("I verify catalog filters are working successfully for {string}, {string}")
    public void iVerifyCatalogFiltersAreWorkingSuccessfullyFor(String gradelevel, String lexilelevel) throws Throwable {
        webConnector.PerformActionOnElement("xpath,//div[@id='mui-component-select-gradeLevels']", "Click", "");
        webConnector.PerformActionOnElement("xpath,//li[text()='"+gradelevel+"']", "Click", "");
        webConnector.PerformActionOnElement("xpath,//div[@id='mui-component-select-lexiles']", "Click", "");
        webConnector.PerformActionOnElement("xpath,//li[text()='"+lexilelevel+"']", "Click", "");
    }
}
