package algorythmPractice;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        boolean restart;
        do{
            System.out.println("===== 숫자 맞추기 게임 =====");
            int targetMax = setLevel();
            int target = rand.nextInt(targetMax) + 1;

            playGame(target, targetMax);
            restart = askRestart();

        }while(restart);
        System.out.println("===== 게임 종료 =====");
        sc.close();
    }

    static int setLevel(){
        while(true){
            System.out.print("난이도를 선택해주세요(1:쉬움, 2:보통, 3:어려움): ");
            try{
                int level = sc.nextInt();
                if(level == 1){
                    return 50;
                } else if(level == 2){
                    return 100;
                } else if(level == 3){
                    return 500;
                } else {
                    System.out.println("다시 입력해 주세요");
                    System.out.println();
                }
            } catch(InputMismatchException e){
                System.out.println("숫자를 입력해 주세요.");
                System.out.println();
                sc.next();
            }
        }
    }

    static int enterNum(int targetMax){
        while (true) {
            System.out.print("1 ~ " + targetMax + "까지의 숫자를 입력해주세요: ");
            try {
                int input = sc.nextInt();
                if (input < 1 || input > targetMax) {
                    System.out.println("1 ~ " + targetMax + "사이의 숫자를 입력해주세요.");
                    System.out.println();
                    continue;
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                sc.next();
                System.out.println();
            }
        }
    }

    static void playGame(int target, int targetMax){
        int count = 0;
        while (true) {
            int input = enterNum(targetMax);
            count++;
            if(input > target){
                System.out.println("더 작은 숫자입니다.");
            } else if(input < target) {
                System.out.println("더 큰 숫자입니다.");
            } else {
                System.out.println("정답입니다!");
                System.out.println("축하합니다!");
                System.out.println("시도 횟수: " + count + "회");
                break;
            }
            System.out.println();
        }
    }

    static boolean askRestart(){
        while(true){
            System.out.print("재시작하시겠습니까?(y/n): ");
            String enter = sc.next();
            if(enter.equalsIgnoreCase("y")){
                System.out.println();
                return true;
            } else if(enter.equalsIgnoreCase("n")){
                System.out.println();
                return false;
            } else {
                System.out.println("다시 입력해주세요.");
                System.out.println();
            }
        }
    }
}