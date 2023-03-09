import java.io.*;
import java.util.*;

class node{
    int index;
    node leftSon;
    node rightSon;
    node father;
    public node(int i){
        index = i;
    }
}

public class Main {
    public static int[] inOrder, postOrder;
    public static node[] allNode;
    public static QWriter out = new QWriter();
    public static void main(String[] args) {
        QReader in = new QReader();
        int Q = in.nextInt();
        while (Q-- > 0){
            int N = in.nextInt();
            inOrder = new int[N+1];
            postOrder = new int[N+1];
            allNode = new node[N+1];
            for (int i = 1; i <= N; i++) {
                inOrder[i] = in.nextInt();
            }
            for (int i = 1; i <= N; i++) {
                postOrder[i] = in.nextInt();
            }
            for (int i = 1; i <= N; i++) {
                allNode[i] = new node(i);
            }

            node root = buildTree(1,N,1,N);
            preOrderPrint(root);
            out.println("");
        }
        out.close();
    }

    public static node buildTree(int inLeft, int inRight, int postLeft, int postRight) {
        if (postRight - postLeft < 2) {  // 1 or 2 node
            if (postRight != postLeft) {  // 2 node
                if (postOrder[postRight] == inOrder[inRight]) {  // postLeft,root
                    allNode[postOrder[postRight]].leftSon = allNode[postOrder[postLeft]];
                } else { // postRight,root
                    allNode[postOrder[postRight]].rightSon = allNode[postOrder[postLeft]];
                }
                allNode[postOrder[postLeft]].father = allNode[postOrder[postRight]];
            }
            return allNode[postOrder[postRight]];
        } else {
            node thisRoot = allNode[postOrder[postRight]];
            int thisRootIndex = postOrder[postRight];
            int thisRootPlace = inLeft; // place in inOrder
            for (int i = inLeft; i <= inRight; i++) {
                if (inOrder[i] == thisRootIndex) {
                    thisRootPlace = i;
                    break;
                }
            }
            if (thisRootPlace == inLeft) {
                int RightSonLength = inRight - thisRootPlace;
                node thisRightSon = buildTree(thisRootPlace+1, inRight,postRight-RightSonLength,postRight-1);
                thisRoot.rightSon = thisRightSon;
                thisRightSon.father = thisRoot;

            } else if (thisRootPlace == inRight) {
                int LeftSonLength = thisRootPlace - inLeft;
                node thisLeftSon = buildTree(inLeft, thisRootPlace-1, postLeft,postLeft+LeftSonLength-1);
                thisRoot.leftSon = thisLeftSon;
                thisLeftSon.father = thisRoot;
            } else {
                int LeftSonLength = thisRootPlace - inLeft;
                node thisLeftSon = buildTree(inLeft, thisRootPlace-1, postLeft,postLeft+LeftSonLength-1);
                int RightSonLength = inRight - thisRootPlace;
                node thisRightSon = buildTree(thisRootPlace+1, inRight,postRight-RightSonLength,postRight-1);
                thisRoot.leftSon = thisLeftSon;
                thisRoot.rightSon = thisRightSon;
                thisLeftSon.father = thisRoot;
                thisRightSon.father = thisRoot;
            }
            return thisRoot;
        }
    }

    public static void preOrderPrint(node n) {
        out.print(n.index+" ");
        if (n.leftSon != null) {
            preOrderPrint(n.leftSon);
        }
        if (n.rightSon != null) {
            preOrderPrint(n.rightSon);
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