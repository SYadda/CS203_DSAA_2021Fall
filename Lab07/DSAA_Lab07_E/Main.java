import java.io.*;
import java.util.*;

class node{
    final int index;
    boolean isVisited = false;
    ArrayList<node> edgeNode = new ArrayList<>();
    node(int i){
        index = i;
    }
}

public class Main {
    public static int[] allPath;
    public static int pathPointer = 1, height = 0, temp = 0;
    public static boolean find = false, fail;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int leafCount = n;
        node[] allNode = new node[n+1];
        allPath = new int[2*n - 1];
        allPath[0] = 1;

        for (int i = 1; i <= n; i++) {
            allNode[i] = new node(i);
        }

        for (int i = 1; i < n; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            allNode[u].edgeNode.add(allNode[v]);
            allNode[v].edgeNode.add(allNode[u]);
            if ( allNode[u].edgeNode.size() == 2 ){
                leafCount--;
            }
            if ( allNode[v].edgeNode.size() == 2 ){
                leafCount--;
            }
        }

        if (allNode[1].edgeNode.size() == 1){
            leafCount--;
        }

        int leaf1 = 1, leaf2;
        while (leafCount-- > 0){
            leaf2 = in.nextInt();
            find = false;
            process(allNode[ leaf1 ], leaf2);
            if (fail) {
                out.print(-1);
                out.close();
                return;
            }
            pathPointer += temp;
            leaf1 = leaf2;
            for (int i = 1; i <= n; i++) {
                allNode[i].isVisited = false;
            }
        }
        find = false;
        process2(allNode[ leaf1 ], 1);
        if (fail) {
            out.print(-1);
            out.close();
            return;
        }



        for (int i = 0; i < allPath.length; i++) {
            out.print(allPath[i]+" ");
        }
        out.close();
    }

    private static void process(node n, int leaf2){
        n.isVisited = true;
        if (n.index == leaf2){
            find = true;
            temp = height;
            if (pathPointer + height > allPath.length){
                fail = true;
                return;
            }
            allPath[pathPointer + height - 1] = n.index;
        }

        for (int i = 0; i < n.edgeNode.size(); i++) {
            if ( ! n.edgeNode.get(i).isVisited ){
                height++;
                process(n.edgeNode.get(i), leaf2);
                height--;
                if (fail){
                    return;
                }
                if (find){
                    if (height!=0) {
                        allPath[pathPointer + height - 1] = n.index;
                    }
                    return;
                }
            }
        }
    }

    private static void process2(node n, int leaf2){
        n.isVisited = true;
        if (n.index == leaf2){
            find = true;
            temp = height;
            if (pathPointer + height > allPath.length){
                fail = true;
                return;
            }
            allPath[pathPointer + height - 1] = n.index;
        }

        for (int i = 0; i < n.edgeNode.size(); i++) {
            if ( ! n.edgeNode.get(i).isVisited ){
                height++;
                if (n.index != 1){
                    process2(n.edgeNode.get(i), leaf2);
                }
                height--;
                if (fail){
                    return;
                }
                if (find){
                    if (height!=0) {
                        allPath[pathPointer + height - 1] = n.index;
                    }
                    return;
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