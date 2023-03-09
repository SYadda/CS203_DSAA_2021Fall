import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] a, b;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int number = in.nextInt();
        a = new int[number];
        b = new int[number];
        for (int i = 0; i < number; i++) {
            a[i] = in.nextInt();
        }

        paiXu1(0,number-1);

        long outOut1, outOut2;
        if (number % 2 == 1) {
            outOut1 = a[number/2];
            outOut1 = 2 * outOut1;
        } else {
            outOut1 = a[number/2];
            outOut2 = a[number/2 - 1];
            outOut1 += outOut2;
        }
        out.println( outOut1 );
        out.close();
    }

    private static void paiXu1(int left, int right) {
        if (right - left == 0) {
            return;
        } else if (right - left == 1){
            if (a[left] < a[right]) {
                int z = a[left];
                a[left] = a[right];
                a[right] = z;
            }
        } else if (right - left == 2){
            if (a[left] < a[left+1]) {
                int z = a[left+1];
                a[left+1] = a[left];
                a[left] = z;
            }
            if (a[left] < a[right]) {
                int z = a[right];
                a[right] = a[left];
                a[left] = z;
            }
            if (a[left+1] < a[right]) {
                int z = a[right];
                a[right] = a[left+1];
                a[left+1] = z;
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
        int count = 0;
        do {
            if (a[pointer1] > a[pointer2]) {
                b[count] = a[pointer1];
                pointer1++;

            } else {
                b[count] = a[pointer2];
                pointer2++;
            }
            count++;
        } while (pointer1 < right+1 && pointer2 < middle+1);

        while (pointer1 < right+1) {
            b[count] = a[pointer1];
            pointer1++;
            count++;
        }

        while (pointer2 < middle+1) {
            b[count] = a[pointer2];
            pointer2++;
            count++;
        }

        for (int i = 0; i < count; i++) {
            a[left + i] = b[i];
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