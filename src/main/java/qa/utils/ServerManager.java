package qa.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class ServerManager {

   private static ThreadLocal< AppiumDriverLocalService> service=new ThreadLocal<>();
    private URL appium_url;

    public URL getAppium_url() {
        return appium_url;
    }

    public void setAppium_url(URL appium_url) {
        this.appium_url = appium_url;
    }
    public void startServer() {
        AppiumDriverLocalService service1;
        service1 = customAppiumServer();
        service1.start();
        setAppium_url(service1.getUrl());

        if (!service1.isRunning()){
            throw new AppiumServerHasNotBeenStartedLocallyException("Server not started! ");
        }
        service.set(service1);
    }

    public AppiumDriverLocalService getService(){
       return service.get();
    }

    public AppiumDriverLocalService defaultAppiumServer() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    public AppiumDriverLocalService customAppiumServer() {
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withTimeout(Duration.ofSeconds(Utils.serverTimeOut))
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
        );

    }

    public AppiumDriverLocalService macAppiumServer() {
        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", System.getenv("PATH"));
        environment.put("ANDROID_HOME", "${HOME}/Library/Android/sdk");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withTimeout(Duration.ofSeconds(Utils.serverTimeOut))
                        .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withEnvironment(environment)

        );
    }
}
