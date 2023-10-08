package Main;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final Character[] CHESS_LETTER_BOARD = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    private static final Character[] CHESS_NUMBER_BOARD = {'1', '2', '3', '4', '5', '6', '7', '8'};


    public static void main(String[] args) throws Exception {
        System.out.println("Please provide start point, format e.g. A1:");
        final Scanner scanner = new Scanner(System.in);
        final String startPoint = scanner.nextLine();
        System.out.println("Please provide end point, format e.g. A1:");
        final String endPoint = scanner.nextLine();

        validateInput(startPoint);
        validateInput(endPoint);

        final Set<String> possibleSteps = new LinkedHashSet<>();
        possibleSteps.add(startPoint);

        int counter = 0;
        countSteps(endPoint, possibleSteps, counter);
    }

    private static void countSteps(String endPoint, Set<String> possibleSteps, int counter) {
        if (possibleSteps.contains(endPoint)) {
            System.out.println("Minimum steps: " + counter);
            return;
        }
        iterateOverEachPossibleStep(possibleSteps);
        counter++;
        countSteps(endPoint, possibleSteps, counter);
    }

    private static void validateInput(final String input) throws Exception {
        if (!Arrays.asList(CHESS_LETTER_BOARD).contains(input.charAt(0)) ||
                !Arrays.asList(CHESS_NUMBER_BOARD).contains(input.charAt(1)) ||
                Character.isLowerCase(input.charAt(0))) {
            throw new Exception("Invalid input");
        }
    }

    private static void iterateOverEachPossibleStep(Set<String> steps) {
        for (String point : steps.stream().toList()) {
            char letter = point.charAt(0);
            char number = point.charAt(1);

            makeStepByLetterAndNumber(steps, letter, number);
        }
    }

    private static void makeStepByLetterAndNumber(Set<String> steps, char letter, char number) {
        for (int letterStep = -2; letterStep <= 2; letterStep++) {
            char possibleLetter = (char) (letter + letterStep);
            for (int numberStep = -2; numberStep <= 2; numberStep++) {
                if (Math.abs(letterStep) + Math.abs(numberStep) == 3) {
                    char possibleNumber = (char) (number + numberStep);

                    if (isValidChessPosition(possibleLetter, possibleNumber)) {
                        steps.add(formatResult(possibleLetter, possibleNumber));
                    }
                }
            }
        }
    }

    private static boolean isValidChessPosition(char letter, char number) {
        return Arrays.asList(CHESS_LETTER_BOARD).contains(letter) &&
                Arrays.asList(CHESS_NUMBER_BOARD).contains(number);
    }

    private static String formatResult(Character character, Character number) {
        return String.format("%s%s", character, number);
    }
}