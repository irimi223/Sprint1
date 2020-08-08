package Utils;

import Drivers.Web;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenShotHelper {

    public static void takeWebScreenShot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) Web.getDriver();
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("./Screenshots/"+fileName+".png");
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
