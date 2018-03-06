package ui;

import java.util.Scanner;

public class ConsoleUI {

    public static void main ( String[] args ) {
        System.out.println( "Enter the names of every player you want to look up:" );
        Scanner in = new Scanner( System.in );
        String ln = in.nextLine();
        String[] players = ln.split( " " );

        for ( int i = 0; i < players.length; i++ ) {
            String[][] results = new String[3][2]; // replace this with a call
                                                   // to our driver class
            System.out.println( "For player " + players[i] + ":" );
            for ( int j = 0; j < 3; j++ ) {
                System.out.println( " " + j + ". " + results[j][0] + " - " + results[j][1] );
            }
            System.out.println();
        }
        in.close();
    }

}
