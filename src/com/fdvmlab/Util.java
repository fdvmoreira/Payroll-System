/**
 * Define utility method (methods that are not member of any class in particular)
 */
package com.fdvmlab;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Util {

    /**
     * Get the user input and cast it to the desired type, the type is has to be the same as inputType
     * @param message - the message to be prompted to the user
     * @param inputType - the type of input expected
     * @return Object - the Object wrapper to be converted to <InputType>
     */
    public static Object getUserInput(String message, InputType inputType){

        //Create a scanner to get a input from keyboard
        Scanner console = new Scanner(System.in);
        Object input = 0;

        //Prompt the message
        Printer.printInfo(message);

        //Store the message to printed if the user enters unexpected value
        String errorMessage = "";

        //Catch error that may arise when user enters wrong data type
        try {
            switch (inputType){
                case BYTE:
                    errorMessage += String.valueOf(inputType.BYTE);
                    input = console.nextByte();
                    break;
                case CHARACTER:
                case SHORT:
                    errorMessage += String.valueOf(inputType.SHORT);
                    input = console.nextShort();
                    break;
                case INTEGER:
                    errorMessage += String.valueOf(inputType.INTEGER);
                    input = console.nextInt();
                    break;
                case FLOAT:
                    errorMessage += String.valueOf(inputType.FLOAT);
                    input = console.nextFloat();
                    break;
                case LONG:
                    errorMessage += String.valueOf(inputType.LONG);
                    input = console.nextLong();
                    break;
                case DOUBLE:
                    errorMessage += String.valueOf(inputType.DOUBLE);
                    input = console.nextDouble();
                    break;
                case STRING:
                    input = console.nextLine();
                    break;
            }
        }catch (InputMismatchException e){
            input = 0;
            Printer.printWarning("  "+errorMessage+ " Expected!");
        }
        //Return the input to the caller
        return input;
    }
}
