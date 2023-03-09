import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt();
        int T = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        for (int i = 0; i < T; i++) {
            int aLeft = in.nextInt() - 1;
            int bLeft = aLeft;
            int aRight = in.nextInt() - 1;
            int bRight = aRight;
            int aMid, bMid;
            int aCheckLeft = 0, aCheckRight = 0, bCheckLeft = 0, bCheckRight = 0;
            boolean findMid = false;
            do {
                aMid = aLeft + (aRight - aLeft) / 2;
                bMid = bLeft + (bRight - bLeft) / 2;
                if (a[aMid] == b[bMid]){
                    System.out.println(a[aMid]);
                    findMid = true;
                    break;
                } else if (a[aMid] < b[bMid]){
                    aCheckLeft = aMid - aLeft;
                    aLeft = aMid;
                    bCheckRight = bRight - bMid;
                    bRight = bMid;
                } else {
                    aCheckRight = aRight - aMid;
                    aRight = aMid;
                    bCheckLeft = bMid - bLeft;
                    bLeft = bMid;
                }

                if ( aCheckLeft > bCheckRight ) {
                    bRight--;
                    bCheckRight = aCheckLeft;
                } else if ( aCheckLeft < bCheckRight ) {
                    aLeft++;
                    aCheckLeft = bCheckRight;
                } else if ( aCheckRight > bCheckLeft) {
                    bLeft++;
                    bCheckLeft = aCheckRight;
                } else if ( aCheckRight < bCheckLeft) {
                    aRight++;
                    aCheckRight = bCheckLeft;
                }


            } while ( aRight - aLeft > 1 || bRight -bLeft > 1 );

            if (!findMid) {
                if (aLeft == aRight){        //////////
                    System.out.println(Math.min(a[aLeft], b[bLeft]));
                } else {
                    int[] temp = { a[aLeft], b[bLeft], a[aRight], b[bRight] };
                    Arrays.sort(temp);
                    System.out.println(temp[1]);
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