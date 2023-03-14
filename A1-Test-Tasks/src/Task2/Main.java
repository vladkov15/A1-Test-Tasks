package Task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите число n");
        System.out.println(GetSum(new Scanner(System.in).nextInt()));

    }

    public static String GetSum(int number){
        double un = (1 / GetFactorial(number));
        double sum = 0;
        for (int i = 1; i <= number ; i++) {
            sum += GetFactorial(i);
        }
        un *= sum;
        String result = String.format("%.6f",un);
        return result;
    }

    public static double GetFactorial(int number){
        int fact = 1;
        for(int i = 1; i <= number; i++){
            fact = fact * i;
        }
        return fact;
    }


}
