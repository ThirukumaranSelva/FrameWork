package qa.org.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import qa.utils.DriverManager;
import qa.utils.ParametersManager;
import qa.utils.ServerManager;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        plugin = {"pretty","html:target/cucumber","summary"},
        glue = "qa/org/stepDef",
        tags = "@test",
        snippets = CAMELCASE,
        dryRun = false,
        monochrome = false
)
public class RunnerTest {
    @BeforeClass
    public static void initialize() throws Exception {
        ParametersManager pm= new ParametersManager();
        pm.initializeParameter();
        new ServerManager().startServer();
        new DriverManager().driverInitialize();
    }

    @AfterClass
    public static void quit(){
        new DriverManager().setDriver(null);
        new ServerManager().getService().stop();
    }

}
