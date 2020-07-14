import java.util.Scanner;

public class NumericConversion {
    public static short hexCharDecode(char digit){
        //Method will be called from hexStringDecode method.
        short x = 0;
        //Set hexadecimal values above 9 to their corresponding letter.
        if (digit == 'a') {
            x = 10;
        } else if (digit == 'b') {
            x = 11;
        } else if (digit == 'c') {
            x = 12;
        } else if (digit == 'd') {
            x = 13;
        } else if (digit == 'e') {
            x = 14;
        } else if (digit == 'f') {
            x = 15;
        } else {
            x = (short) ((int)digit - 48);
        }
        return x;
    }
    public static long hexStringDecode(String hex) {
        //Turns hexadecimal into decimal.
        double total = 0;
        //Convert all characters within a string to lowercase if it is a letter.
        String hexL = hex.toLowerCase();
        //Raise 16 to the power
        int power = 0;
        if (hexL.charAt(0) == '0' && hexL.charAt(1) == 'x') {
            //Start from last character all the way to front if start with 0x.
            for (int i = hexL.length() - 1; i >= 2; i--) {
                //Gives a short from the hexCharDecode function depending on the character.
                double functionCatcher = hexCharDecode(hexL.charAt(i));
                double subTotal = Math.pow(16,power) * functionCatcher;
                total = total + subTotal;
                power = power + 1;
            }
        }
        else {
            for (int i = hexL.length() - 1; i >= 0; i--) {
                //Same process abover, but for strings that do not start with 0x
                double functionCatcher = hexCharDecode(hexL.charAt(i));
                double subTotal = Math.pow(16,power) * functionCatcher;
                total = total + subTotal;
                power = power + 1;
            }
        }
        return (long)total;
    }
    public static short binaryStringDecode(String binary) {
        double total = 0;
        if (binary.charAt(0) == '0' && binary.charAt(1) == 'b') {
            //For string with 0b
            int power = 0;
            for (int i = binary.length() - 1; i >= 2; i--) {
                //Starts at last character of the string and move forward.
                if (binary.charAt(i) == '1') {
                    //Only character with 1 will be turned on and raised to the power.
                    double subTotal = Math.pow(2, power);
                    total = subTotal + total;
                }
                power += 1;
            }
        } else {
            //For strings without 0b
            int power = 0;
            for (int i = binary.length() - 1; i >= 0; i--) {
                if (binary.charAt(i) == '1') {
                    double subTotal = Math.pow(2, power);
                    total = subTotal + total;
                }
                power += 1;
            }
        }
        return (short)total;
    }
    public static String binaryToHex(String binary){
        //First need to convert binary to decimal using binaryStringDecode method.
        short binaryToDeci = binaryStringDecode(binary);
        //Concatenate backwards after dividing by 16 continuously
        String hexaDecimal = "";
        //What's left after integer division.
        int numLeftToDivide;
        //What will be use to concatenate
        int remainder;
           while (true) {
               //As long as the numLeftToDivide is not zero, we will continue to divide by 16 and concatenating the remainders.
               numLeftToDivide = binaryToDeci / 16;
               remainder = binaryToDeci % 16;
               binaryToDeci = (short) (binaryToDeci / 16);
               //For hexadecimal representations above 9
               if (remainder == 10) {
                   hexaDecimal = hexaDecimal + "A";
               } else if (remainder == 11) {
                   hexaDecimal = hexaDecimal + "B";
               } else if (remainder == 12) {
                   hexaDecimal = hexaDecimal + "C";
               } else if (remainder == 13) {
                   hexaDecimal = hexaDecimal + "D";
               } else if (remainder == 14) {
                   hexaDecimal = hexaDecimal + "E";
               } else if (remainder == 15) {
                   hexaDecimal = hexaDecimal + "F";
               } else {
                   hexaDecimal = hexaDecimal + Integer.toString(remainder);
               }
               if (numLeftToDivide == 0) {
                   break;
               }
           }
               //Invert the string.
            String output = "";
            for (int i = hexaDecimal.length() - 1; i >= 0; i--) {
                output = output + hexaDecimal.charAt(i);
            }
        return output;
    }
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        while (true) {
            //Print out decoding menu until option 4 is pressed.
            System.out.println("Decoding Menu");
            System.out.println("-------------");
            System.out.println("1. Decode hexadecimal");
            System.out.println("2. Decode binary");
            System.out.println("3. Convert binary to hexadecimal");
            System.out.println("4. Quit\n");
            System.out.println("Please enter an option: ");

            int option = scnr.nextInt();

            if (option == 1) {
                System.out.println("Please enter the numeric string to convert: ");
                String input = scnr.next();
                System.out.println("Result: " + hexStringDecode(input) + "\n");
            }
            if (option == 2) {
                System.out.println("Please enter the numeric string to convert: ");
                String input = scnr.next();
                System.out.println("Result: " + binaryStringDecode(input) + "\n");
            }
            if (option == 3) {
                System.out.println("Please enter the numeric string to convert: ");
                String input = scnr.next();
                System.out.println("Result: " + binaryToHex(input) + "\n");
            }
            if (option == 4) {
                System.out.println("Goodbye!");
                break;
            }
        }
    }
}

