import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            String str1 = in.next();
            String str2 = in.next();
            String[] allStr1 = str1.split("");
            String[] allStr2 = str2.split("");
            int Length = str1.length();
            int Length_1 = str1.length() - 1;
            int powerCount = 1;

            for (int j = 0; j < Length_1; j++) {
                for (int k = 1; k < Length; k++) {
                    if (allStr1[j].equals(allStr2[k])){
                        if (allStr1[j+1].equals(allStr2[k-1])){
                            powerCount *= 2;
                        }
                        break;
                    }
                }
            }
            out.println(powerCount);
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