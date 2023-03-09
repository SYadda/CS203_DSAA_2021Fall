import java.io.*;
import java.util.*;

class node{
    final int index;
    boolean isVisited = false;
    ArrayList<node> edgeNode = new ArrayList<>();
    node(int i){
        index = i;
    }
    node friNext1;
    node friNext2;
}

public class Main {
    public static node head, tail, curr;
    public static int height = 0, abMax = 0, bcMax = 0, bMaxFriend = 0, k;
    public static int[] allFriends;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();  // the number of test cases
        for (int z = 0; z < T; z++) {
            int n = in.nextInt();  // the number of cities (nodes)
            k = in.nextInt();  // the number of friends

            node[] allNode = new node[n+1];
            for (int i = 1; i <= n; i++) {
                allNode[i] = new node(i);
            }

            for (int i = 1; i < n; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                allNode[u].edgeNode.add(allNode[v]);
                allNode[v].edgeNode.add(allNode[u]);
            }


            head = new node(-10);
            tail = new node(-20);
            curr = head;
            allFriends = new int[k];
            for (int i = 0; i < k; i++) {
                allFriends[i] = in.nextInt();
                curr.friNext1 = allNode[  allFriends[i]  ];
                curr.friNext2 = allNode[  allFriends[i]  ];
                curr = curr.friNext1;
            }
            curr.friNext1 = tail;
            curr.friNext2 = tail;
            curr = head;

            if (k==1){
                out.println(0);
                continue;
            }

            height = 0;
            process(  allNode[allFriends[0]]  );

            for (int i = 1; i <= n; i++) {
                allNode[i].isVisited = false;
            }

            bcMax = abMax;
            height = 0;
            process2(  allNode[bMaxFriend]  );



            if (bcMax % 2 == 1){
                out.println(bcMax / 2 + 1);
            } else {
                out.println(bcMax / 2);
            }
            height = 0; abMax = 0; bcMax = 0; bMaxFriend = 0;
        }
        out.close();
    }

    private static void process(node n){
        n.isVisited = true;
        curr = head;
        if (height > abMax){
            while (curr.friNext1 != tail) {
                if (n.index == curr.friNext1.index){
                    curr.friNext1 = curr.friNext1.friNext1;
                    abMax = height;
                    bMaxFriend = n.index;
                    break;
                } else {
                    curr = curr.friNext1;
                }
            }
        }

        for (int i = 0; i < n.edgeNode.size(); i++) {
            if ( ! n.edgeNode.get(i).isVisited ){
                height++;
                process(n.edgeNode.get(i));
                height--;
            }
        }
    }

    private static void process2(node n){
        n.isVisited = true;
        curr = head;
        if (height > bcMax){
            while (curr.friNext2 != tail) {
                if (n.index == curr.friNext2.index){
                    curr.friNext2 = curr.friNext2.friNext2;
                    bcMax = height;
                    break;
                } else {
                    curr = curr.friNext2;
                }
            }
        }


        for (int i = 0; i < n.edgeNode.size(); i++) {
            if ( ! n.edgeNode.get(i).isVisited ){
                height++;
                process2(n.edgeNode.get(i));
                height--;
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