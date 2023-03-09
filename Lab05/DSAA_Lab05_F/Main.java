import java.io.*;
import java.util.*;

class node{
    int number;
    int index;
    public node(int n, int i){
        number = n;
        index = i;
    }
}

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        int k = in.nextInt();
        int n = in.nextInt();
        int n_1 = n-1;
        node[] all = new node[n];
        for (int i = 0; i < n; i++) {
            all[i] = new node(in.nextInt(), i);
        }
        node[] max = new node[n];    max[0] = all[0];
        node[] min = new node[n];    min[0] = all[0];
        node kong = new node(-1,-1);


        int nowMaxLength = 1, leftIndex = 0, rightIndex = 0, maxL = 0, maxR = 0, minL = 0, minR = 0;
        while (rightIndex < n_1){
            rightIndex++;

            while (maxR - maxL != -1 && all[rightIndex].number > max[maxR].number) {
                max[maxR] = kong;
                maxR--;
            }
            maxR++;
            max[maxR] = all[rightIndex];

            while (minR - minL != -1 && all[rightIndex].number < min[minR].number) {
                min[minR] = kong;
                minR--;
            }
            minR++;
            min[minR] = all[rightIndex];



            while ( max[maxL].number - min[minL].number > k ){
                leftIndex++;
                if (max[maxL].index < all[leftIndex].index) {
                    maxL++;
                }
                if (min[minL].index < all[leftIndex].index) {
                    minL++;
                }
            }
            nowMaxLength = Math.max(nowMaxLength, rightIndex-leftIndex+1);
        }
        System.out.println(nowMaxLength);
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