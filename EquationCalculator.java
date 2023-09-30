import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EquationCalculator {
    private Scanner scanner;

    public static void main(String[] args) {
        EquationCalculator equationCalculator = new EquationCalculator();
        equationCalculator.run();
    }

    private void run() {
        scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("========= Equation Program =========");
            System.out.println("1. Calculate Superlative Equation");
            System.out.println("2. Calculate Quadratic Equation");
            System.out.println("3. Exit");
            System.out.println("Please choose one option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    calculateSuperlativeEquation();
                    break;
                case 2: 
                    calculateQuadraticEquation();
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    break;
                default: 
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        } while (choice != 3);
    }

    private float getVal(String msg) {
        float val;
        do {
            System.out.print(msg);
            String input = scanner.nextLine();
            if (isNumeric(input)) {
                val = Float.parseFloat(input);
                return val;
            } else {
                System.out.println("Please input a valid number!");
            }
        } while (true);
    }

    private void calculateSuperlativeEquation() {
        float a = getVal("Input A: ");
        float b = getVal("Input B: ");

        List<Float> solutions = calculateEquation(a, b);

        if (solutions == null) {
            System.out.println("No solution.");
        } else if (solutions.isEmpty()) {
            System.out.println("Infinite solutions.");
        } else {
            System.out.printf("Solution: x = %.3f\n", solutions.get(0));
            displayOddEvenSquare(a, b);
        }
    }

    private void calculateQuadraticEquation() {
        float a = getVal("Input A: ");
        float b = getVal("Input B: ");
        float c = getVal("Input C: ");

        List<Float> solutions = calculateQuadraticEquation(a, b, c);

        if (solutions == null) {
            System.out.println("No solution.");
        } else if (solutions.isEmpty()) {
            System.out.println("Infinite solutions.");
        } else {
            System.out.printf("Solutions: x1 = %.3f and x2 = %.3f\n", solutions.get(0), solutions.get(1));
            displayOddEvenSquare(a, b, c);
        }
    }

    private List<Float> calculateEquation(float a, float b) {
        if ( a == 0 ) {
            return null;
        } else {
            List<Float> solutions = new ArrayList<>();
            solutions.add(-b / a);
            return solutions;
        }
    }

    private List<Float> calculateQuadraticEquation(float a, float b, float c) {
        float d = b * b - 4 * a * c;
        List<Float> solutions = new ArrayList<>();

        if ( d < 0 ) {
            return null;
        } else if ( d == 0 ) {
            solutions.add(-b / (2 * a));
        } else {
            solutions.add((-b + (float) Math.sqrt(d)) / (2 * a));
            solutions.add((-b - (float) Math.sqrt(d)) / (2 * a));
        }

        return solutions;
    }

    private boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void displayOddEvenSquare(float...numbers) {
        List<Float> odd = new ArrayList<>();
        List<Float> even = new ArrayList<>();
        List<Float> square = new ArrayList<>();

        for (float number : numbers) {
            if (number % 2 == 0) {
                even.add(number);
            } else {
                odd.add(number);
            }

            if (isPerfectSquare(number)) {
                square.add(number);
            }
        }

        System.out.println("Number is odd: " + odd);
        System.out.println("Number is even: " + even);
        System.out.println("Number is perfect square: " + square);
    }

    private boolean isPerfectSquare(float number) {
        if (number < 0) {
            return false;
        }
        
        double sqrt = Math.sqrt(number);
        return sqrt == Math.floor(sqrt);
    }
}