package algorythmPractice;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Random rand = new Random();
        int count = 0;
        int target = rand.nextInt(100);
        System.out.println("Guessing number: " + target);

        System.out.println("===== 숫자 맞추기 게임 =====");
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.print("숫자를 입력하세요: ");
            int input = sc.nextInt();
            count++;
            if(input > target){
                System.out.println("더 작은 숫자입니다.");
                System.out.println();
            }
            if(input < target){
                System.out.println("더 큰 숫자입니다.");
                System.out.println();
            }
            if(input == target){
                System.out.println("정답입니다!");
                System.out.println("축하합니다!");
                System.out.println("시도 횟수: " + count + "회");
                System.out.println();
                System.out.println("===== 게임 종료 =====");
                break;
            }
        }
    }
}
