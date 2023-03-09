import java.io.*;
import java.util.*;

class node{
    private final int index;
    int myWeight = 0;
    boolean isVisited = false;
    ArrayList<Integer> weight = new ArrayList<>();
    ArrayList<node> edgeNode = new ArrayList<>();
    node(int i){
        index = i;
    }
}



public class Main {
    private static int num, count = 0;
    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt();
        num = in.nextInt();
        node[] allNode = new node[n+1];
        for (int i = 1; i <= n; i++) {
            allNode[i] = new node(i);
        }


        for (int i = 1; i < n; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            allNode[u].edgeNode.add(allNode[v]);
            allNode[u].weight.add(w);
            allNode[v].edgeNode.add(allNode[u]);
            allNode[v].weight.add(w);
        }

        process(allNode[1]);
        System.out.println(count);
    }

    private static void process(node n){
        n.isVisited = true;
        if (n.edgeNode.size() == 1){
            if (n.myWeight == num){
                count++;
                return;
            }
        }

        for (int i = 0; i < n.edgeNode.size(); i++) {
            if ( ! n.edgeNode.get(i).isVisited ){
                n.edgeNode.get(i).myWeight = n.myWeight + n.weight.get(i);
                process(n.edgeNode.get(i));
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