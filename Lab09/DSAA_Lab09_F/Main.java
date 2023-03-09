import java.io.*;
import java.util.*;

class node{
    int index;
    ArrayList<Integer> child = new ArrayList<>();

    public node(int i){
        index = i;
    }
}

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        while (T-- > 0){
            int n = in.nextInt();
            int m = in.nextInt();
            int[] a = new int[n+1];
            int[] b = new int[n+1];
            long[] f = new long[n+1];
            int[] ruDu = new int[n+1];
            node[] allNode = new node[n+1];
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextInt();
                b[i] = in.nextInt();
                allNode[i] = new node(i);
                f[i] = 0;
                ruDu[i] = 0;
            }
            for (int i = 1; i <= m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                allNode[u].child.add(v);
                ruDu[v]++;
            }

            int pointerL = 0, pointerR = 0;
            int[] tuoPu = new int[n+10];
            for (int i = 1; i <= n; i++) {
                if (ruDu[i] == 0){
                    tuoPu[pointerR] = i;
                    pointerR++;
                }
            }

            while (pointerL < n){
                if ( pointerL == pointerR ) {
                    out.println ("This graph is not a DAG.");
                    break;
                }

                ArrayList<Integer> child = allNode[tuoPu[pointerL]].child;
                for (int i = 0; i < child.size(); i++) {
                    int thisChild = child.get(i);
                    f[thisChild] += (f[tuoPu[pointerL]] + a[tuoPu[pointerL]]) % (1e9 + 7);
                    if (--ruDu[thisChild] == 0) {
                        tuoPu[pointerR] = thisChild;
                        pointerR++;
                    }
                }
                pointerL++;
            }

            ArrayList<Integer> cycle = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if ( ! contain(tuoPu, i) && ! allNode[i].child.isEmpty()) {
                    cycle.add(i);
                    break;
                }
            }
            boolean findCycle = false;
            int cycleStartPointer = 0;
            int cycleEndPointer = 0;
            while ( ! cycle.isEmpty()) {
                node thisNode = allNode[cycle.get(cycleEndPointer)];
                for (int chi : thisNode.child) {
                    if ( ! contain(tuoPu, chi) && ! allNode[chi].child.isEmpty()) {
                        if (cycle.contains(chi)) {
                            findCycle = true;
                            cycleStartPointer = cycle.indexOf(chi);
                        } else {
                            cycle.add(chi);
                            cycleEndPointer++;
                        }
                        break;
                    }
                }
                if (findCycle) {
                    out.println("The cycle is : ");
                    for (int i = cycleStartPointer; i <= cycleEndPointer; i++) {
                        out.print(cycle.get(i)+" ");
                    }
                    out.close();
                    return;
                }
            }

            long sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += (long)(  (f[i] % (1e9+7)) * b[i] ) % (1e9+7);
            }
            out.println((long)(sum % (1e9+7)));
        }
        out.close();
    }

    public static boolean contain(int[] tuoPu, int i) {
        for (int t: tuoPu) {
            if (t == i) {
                return true;
            }
        }
        return false;
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