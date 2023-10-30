package qa.utils;

import io.appium.java_client.screenrecording.CanRecordScreen;
import io.cucumber.java.Scenario;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class VideoManager {

    public void startRecording() {

        ((CanRecordScreen) new DriverManager().getDriver()).startRecordingScreen();
        System.out.println("RecordingStarted");
    }

    public void stopRecording(String scenario) {
        var media = ((CanRecordScreen) new DriverManager().getDriver()).stopRecordingScreen();

        ParametersManager pm = new ParametersManager();
        String path = pm.getPlatformName() + "_" + pm.getName() + File.separator + "Videos";
        File file = new File(path);
        synchronized (file){
        if (!file.exists()) {
            file.mkdirs();
        }}
        FileOutputStream fileOutputStream = null;
        try {
             fileOutputStream = new FileOutputStream(file+File.separator+ scenario+Utils.dateAndTime()+".mp4");
            try {
                fileOutputStream.write(Base64.decodeBase64(media));
                fileOutputStream.close();
                System.out.println("RecordingEnded");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
