package algorythmPractice;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GenerateLotto {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("===== 로또 번호 생성 프로그램 =====");
        System.out.println();
        int numOfGame = setNumOfGame();
//        System.out.println(numOfGame);
    }

    // 로또 생성 게임 횟수 설정
    static int setNumOfGame(){
        while (true){
            System.out.print("생성할 게임 수를 입력하세요: ");
            try {
                int n = sc.nextInt();
                if(n < 1){
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
}
