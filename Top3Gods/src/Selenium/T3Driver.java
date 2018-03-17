package Selenium;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import util.NameWeightList;

public class T3Driver {

    public static String[][] getTop3 ( final String player ) throws InterruptedException {
        final NameWeightList n = new NameWeightList();
        final String url = "http://smite.guru/profile/pc/" + player + "/matches";

        final WebDriver driver = new HtmlUnitDriver();

        driver.get( url );
        for ( int i = 0; i < 3; i++ ) { // change this back to 3 when we work
                                        // with multiple pages
            if ( i > 0 ) {
                driver.get( url + "?page=" + ( i + 1 ) ); // Update the driver
                                                          // to a new page
            }
            try {
                final String src = driver.getPageSource();
                final Scanner pageSource = new Scanner( src );
                while ( pageSource.hasNextLine() ) {
                    final String line = pageSource.nextLine();
                    // You can add more game modes here
                    if ( line.equalsIgnoreCase( "                   Normal: Conquest - " )
                            || line.equalsIgnoreCase( "                   Ranked: Conquest - " ) ) {
                        for ( int j = 0; j < 38; j++ ) {
                            pageSource.nextLine(); // Skip the next 38 lines
                        }
                        final String godName = pageSource.nextLine();
                        final Scanner nameScan = new Scanner( godName );
                        String refName = "";
                        // Check the string to ensure correct formatting
                        while ( nameScan.hasNext() ) {
                            String token = nameScan.next();
                            if ( token.contains( "'" ) ) {
                                token = token.replace( "'", "" );
                            }
                            refName += token;
                            if ( nameScan.hasNext() ) {
                                refName += "-";
                            }
                        }
                        nameScan.close();
                        // Log the occurrence
                        try {
                            n.addOccurrence( refName );
                        }
                        catch ( final IllegalArgumentException e ) {
                            System.out.println( e.getMessage() );
                        }
                    }
                }

                pageSource.close();
            }
            catch ( final Exception e ) {
                e.printStackTrace();
                break;
            }

        }

        return n.top3();
    }

    public static String getLevel ( String player ) {
        final String url = "http://smite.guru/profile/pc/" + player + "/ranked";

        final WebDriver driver = new HtmlUnitDriver();

        driver.get( url );

        final String lev = driver.findElement( By.className( "level" ) ).getText();
        return lev;
    }

    public static String getRank ( String player ) throws InterruptedException {

        final String url = "http://smite.guru/profile/pc/" + player + "/ranked";

        final WebDriver driver = new HtmlUnitDriver();

        driver.get( url );
        String rank = "";
        final String src = driver.getPageSource();
        final Scanner reader = new Scanner( src );
        while ( reader.hasNextLine() ) {
            String ln = reader.nextLine();
            ln = ln.trim();
            if ( ln.equalsIgnoreCase( "<div class=\"widget-header queue-header\">" ) && reader.hasNextLine() ) {
                String gameMode = reader.nextLine();
                gameMode = gameMode.trim();
                if ( gameMode.equalsIgnoreCase( "Ranked: Conquest" ) ) {
                    String nextLn = reader.nextLine();
                    while ( !nextLn.contains( "<img src=\"/assets/img/ranked/" ) ) {
                        nextLn = reader.nextLine();
                    }
                    nextLn = reader.nextLine();
                    nextLn = reader.nextLine().trim();
                    rank = nextLn;
                }
            }
        }
        reader.close();
        return rank;
    }
}
