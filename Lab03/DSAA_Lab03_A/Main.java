import java.io.*;
import java.util.*;

public class Main {
    private static int[] A, B, C;
    private static int j, p, q;
    private static boolean aFinish, bFinish;
    private static long reverseCount;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int testCase = in.nextInt();
        for (int i = 0; i < testCase; i++) {
            A = new int[in.nextInt()];
            B = new int[in.nextInt()];

            for (int j = 0; j < A.length; j++) {
                A[j] = in.nextInt();
            }
            for (int j = 0; j < B.length; j++) {
                B[j] = in.nextInt();
            }

            p = 0; q = 0;
            aFinish = false; bFinish = false;
            reverseCount = 0;
            C = new int[A.length + B.length];
            for (j = 0; j < C.length; j++) {
                if (aFinish) {
                    doB2();
                } else if (bFinish){
                    doA();
                } else if (A[p] <= B[q]){
                    doA();
                } else {
                    doB();
                }
            }
            out.println(reverseCount);
            for (int j = 0; j < C.length; j++) {
                out.print(C[j]+" ");
            }
            out.println("");
        }
        out.close();
    }

    private static void doA() {
        C[j] = A[p];
        if (p < A.length-1) {
            p++;
        } else {
            aFinish = true;
        }
    }
    private static void doB() {
        C[j] = B[q];
        if (q < B.length-1) {
            q++;
        } else {
            bFinish = true;
        }
        reverseCount += (A.length - p);
    }
    private static void doB2() {
        C[j] = B[q];
        if (q < B.length-1) {
            q++;
        } else {
            bFinish = true;
        }
    }
}

//Java Fast IO
class Untitled {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        while (in.hasNext()) {
            int x = in.nextInt();
            out.println(x);
        }
        out.close();
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