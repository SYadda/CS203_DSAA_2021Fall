import java.io.*;
import java.util.*;

class node {
    int value;
    ArrayList<Integer> edgeNode = new ArrayList<>();
    public node(int i){
        value = i;
    }
    node currFather;
}

public class Main {
    public static boolean findRing;
    public static node curr;
    public static boolean[] isVisited;
    public static node[] allNode;
    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt();
        int m = in.nextInt();
        allNode = new node[n+1];
        isVisited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            allNode[i] = new node(i);
            isVisited[i] = false;
        }
        while (m-- > 0){
            int u = in.nextInt();
            int v = in.nextInt();
            allNode[u].edgeNode.add(v);
            allNode[v].edgeNode.add(u);
        }

        findRing = false;
        for (int i = 1; i <= n; i++) {
            if ( ! isVisited[i]){
                DFS(i);
            }
        }
        if (! findRing) {
            System.out.println("Good");
        }
    }

    public static void DFS(int i){
        if (isVisited[i]){
            System.out.println("Bad");
            findRing = true;
            return;
        } else {
            isVisited[i] = true;
            for (int x: allNode[i].edgeNode) {
                if (findRing){
                    return;
                } else if ( ! allNode[x].equals(allNode[i].currFather)){  // no father-son false ring
                    allNode[x].currFather = allNode[i];
                    DFS(x);
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