package qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    Properties props=new Properties();

    public Properties getProps() {
        InputStream file;
        String s="androidProperties.properties";
        file = getClass().getClassLoader().getResourceAsStream(s);
        try {
            props.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (file!=null)
            {
                try {
                    file.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return props;
    }

}
