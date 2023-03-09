import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        int N = in.nextInt();
        int temp, curr;
        int[] all = new int[N+1];
        for (int i = 1; i <= N; i++) {
            all[i] = in.nextInt();
        }

        int N_2_1 = N / 2 - 1;

        if (all[1] > all[2]) {
            for (int i = 1; i <= N_2_1; i++) {
                if (all[i] < all[2*i] || all[i] < all[2*i + 1]){
                    System.out.println("Neither");
                    return;
                }
            }
            if (N % 2 == 0){
                if (all[N/2] < all[N]){
                    System.out.println("Neither");
                    return;
                }
            } else {
                if (all[N/2] < all[N-1] || all[N/2] < all[N]){
                    System.out.println("Neither");
                    return;
                }
            }
            System.out.println("Max");
        } else {
            for (int i = 1; i <= N_2_1; i++) {
                if (all[i] > all[2*i] || all[i] > all[2*i + 1]){
                    System.out.println("Neither");
                    return;
                }
            }
            if (N % 2 == 0){
                if (all[N/2] > all[N]){
                    System.out.println("Neither");
                    return;
                }
            } else {
                if (all[N/2] > all[N-1] || all[N/2] > all[N]){
                    System.out.println("Neither");
                    return;
                }
            }
            System.out.println("Min");
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