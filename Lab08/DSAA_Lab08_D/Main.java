import java.io.*;
import java.util.*;

public class Main {
    public static long temp;
    public static int curr;
    public static long[] all;
    public static boolean[] cunZai;
    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt();
        all = new long[n+1];
        cunZai = new boolean[2*n+2];
        for (int i = 1; i <= n; i++) {
            all[i] = in.nextInt();
            cunZai[i] = true;
        }

        if (n == 1){
            System.out.println(0);
            return;
        }

        Arrays.sort(all, 1, n+1);
        long sum;
        long count = 0;
        for (int i = 1; i < n-1; i++) {
            sum = all[1];
            count += all[1];
            deleteHeap(n-i+1);

            sum += all[1];
            count += all[1];
            deleteHeap(n-i);

            addHeap(n-i, sum);
        }
        count += all[1];
        count += all[2];
        System.out.println(count);
    }

    public static void deleteHeap(int size){
        all[1] = all[size];
        cunZai[size] = false;
        curr = 1;
        while (curr < size-1){
            if (cunZai[2*curr]){  // cunZai left son
                if  (cunZai[2*curr+1]){   // cunZai right son
                    if (all[curr] > all[2*curr] || all[curr] > all[2*curr+1]){  // top is bigger than at least one son
                        if (all[2*curr] < all[2*curr+1]){   // left son is smaller
                            temp = all[2*curr];
                            all[2*curr] = all[curr];
                            all[curr] = temp;
                            curr *= 2;
                        } else {    // right son is smaller
                            temp = all[2*curr+1];
                            all[2*curr+1] = all[curr];
                            all[curr] = temp;
                            curr = 2*curr+1;
                        }
                    } else {  // top is smaller than both son
                        break;
                    }
                } else { // ! cunZai right son, only cunZai left son
                    if (all[curr] > all[2 * curr]) {  // top is bigger than at left son
                        temp = all[2 * curr];
                        all[2 * curr] = all[curr];
                        all[curr] = temp;
                        curr *= 2;
                    } else {  // top is smaller than left son
                        break;
                    }
                }
            } else {  // ! cunZai left son
                break;
            }
        }
    }

    public static void addHeap(int size, long number){
        all[size] = number;
        cunZai[size] = true;
        curr = size;
        while (curr != 1){
            if (all[curr] < all[curr/2]){  // son < top
                temp = all[curr];
                all[curr] = all[curr/2];
                all[curr/2] = temp;
            } else {
                break;
            }
            curr /= 2;
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