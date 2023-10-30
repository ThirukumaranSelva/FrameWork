package qa.utils;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;


public class CapabilitiesManager {
    public DesiredCapabilities deviceCapabilities() {

        ParametersManager pm = new ParametersManager();
        PropertiesManager pro = new PropertiesManager();
        DesiredCapabilities dc = null;
        try {
            dc = new DesiredCapabilities();
            dc.setCapability("platformName", pm.getPlatformName());
            dc.setCapability("deviceName", pm.getName());
            dc.setCapability("udid", pm.getDeviceUDID());
            dc.setCapability("platformVersion", pm.getDeviceVersion());
            switch (pm.getPlatformName()) {
                case "Android":

                   /* dc.setCapability("platformName", pro.getProps().getProperty("platformAndroid"));
                    dc.setCapability("deviceName", pro.getProps().getProperty("deviceAndroid"));
                    dc.setCapability("udid", pro.getProps().getProperty("UDIDAndroid"));
                    dc.setCapability("platformVersion", pro.getProps().getProperty("versionAndroid"));
*/

                    dc.setCapability("automationName", pro.getProps().getProperty("automationAndroid"));
                    String androidAppURL =
                            System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +
                                    "resources" + File.separator + "apps" + File.separator +
                                    pro.getProps().getProperty("appAndroid");
                   // dc.setCapability("app", androidAppURL);
                    dc.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
                    dc.setCapability("appPackage",pro.getProps().getProperty("appPackageAndroid"));
                    break;
                case "iOS":
                   /* dc.setCapability("platformName", pro.getProps().getProperty("platformIOS"));
                    dc.setCapability("deviceName", pro.getProps().getProperty("deviceIOS"));
                    dc.setCapability("udid", pro.getProps().getProperty("UDIDIOS"));
                    dc.setCapability("platformVersion", pro.getProps().getProperty("versionIOS"));
                   */
                    dc.setCapability("automationName", pro.getProps().getProperty("automationIOS"));
                    String iosAppUrl=System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +
                            "resources" + File.separator + "apps" + File.separator +
                            pro.getProps().getProperty("appIOS");
                    dc.setCapability("app",iosAppUrl);
                   // dc.setCapability("bundleId", pro.getProps().getProperty("bundleIDIOS"));
                    break;
                default: {
                    try {
                        throw new Exception("Capabilities not Set Properly");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Exception in Desired Capabilities" + e.getStackTrace());
        } return dc;
    }
}
