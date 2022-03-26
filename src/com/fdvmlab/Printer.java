/**
 * This class defines few methods to print colored text to the console
 */
package com.fdvmlab;
public class Printer {

    /**
     * define the color to use on texts
     */
    //Reset to default color
    public static final String RESET_COLOR =  "\033[0m";

    public static final String RED =          "\033[31m";
    public static final String GREEN =        "\033[32m";
    public static final String YELLOW =       "\033[33m";
    public static final String BLUE =         "\033[34m";


    /**
     * Print blue text
     * @param message - the colored message to be printed
     */
    public static void printInfo(String message){
        System.out.print(BLUE+"\n "+message+""+RESET_COLOR);
    }

    /**
     * Print green text
     * @param message - the colored message to be printed
     */
    public static void printSuccess(String message){
        System.out.print(GREEN+"\n "+message+""+RESET_COLOR);
    }

    /**
     * Print yellow text
     * @param message - the colored message to be printed
     */
    public static void printWarning(String message){
        System.out.print(YELLOW+"\n "+message+""+RESET_COLOR);
    }

    /**
     * Print red text
     * @param message - the colored message to be printed
     */
    public static void printError(String message){
        System.out.print(RED+"\n "+message+""+RESET_COLOR);
    }
}
