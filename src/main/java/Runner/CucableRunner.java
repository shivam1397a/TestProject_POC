package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "Steps",
        features = {"target/parallel/features/[CUCABLE:FEATURE].feature"},
        plugin = {"html:target/cucumber-report/[CUCABLE:RUNNER].html"}
)
public class CucableRunner {

}