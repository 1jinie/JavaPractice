package algorythmPractice;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GenerateLotto {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static final int MAX = 45;

    public static void main(String[] args) {
        System.out.println("===== 로또 번호 생성 프로그램 =====");
        boolean restart = false;
        System.out.println();
        System.out.println("===== 입력 방식 선택 =====");
        System.out.println("1. 자동 선택");
        System.out.println("2. 수동 입력");
        System.out.print("선택: ");
        int select = sc.nextInt();

        do {
            while (true) {
                System.out.println();
                int numOfGame = setNumOfGame();
                System.out.println();
                if (select == 1) {
                    generateNumber(numOfGame);
                    break;
                } else if (select == 2) {
                    inputNumber(numOfGame);
                    break;
                } else {
                    System.out.println("잘못된 입력입니다.");
                }
            }

            if (select == 1) {
                restart = askRestart();
            }
        } while (restart);

        System.out.println();
        System.out.println("===== 프로그램 종료 =====");
    }

    // 로또 생성 게임 횟수 설정
    static int setNumOfGame() {
        while (true) {
            System.out.print("생성할 게임 수를 입력하세요: ");
            try {
                int n = sc.nextInt();
                if (n < 1) {
                    System.out.println("1 이상의 수를 입력해주세요.");
                    System.out.println();
                    continue;
                }
                return n;
            } catch (InputMismatchException e) {
                System.out.println("정수로 입력해주세요.");
                System.out.println();
                sc.next();
            }
        }
    }

    // 로또 번호 생성 메서드
    static void generateNumber(int numOfGame) {
        int[][] totalGame = new int[numOfGame][6];
        int[] bonusNumber = new int[numOfGame];
        int i = 0;
        while (i < numOfGame) {
            for (int j = 0; j < 6; j++) {
                totalGame[i][j] = rand.nextInt(MAX) + 1;
            }
            bonusNumber[i] = rand.nextInt(MAX) + 1;
            boolean isDuplicate = checkDuplicate(totalGame[i], bonusNumber);
            if (isDuplicate) continue;
            Arrays.sort(totalGame[i]);
            i++;
        }
        printResult(totalGame, bonusNumber);
    }

    // 중복 검사 메서드(자동)
    static boolean checkDuplicate(int[] totalGame, int[] bonusNumber) {
        for (int i = 1; i < totalGame.length; i++) {
            for (int j = 0; j < i; j++) {
                if (totalGame[i] == totalGame[j]) {
                    return true;
                }
            }
        }
        for (int j : totalGame) {
            for (int k : bonusNumber) {
                if (j == k) return true;
            }
        }
        return false;
    }

    // 중복 검사 메서드(수동)
    static boolean checkDuplicate(int[] totalGame, int row, int input) {
        for (int i = 0; i < row; i++) {
            if (totalGame[i] == input) return true;
        }
        return false;
    }

    // 결과 출력 메서드
    static void printResult(int[][] totalGame, int[] bonusNumber) {
        System.out.println("===== 로또 번호 생성 결과 =====");
        System.out.println();

        for (int i = 0; i < totalGame.length; i++) {
            System.out.print(i + 1 + "번 게임: ");
            for (int j = 0; j < totalGame[i].length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(totalGame[i][j]);
            }
            if (bonusNumber != null) {
                System.out.print(" + " + bonusNumber[i]);
            }
            System.out.println();
        }

        System.out.println();
        if (totalGame.length > 10) {
            statistics(totalGame, bonusNumber);
        }
        System.out.println();
        System.out.println("총 " + totalGame.length + "게임 생성 완료");
    }

    // 재시작 메서드
    static boolean askRestart() {
        while (true) {
            System.out.println();
            System.out.print("다시 생성하시겠습니까?(y/n): ");
            String enter = sc.next();
            if (enter.equalsIgnoreCase("y")) {
                return true;
            } else if (enter.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("다시 입력해주세요.");
                System.out.println();
            }
        }
    }

    // 수동 번호 입력
    static void inputNumber(int numOfGame) {
        int[][] totalGame = new int[numOfGame][6];
        for (int i = 0; i < numOfGame; i++) {
            System.out.println(i + 1 + "번 게임 번호 입력");
            System.out.println();
            int j = 0;
            boolean isDuplicate;
            while (j < 6) {
                System.out.print(j + 1 + "번째 번호: ");
                try {
                    totalGame[i][j] = sc.nextInt();
                    if (totalGame[i][j] < 1 || totalGame[i][j] > MAX) {
                        System.out.println("1 ~ 45 사이의 값을 입력하세요");
                        System.out.println();
                        continue;
                    }
                    if (j > 0) {
                        isDuplicate = checkDuplicate(totalGame[i], j, totalGame[i][j]);
                        if (isDuplicate) {
                            System.out.println("이미 입력한 번호입니다. 다시 입력해주세요");
                            continue;
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요");
                    System.out.println();
                    sc.next();
                    continue;
                }
                j++;
            }
            Arrays.sort(totalGame[i]);
            System.out.println();
        }
        printResult(totalGame, null);
    }

    // 생성된 번호 통계 메서드
    static void statistics(int[][] totalGame, int[] bonusNumber) {
        int[] number = new int[45];
        for (int[] ints : totalGame) {
            for (int anInt : ints) {
                number[anInt - 1]++;
            }
        }
        for (int j : bonusNumber) {
            number[j - 1]++;
        }
        int max = Arrays.stream(number).max().getAsInt();
        int maxNumber = 0;
        int min = Arrays.stream(number).min().getAsInt();
        int minNumber = 0;
        for(int i = 0; i < number.length; i++) {
            if(number[i] == max) maxNumber = i + 1;
            if(number[i] == min) minNumber = i + 1;
        }
        System.out.println("가장 많이 등장한 수 : " + maxNumber);
        System.out.println("가장 적게 등장한 수 : " + minNumber);
    }
}