import java.io.*;
import java.util.*;

class node {
    int value;
    int[] child = new int[30];
    int childPointer = 0;
    int[] childCSM = new int[30];
    int childCSMPointer = 0;
    int[] childLength = new int[30];
    int childLengthPointer = 0;
    public node(int i){
        value = i;
    }
}

public class Main {
    public static int currMinNode, n, n1, end, k, currCeng = 0, nextCeng = 0;
    public static long currMinLength;
    public static node[] allNode;
    public static long[][] minLength;
    public static boolean[][] isVisited;
    public static void main(String[] args) {
        QReader in = new QReader();
        n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        k = in.nextInt();
//        n1 = n;
        n1 = 100; //fixme
        allNode = new node[n+1];
        minLength = new long[k+1][n+1];
        isVisited = new boolean[k+1][n+1];
        for (int i = 1; i <= n; i++) {
            allNode[i] = new node(i);
            for (int j = 0; j <= k; j++) {
                minLength[j][i] = 920000000000000000L;
                isVisited[j][i] = false;
            }
        }

        while (m-- > 0){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            allNode[u].child[ allNode[u].childPointer++ ] = v;
            allNode[u].childLength[ allNode[u].childLengthPointer++ ] = w;
        }

        while (p-- > 0){
            int u = in.nextInt();
            int v = in.nextInt();
            allNode[u].childCSM[ allNode[u].childCSMPointer++ ] = v;
        }

        int start = in.nextInt();
        end = in.nextInt();

        minLength[0][start] = 0;

        int x = start;
        while (true) {
            isVisited[currCeng][x] = true;
            int childPointer = allNode[x].childPointer;
            for (int i = 0; i < childPointer; i++) {
                if (minLength[currCeng][x] != 920000000000000000L){
                    if (minLength[currCeng][ allNode[x].child[i] ] > minLength[currCeng][x] + allNode[x].childLength[i]  ){
                        minLength[currCeng][ allNode[x].child[i] ] = minLength[currCeng][x] + allNode[x].childLength[i];
                    }
                }
            }

            if (currCeng < k) {
                int childCSMPointer = allNode[x].childCSMPointer;
                for (int i = 0; i < childCSMPointer; i++) {
                    if (minLength[currCeng+1][ allNode[x].childCSM[i] ] > minLength[currCeng][x] ){
                        minLength[currCeng+1][ allNode[x].childCSM[i] ] = minLength[currCeng][x];
                    }
                }
            }


            currMinLength = 920000000000000000L; // 9223372036854775807L
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= k; j++) {
                    if ( ! isVisited[j][i] && currMinLength > minLength[j][i]){
                        currMinLength = minLength[j][i];
                        currMinNode = i;
                        nextCeng = j;
                    }
                }
            }

            if (currMinNode == end) {
                System.out.println(minLength[nextCeng][currMinNode]);
                return;
            } else {
                x = currMinNode;
                currCeng = nextCeng;
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