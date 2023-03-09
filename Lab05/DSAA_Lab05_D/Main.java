import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] left = new int[50100];
        for (int i = 0; i < 50100; i++) {
            left[i] = 0;
        }
        int pointer = 0;

        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] allStr = str.split("");
        for (int i = 0; i < allStr.length; i++) {
            if (allStr[i].equals("(")){
                pointer++;
            } else if (allStr[i-1].equals("(")){
                left[pointer-1]++;
                pointer--;
                left[pointer] = left[pointer] % 514329;
            } else {
                left[pointer-1] += left[pointer] * 2;
                left[pointer] = 0;
                pointer--;
                left[pointer] = left[pointer] % 514329;
            }
        }
        System.out.println(left[0]);
    }
}