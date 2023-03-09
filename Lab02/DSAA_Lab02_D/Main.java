import java.util.*;
import java.io.*;

public class Main {
    static long pairsCount = 0;
    static int[] a;
    static int n;
    public static void main(String[] args) {
        QReader in = new QReader();
        n = in.nextInt();
        a = new int[n];  //a.Length == n   power.Length == 30;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] power = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768,65536,131072,262144,524288,1048576,2097152,4194304,8388608,16777216,33554432,67108864,134217728,268435456,536870912,1073741824};

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < 30; j++) {
                if (a[i] < power[j]){
                    int another = power[j] - a[i];
                    int left = i+1;
                    int right = n-1;
                    int middle;
                    boolean find = false;
                    do {
                        middle = left + (right - left) / 2;
                        if ( a[middle] == another ) {
                            find = true;
                            break;
                        } else if ( a[middle] > another ){
                            right = middle;
                        } else {
                            left = middle;
                        }
                    } while ( right - left > 1 );

                    if (find) {
                        findAround(i+1,middle,another);
                    } else if ( a[left] == another){
                        findAround(i+1,left,another);
                    } else if ( a[right] == another){
                        findAround(i+1,right,another);
                    }
                }
            }
        }

        System.out.println(pairsCount);
    }

    private static void findAround(int min, int iLeft, int another){
        pairsCount++;
        int iRight = iLeft;
        boolean findLeft = true, findRight = true;

        if (iLeft > min) {
            do {
                if ( --iLeft >= min && a[iLeft] == another){ pairsCount++; }
                else { findLeft = false; }
            } while (findLeft);
        }

        if (iRight < n-1) {
            do {
                if ( ++iRight <= n-1 && a[iRight] == another){ pairsCount++; }
                else { findRight = false; }
            } while (findRight);
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