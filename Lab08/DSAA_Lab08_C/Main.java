import java.io.*;
import java.util.*;

class node{
    long key;
    int bIndex;
    public node(int a, int b){
        key = (long)a * b;
        bIndex = 1;
    }
}

public class Main {
    public static int A;
    public static node temp;
    public static node[] heap;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        A= in.nextInt();
        int B= in.nextInt();
        int K= in.nextInt();
        int[] allA = new int[A+1];
        int[] allB = new int[B+1];
        for (int i = 1; i <= A; i++) {
            allA[i] = in.nextInt();
        }
        for (int i = 1; i <= B; i++) {
            allB[i] = in.nextInt();
        }
        Arrays.sort(allB);

        heap = new node[A+1];
        for (int i = 1; i <= A; i++) {
            heap[i] = new node(allA[i], allB[1]);
        }

        for (int i = A / 2; i > 0; i--) {
            exchange(i);
        }

        // heap build finished

        while (K-- > 0){
            node i = delete();
            out.print(i.key+" ");

            if (i.bIndex != B) { //  A[i]B[all] has not been print
                i.key /= allB[i.bIndex];
                i.bIndex++;
                i.key *= allB[i.bIndex];

                A++;
                heap[A] = i;   // insert
                exchange2(A);  // insert
            }
        }
        out.close();
    }

    public static void exchange(int i) {  // go down
        int curr = i;
        while (2*curr <= A){   //  heap[curr] has left son
            if (2*curr < A){   // heap[curr] has right son
                if (heap[curr].key > heap[2*curr].key || heap[curr].key > heap[2*curr + 1].key) {  // heap[curr] > at least one of its sons
                    if ( heap[2*curr].key < heap[2*curr + 1].key ) {  // left son < right son
                        temp = heap[curr];
                        heap[curr] = heap[2*curr];
                        heap[2*curr] = temp;
                        curr *= 2;
                    } else { // left son >= right son
                        temp = heap[curr];
                        heap[curr] = heap[2*curr + 1];
                        heap[2*curr + 1] = temp;
                        curr = 2*curr + 1;
                    }
                } else {
                    break;
                }
            } else { // heap[curr] has not right son (only has left son)
                if (heap[curr].key > heap[2*curr].key){   // heap[curr] > its left son
                    temp = heap[curr];
                    heap[curr] = heap[2*curr];
                    heap[2*curr] = temp;
                    curr *= 2;
                } else {
                    break;
                }
            }
        }
    }

    public static void exchange2(int i) {  // go up
        int curr = i;

        while (curr / 2 != 0){   //  heap[curr] is not root (heap[curr] has parent)
            if (heap[curr].key < heap[curr / 2].key){   // heap[curr] < its parent
                temp = heap[curr];
                heap[curr] = heap[curr / 2];
                heap[curr / 2] = temp;
                curr /= 2;
            } else {
                break;
            }
        }
    }

    public static node delete(){
        node ret = heap[1];
        heap[1] = heap[A];
        A--;
        exchange(1);
        return ret;
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