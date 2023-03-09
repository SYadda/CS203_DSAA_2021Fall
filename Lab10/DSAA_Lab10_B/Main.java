import java.io.*;
import java.util.*;

class node{
    int index;
    ArrayList<Integer> child = new ArrayList<>();
    ArrayList<Integer> reverseChild = new ArrayList<>();
    int SCCIndex;
    int ruDu;
    int chuDu;
    public node(int i){ index = i; }
}

public class Main {
    public static int n;
    public static int[] edgeU;
    public static int[] edgeV;
    public static QReader in = new QReader();
    public static int[] stackOut;
    public static int pointerOut = 0;
    public static node[] allNode;
    public static boolean[] isVisited;
    public static void main(String[] args){
        n = in.nextInt();
        int m = in.nextInt();

        edgeU = new int[m];
        edgeV = new int[m];
        for (int i = 0; i < m; i++) {
            edgeU[i] = in.nextInt();
            edgeV[i] = in.nextInt();
        }

        if ( ! checkSCC(m)){
            System.out.println(-1);
            return;
        }

        if ( n == 1 ){ //  checkSCC(1)
            System.out.println(0);
            return;
        }

        int left = 1;
        int right = m;
        int mid;
        while (right - left > 1){
            mid = left + ( right - left ) / 2;
            if (checkSCC(mid)){
                right = mid;
            } else {
                left = mid;
            }
        }
        System.out.println(right);
    }

    public static boolean checkSCC (int m){
        pointerOut = 0;

        allNode = new node[n+1];
        for (int i = 1; i <= n; i++) {
            allNode[i] = new node(i);
        }
        stackOut = new int[n+1];
        isVisited = new boolean[n+1];

        for (int i = 0; i < m; i++) {
            int u = edgeU[i];
            int v = edgeV[i];
            allNode[u].child.add(v);
            allNode[v].reverseChild.add(u);
        }

        for (int i = 1; i <= n; i++) {
            if (! isVisited[i]){
                DFS(i);
            }
        }

        Arrays.fill(isVisited, false);

        int countSCC = 0;
        for (int i = pointerOut - 1; i >= 0; i--) {
            if (! isVisited[  stackOut[i]  ]){
                countSCC++;
                allNode[  stackOut[i]  ].SCCIndex = countSCC;
                DFS2(stackOut[i]);
            }
        }

        return (countSCC == 1);
    }

    public static void DFS(int i){
        isVisited[i] = true;
        for (int x: allNode[i].reverseChild) {
            if (!isVisited[x]){
                DFS(x);
            }
        }
        stackOut[pointerOut++] = i;
    }

    public static void DFS2(int i){
        isVisited[i] = true;
        for (int x: allNode[i].child) {
            if (!isVisited[x]){
                allNode[x].SCCIndex = allNode[i].SCCIndex;
                DFS2(x);
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