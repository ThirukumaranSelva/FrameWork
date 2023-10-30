package qa.org.stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import qa.utils.DriverManager;
import qa.utils.ParametersManager;
import qa.utils.VideoManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Hooks {
    @Before
    public void initialize() {
        new VideoManager().startRecording();
    }

    @After
    public void quit(Scenario scenario) {
        new VideoManager().stopRecording(scenario.getName());
        if(scenario.isFailed()){
            ParametersManager pm= new ParametersManager();
            var screenshot=((TakesScreenshot)new DriverManager().getDriver()).getScreenshotAs(OutputType.FILE);
            String loc= pm.getPlatformName()+"_"+pm.getName()+File.separator+"Screenshot"+File.separator;
            File file=new File(loc+scenario.getName());
            try {
                FileUtils.copyFile(screenshot,file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
