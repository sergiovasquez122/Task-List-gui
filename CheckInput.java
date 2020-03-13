import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Static functions used to check console input for validity.
 * <p>
 * Use:	Place CheckInput class in the same project folder as your code.
 * Call CheckInput functions from your code using "CheckInput."
 * <p>
 * Example:  int num = CheckInput.getInt();
 *
 * @author Shannon Cleary 2019
 */
public class CheckInput {

    /**
     * Checks if the inputted value is an integer.
     * @return the valid input.
     */
    public static int getInt() {
        Scanner in = new Scanner(System.in);
        int input = 0;
        boolean valid = false;
        while (!valid) {
            if (in.hasNextInt()) {
                input = in.nextInt();
                valid = true;
            } else {
                in.next(); //clear invalid string
                System.out.println("Invalid Input.");
            }
        }
        return input;
    }

    /**
     * Checks if the inputted value is an integer and
     * within the specified range (ex: 1-10)
     * @param low  lower bound of the range.
     * @param high upper bound of the range.
     * @return the valid input.
     */
    public static int getIntRange(int low, int high) {
        Scanner in = new Scanner(System.in);
        int input = 0;
        boolean valid = false;
        while (!valid) {
            if (in.hasNextInt()) {
                input = in.nextInt();
                if (input <= high && input >= low) {
                    valid = true;
                } else {
                    System.out.println("Invalid Range.");
                }
            } else {
                in.next(); //clear invalid string
                System.out.println("Invalid Input.");
            }
        }
        return input;
    }

    /**
     * Checks if the inputted value is a non-negative integer.
     * @return the valid input.
     */
    public static int getPositiveInt() {
        Scanner in = new Scanner(System.in);
        int input = 0;
        boolean valid = false;
        while (!valid) {
            if (in.hasNextInt()) {
                input = in.nextInt();
                if (input >= 0) {
                    valid = true;
                } else {
                    System.out.println("Invalid Range.");
                }
            } else {
                in.next(); //clear invalid string
                System.out.println("Invalid Input.");
            }
        }
        return input;
    }

    /**
     * Checks if the inputted value is a double.
     * @return the valid input.
     */
    public static double getDouble() {
        Scanner in = new Scanner(System.in);
        double input = 0;
        boolean valid = false;
        while (!valid) {
            if (in.hasNextDouble()) {
                input = in.nextDouble();
                valid = true;
            } else {
                in.next(); //clear invalid string
                System.out.println("Invalid Input.");
            }
        }
        return input;
    }

    /**
     * Takes in a string from the user.
     * @return the inputted String.
     */
    public static String getString() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    /**
     * Takes in a yes/no from the user.
     * @return true if yes, false if no.
     */
    public static boolean getYesNo() {
        boolean valid = false;
        while (!valid) {
            String s = getString();
            if (s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("y")) {
                return true;
            } else if (s.equalsIgnoreCase("no") || s.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Invalid Input.");
            }
        }
        return false;
    }

    /**
     * Checks if a string is composed of a valid date and return a date if it is else null object
     * @param dateValue String that represents date to be checked
     * @param pattern String that represents the pattern of date
     * @return Date object if the string date is valid else null
     */
    public static Date validDate(String dateValue, String pattern){
        /*
         * Create a Date from the dateValue and then
         * check if the string representation of the Date
         * object matches the dateValue if not then it
         * was not a valid date
         */
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date  = null;
        try {
            date = formatter.parse(dateValue);
            if(!dateValue.equals(formatter.format(date))){
                date = null;
            }
        } catch (ParseException pe) {
            // Ignore this exception
        }
        return date;
    }
}
