package Selenium;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.NameWeightList;

public class T3Driver {

    public static String[][] getTop3 ( String player ) throws InterruptedException {
        NameWeightList n = new NameWeightList();
        String url = "http://smite.guru/profile/pc/" + player + "/matches";

        String fName = "";
        WebDriver driver = new HtmlUnitDriver();

        driver.get( url );
        Thread.sleep( 500 );
        for ( int i = 0; i < 3; i++ ) {
            try {
                String src = driver.getPageSource();
                // Scanner pageSource = new Scanner( src );
                System.out.println( src );
            }
            catch ( Exception e ) {
                e.printStackTrace();
                break;
            }

        }

        return null;
    }
}
