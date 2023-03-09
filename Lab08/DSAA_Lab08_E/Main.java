import java.io.*;
import java.util.*;

public class Main {
    public static int A, pointerS = 1, pointerL = 0;
    public static int temp;
    public static int[] smallTop;
    public static int[] largeTop;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        A= in.nextInt() - 1;
        smallTop = new int[A / 2 + 3];
        largeTop = new int[A / 2 + 3];

        //first

        smallTop[1] = in.nextInt();
        out.print(smallTop[1]+" ");

        // other

        boolean SorL = false; // true: next smallTop ++

        while (A-- > 0){
            int i = in.nextInt();
            if (i > smallTop[1]){
                pointerS++;
                smallTop[pointerS] = i;
                exchange2S(pointerS);
                if ( ! SorL){
                    int d = deleteS();
                    pointerL++;
                    largeTop[pointerL] = d;
                    exchange2L(pointerL);
                }
            } else {
                pointerL++;
                largeTop[pointerL] = i;
                exchange2L(pointerL);
                if (SorL){
                    int d = deleteL();
                    pointerS++;
                    smallTop[pointerS] = d;
                    exchange2S(pointerS);
                }
            }

            if (SorL){
                out.print(smallTop[1]+" ");
            } else {
                out.print(largeTop[1]+" ");
            }
            SorL = ! SorL;
        }
        out.close();
    }

    public static void exchangeS(int i) {  // go down
        int curr = i;
        while (2*curr <= pointerS){   //  smallTop[curr] has left son
            if (2*curr < pointerS){   // smallTop[curr] has right son
                if (smallTop[curr] > smallTop[2*curr] || smallTop[curr] > smallTop[2*curr + 1]) {  // smallTop[curr] > at least one of its sons
                    if ( smallTop[2*curr] < smallTop[2*curr + 1] ) {  // left son < right son
                        temp = smallTop[curr];
                        smallTop[curr] = smallTop[2*curr];
                        smallTop[2*curr] = temp;
                        curr *= 2;
                    } else { // left son >= right son
                        temp = smallTop[curr];
                        smallTop[curr] = smallTop[2*curr + 1];
                        smallTop[2*curr + 1] = temp;
                        curr = 2*curr + 1;
                    }
                } else {
                    break;
                }
            } else { // smallTop[curr] has not right son (only has left son)
                if (smallTop[curr] > smallTop[2*curr]){   // smallTop[curr] > its left son
                    temp = smallTop[curr];
                    smallTop[curr] = smallTop[2*curr];
                    smallTop[2*curr] = temp;
                    curr *= 2;
                } else {
                    break;
                }
            }
        }
    }

    public static void exchangeL(int i) {  // go down
        int curr = i;
        while (2*curr <= pointerL){   //  largeTop[curr] has left son
            if (2*curr < pointerL){   // largeTop[curr] has right son
                if (largeTop[curr] < largeTop[2*curr] || largeTop[curr] < largeTop[2*curr + 1]) {  // largeTop[curr] < at least one of its sons
                    if ( largeTop[2*curr] > largeTop[2*curr + 1] ) {  // left son > right son
                        temp = largeTop[curr];
                        largeTop[curr] = largeTop[2*curr];
                        largeTop[2*curr] = temp;
                        curr *= 2;
                    } else { // left son <= right son
                        temp = largeTop[curr];
                        largeTop[curr] = largeTop[2*curr + 1];
                        largeTop[2*curr + 1] = temp;
                        curr = 2*curr + 1;
                    }
                } else {
                    break;
                }
            } else { // largeTop[curr] has not right son (only has left son)
                if (largeTop[curr] < largeTop[2*curr]){   // largeTop[curr] < its left son
                    temp = largeTop[curr];
                    largeTop[curr] = largeTop[2*curr];
                    largeTop[2*curr] = temp;
                    curr *= 2;
                } else {
                    break;
                }
            }
        }
    }


    public static void exchange2S(int i) {  // go up
        int curr = i;

        while (curr / 2 != 0){   //  smallTop[curr] is not root (smallTop[curr] has parent)
            if (smallTop[curr] < smallTop[curr / 2]){   // smallTop[curr] < its parent
                temp = smallTop[curr];
                smallTop[curr] = smallTop[curr / 2];
                smallTop[curr / 2] = temp;
                curr /= 2;
            } else {
                break;
            }
        }
    }

    public static void exchange2L(int i) {  // go up
        int curr = i;

        while (curr / 2 != 0){   //  largeTop[curr] is not root (largeTop[curr] has parent)
            if (largeTop[curr] > largeTop[curr / 2]){   // largeTop[curr] > its parent
                temp = largeTop[curr];
                largeTop[curr] = largeTop[curr / 2];
                largeTop[curr / 2] = temp;
                curr /= 2;
            } else {
                break;
            }
        }
    }

    public static int deleteS(){
        int ret = smallTop[1];
        smallTop[1] = smallTop[pointerS];
        pointerS--;
        exchangeS(1);
        return ret;
    }

    public static int deleteL(){
        int ret = largeTop[1];
        largeTop[1] = largeTop[pointerL];
        pointerL--;
        exchangeL(1);
        return ret;
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