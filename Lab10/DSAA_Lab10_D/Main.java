import java.io.*;
import java.util.*;

class node{
    int cost;
    ArrayList<Integer> edgeNode = new ArrayList<>();
    ArrayList<edge> edgeOfThis = new ArrayList<>();
    boolean oneConnect = false;
    boolean twoConnect = false;
    public node(int i){ cost = i; }
}

class edge{
    long cost;
    node n1, n2;
    public edge (long c, node nn1, node nn2){
        cost = c;
        n1 = nn1;
        n2 = nn2;
    }
}

public class Main {
    public static edge temp, minEdge = null;
    public static node[] allNode;
    public static boolean[] isVisited;
    public static edge[] allEdgeHeap = new edge[5000100];
    public static int edgeHeapPointer, minEdgeNode1 = -1, minEdgeNode2 = -1;
    public static long minEdgeCost = 9223372036854775807L;
    public static void main(String[] args){
        QReader in = new QReader();
        int x = in.nextInt(); // n in the question
        int n = 3*x*x + 3*x + 1;  // number of node
        int m = 9*x*x + 3*x;     // number of edge
        int rowSize = 2*x + 1;   // from row[0] to row[2x]
        int firstNodeCount = 0;
        int[] firstNode = new int[rowSize + 1];  // first node of each row;  2x+2
        for (int i = 0; i < x; i++) {
            firstNode[i] = firstNodeCount;
            firstNodeCount += (x + i + 1);
        }
        for (int i = x; i < rowSize; i++) {
            firstNode[i] = firstNodeCount;
            firstNodeCount += (3*x - i + 1);
        }
        firstNode[rowSize] = firstNodeCount;

        allNode = new node[n+1];
        isVisited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            allNode[i] = new node(in.nextInt());
        }

        // connect -
        for (int i = 0; i < rowSize; i++) {   // loop 2x+1
            for (int temp = firstNode[i]; temp < firstNode[i+1] - 1; temp++) {
                addEdge(temp+1, temp+2, (long) allNode[temp + 1].cost * allNode[temp + 2].cost);  // hexagon build: 0-2x, allNode: 1-2x+1
        }
        }

        // connect /
        for (int i = 0; i < x; i++) {   // loop x
            int temp2 = firstNode[i+1];
            for (int temp = firstNode[i]; temp < firstNode[i+1]; temp++) {
                addEdge(temp+1, temp2+1, (long) allNode[temp + 1].cost * allNode[temp2 + 1].cost);
                temp2++;
            }
        }

        for (int i = x; i < 2*x; i++) {   // loop x
            int temp2 = firstNode[i+1];
            for (int temp = firstNode[i] + 1; temp < firstNode[i+1]; temp++) {
                addEdge(temp+1, temp2+1, (long) allNode[temp + 1].cost * allNode[temp2 + 1].cost);
                temp2++;
            }
        }

        // connect \
        for (int i = 0; i < x; i++) {   // loop x
            int temp2 = firstNode[i+1] + 1;
            for (int temp = firstNode[i]; temp < firstNode[i+1]; temp++) {
                addEdge(temp+1, temp2+1, (long) allNode[temp + 1].cost * allNode[temp2 + 1].cost);
                temp2++;
            }
        }

        for (int i = x; i < 2*x; i++) {   // loop x
            int temp2 = firstNode[i+1];
            for (int temp = firstNode[i]; temp < firstNode[i+1] - 1; temp++) {
                addEdge(temp+1, temp2+1, (long) allNode[temp + 1].cost * allNode[temp2 + 1].cost);
                temp2++;
            }
        }

        allNode[minEdgeNode1].oneConnect = true;
        allNode[minEdgeNode2].oneConnect = true;
        long minCostCount = minEdgeCost;
        int visitNodeCount = 2;
        edgeHeapPointer = 1;

        int size1 = allNode[minEdgeNode1].edgeOfThis.size();
        for (int i = 0; i < size1; i++) {
            temp = allNode[minEdgeNode1].edgeOfThis.get(i);
            if ( ! temp.equals(minEdge) ) {
                HeapIn(temp);
            }
        }

        int size2 = allNode[minEdgeNode2].edgeOfThis.size();
        for (int i = 0; i < size2; i++) {
            temp = allNode[minEdgeNode2].edgeOfThis.get(i);
            if ( ! temp.equals(minEdge) ) {
                HeapIn(temp);
            }
        }

        while (visitNodeCount < n){
            edge nowMinEdge = HeapOut();
            while ( nowMinEdge.n1.oneConnect && nowMinEdge.n2.oneConnect ){
                nowMinEdge = HeapOut();
            }

            visitNodeCount++;
            minCostCount += nowMinEdge.cost;

            if (nowMinEdge.n1.oneConnect){
                nowMinEdge.n1.twoConnect = true;
                nowMinEdge.n2.oneConnect = true;
                int size = nowMinEdge.n2.edgeOfThis.size();
                for (int i = 0; i < size; i++) {
                    temp = nowMinEdge.n2.edgeOfThis.get(i);
                    if ( ! temp.equals(nowMinEdge) ) {
                        HeapIn(temp);
                    }
                }
            } else {  // nowMinEdge.n2.oneConnect
                nowMinEdge.n2.twoConnect = true;
                nowMinEdge.n1.oneConnect = true;
                int size = nowMinEdge.n1.edgeOfThis.size();
                for (int i = 0; i < size; i++) {
                    temp = nowMinEdge.n1.edgeOfThis.get(i);
                    if ( ! temp.equals(nowMinEdge) ) {
                        HeapIn(temp);
                    }
                }
            }
        }

        System.out.println(minCostCount);
    }

    public static void addEdge(int u, int v, long w) {
        edge uToV = new edge(w, allNode[u], allNode[v]);
        allNode[u].edgeNode.add(v);
        allNode[u].edgeOfThis.add(uToV);
        allNode[v].edgeNode.add(u);
        allNode[v].edgeOfThis.add(uToV);

        if (w < minEdgeCost){
            minEdgeCost = w;
            minEdge = uToV;
            minEdgeNode1 = u;
            minEdgeNode2 = v;
        }
    }

    public static void HeapIn(edge x) {
        int tempPointer = edgeHeapPointer++;
        allEdgeHeap[tempPointer] = x;
        while ( tempPointer != 1 && allEdgeHeap[tempPointer].cost < allEdgeHeap[tempPointer / 2].cost ){
            temp = allEdgeHeap[tempPointer];
            allEdgeHeap[tempPointer] = allEdgeHeap[tempPointer / 2];
            allEdgeHeap[tempPointer / 2] = temp;
            tempPointer /= 2;
        }
    }

    public static edge HeapOut() {
        edge out = allEdgeHeap[1];
        int tPer = 1;  // tempPointer
        allEdgeHeap[1] = allEdgeHeap[--edgeHeapPointer];
        if (edgeHeapPointer % 2 == 0){
            while (tPer * 2 + 1 < edgeHeapPointer){
                if (allEdgeHeap[tPer].cost > allEdgeHeap[tPer * 2].cost || allEdgeHeap[tPer].cost > allEdgeHeap[tPer * 2 + 1].cost){
                    if (allEdgeHeap[tPer * 2].cost > allEdgeHeap[tPer * 2 + 1].cost) {
                        temp = allEdgeHeap[tPer];
                        allEdgeHeap[tPer] = allEdgeHeap[tPer * 2 + 1];
                        allEdgeHeap[tPer * 2 + 1] = temp;
                        tPer = tPer * 2 + 1;
                    } else {
                        temp = allEdgeHeap[tPer];
                        allEdgeHeap[tPer] = allEdgeHeap[tPer * 2];
                        allEdgeHeap[tPer * 2] = temp;
                        tPer *= 2;
                    }
                } else {
                    break;
                }
            }
        } else {
            while (tPer * 2 < edgeHeapPointer){
                if (tPer * 2 + 1 == edgeHeapPointer){
                    if (allEdgeHeap[tPer].cost > allEdgeHeap[tPer * 2].cost) {
                        temp = allEdgeHeap[tPer];
                        allEdgeHeap[tPer] = allEdgeHeap[tPer * 2];
                        allEdgeHeap[tPer * 2] = temp;
                    }
                    break; // tPer *= 2;
                } else {
                    if (allEdgeHeap[tPer].cost > allEdgeHeap[tPer * 2].cost || allEdgeHeap[tPer].cost > allEdgeHeap[tPer * 2 + 1].cost) {
                        if (allEdgeHeap[tPer * 2].cost > allEdgeHeap[tPer * 2 + 1].cost) {
                            temp = allEdgeHeap[tPer];
                            allEdgeHeap[tPer] = allEdgeHeap[tPer * 2 + 1];
                            allEdgeHeap[tPer * 2 + 1] = temp;
                            tPer = tPer * 2 + 1;
                        } else {
                            temp = allEdgeHeap[tPer];
                            allEdgeHeap[tPer] = allEdgeHeap[tPer * 2];
                            allEdgeHeap[tPer * 2] = temp;
                            tPer *= 2;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return out;
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