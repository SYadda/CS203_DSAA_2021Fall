import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, currSum, maxSum;
    public static int[][] all;
    public static boolean[][] canVisit;
    public static boolean[][] curAllAdd;
    public static boolean[][] specialFalse;
    public static ArrayList<Integer> currAllAddend = new ArrayList<>();
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        while (T-- > 0){
            N = in.nextInt();
            M = in.nextInt();
            all = new int[N+1][M+1];
            canVisit = new boolean[N+1][M+1];
            curAllAdd = new boolean[N+1][M+1];
            specialFalse = new boolean[N+1][M+1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    all[i][j] = in.nextInt();
                    canVisit[i][j] = true;
                }
            }
            // input OK

            currSum = 0;
            maxSum = 0;
            currAllAddend.clear();
            plus(1,1);

            System.out.println(maxSum);
        }
        out.close();
    }

    public static void plus(int n, int m){
        if (m == M && n == N) {
            if (canVisit[n][m]) {
                currSum += all[n][m];
                if (maxSum < currSum) {
                    maxSum = currSum;
                }
                currSum -= all[n][m];
            }
            if (maxSum < currSum) {
                maxSum = currSum;
            }
            // count Sum
            if (currAllAddend.isEmpty()){
                return;
            }
            // daoChe
            int lastM = currAllAddend.remove(currAllAddend.size() - 1);
            int lastN = currAllAddend.remove(currAllAddend.size() - 1);
            currSum -= all[lastN][lastM];
            canVisit[lastN][lastM] = false;
            specialFalse[lastN][lastM] = true;  //  canVisit[lastN][lastM] = false;
            curAllAdd[lastN][lastM] = false;



            for (int i = lastM+1; i <= M ; i++) {
                if (specialFalse[lastN][i]){
                    canVisit[lastN][i] = true;
                }
            }
            for (int i = lastN+1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (specialFalse[i][j]){
                        canVisit[i][j] = true;
                        specialFalse[i][j] = false;
                    }
                }
            }

            if (lastM < M) {   // exist canVisit[lastN][lastM+1]
                if (lastN == 1 ||   // exist canVisit[lastN-1]
                        (!curAllAdd[lastN - 1][lastM] && !curAllAdd[lastN - 1][lastM + 1] &&
                                (lastM + 1 == M || !curAllAdd[lastN - 1][lastM + 2])
                        )) {
                    canVisit[lastN][lastM + 1] = true;
                }
            }
            if (lastN < N) {   // exist canVisit[lastN+1][lastM]
                if (lastM == 1 ||   // exist canVisit[lastN][lastM-1]
                        ! curAllAdd[lastN][lastM-1]) {
                    canVisit[lastN+1][lastM] = true;
                }
            }
            if (lastN < N && lastM < M){   // exist canVisit[lastN+1][lastM+1]
                canVisit[lastN+1][lastM+1] = true;
            }
            if (lastN < N && lastM != 1){   // exist canVisit[lastN+1][lastM-1]
                if ( ! curAllAdd[lastN][lastM-1] &&
                        (lastM == 2 || ! curAllAdd[lastN][lastM-2])
                ) {
                    canVisit[lastN+1][lastM-1] = true;
                }
            }

            return;
        }

        if (canVisit[n][m]){
            currSum += all[n][m];
            curAllAdd[n][m] = true;
            currAllAddend.add(n);
            currAllAddend.add(m);

            if (m < M && canVisit[n][m+1]){
                canVisit[n][m+1] = false;
            }
            if (n < N && canVisit[n+1][m]) {
                canVisit[n+1][m] = false;
            }
            if (n < N && m < M && canVisit[n+1][m+1]){
                canVisit[n+1][m+1] = false;
            }
            if (n < N && m > 1 && canVisit[n+1][m-1]){
                canVisit[n+1][m-1] = false;
            }

            if (m < M) {
                plus(n, m+1);
            } else {   // else if (n < N)
                plus(n+1, 1);
            }
        }

        if (m < M) {
            plus(n, m+1);
        } else {   // else if (n < N)
            plus(n+1, 1);
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