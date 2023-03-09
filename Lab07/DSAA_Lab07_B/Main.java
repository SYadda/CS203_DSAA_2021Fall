import java.io.*;
import java.util.*;

class node{
    final int index;
    boolean isVisited = false;
    ArrayList<node> edgeNode = new ArrayList<>();
    node(int i){
        index = i;
    }
}


public class Main {
    private static int[] leaf, b;
    private static int temp;
    private static int leafCount = 0;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        node[] allNode = new node[n+1];
        leaf = new int[n];
        b = new int[n];
        for (int i = 1; i <= n; i++) {
            allNode[i] = new node(i);
        }

        for (int i = 1; i < n; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            allNode[u].edgeNode.add(allNode[v]);
            allNode[v].edgeNode.add(allNode[u]);
        }

        process(allNode[1]);
        paiXu1(0,leafCount-1);
        for (int i = 0; i < leafCount; i++) {
            out.print(leaf[i]+" ");
        }
        out.close();
    }

    private static void process(node n){
        n.isVisited = true;
        if (n.edgeNode.size() == 1){
            leaf[leafCount] = n.index;
            leafCount++;
            return;
        }

        for (int i = 0; i < n.edgeNode.size(); i++) {
            if ( ! n.edgeNode.get(i).isVisited ){
                process(n.edgeNode.get(i));
            }
        }
    }

    private static void exchangeA (int m, int n){
        temp = leaf[m];
        leaf[m] = leaf[n];
        leaf[n] = temp;
    }

    private static void paiXu1(int left, int right) {
        if (right - left == 0) {
            return;
        } else if (right - left == 1){
            if (leaf[left] > leaf[right]) {
                exchangeA(left,right);
            }
        } else if (right - left == 2){
            if (leaf[left+1] > leaf[right]) {
                exchangeA(left+1, right);
            }
            if (leaf[left] > leaf[left+1]) {
                exchangeA(left,left+1);
            }
            if (leaf[left+1] > leaf[right]) {
                exchangeA(left+1, right);
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
        int step = left;
        do {
            if (leaf[pointer1] < leaf[pointer2]) {
                b[step] = leaf[pointer1];
                pointer1++;
            } else {
                b[step] = leaf[pointer2];
                pointer2++;
            }
            step++;
        } while (pointer1 < right+1 && pointer2 < middle+1);

        while (pointer1 < right+1) {
            b[step] = leaf[pointer1];
            pointer1++;
            step++;
        }

        while (pointer2 < middle+1) {
            b[step] = leaf[pointer2];
            pointer2++;
            step++;
        }

        for (int i = left; i < step; i++) {
            leaf[i] = b[i];
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