import java.io.*;
import java.util.*;

class node {
    int index;
    node father;
    node son;
    public node(int i) {
        index = i;
    }
}

public class Main {
    public static node a, b, aNext, bLast, curr;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        int length = n * m;
        node[] allNode = new node[length];
        int count = m;
        for (int i = 0; i < length; i++) {
            allNode[i] = new node(i);
            if (count < m) {
                count++;
                allNode[i-1].son = allNode[i];
                allNode[i].father = allNode[i-1];
            } else {
                count = 1;
            }
        }

        while (k-- > 0){
            a = allNode[in.nextInt()];
            b = allNode[in.nextInt()];
            if (a.son == null){
                if (b.father != null) {
                    b.father.son = null;
                }
            } else {
                if (b.father == null) {
                    a.son.father = null;
                } else {
                    aNext = a.son;
                    bLast = b.father;
                    bLast.son = aNext;
                    aNext.father = bLast;
                }
            }
            a.son = b;
            b.father = a;
        }

        curr = allNode[x];
        while (curr.father != null) {
            curr = curr.father;
        }

        while (true) {
            out.print(curr.index+" ");
            if (curr.son != null){
                curr = curr.son;
            } else {
                break;
            }
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