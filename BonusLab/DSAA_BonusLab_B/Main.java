import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static long[][] allC = new long[1001][1001];
    public static final int[] chengFang2 = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023};  // length = 10
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        Arrays.fill(allC[0], 1);
        for (int i = 1; i <= 1000; i++) {
            for (int j = 1; j <= 1000; j++) {
                if (i <= j) {
                    if (j == 1) {  // i == 1
                        allC[i][j] = 1;
                    } else {
                        allC[i][j] = ( (allC[i-1][j-1] % 998244353) + (allC[i][j-1] % 998244353) ) % 998244353;
                    }
                }
            }
        }

        long topN = countTop(N) % 998244353 % 998244353;
        System.out.println( topN % 998244353 % 998244353 );
    }

    public static int splitN (int N) {  // return NLeft
        int max2ChengFang = 0;
        for (int i = 0; i < 10; i++) {
            if (N < chengFang2[i]) {
                max2ChengFang = i - 1;
                break;
            }
        }
        int lastLine = N - chengFang2[max2ChengFang];
        int NLeft, NRight;
        if (lastLine >= chengFang2[max2ChengFang-1] + 1) {  // left is full
            NLeft = chengFang2[max2ChengFang];
            // NRight = N - NLeft - 1;
        } else { // left is not full
            NRight = chengFang2[max2ChengFang-1];
            NLeft = N - NRight - 1;
        }
        return NLeft;
    }

    public static long countTop (int top) {
        if (top > 2){
            int left = splitN(top);
            int right = top - left - 1;

            long numberLeft = countTop(left) % 998244353 % 998244353;
            long numberRight = countTop(right) % 998244353 % 998244353;

            long couC;
            if (left < right) {
                couC = allC[left][top - 1] % 998244353 % 998244353;
            } else {
                couC = allC[right][top - 1] % 998244353 % 998244353;
            }

            return ((((couC % 998244353 % 998244353) * (numberLeft % 998244353 % 998244353)) % 998244353 % 998244353 ) * (numberRight % 998244353 % 998244353)) % 998244353 % 998244353;
        } else {
            return 1;
        }
    }
}