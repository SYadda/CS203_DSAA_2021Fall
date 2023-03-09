import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] input = new int[size];
        for (int i = 0; i < size; i++) {
            input[i] = sc.nextInt();
        }

        for (int i = 0; i < size; i++) {
            int check = input[i];
            int number = 0;
            do {
                number++;
                check = check - power(3,number);
            } while (check > 0);


            int[] split = new int[number];
            int remain = input[i];
            for (int j = 1; j < number; j++) {
                remain = remain - power(3,j);
            }

            if (number > 1){
                remain--;
            }

            for (int j = 0; j < number; j++) {
                int powerrr = power(3, number-j-1);
                split[j] = remain / powerrr;
                remain = remain % powerrr;
            }

            if (number == 1){
                split[0]--;
            }

            for (int j = 0; j < number; j++) {
                switch (split[j]){
                    case 0:
                        split[j] = 2;
                        break;
                    case 1:
                        split[j] = 3;
                        break;
                    case 2:
                        split[j] = 6;
                        break;
                }
            }

            StringBuilder add = new StringBuilder();
            for (int j = 0; j < number; j++) {
                add.append(split[j]);
            }

            BigInteger bi = new BigInteger(add.toString());

            long output = bi.longValue();
            System.out.println(output);
        }
    }

    public static int power(int a, int b){
        int x = 1;
        for (int i = 0; i < b; i++) {
            x = x * a;
        }
        return x;
    }
}