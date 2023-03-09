import java.io.*;
import java.util.*;

class node {
    int value;
    ArrayList<Integer> son = new ArrayList<>();
    ArrayList<Double> sonLength = new ArrayList<>();
    public node(int i){
        value = i;
    }
}

public class Main {
    public static int currMinNode, n, n1;
    public static double currMinLength;
    public static node[] allNode;
    public static double[] allMinLength;
    public static boolean[] isVisited;
    public static void main(String[] args) {
        QReader in = new QReader();
        int row = in.nextInt();
        int column = in.nextInt();
        n = row * column;
        n1 = n;
        allNode = new node[n+1];
        allMinLength = new double[n+1];
        isVisited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            allNode[i] = new node(i);
            allMinLength[i] = Long.MAX_VALUE;
            isVisited[i] = false;
        }
        allMinLength[1] = 0;

        int[][] matrix = new int[row+1][column+1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        int matrixFirstValue = matrix[1][1];
        int[] firstNodeIndex = new int[row+1];
        firstNodeIndex[1] = 1;
        for (int i = 2; i <= row ; i++) {
            firstNodeIndex[i] = firstNodeIndex[i-1] + column;
        }

        int thisIndex, connectIndex;
        double thisLength;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                thisIndex = firstNodeIndex[i] + j - 1;
                thisLength = Math.pow(2,matrix[i][j]-matrixFirstValue);
                if (i != 1) { // connect with up
                    connectIndex = thisIndex - column;
                    allNode[thisIndex].son.add(connectIndex);
                    allNode[thisIndex].sonLength.add(thisLength);
                }
                if (j != 1) { // connect with left
                    connectIndex = thisIndex - 1;
                    allNode[thisIndex].son.add(connectIndex);
                    allNode[thisIndex].sonLength.add(thisLength);
                }
                if (i != row) { // connect with down
                    connectIndex = thisIndex + column;
                    allNode[thisIndex].son.add(connectIndex);
                    allNode[thisIndex].sonLength.add(thisLength);
                }
                if (j != column) { // connect with right
                    connectIndex = thisIndex + 1;
                    allNode[thisIndex].son.add(connectIndex);
                    allNode[thisIndex].sonLength.add(thisLength);
                }
            }
        }

        go(1);
    }

    public static void go(int x){
        isVisited[x] = true;
        int sonSize = allNode[x].son.size();
        for (int i = 0; i < sonSize; i++) {
            if (  allMinLength[ allNode[x].son.get(i) ] > allMinLength[x] + allNode[x].sonLength.get(i)  ){
                allMinLength[ allNode[x].son.get(i) ] = allMinLength[x] + allNode[x].sonLength.get(i);
            }
        }

        currMinLength = 9223372036854775807L; // fixme double
        for (int i = 1; i <= n; i++) {
            if ( ! isVisited[i] && currMinLength > allMinLength[i]){
                currMinLength = allMinLength[i];
                currMinNode = i;
            }
        }
        if (currMinNode == n){
            System.out.printf("%.2f",currMinLength);
        } else if (n1-- > 0){
            go(currMinNode);
        } else {
            System.out.println(-1);
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