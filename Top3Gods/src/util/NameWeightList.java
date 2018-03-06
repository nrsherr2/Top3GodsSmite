package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * List that contains all smite gods and how many times this player has played
 * that god. Contains methods for keeping track how many times a player plays
 * that god and giving the user that player's top 3 gods
 * 
 * @author Nick
 *
 */
public class NameWeightList {

    private String[] godNames;
    private int[]    occurrences;

    /**
     * Creates a new NameWeightList. Goes through a file containing all smite
     * gods' names and creates a list out of them. Then creates a list of all 0s
     * for the number of times that god appears.
     */
    public NameWeightList () {
        try {
            int i = 0;
            int len = 0;
            File f = new File( "rec/smitegods.txt" );
            Scanner reader = new Scanner( f );
            // figure out how many gods you are reading in
            Scanner reader2 = new Scanner( f );
            while ( reader2.hasNext() ) {
                reader2.next();
                len++;
            }
            reader2.close();
            // read in the names
            godNames = new String[len];
            while ( reader.hasNext() ) {
                String godName = reader.next();
                godNames[i] = godName;
                i++;
            }
            reader.close();
        }
        catch ( FileNotFoundException e ) {
            throw new IllegalArgumentException( "Can not find file" );
        }
        this.occurrences = new int[godNames.length];
        for ( int i = 0; i < occurrences.length; i++ ) {
            occurrences[i] = 0;
        }
    }

    /**
     * adds an occurrence of playing a god to the list by telling the method the
     * god that was played
     * 
     * @param godName
     *            the god to add an occurrence of
     * @throws IllegalArgumentException
     *             if you did not give it a valid god
     */
    public void addOccurrence ( String godName ) {
        boolean found = false;
        for ( int i = 0; i < godNames.length; i++ ) {
            if ( godNames[i].equalsIgnoreCase( godName ) ) {
                occurrences[i]++;
                found = true;
            }
        }
        if ( !found ) {
            throw new IllegalArgumentException( "Could not find god " + godName );
        }
    }

    /**
     * tells the user the top 3 gods in the list, by weight.
     * 
     * @return a list of 3 gods and their corresponding occurrences
     */
    public String[][] top3 () {
        String god1 = "", god2 = "", god3 = "";
        int oc1 = 0, oc2 = 0, oc3 = 0;
        for ( int i = 0; i < godNames.length; i++ ) {
            if ( occurrences[i] > oc1 ) {
                oc3 = oc2;
                oc2 = oc1;
                oc1 = occurrences[i];
                god3 = god2;
                god2 = god1;
                god1 = godNames[i];
            }
            else if ( occurrences[i] > oc2 ) {
                oc3 = oc2;
                oc2 = occurrences[i];
                god3 = god2;
                god2 = godNames[i];
            }
            else if ( occurrences[i] > oc3 ) {
                oc3 = occurrences[i];
                god3 = godNames[i];
            }
        }
        String[][] ret = new String[3][2];
        ret[0][0] = god1;
        ret[0][1] = Integer.toString( oc1 );
        ret[1][0] = god2;
        ret[1][1] = Integer.toString( oc2 );
        ret[2][0] = god3;
        ret[2][1] = Integer.toString( oc3 );
        return ret;
    }
}
