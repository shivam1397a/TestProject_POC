import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/Steps/"},
        glue = {"Steps"},
        plugin = {"io.testproject.sdk.internal.reporting.extensions.cucumber.CucumberReporter"}
)

public class TestRunner {
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios(){
//        return super.scenarios();
//    }
}