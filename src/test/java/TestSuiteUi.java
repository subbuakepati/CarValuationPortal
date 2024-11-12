import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@test",
        plugin={"pretty","junit:target/cucumber-junit-report.xml", "html:target/site/cucumber-pretty", "json:target/cucumber.json"},
        features = "src/test/resources/features/",
        glue = "stepdefs"
)
public class TestSuiteUi {

}
