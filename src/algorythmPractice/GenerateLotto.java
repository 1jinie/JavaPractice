package algorythmPractice;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GenerateLotto {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("===== 로또 번호 생성 프로그램 =====");
        System.out.println();
        int numOfGame = setNumOfGame();
//        System.out.println(numOfGame);    // 게임 횟수 확인
        System.out.println();
        System.out.println("===== 로또 번호 생성 결과 =====");
        System.out.println();
        generateNumber(numOfGame);

    }

    // 로또 생성 게임 횟수 설정
    static int setNumOfGame() {
        while (true) {
            System.out.print("생성할 게임 수를 입력하세요: ");
            try {
                int n = sc.nextInt();
                if (n < 1) {
                    System.out.println("1보다 큰 수를 입력해주세요.");
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
        int i = 0;
        while(i < numOfGame) {
            for (int j = 0; j < 6; j++) {
                totalGame[i][j] = rand.nextInt(45) + 1;
            }
            boolean isDuplicate = checkDuplicate(totalGame[i]);
            if(isDuplicate) continue;
            Arrays.sort(totalGame[i]);
            System.out.println(Arrays.toString(totalGame[i]));
            i++;
        }
    }

    // 중복 검사 메서드
    static boolean checkDuplicate(int[] totalGame) {
        for (int i = 1; i < totalGame.length; i++) {
            for(int j = 0; j < i; j++) {
                if(totalGame[i] == totalGame[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
