import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        long power = 1;
        long[] allPower = new long[61];
        allPower[0] = power;  // allPower[i] = 2^i
        for (int i = 1; i < 61; i++) {
            power *= 2;
            allPower[i] = power;
        }

        QReader in = new QReader();
        QWriter out = new QWriter();

        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            long test = in.nextLong();
            int left = 0, right = 60, mid;
            while (right - left > 1) {
                mid = left + (right - left) / 2;
                if (allPower[mid] <= test){
                    left = mid;
                } else {
                    right = mid;
                }
            }
            out.println(right);
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