import java.io.*;
import java.util.*;

class node{
    int n1;
    int n2;
    int length;
    public node (int i1, int i2, int l){
        n1 = i1;
        n2 = i2;
        length = l;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        node node = (node) o;
        return n1 == node.n1 && n2 == node.n2;
    }
}

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int num = in.nextInt();
        while (num-- > 0){
            String strA = in.next();
            String strB = in.next();

            int a1 = strA.charAt(0) - 96;
            int a2 = strA.charAt(1) - 48;
            node start = new node(a1, a2, 0);
            int b1 = strB.charAt(0) - 96;
            int b2 = strB.charAt(1) - 48;
            node end = new node(b1, b2, 0);

            ArrayList<node> all = new ArrayList<>();
            all.add(start);

            boolean[][] isVisited = new boolean[9][9];

            while (true) {
                if (all.get(0).equals(end)) {
                    out.println(all.get(0).length);
                    break;
                } else {
                    int x1 = all.get(0).n1;
                    int x2 = all.get(0).n2;
                    int len = all.get(0).length + 1;
                    isVisited[x1][x2] = true;
                    all.remove(0);

                    if (x1 < 8 && x2 < 7 && ! isVisited[x1+1][x2+2]) {
                        all.add(new node(x1 + 1, x2 + 2, len));
                    }
                    if (x1 < 7 && x2 < 8 && ! isVisited[x1+2][x2+1]) {
                        all.add(new node(x1 + 2, x2 + 1, len));
                    }
                    if (x1 > 1 && x2 > 2 && ! isVisited[x1-1][x2-2]) {
                        all.add(new node(x1 - 1, x2 - 2, len));
                    }
                    if (x1 > 2 && x2 > 1 && ! isVisited[x1-2][x2-1]) {
                        all.add(new node(x1 - 2, x2 - 1, len));
                    }
                    if (x1 < 8 && x2 > 2 && ! isVisited[x1+1][x2-2]) {
                        all.add(new node(x1 + 1, x2 - 2, len));
                    }
                    if (x1 < 7 && x2 > 1 && ! isVisited[x1+2][x2-1]) {
                        all.add(new node(x1 + 2, x2 - 1, len));
                    }
                    if (x1 > 1 && x2 < 7 && ! isVisited[x1-1][x2+2]) {
                        all.add(new node(x1 - 1, x2 + 2, len));
                    }
                    if (x1 > 2 && x2 < 8 && ! isVisited[x1-2][x2+1]) {
                        all.add(new node(x1 - 2, x2 + 1, len));
                    }
                }
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