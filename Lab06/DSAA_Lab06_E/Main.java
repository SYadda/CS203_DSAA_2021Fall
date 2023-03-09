import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] allStr = str.split("");
        StringBuilder strBu = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            strBu.append("^");
            strBu.append(allStr[i]);
        }
        strBu.append("^");

        String[] allStrBu = strBu.toString().split("");
        int aSBLength = allStrBu.length;
        int[] huiWen = new int[aSBLength];
        for (int i = 0; i < aSBLength; i++) {
            huiWen[i] = 1;
        }
        int max = 1, center = 0, right = 0;

        for (int i = 0; i < aSBLength; i++) {

            if (right > i) {
                if (right > i + huiWen[center * 2 - i]) {
                    huiWen[i] = huiWen[center * 2 - i];
                } else {
                    huiWen[i] = right - i;
                }
            }

            while (  i - huiWen[i] != -1 &&
                     i + huiWen[i] != aSBLength &&
                     allStrBu[i-huiWen[i]].equals(  allStrBu[i+huiWen[i]]  )
            ) {
                huiWen[i]++;
            }
            max = Math.max(huiWen[i]-1, max);

            if ( huiWen[i] <= right - i ) {
                continue;
            }
            center = i;
            right = i + huiWen[i];
        }
        System.out.println(max);
    }
}