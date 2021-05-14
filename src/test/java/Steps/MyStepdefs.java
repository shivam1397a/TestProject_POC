package Steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static Steps.WebConnector.driver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class MyStepdefs {
    public Scenario scenario;
    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    WebConnector webConnector = new WebConnector();

    private String getFeatureFileName() {
        String[] tab = scenario.getId().split("/");
        int rawFeatureNameLength = tab.length;
        String featureName = (tab[rawFeatureNameLength - 1].split(":")[0]).split("\\.")[0];
        System.out.println("featureName: " + featureName);
        return featureName;
    }

    @Given("I open web browser on port {int}")
    public void iOpenWebBrowser(int port,DataTable dataTable) throws Throwable {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);
        webConnector.setUpDriver(table.get(0).get("browser"),port,getFeatureFileName(),scenario.getName());
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
