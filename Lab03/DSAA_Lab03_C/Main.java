import java.io.*;
import java.util.*;

public class Main {
    public static int n, isCount = 0, ssCount = 0, temp;
    public static int[] a = new int[1000];
    public static int[] b;
    public static int[] c;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            n = in.nextInt();
            for (int j = 0; j < n; j++) {
                a[j] = in.nextInt();
            }

            InsertionSort();
            SelectionSort();

            for (int j = 0; j < n; j++) {
                out.print(b[j]+" ");
            }
            out.println("");

            if (isCount < ssCount) {  out.println("Insertion Sort wins!");  }
            else                   {  out.println("Selection Sort wins!");  }

            isCount = 0;    ssCount = 0;
        }
        out.close();
    }

    public static void InsertionSort(){

        b = Arrays.copyOfRange(a, 0, n);

        int i, j;
        for (i = 0; i < n; i++) {
            for (j = i; j > 0; j--) {
                isCount++; // if (b[j-1] > b[j])
                if (b[j-1] > b[j]){
                    exchangeB(j,j-1);   isCount++;
                } else {
                    break;
                }
            }
        }
    }

    public static void SelectionSort(){

        c = Arrays.copyOfRange(a, 0, n);

        int i, j, min;
        for (i = 0; i < n-1; i++) {
            min = i;
            for (j = i+1; j < n; j++) {
                ssCount++; //  if ( c[min] > c[j] )
                if ( c[min] > c[j] ){
                    min = j;
                }
            }
            exchangeC(min, i); ssCount++;
        }
    }

    public static void exchangeB(int b1, int b2){
        temp = b[b1];
        b[b1] = b[b2];
        b[b2] = temp;
    }
    public static void exchangeC(int c1, int c2){
        temp = c[c1];
        c[c1] = c[c2];
        c[c2] = temp;
    }
}

//Java Fast IO

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

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}