package calculator;

import java.io.Console;

public class Main {

    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String DIVIDE = "/";
    public static final String MULTIPLY = "*";

    public static void main(String[] args) {
        // Get the console
        Console cons = System.console();
        
        String input = "";
        Double $last = 0d;
        Double firstNum = 0d;
        Double secondNum = 0d;
        String operator = "";

        System.out.printf("Welcome.\n");

        while (true) {
            // Read line
            input = cons.readLine("> ").trim();

            // If user input exit to quit the program
            if(input.equalsIgnoreCase("exit")) {
                System.out.printf("Bye bye\n");
                break;
            }

            String[] userInputs = input.split(" ");

            if (userInputs[0].equals("$last")) {
                firstNum = $last;
            } else {
                firstNum = Double.parseDouble(userInputs[0]);
            }

            if (userInputs[2].equals("$last")) {
                secondNum = $last;
            } else {
                secondNum = Double.parseDouble(userInputs[2]);
            }

            operator = userInputs[1];

            switch (operator) {
                case ADD:
                    $last = firstNum + secondNum;
                    break;
                case SUBTRACT:
                    $last = firstNum - secondNum;
                    break;
                case DIVIDE:
                    $last = firstNum / secondNum;
                    break;
                case MULTIPLY:  
                    $last = firstNum * secondNum;
                    break;
                default:
                    break;
            }
            
            System.out.printf("%.2f\n", $last);

        }

    }
}