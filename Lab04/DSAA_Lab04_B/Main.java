import java.io.*;
import java.util.Scanner;

class node{
    int index;
    node next;
    node last;
    public node(int i){
        index = i;
    }
}

public class Main {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   //  m <= n < 2*10^6
        int m = sc.nextInt();   //  1 < m <= 10
        int m_1 = m-1;
        int mCount;
        int lineLength = n;
        int lineLengthTemp = 0;

        node head = new node(0);
        node tail = new node(9999999);
        node curr = head;

        for (int i = 1; i <= lineLength; i++) {
            node node1 = new node(i);
            curr.next = node1;
            node1.last = curr;
            curr = curr.next;
        }
        curr.next = tail;
        tail.last = curr;

        while (lineLength > 0){
            mCount = m-1;
            curr = head;
            for (int i = 1; i <= lineLength; i++) {
                if (mCount == m_1){
                    mCount = 0;
                    out.print(curr.next.index+" ");
                    lineLengthTemp++;
                    curr.next.next.last = curr;
                    curr.next = curr.next.next;
                } else {
                    mCount++;
                    curr = curr.next;
                }
            }
            lineLength -= lineLengthTemp;
            lineLengthTemp = 0;
        }
        out.close();
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