package algorythmPractice;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        boolean restart = true;

        do{
            int count = 0;
            int target = rand.nextInt(100) + 1;
//            System.out.println(target);
            int input;
            System.out.println("===== 숫자 맞추기 게임 =====");
            while (true){
                System.out.print("숫자를 입력하세요: ");
                try {
                    input = sc.nextInt();
                    if(input < 1 || input > 100){
                        System.out.println("1 ~ 100까지의 숫자를 입력해주세요.");
                        System.out.println();
                        continue;
                    }
                    count++;
                    if(input > target){
                        System.out.println("더 작은 숫자입니다.");
                        System.out.println();
                    } else if(input < target) {
                        System.out.println("더 큰 숫자입니다.");
                        System.out.println();
                    } else{
                        System.out.println("정답입니다!");
                        System.out.println("축하합니다!");
                        System.out.println("시도 횟수: " + count + "회");
                        System.out.println();
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("숫자를 입력해주세요.");
                    sc.next();
                    System.out.println();
                }
            }
            while(true){
                System.out.print("재시작하시겠습니까?(y/n): ");
                String enter = sc.next();
                if(enter.equalsIgnoreCase("y")){
                    restart = true;
                    System.out.println();
                    break;
                } else if(enter.equalsIgnoreCase("n")){
                    restart = false;
                    break;
                } else {
                    System.out.println("다시 입력해주세요.");
                    System.out.println();
                }
            }
        }while(restart);
        System.out.println("===== 게임 종료 =====");
    }
}