import java.io.*;
import java.util.*;

class node{
    int index;
    ArrayList<node> edgeNode = new ArrayList<>();
    boolean isVisited = false;
    int time = 0;
    public node (int i){
        index = i;
    }
    public node(){}
}

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt();
        int m = in.nextInt();
        node[] BFS = new node[2*m+2];
        node[] allNode = new node[n+1];
        for (int i = 0; i <= n; i++) {
            allNode[i] = new node(i);
        }
        while (m-- > 0){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            if (w == 1){
                allNode[u].edgeNode.add(allNode[v]);
            } else {
                node temp = new node(-1);
                allNode[u].edgeNode.add(temp);
                temp.edgeNode.add(allNode[v]);
            }
        }


        int front=0;
        int rear=0;
        BFS[rear++]= allNode[1];
        allNode[1].isVisited=true;
        while ( front!=rear){
            node curr = BFS[front++];


            for (int i = 0; i < curr.edgeNode.size(); i++) {
                if ( ! curr.edgeNode.get(i).isVisited ){
                    curr.edgeNode.get(i).time = curr.time + 1;
                    BFS[rear++]=curr.edgeNode.get(i);
                    curr.edgeNode.get(i).isVisited=true;
                }
            }
        }
        if(allNode[n].isVisited) System.out.println(allNode[n].time);
        else System.out.println(-1);
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