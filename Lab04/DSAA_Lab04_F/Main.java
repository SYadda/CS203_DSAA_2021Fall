import java.io.*;
import java.util.*;

class node {
    ArrayList<Character> arr = new ArrayList<>();
    node next;
    node last;
    boolean isTransform = false;
}

public class Main {
    private static int strLen, strLenRemain;
    private static node curr, head, tail;
    private static QWriter out = new QWriter();
    public static void main(String[] args) {
        QReader in = new QReader();
        String str = in.next();
        strLen = str.length();
        int lineCount = strLen / 1414;

        head = new node();
        tail = new node();
        curr = head;
        for (int i = 0; i < lineCount; i++) {  // O(n^0.5)
            node n1 = new node();
            curr.next = n1;
            n1.last = curr;
            curr = curr.next;
            int i_1414 = i*1414;
            for (int j = 0; j < 1414; j++) {   // O(n^0.5)
                n1.arr.add(  str.charAt( i_1414 + j)  );
            }
        }

        if (strLen % 1414 != 0) {
            node n1 = new node();
            curr.next = n1;
            n1.last = curr;
            curr = curr.next;
            strLenRemain = strLen - lineCount * 1414;
            int lineCount_1414 = lineCount*1414;
            for (int i = 0; i < strLenRemain; i++) {
                n1.arr.add(  str.charAt( lineCount_1414 + i)  );
            }
        }

        curr.next = tail;
        tail.last = curr;


        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int operation = in.nextInt();
            if (operation == 1){
                Insert( in.next().charAt(0), in.nextInt() );
            } else if (operation == 2){
                Find( in.nextInt() );
            } else {
                Transform( in.nextInt(), in.nextInt() );
            }
        }
        out.close();
    }

    private static void Insert( char ch, int p ){
        p--;
        curr = head.next;
        while (true){    // O(n^0.5)
            if (p >= curr.arr.size()){
                p -= curr.arr.size();
                curr = curr.next;
            } else {
                break;
            }
        }
        if (curr.isTransform){
            curr.arr.add(p, (char) ('a'+'z'-ch) );
        } else {
            curr.arr.add(p,ch);
        }

        if (curr.arr.size() == 2000){
            node n1 = new node();
            n1.isTransform = curr.isTransform;
            n1.next = curr.next;
            n1.last = curr;
            curr.next.last = n1;
            curr.next = n1;
            for (int i = 0; i < 1000; i++) {   // O(n^0.5)
                n1.arr.add(   curr.arr.get(1000)   );
                curr.arr.remove(1000);
            }
        }
    }



    private static void Find( int p ){
        p--;
        curr = head.next;
        while (true){    // O(n^0.5)
            if (p >= curr.arr.size()){
                p -= curr.arr.size();
                curr = curr.next;
            } else {
                break;
            }
        }
        if (curr.isTransform){
            out.println(  (char)(  'a'+'z'-curr.arr.get(p)  )  );
        } else {
            out.println(  curr.arr.get(p)  );
        }
    }



    private static void Transform( int l, int r ){
        l--; r--;
        int lineCountL = 0, lineCountR = 0;
        node currL = head.next;
        while (true){     // O(n^0.5)
            if (l >= currL.arr.size()){
                l -= currL.arr.size();
                currL = currL.next;
                lineCountL++;
            } else {
                break;
            }
        }

        node currR = head.next;
        while (true){      // O(n^0.5)
            if (r >= currR.arr.size()){
                r -= currR.arr.size();
                currR = currR.next;
                lineCountR++;
            } else {
                break;
            }
        }

        if (lineCountL == lineCountR){
            if (currR.isTransform){
                for (int i = 0; i < l; i++) {          // O(n^0.5)
                    currR.arr.set(   i, (char) ('a'+'z'-currR.arr.get(i))   );
                }
                for (int i = r+1; i < currR.arr.size(); i++) {          // O(n^0.5)
                    currR.arr.set(   i, (char) ('a'+'z'-currR.arr.get(i))   );
                }
            } else {
                for (int i = l; i <= r; i++) {          // O(n^0.5)
                    currR.arr.set(   i, (char) ('a'+'z'-currR.arr.get(i))   );
                }
            }
        } else {
            if (currL.isTransform){
                for (int i = 0; i < l; i++) {          // O(n^0.5)
                    currR.arr.set(   i, (char) ('a'+'z'-currR.arr.get(i))   );
                }
            } else {
                for (int i = l; i < currL.arr.size(); i++) {      // O(n^0.5)
                    currL.arr.set(   i, (char) ('a'+'z'-currL.arr.get(i))   );
                }
            }

            for (int i = lineCountL+1; i < lineCountR; i++) {     // O(n^0.5)
                currL = currL.next;
                currL.isTransform = ! currL.isTransform;
            }

            if (currR.isTransform){
                for (int i = r+1; i < currR.arr.size(); i++) {     // O(n^0.5)
                    currR.arr.set(   i, (char) ('a'+'z'-currR.arr.get(i))   );
                }
            } else {
                for (int i = 0; i <= r; i++) {       // O(n^0.5)
                    currR.arr.set(   i, (char) ('a'+'z'-currR.arr.get(i))   );
                }
            }
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