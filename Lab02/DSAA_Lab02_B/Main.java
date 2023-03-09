import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] count = new int[T];

        for (int i = 0; i < T; i++) {
            count[i] = sc.nextInt();
        }

        for (int i = 0; i < T; i++) {
            long n = count[i];
            long answer = n*(n+1)*(n+2)/6;
            System.out.println( answer );
        }
    }
}