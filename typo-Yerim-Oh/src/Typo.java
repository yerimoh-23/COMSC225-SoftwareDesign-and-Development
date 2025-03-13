/**
 * @author Yerim Oh
 * @date Feb 7, 2023
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

/**
 * Reads a file where the user's hands were one key to far to the right.
 * Outputs to standard output what the user intended to type.
 *
 */
public class Typo {
    // The map from mistyped characters to the intended characters.
    private static Map<Character, Character> corrections = new HashMap<>();
    
    private static void initCorrections() {
        // ============== TODO:  You need to initialize this map. ==============
    	
    	// Upper case
    	corrections.put('+', '_');
    	corrections.put('_', ')');
    	corrections.put(')', '(');
    	corrections.put('(', '*');
    	corrections.put('*', '&');
    	corrections.put('&', '^');
    	corrections.put('^', '%');
    	corrections.put('%', '$');
    	corrections.put('$', '#');
    	corrections.put('#', '@');
    	corrections.put('@', '!');
    	corrections.put('!', '~');
    	corrections.put('|', '}');
    	corrections.put('}', '{');
    	corrections.put('{', 'P');
    	corrections.put('P', 'O');
    	corrections.put('O', 'I');
    	corrections.put('I', 'U');
    	corrections.put('U', 'Y');
    	corrections.put('Y', 'T');
    	corrections.put('T', 'R');
    	corrections.put('R', 'E');
    	corrections.put('E', 'W');
    	corrections.put('W', 'Q');
    	corrections.put('"', ':');
    	corrections.put(':', 'L');
    	corrections.put('L', 'K');
     	corrections.put('K', 'J');
    	corrections.put('J', 'H');
    	corrections.put('H', 'G');
    	corrections.put('G', 'F');
    	corrections.put('F', 'D');
    	corrections.put('D', 'S');
    	corrections.put('S', 'A');
    	corrections.put('?', '>');
    	corrections.put('>', '<');
    	corrections.put('<', 'M');
    	corrections.put('M', 'N');
    	corrections.put('N', 'B');
    	corrections.put('B', 'V');
    	corrections.put('V', 'C');
    	corrections.put('C', 'X');
    	corrections.put('X', 'Z');
    	
    	// Lower case
    	corrections.put('=', '-');
    	corrections.put('-', '0');
    	corrections.put('0', '9');
    	corrections.put('9', '8');
    	corrections.put('8', '7');
    	corrections.put('7', '6');
    	corrections.put('6', '5');
    	corrections.put('5', '4');
    	corrections.put('4', '3');
    	corrections.put('3', '2');
    	corrections.put('2', '1');
    	corrections.put('1', '`');
    	corrections.put('\\', ']');
    	corrections.put(']', '[');
    	corrections.put('[', 'p');
    	corrections.put('p', 'o');
    	corrections.put('o', 'i');
    	corrections.put('i', 'u');
    	corrections.put('u', 'y');
    	corrections.put('y', 't');
    	corrections.put('t', 'r');
    	corrections.put('r', 'e');
    	corrections.put('e', 'w');
    	corrections.put('w', 'q');
    	corrections.put('\'', ';');
    	corrections.put(';', 'l');
    	corrections.put('l', 'k');
    	corrections.put('k', 'j');
    	corrections.put('j', 'h');
    	corrections.put('h', 'g');
    	corrections.put('g', 'f');
    	corrections.put('f', 'd');
    	corrections.put('d', 's');
    	corrections.put('s', 'a');
    	corrections.put('/', '.');
    	corrections.put('.', ',');
    	corrections.put(',', 'm');
    	corrections.put('m', 'n');
    	corrections.put('n', 'b');
    	corrections.put('b', 'v');
    	corrections.put('v', 'c');
    	corrections.put('c', 'x');
    	corrections.put('x', 'z');
    	
    	// space
    	corrections.put(' ', ' ');
    	    	        
        // =====================================================================
    }
    
    public static void main (String[] args) {
        
        // Initialize the HashMap
        System.out.println("Welcome to the Typo Correction System.");
        initCorrections();
        
        // Get the file name.  Exit if none was provided.
        if (args.length == 0) {
            System.out.println("Please enter a file name when you run the program");
            return;
        }
        
        // Read and correct the file.
        try (Scanner in = new Scanner (new File (args[0]))) {
            while (in.hasNext()) {
                String notCorrected = in.nextLine();
                System.out.println ("Before:  " + notCorrected);
                String corrected = correct (notCorrected);
                System.out.println ("After:   " + corrected);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + args[0] + " could not be found.");
        }
    }

    /**
     * Correct one line by shifting the characters one to the left on the keyboard.
     * @param line the line that was mis-typed
     * @return the corrected line
     */
    private static String correct(String line) {
        // ============== TODO:  You need to implement this method. ==============
    	
    	// Convert a String to a List of Characters in Java
    	List<Character> chars = new ArrayList<>();
    	// For each character in the String add it to the List
        for (char ch : line.toCharArray()) {
        	chars.add(ch);
        }
        
        // Make an empty string
        String lineCorrected = "";
        // For each item in the ArrayList, use the map to get the value
        // add it to the string lineCorrected
        for (int i = 0; i < chars.size(); i++) {
        	lineCorrected = lineCorrected + corrections.get(chars.get(i));
        }
        
        return lineCorrected;
        
        // =====================================================================
    }
}
