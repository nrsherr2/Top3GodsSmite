package Selenium;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class T3Driver {

    public String[][] getTop3 ( String player ) {

        String url = "smite.guru/profile/pc/" + player + "/matches";
        WebDriver driver = new ChromeDriver();
        driver.get( url );
        for ( int i = 0; i < 3; i++ ) {
            try {
                String src = driver.getPageSource();
                Scanner pageSource = new Scanner( src );
            }
            catch ( Exception e ) {
                e.printStackTrace();
                break;
            }

        }

        return null;
    }
}
