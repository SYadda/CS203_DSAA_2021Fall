import java.io.*;
import java.util.*;

class node{
    int index;
    int waitTime;
    node next1;
    node last1;
    node next2;
    node last2;
    public node(int i){
        index = i;
    }
}

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();

        node head = new node(0);
        node tail = new node(-1);
        node curr = head;

        node[] allNode = new node[n+1];
        allNode[0] = head;
        for (int i = 1; i <= n; i++) {
            allNode[i] = new node(i);
        }




        int index;
        for (int i = 0; i < p; i++) {
            index = in.nextInt();
            curr.next1 = allNode[index];
            allNode[index].last1 = curr;
            curr = curr.next1;
        }
        curr.next1 = tail;
        tail.last1 = curr;

        curr = head;
        for (int i = 0; i < q; i++) {
            index = in.nextInt();
            curr.next2 = allNode[index];
            allNode[index].last2 = curr;
            curr = curr.next2;
        }
        curr.next2 = tail;
        tail.last2 = curr;



        boolean work1 = true, work2 = true;
        node curr1Origin, curr1 = head.next1, curr2 = head.next2;
        int time = 0;
        while (work1 && work2){
            time++;

            curr1.waitTime = time;
            curr1.last1.next1 = curr1.next1;
            curr1.next1.last1 = curr1.last1;
            if (curr1.next2 != null) {
                curr1.last2.next2 = curr1.next2;
                curr1.next2.last2 = curr1.last2;
            }

            curr1Origin = curr1;
            curr1 = curr1.next1;

            if (curr1Origin.index != curr2.index){
                curr2.waitTime = time;
                curr2.last2.next2 = curr2.next2;
                curr2.next2.last2 = curr2.last2;
                if (curr2.next1 != null) {
                    curr2.last1.next1 = curr2.next1;
                    curr2.next1.last1 = curr2.last1;
                }
            }
            if (curr1.index == curr2.index) {
                curr1 = curr1.next1;
            }
            curr2 = curr2.next2;

            if ( curr1.index == -1 ){
                work1 = false;
            }
            if ( curr2.index == -1 ){
                work2 = false;
            }
        }

        while (work1) {
            time++;
            curr1.waitTime = time;
            curr1 = curr1.next1;

            if ( curr1.index == -1 ){
                work1 = false;
            }
        }

        while (work2) {
            time++;
            curr2.waitTime = time;
            curr2 = curr2.next2;

            if ( curr2.index == -1 ){
                work2 = false;
            }
        }




        for (int i = 1; i <= n; i++) {
            out.print(allNode[i].waitTime+" ");
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