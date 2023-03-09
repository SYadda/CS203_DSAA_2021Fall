import java.io.*;
import java.util.*;

class node{
    int index;
    int number;
    node next;
    node last;
    public node(int i, int n){
        index = i;
        number = n;
    }
}

public class Main {
    private static boolean improveThis;
    private static node head = new node(-2147483648, 0);
    private static node tail = new node(2147483647, -1);
    private static node curr, temp;
    private static int size;
    private static ArrayList<Integer> delete = new ArrayList<>();
    private static ArrayList<node> checkAgain = new ArrayList<>();


    private static int n;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            node[] allNode = new node[n+1];

            curr = head; temp = null;
            for (int j = 1; j <= n; j++) {
                allNode[j] = new node(in.nextInt(), j);
                curr.next = allNode[j];
                allNode[j].last = curr;
                curr = curr.next;
            }
            curr.next = tail;
            tail.last = curr;

            curr = head;
            improveThis = false;
            for (int j = 0; j < n; j++) {
                curr = curr.next;
                if (!improveThis){   //improveThis: 检查for循环中上一次操作是否剔除
                    if (curr.last.index > curr.index || curr.index > curr.next.index){
                        improveThis = true;
                        temp = curr;
                        curr.last.next = curr.next;
                        curr.next.last = curr.last;
                        delete.add(curr.number);
                    }
                } else {
                    improveThis = false;
                    if (temp.index > curr.index || curr.index > curr.next.index){
                        improveThis = true;
                        temp = curr;
                        curr.last.next = curr.next;
                        curr.next.last = curr.last;
                        delete.add(curr.number);
                    }
                }
            }

            while (!delete.isEmpty()) {

                while (!delete.isEmpty()) {
                    curr = allNode[delete.get(0)].last;
                    delete.remove(0);
                    if (curr.equals(head) || curr.next.equals(tail)){
                        continue;
                    }
                    if (!checkAgain.contains(curr)) {
                        checkAgain.add(curr);
                    }
                    if (!checkAgain.contains(curr.next)) {
                        checkAgain.add(curr.next);
                    }
                }

                improveThis = false;
                for (int j = 0; j < checkAgain.size(); j++) {
                    curr = checkAgain.get(j);
                    if (!improveThis) {
                        if (curr.last.index > curr.index || curr.index > curr.next.index) {
                            improveThis = true;
                            temp = curr;
                            curr.last.next = curr.next;
                            curr.next.last = curr.last;
                            delete.add(curr.number);
                        }
                    } else {
                        improveThis = false;
                        if (temp.index > curr.index || curr.index > curr.next.index){
                            improveThis = true;
                            temp = curr;
                            curr.last.next = curr.next;
                            curr.next.last = curr.last;
                            delete.add(curr.number);
                        }
                    }
                }
                checkAgain.clear();
            }

            curr = head.next;
            while ( ! curr.equals(tail) ) {
                out.print(curr.index + " ");
                curr = curr.next;
            }
            out.println("");
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