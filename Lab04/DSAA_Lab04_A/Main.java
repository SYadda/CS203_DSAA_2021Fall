import java.io.*;
import java.util.*;

class node{
    int di;
    int zhi;
    node next;
    node last;
    public node(int di, int zhi){
        this.di = di;
        this.zhi = zhi;
    }
}

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        node head = new node(2000000000,2000000000);
        node tail = new node(-2000000000,-2000000000);
        node current = head;
        for (int i = 0; i < n; i++) {
            node node1 = new node(in.nextInt(), in.nextInt());
            current.next = node1;
            node1.last = current;
            current = current.next;
        }
        current.next = tail;
        tail.last = current;

        current = head;////////////
        for (int i = 0; i < m; i++) {
            int di2 = in.nextInt();
            int zhi2 = in.nextInt();

            while ( current.next.zhi >= zhi2 ){
                current = current.next;
            }

            if ( zhi2 < current.zhi) {
                node node2 = new node(di2,zhi2);

                node2.next = current.next;
                current.next.last = node2;

                current.next = node2;
                node2.last = current;

                current = current.next;
            } else if ( zhi2 > current.zhi ){
                node node2 = new node(di2, zhi2);

                current.last.next = node2;
                node2.last = current.last;

                node2.next = current;
                current.last = node2;

                current = current.next;
            } else {
                current.di += di2;
            }
        }

        current = head.next;
        int count = 0;
        while (current.di != -2000000000) {
            if (current.di != 0){
                //out.println(current.di +" "+current.zhi);
                count++;
            }
            current = current.next;
        }
        out.println(count);

        current = head.next;
        while (current.di != -2000000000) {
            if (current.di != 0){
                out.println(current.di +" "+current.zhi);
                count++;
            }
            current = current.next;
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