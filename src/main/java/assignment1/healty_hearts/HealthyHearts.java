package assignment1.healty_hearts;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HealthyHearts {

    public static void main(String[] args) {
        boolean gotAge = false;
        Scanner scanner = new Scanner(System.in);
        while (!gotAge) {
            try {
                System.out.println("What is your age?");
                String input = scanner.nextLine();
                int age = Integer.parseInt(input.trim());
                if (age < 1) throw new InputMismatchException();
                gotAge = true;
                int maximum_heartbeats = 220 - age;
                int target_lower_bound = (int) Math.round(maximum_heartbeats * 0.5);
                int target_upper_bound = (int) Math.round(maximum_heartbeats * 0.85);
                System.out.println("Your maximum heart rate should be " + maximum_heartbeats + " beats per minute");
                System.out.println(String.format("Your target HR Zone is %d - %d beats per minute", target_lower_bound, target_upper_bound));
            } catch (NumberFormatException | InputMismatchException e) {
                System.err.println("Please enter a positive integer.");
            }
        }
    }

}
