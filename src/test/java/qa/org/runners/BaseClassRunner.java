package qa.org.runners;

import io.cucumber.junit.Cucumber;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.junit.runner.RunWith;
import org.testng.annotations.*;
import qa.utils.DriverManager;
import qa.utils.ParametersManager;
import qa.utils.ServerManager;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        plugin = {"pretty","html:target/cucumber","summary"},
        glue = "qa/org/stepDef",
        tags = "@test",
        dryRun = false,
        monochrome = false
)
public class BaseClassRunner {

    private static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner = new ThreadLocal<>();

    public static TestNGCucumberRunner getTestNGCucumberRunner() {
        return testNGCucumberRunner.get();
    }

    public static void setTestNGCucumberRunner(TestNGCucumberRunner testNGCucumberRunner1) {
        testNGCucumberRunner.set(testNGCucumberRunner1);
    }

    @Parameters({"platformName", "udid", "version", "deviceName"})
    @BeforeClass(alwaysRun = true)
    public void setupClass(String platform, String udid, String version, String deviceName) throws Exception {
        ParametersManager pm = new ParametersManager();
        pm.setPlatformName(platform);
        pm.setDeviceUDID(udid);
        pm.setDeviceVersion(version);
        pm.setDeviceName(deviceName);
        new ServerManager().startServer();
        new DriverManager().driverInitialize();
        setTestNGCucumberRunner(new TestNGCucumberRunner(this.getClass()));
    }


  /*  @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) throws Throwable {
        getTestNGCucumberRunner().runScenario(pickle.getPickle());
    }
    */

    @Test(dataProvider = "scenarios")
    public void scenario( PickleWrapper pickle,FeatureWrapper featureWrapper){
        getTestNGCucumberRunner().runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return getTestNGCucumberRunner().provideScenarios();
    }

    @AfterClass
    public void quit() {

        ServerManager sm = new ServerManager();
        if (sm.getService() != null) {
            sm.getService().stop();
        }
        DriverManager dm = new DriverManager();
        if (dm.getDriver() != null) {
           // dm.getDriver().quit();
            dm.setDriver(null);
        }
        if (testNGCucumberRunner!= null)
            getTestNGCucumberRunner().finish();

    }
}
