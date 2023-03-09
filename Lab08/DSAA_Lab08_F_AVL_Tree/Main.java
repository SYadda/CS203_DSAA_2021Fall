import java.io.*;
import java.util.*;

class node{
    int value;
    node father;
    node leftSon;
    node rightSon;
    boolean isFather_s_LeftSon;
    int height = 1; // the height of this tree (root is this node)
    public node(int v){
        value = v;
    }
}

public class Main {
    public static node curr, root, newNode, temp, nowClosest;
    public static boolean GorB = false; // true: current tree is girls
    public static int nodeCount = 0;  // the number of nodes in current tree
    public static long totalMoney = 0;
    public static int thisMoney = 2000000000;
    public static void main(String[] args) {
        QReader in = new QReader();
        int caoZuo = in.nextInt(); // a girl or a bunny enter is a caoZuo
        while (caoZuo-- > 0){
            int enterGB = in.nextInt();
            if (nodeCount == 0){ // tree is empty, no girl or bunny is waiting
                GorB = (enterGB == 1);  // if a girl enter, then enterGB == 1
                root = new node(in.nextInt());
                nodeCount++;
            } else {
                if (GorB && enterGB == 1){ // current tree is girl && a girl enter
                    avlAdd(in.nextInt());
                } else if ( ! GorB && enterGB == 0){ // current tree is bunny && a bunny enter
                    avlAdd(in.nextInt());
                } else {
                    match(in.nextInt());  // match a girl and a bunny
                }
            }
        }
        System.out.println(totalMoney);
    }

    public static void match(int i){
        thisMoney = 2000000000;
        nowClosest = null;
        nodeCount--;
        curr = root;
        while (true){
            if (i == curr.value){
                avlDelete(curr);
                break;
            } else {
                if (i > curr.value){
                    if (i - curr.value <= thisMoney){
                        thisMoney = i - curr.value;
                        nowClosest = curr;
                    }
                    if (curr.rightSon == null){
                        avlDelete(nowClosest);
                        totalMoney += thisMoney;
                        break;
                    } else {
                        curr = curr.rightSon;
                    }
                } else { // i < curr.value
                    if (curr.value - i < thisMoney){
                        thisMoney = curr.value - i;
                        nowClosest = curr;
                    }
                    if (curr.leftSon == null){
                        avlDelete(nowClosest);
                        totalMoney += thisMoney;
                        break;
                    } else {
                        curr = curr.leftSon;
                    }
                }
            }
        }
    }

    public static void avlDelete(node n){  // delete n,   delete root and middle node >> delete leaf node
        if (n.leftSon == null){
            if (n.rightSon == null){ // n is a leaf

                if ( n.father != null ) {
                    if (n.isFather_s_LeftSon){
                        n.father.leftSon = null;
                        if (n.father.rightSon == null) {
                            n.father.height--;
                            balanceFromDelete(n.father);
                        } else if (n.father.rightSon.height == 2) {

                            curr = n.father.rightSon;
                            // RR & RL & R same
                            if (curr.leftSon == null) {  // RR
                                curr.father.height -= 2;
                                leftSpin(curr);
                                balanceFromDelete(curr);
                            } else if (curr.rightSon == null) {  // RL
                                curr.height--;
                                curr.father.height -= 2;
                                curr.leftSon.height++;

                                node nn = curr.leftSon;
                                rightSpin(nn);
                                leftSpin(nn);
                                balanceFromDelete(nn);
                            } else if (curr.leftSon.height < curr.rightSon.height) {  // RR
                                curr.father.height -= 2;
                                leftSpin(curr);
                                balanceFromDelete(curr);
                            } else if (curr.leftSon.height > curr.rightSon.height) {   // RL
                                curr.height--;
                                curr.father.height -= 2;
                                curr.leftSon.height++;

                                node nn = curr.leftSon;
                                rightSpin(nn);
                                leftSpin(nn);
                                balanceFromDelete(nn);
                            } else { // R same
                                curr.height++;
                                curr.father.height--;
                                leftSpin(curr);
                            }
                            // RR & RL & R same
                        }
                    } else {
                        n.father.rightSon = null;
                        if (n.father.leftSon == null) {
                            n.father.height--;
                            balanceFromDelete(n.father);
                        } else if (n.father.leftSon.height == 2) {

                            curr = n.father.leftSon;
                            // LL & LR & L same
                            if (curr.rightSon == null) {  // LL
                                curr.father.height -= 2;
                                rightSpin(curr);
                                balanceFromDelete(curr);
                            } else if (curr.leftSon == null) {  // LR
                                curr.height--;
                                curr.father.height -= 2;
                                curr.rightSon.height++;

                                node nn = curr.rightSon;
                                leftSpin(nn);
                                rightSpin(nn);
                                balanceFromDelete(nn);
                            } else if (curr.leftSon.height > curr.rightSon.height) {  // LL
                                curr.father.height -= 2;
                                rightSpin(curr);
                                balanceFromDelete(curr);
                            } else if (curr.leftSon.height < curr.rightSon.height) {   // LR
                                curr.height--;
                                curr.father.height -= 2;
                                curr.rightSon.height++;

                                node nn = curr.rightSon;
                                leftSpin(nn);
                                rightSpin(nn);
                                balanceFromDelete(nn);
                            } else { // L same
                                curr.height++;
                                curr.father.height--;
                                rightSpin(curr);
                            }
                            // LL & LR & L same
                        }
                    }
                }
            } else {  // n only has rightSon
                node houJi = n.rightSon;
                while (houJi.leftSon != null){
                    houJi = houJi.leftSon;
                }
                n.value = houJi.value;
                avlDelete(houJi);
            }
        } else {  // n has leftSon
            node qianJi = n.leftSon;
            while (qianJi.rightSon != null){
                qianJi = qianJi.rightSon;
            }
            n.value = qianJi.value;
            avlDelete(qianJi);
        }
    }

    public static void avlAdd(int i){
        nodeCount++;
        newNode = new node(i);
        curr = root;
        while (true){
            if (newNode.value < curr.value){ // newNode go to curr's leftSon tree
                if (curr.leftSon == null){  // curr has no leftSon tree
                    newNode.father = curr;
                    curr.leftSon = newNode;
                    newNode.isFather_s_LeftSon = true;
                    // add is finished, do balance
                    if (curr.rightSon == null){
                        curr.height++;
                        balanceFromAdd(curr);  //  ↙
                    }
                    break;
                } else {  // curr has leftSon tree
                    curr = curr.leftSon;
                }
            } else {  // newNode go to curr's rightSon tree
                if (curr.rightSon == null){  // curr has no rightSon tree
                    newNode.father = curr;
                    curr.rightSon = newNode;
                    newNode.isFather_s_LeftSon = false;
                    // add is finished, do balance
                    if (curr.leftSon == null){
                        curr.height++;
                        balanceFromAdd(curr);  //  ↘
                    }
                    break;
                } else {  // curr has rightSon tree
                    curr = curr.rightSon;
                }
            }
        }
    }

    public static void balanceFromDelete(node curr) {
        if (curr == root) {
            return;
        }

        if ( curr.isFather_s_LeftSon ) {  // curr.father.rightSon must != null
            int heightDifference = curr.height - curr.father.rightSon.height;
            if ( heightDifference == -2) {

                curr = curr.father.rightSon;

                // RR & RL & R same
                if (curr.leftSon == null) {  // RR
                    curr.father.height -= 2;
                    leftSpin(curr);
                    balanceFromDelete(curr);
                } else if (curr.rightSon == null) {  // RL
                    curr.height--;
                    curr.father.height -= 2;
                    curr.leftSon.height++;

                    node nn = curr.leftSon;
                    rightSpin(nn);
                    leftSpin(nn);
                    balanceFromDelete(nn);
                } else if (curr.leftSon.height < curr.rightSon.height) {  // RR
                    curr.father.height -= 2;
                    leftSpin(curr);
                    balanceFromDelete(curr);
                } else if (curr.leftSon.height > curr.rightSon.height) {   // RL
                    curr.height--;
                    curr.father.height -= 2;
                    curr.leftSon.height++;

                    node nn = curr.leftSon;
                    rightSpin(nn);
                    leftSpin(nn);
                    balanceFromDelete(nn);
                } else { // R same
                    curr.height++;
                    curr.father.height--;
                    leftSpin(curr);
                }
                // RR & RL & R same

            } else if (heightDifference == 0) {
                curr.father.height--;
                balanceFromDelete(curr.father);
            } // else : heightDifference == -1

        } else { // curr is Father's RightSon,   curr.father.leftSon must != null
            int heightDifference = curr.height - curr.father.leftSon.height;
            if ( heightDifference == -2) {

                curr = curr.father.leftSon;

                // LL & LR & L same
                if (curr.rightSon == null) {  // LL
                    curr.father.height -= 2;
                    rightSpin(curr);
                    balanceFromDelete(curr);
                } else if (curr.leftSon == null) {  // LR
                    curr.height--;
                    curr.father.height -= 2;
                    curr.rightSon.height++;

                    node nn = curr.rightSon;
                    leftSpin(nn);
                    rightSpin(nn);
                    balanceFromDelete(nn);
                } else if (curr.leftSon.height > curr.rightSon.height) {  // LL
                    curr.father.height -= 2;
                    rightSpin(curr);
                    balanceFromDelete(curr);
                } else if (curr.leftSon.height < curr.rightSon.height) {   // LR
                    curr.height--;
                    curr.father.height -= 2;
                    curr.rightSon.height++;

                    node nn = curr.rightSon;
                    leftSpin(nn);
                    rightSpin(nn);
                    balanceFromDelete(nn);
                } else { // L same
                    curr.height++;
                    curr.father.height--;
                    rightSpin(curr);
                }
                // LL & LR & L same

            } else if (heightDifference == 0) {
                curr.father.height--;
                balanceFromDelete(curr.father);
            } // else : heightDifference == -1
        }
    }

    public static void balanceFromAdd(node curr) {
        if (curr == root) {
            return;
        }

        if ( curr.isFather_s_LeftSon ) {
            if (curr.father.rightSon == null) {

                // LL & LR
                curr.father.height--;

                if (curr.rightSon == null) {  // LL
                    rightSpin(curr);
                } else if (curr.leftSon == null) {  // LR
                    curr.height--;
                    node n = curr.rightSon;
                    n.height++;
                    leftSpin(n);
                    rightSpin(n);
                } else if (curr.leftSon.height > curr.rightSon.height) {  // LL
                    rightSpin(curr);
                } else {   // LR
                    curr.height--;
                    node n = curr.rightSon;
                    n.height++;
                    leftSpin(n);
                    rightSpin(n);
                }
                // LL & LR

            } else {
                int heightDifference = curr.height - curr.father.rightSon.height;
                if ( heightDifference == 2) {

                    // LL & LR
                    curr.father.height--;

                    if (curr.rightSon == null) {  // LL
                        rightSpin(curr);
                    } else if (curr.leftSon == null) {  // LR
                        curr.height--;
                        node n = curr.rightSon;
                        n.height++;
                        leftSpin(n);
                        rightSpin(n);
                    } else if (curr.leftSon.height > curr.rightSon.height) {  // LL
                        rightSpin(curr);
                    } else {   // LR
                        curr.height--;
                        node n = curr.rightSon;
                        n.height++;
                        leftSpin(n);
                        rightSpin(n);
                    }
                    // LL & LR

                } else if (heightDifference == 1) {
                    curr.father.height++;
                    balanceFromAdd(curr.father);
                } // else : heightDifference == 0
            }
        } else { // curr is Father's RightSon
            if (curr.father.leftSon == null) {

                // RR & RL
                curr.father.height--;

                if (curr.leftSon == null) {  // RR
                    leftSpin(curr);
                } else if (curr.rightSon == null) {  // RL
                    curr.height--;
                    node n = curr.leftSon;
                    n.height++;
                    rightSpin(n);
                    leftSpin(n);
                } else if (curr.leftSon.height < curr.rightSon.height) {  // RR
                    leftSpin(curr);
                } else {   // RL
                    curr.height--;
                    node n = curr.leftSon;
                    n.height++;
                    rightSpin(n);
                    leftSpin(n);
                }
                // RR & RL

            } else {
                int heightDifference = curr.height - curr.father.leftSon.height;
                if ( heightDifference == 2) {

                    // RR & RL
                    curr.father.height--;

                    if (curr.leftSon == null) {  // RR
                        leftSpin(curr);
                    } else if (curr.rightSon == null) {  // RL
                        curr.height--;
                        node n = curr.leftSon;
                        n.height++;
                        rightSpin(n);
                        leftSpin(n);
                    } else if (curr.leftSon.height < curr.rightSon.height) {  // RR
                        leftSpin(curr);
                    } else {   // RL
                        curr.height--;
                        node n = curr.leftSon;
                        n.height++;
                        rightSpin(n);
                        leftSpin(n);
                    }
                    // RR & RL

                } else if (heightDifference == 1) {
                    curr.father.height++;
                    balanceFromAdd(curr.father);
                } // else : heightDifference == 0
            }
        }
    }

    public static void leftSpin(node curr) {
        node Father = curr.father;
        if (root == Father) {
            root = curr;
        }

        if (Father.father != null) {
            node Top = Father.father;
            if (Father.isFather_s_LeftSon) {
                curr.father = Top;
                Top.leftSon = curr;
                curr.isFather_s_LeftSon = true;
            } else {
                curr.father = Top;
                Top.rightSon = curr;
            }
        } else {
            curr.father = null;
        }

        if (curr.leftSon != null) {
            node currLS = curr.leftSon;
            currLS.father = Father;
            Father.rightSon = currLS;
            currLS.isFather_s_LeftSon = false;
        } else {
            Father.rightSon = null;
        }

        Father.father = curr;
        curr.leftSon = Father;
        Father.isFather_s_LeftSon = true;
    }

    public static void rightSpin(node curr) {
        node Father = curr.father;
        if (root == Father) {
            root = curr;
        }

        if (Father.father != null) {
            node Top = Father.father;
            if (Father.isFather_s_LeftSon) {
                curr.father = Top;
                Top.leftSon = curr;
            } else {
                curr.father = Top;
                Top.rightSon = curr;
                curr.isFather_s_LeftSon = false;
            }
        } else {
            curr.father = null;
        }

        if (curr.rightSon != null) {
            node currRS = curr.rightSon;
            currRS.father = Father;
            Father.leftSon = currRS;
            currRS.isFather_s_LeftSon = true;
        } else {
            Father.leftSon = null;
        }


        Father.father = curr;
        curr.rightSon = Father;
        Father.isFather_s_LeftSon = false;
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
