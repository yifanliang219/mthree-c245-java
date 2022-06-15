package assignment1.rock_paper_scissors;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class RockPaperScissors {

    private static class Game implements Runnable {

        private int numberOfRounds;
        private int currentRound;
        private int numUserWins = 0;
        private int numAiWins = 0;
        private int numTies = 0;

        private final String ROCK = "ROCK";
        private final String PAPER = "PAPER";
        private final String SCISSORS = "SCISSORS";

        private final Map<Integer, String> choiceMap = new HashMap<>();

        private Game() {
            initializeMap();
            initializeGame();
        }

        private void initializeGame() {
            System.out.println("How many rounds do you want to play? Enter a number between 1 and 10 inclusive.");
            boolean setup_numberOfRounds = false;
            Scanner scanner = new Scanner(System.in);
            int input_numOfRounds;
            while (!setup_numberOfRounds) {
                try {
                    input_numOfRounds = scanner.nextInt();
                    if (input_numOfRounds < 1 || input_numOfRounds > 10) throw new InputMismatchException();
                    setup_numberOfRounds = true;

                    this.numberOfRounds = input_numOfRounds;
                    this.currentRound = 1;
                    this.numUserWins = 0;
                    this.numAiWins = 0;
                    this.numTies = 0;

                } catch (InputMismatchException e) {
                    System.err.println("Need to enter a number between 1 and 10 inclusive. System quit.");
                    scanner.nextLine();
                    System.exit(-1);
                }
            }
        }

        private void initializeMap() {
            choiceMap.put(1, ROCK);
            choiceMap.put(2, PAPER);
            choiceMap.put(3, SCISSORS);
        }

        private void play_a_round() {
            if (currentRound > numberOfRounds) {
                System.err.println("Game is finished.");
                return;
            }
            System.out.println("Round " + currentRound + " starts.");
            System.out.println("Rock, paper or scissors? (1 = Rock, 2 = Paper, 3 = Scissors)");
            Scanner scanner = new Scanner(System.in);
            boolean setupRoundChoice = false;
            while (!setupRoundChoice) {
                try {
                    int inputUserChoice = scanner.nextInt();
                    if (inputUserChoice < 1 || inputUserChoice > 3) throw new InputMismatchException();
                    setupRoundChoice = true;
                    int randomAiChoice = 1 + (int) (Math.random() * 3);
                    String userChoice = choiceMap.get(inputUserChoice);
                    String AiChoice = choiceMap.get(randomAiChoice);
                    System.out.println("Your choice: " + userChoice);
                    System.out.println("Computer choice: " + AiChoice);
                    processOutcome(userChoice, AiChoice);
                    if (currentRound > numberOfRounds) {
                        afterFinishedAllRounds();
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Need to enter a choice between 1 and 3. (1 = Rock, 2 = Paper, 3 = Scissors)");
                    scanner.nextLine();
                }
            }
        }

        private void processOutcome(String user_choice, String ai_choice) {
            switch (user_choice) {
                case ROCK:
                    if (ai_choice.equals(ROCK)) processTie();
                    else if (ai_choice.equals(PAPER)) processUserLost();
                    else processUserWin();
                    break;
                case PAPER:
                    if (ai_choice.equals(ROCK)) processUserWin();
                    else if (ai_choice.equals(PAPER)) processTie();
                    else processUserLost();
                    break;
                case SCISSORS:
                    if (ai_choice.equals(ROCK)) processUserLost();
                    else if (ai_choice.equals(PAPER)) processUserWin();
                    else processTie();
                    break;
                default:
                    System.err.println("Error user input choice.");
                    System.exit(-1);
            }
        }

        private void processUserWin() {
            currentRound++;
            numUserWins++;
            System.out.println("You win.\n");
        }

        private void processTie() {
            currentRound++;
            numTies++;
            System.out.println("Tie.\n");
        }

        private void processUserLost() {
            currentRound++;
            numAiWins++;
            System.out.println("You lost.\n");
        }

        private void afterFinishedAllRounds() {
            printFinalScoreBoard();
            System.out.println("All rounds are completed. Do you wish to play again? (Enter YES/NO)");
            Scanner scanner = new Scanner(System.in);
            String userInputLine = scanner.nextLine();
            if (userInputLine.trim().equals("YES")) {
                initializeGame();
            } else {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
        }

        private void printFinalScoreBoard() {
            System.out.println("Number of Wins: " + numUserWins);
            System.out.println("Number of ties: " + numTies);
            System.out.println("Number of Loses: " + numAiWins);
            String overall;
            if (numUserWins > numAiWins) {
                overall = "You are the overall winner.";
            } else if (numAiWins > numUserWins) {
                overall = "The computer is the overall winner.";
            } else {
                overall = "Tied game.";
            }
            System.out.println(overall);
        }


        @Override
        public void run() {
            while (currentRound <= numberOfRounds) {
                play_a_round();
            }
        }
    }

    public static void main(String[] args) {

        Game game = new Game();
        game.run();

    }


}