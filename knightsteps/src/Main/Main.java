package Main;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final Scanner scanner = new Scanner(System.in);
        final String startPoint = scanner.nextLine();
        final String endPoint = scanner.nextLine();
        final Character[] chessLetterBoard = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        final Character[] chessNumberBoard = {'1', '2', '3', '4', '5', '6', '7', '8'};

        validateInput(startPoint, endPoint, chessLetterBoard, chessNumberBoard);

        final Set<String> possibleSteps = new LinkedHashSet<>();
        possibleSteps.add(startPoint);

        int counter = 0;
        final int maxIterations = 500;
        while (true) {
            if (possibleSteps.contains(endPoint)) {
                System.out.println("Minimum steps: " + counter);
                break;
            }
            iterateOverEachPossibleStep(chessLetterBoard, possibleSteps);
            counter++;

            if (counter >= maxIterations) {
                System.out.println("Max iterations reached");
                break;
            }
        }
    }

    private static void validateInput(String startPoint, String endPoint, Character[] chessLetterBoard, Character[] chessNumberBoard) throws Exception {
        if (Arrays.stream(chessLetterBoard).noneMatch(c -> c.equals(startPoint.charAt(0)))
                || Arrays.stream(chessNumberBoard).noneMatch(c -> c.equals(startPoint.charAt(1))) ||
                Character.isLowerCase(startPoint.charAt(0))) {
            throw new Exception("Invalid start point");
        }
        if (Arrays.stream(chessLetterBoard).noneMatch(c -> c.equals(endPoint.charAt(0)))
                || Arrays.stream(chessNumberBoard).noneMatch(c -> c.equals(endPoint.charAt(1))) ||
                Character.isLowerCase(endPoint.charAt(0))) {
            throw new Exception("Invalid end point");
        }
    }

    private static void iterateOverEachPossibleStep(Character[] chessLetterBoard, Set<String> steps) {
        for (String point : steps.stream().toList()) {
            for (int i = 0; i < chessLetterBoard.length; i++) {
                if (i + 1 >= chessLetterBoard.length) {
                    break;
                }
                if (chessLetterBoard[i] + 1 == point.charAt(0) || chessLetterBoard[i] - 1 == point.charAt(0)) {
                    if (i - 2 > 0) {
                        steps.add(formatResult(chessLetterBoard[i], (char) (point.charAt(1) - 2)));
                    }
                    steps.add(formatResult(chessLetterBoard[i], (char) (point.charAt(1) + 2)));
                } else if (chessLetterBoard[i] + 2 == point.charAt(0) || chessLetterBoard[i] - 2 == point.charAt(0)) {
                    if (i - 1 > 0) {
                        steps.add(formatResult(chessLetterBoard[i], (char) (point.charAt(1) - 1)));
                    }
                    steps.add(formatResult(chessLetterBoard[i], (char) (point.charAt(1) + 1)));
                }
            }
        }
    }

    private static String formatResult(Character character, Character number) {
        return String.format("%s%s", character, number);
    }
}