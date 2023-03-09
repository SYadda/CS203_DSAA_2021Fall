import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        int[] count = new int[10000];

        for (int i = 0; i < 10000; i++) {
            count[i] = i;
        }

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10000; i++) {
            long n = count[i];
            long answer = n*(n+1)*(n+2)/6;
            System.out.println( answer );
        }
    }
}