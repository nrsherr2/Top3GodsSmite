package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class T3Driver {

    public String[][] getTop3 ( String player ) {

        String url = "smite.guru/profile/pc/" + player + "/matches";
        WebDriver driver = new ChromeDriver();
        driver.get( url );
        String src = driver.getPageSource();
        return null;
    }
}
