package pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
    private static final Logger logger = LoggerFactory.getLogger(PledgesPage.class);

    public ReadPropertyFile() {
    }

    public String getProperty(File propFile, String key) {
        Properties prop = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(propFile);
            prop.load(fis);
        } catch (IOException var6) {
            logger.error("Error reading the property file " + var6.getMessage());
        }
        return prop.getProperty(key);
    }
}
