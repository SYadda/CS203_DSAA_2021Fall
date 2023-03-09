import java.io.*;
import java.util.*;

class node{
    boolean isHead;
    int weight;
    node next;
    node last;
    node myTail;
    public node(int w){
        isHead = true;
        weight = w;
        myTail = this;
    }
}

public class Main {
    private static int first, second, qTemp;
    private static boolean isNull;
    private static node curr;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();     int p = in.nextInt();     int q = in.nextInt();

        node head = new node(0);
        node tail = new node(-9999999);
        node[] allChain = new node[n+1];
        allChain[0] = head;
        for (int i = 1; i <= n; i++) {
            allChain[i] = new node( in.nextInt() );
            allChain[i].last = head;
            allChain[i].next = tail;
        }

        while (p-- > 0){
            first = in.nextInt();
            second = in.nextInt();
            allChain[second].isHead = false;
            allChain[first].myTail.next = allChain[second];
            allChain[second].last = allChain[first].myTail;
            allChain[first].myTail = allChain[second].myTail;
        }

        for (int i = 1; i <= n; i++) {
            curr = allChain[i];
            if (! curr.isHead){
                continue;
            }

            isNull = false;
            qTemp = q-1;
            while (qTemp-- > 0){
                curr = curr.next;
                if (curr == tail){
                    isNull = true;
                    break;
                }
            }

            if ( ! isNull ){
                out.print(curr.weight+" ");
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