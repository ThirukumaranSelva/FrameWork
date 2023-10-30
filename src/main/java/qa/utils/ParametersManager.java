package qa.utils;


public class ParametersManager {

    private static final ThreadLocal<String> deviceName = new ThreadLocal<>();
    private static final ThreadLocal<String> platformName = new ThreadLocal<>();
    private static final ThreadLocal<String> deviceUDID = new ThreadLocal<>();
    private static final ThreadLocal<String> deviceVersion=new ThreadLocal<>();

    public void setDeviceName(String name) {
        deviceName.set(name);
    }

    public String getName() {
        return deviceName.get();
    }

    public void setPlatformName(String platform) {
     platformName.set(platform);
    }

    public String getDeviceUDID() {
        return deviceUDID.get();
    }

    public void setDeviceUDID(String udid) {
        deviceUDID.set(udid);
    }


    public String getPlatformName() {
        return platformName.get();
    }

    public String getDeviceVersion(){
        return deviceVersion.get();
    }
    public void setDeviceVersion(String version){
        deviceVersion.set(version);
    }

    public void initializeParameter() {
        setPlatformName(System.getProperty("platformName", "Android"));
        setDeviceUDID(System.getProperty("udid", "ce10171ab374340704"));
        setDeviceName(System.getProperty("deviceName", "Samsung Galaxy S8+"));
        setDeviceVersion(System.getProperty("deviceVersion", "9.0"));

    }

}
