package qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.util.Arrays;


public class DriverManager {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver d) {
        driver.set(d);
    }


    public void driverInitialize() throws Exception {

        ParametersManager pm = new ParametersManager();
        System.out.println(pm.getPlatformName());
        AppiumDriver driver1 = null;
        ServerManager serverManager = new ServerManager();
        CapabilitiesManager capabilitiesManager = new CapabilitiesManager();
        try {
            if (driver1 == null) {
                switch (pm.getPlatformName()) {
                    case "Android":
                        System.out.println("URL:" + serverManager.getService().getUrl());
                        driver1 = new AndroidDriver(serverManager.getService().getUrl(), capabilitiesManager.deviceCapabilities());
                        break;

                    case "iOS":
                        driver1 = new IOSDriver(serverManager.getService().getUrl(), capabilitiesManager.deviceCapabilities());
                        break;
                }
            }
            if (driver1 == null) {
                throw new Exception("Driver Initialization Failed!");
            }

            DriverManager.driver.set(driver1);
        } catch (Exception e) {

            System.out.println("Exception Caught driver :" + Arrays.toString(e.getStackTrace()));
        }
    }

}
