import java.io.*;
import java.util.*;

class node{
    int index;
    int waitTime;
    node next1;
    node last1;
    node next2;
    node last2;
    public node(int i){
        index = i;
    }
}

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        while (in.hasNext()) {
            int n = in.nextInt();
            int q = in.nextInt();
            int[][] all = new int[n+1][200];
            for (int i = 0; i < n+1; i++) {
                for (int j = 0; j < 200; j++) {
                    all[i][j] = -1;
                }
            }
            int[] pointerL = new int[n+1];
            int[] pointerR = new int[n+1];
            for (int i = 1; i <= n; i++) {
                pointerL[i] = 100;
                pointerR[i] = 100;
            }

            int cz;
            for (int i = 0; i < q; i++) {
                cz = in.nextInt();
                if (cz == 1) {
                    int u = in.nextInt();
                    int w = in.nextInt();
                    int val = in.nextInt();
                    if (pointerL[u] == pointerR[u] && all[u][ pointerL[u] ] == -1){
                        all[u][ pointerL[u] ] = val;
                    } else if (w==0){
                        pointerL[u]--;
                        all[u][ pointerL[u] ] = val;
                    } else {
                        pointerR[u]++;
                        all[u][ pointerR[u] ] = val;
                    }
                } else if (cz == 2) {
                    int u = in.nextInt();
                    int w = in.nextInt();
                    if (pointerL[u] == pointerR[u]){
                        if ( all[u][ pointerL[u] ] == -1 ){
                            out.println("-1");
                        } else {
                            out.println(  all[u][ pointerL[u] ]  );
                            all[u][ pointerL[u] ] = -1;
                        }
                    } else if (w==0){
                        out.println(  all[u][ pointerL[u] ]  );
                        all[u][ pointerL[u] ] = -1;
                        pointerL[u]++;
                    } else {
                        out.println(  all[u][ pointerR[u] ]  );
                        all[u][ pointerR[u] ] = -1;
                        pointerR[u]--;
                    }
                } else {
                    int u1 = in.nextInt();
                    int v1 = in.nextInt();
                    int w1 = in.nextInt();

                    if (!  (pointerL[v1] == pointerR[v1] && all[v1][ pointerL[v1] ] == -1)  ){
                        if (pointerL[u1] == pointerR[u1] && all[u1][ pointerL[u1] ] == -1)  {
                            pointerL[u1]++;
                        }

                        int repeat = pointerR[v1] - pointerL[v1] + 1;
                        if (w1==0){
                            while (repeat-- > 0){
                                pointerR[u1]++;
                                all[u1][ pointerR[u1] ] = all[v1][ pointerR[v1] - repeat ];
                                all[v1][ pointerR[v1] - repeat ] = -1;
                            }
                        } else {
                            while (repeat-- > 0){
                                pointerR[u1]++;
                                all[u1][ pointerR[u1] ] = all[v1][ pointerL[v1] + repeat ];
                                all[v1][ pointerL[v1] + repeat ] = -1;
                            }
                        }
                        pointerL[v1] = 100;
                        pointerR[v1] = 100;
                    }
                }
            }

        }
        out.close();
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