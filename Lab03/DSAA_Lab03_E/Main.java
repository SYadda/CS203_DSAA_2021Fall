import java.io.*;
import java.util.*;

public class Main {
    static int[] a, b;
    static long count = 0;
    static int temp;
    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt();
        a = new int[n];
        b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        paiXu1(0,n-1);

        System.out.println(count);
    }

    private static void exchangeA (int m, int n){
        temp = a[m];
        a[m] = a[n];
        a[n] = temp;
    }

    private static void paiXu1(int left, int right) {
        if (right - left == 0) {
            return;
        } else if (right - left == 1){
            if (a[left] > a[right]) {
                exchangeA(left,right);
                count += a[left];
            }
        } else if (right - left == 2){
            if (a[left+1] > a[right]) {
                exchangeA(left+1, right);
                count += a[left+1];
            }
            if (a[left] > a[left+1]) {
                exchangeA(left,left+1);
                count += a[left];
            }
            if (a[left+1] > a[right]) {
                exchangeA(left+1, right);
                count += a[left+1];
            }
        } else {
            int middle = (left + right) / 2;
            paiXu1(middle+1, right);
            paiXu1(left, middle);
            paiXu2(left, right);
        }
    }

    private static void paiXu2(int left, int right) {
        int middle = (left + right) / 2;
        int pointer1 = middle+1, pointer2 = left;
        int step = left;
        do {
            if (a[pointer1] < a[pointer2]) {
                b[step] = a[pointer1];
                count += ((long) (pointer1 - step) * a[pointer1] );
                pointer1++;
            } else {
                b[step] = a[pointer2];
                pointer2++;
            }
            step++;
        } while (pointer1 < right+1 && pointer2 < middle+1);

        while (pointer1 < right+1) {
            b[step] = a[pointer1];
            pointer1++;
            step++;
        }

        while (pointer2 < middle+1) {
            b[step] = a[pointer2];
            pointer2++;
            step++;
        }

        for (int i = left; i < step; i++) {
            a[i] = b[i];
        }

    }

}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}