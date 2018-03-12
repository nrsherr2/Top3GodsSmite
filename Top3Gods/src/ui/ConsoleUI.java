package ui;

import java.util.Scanner;

import Selenium.T3Driver;

public class ConsoleUI {

    public static void main ( final String[] args ) throws InterruptedException {
        System.out.println( "Enter the names of every player you want to look up:" );
        final Scanner in = new Scanner( System.in );
        final String ln = in.nextLine();
        final String[] players = ln.split( " " );

        for ( int i = 0; i < players.length; i++ ) {
            String[][] results = new String[3][2]; // replace this with a
                                                   // call
            // to our driver class
            results = T3Driver.getTop3( players[i] );
            String lev = T3Driver.getLevel(players[i]);
            String rank = T3Driver.getRank( players[i] );

            System.out.println( "For player " + players[i] + ":" );
            System.out.println( "level: " + lev );
            for ( int j = 0; j < 3; j++ ) {
                System.out.println( " " + ( j + 1 ) + ". " + results[j][0] + " - " + results[j][1] );
            }
            System.out.println();

        }
        in.close();
    }

}
