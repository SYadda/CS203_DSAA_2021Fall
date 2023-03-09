import java.io.*;
import java.util.*;

public class Main {
    private static Plant temp; // exchangePlants
    public static int n, p, q;
    public static Plant[] allPlants, tempPlants;
    public static long countSum = 0;
    public static void main(String[] args) {
        QReader in = new QReader();
        n = in.nextInt();
        allPlants = new Plant[n];  tempPlants = new Plant[n];
        p = in.nextInt();
        q = in.nextInt();
        q = Math.min(q,n);
        for (int i = 0; i < n; i++) {
            allPlants[i] = new Plant(in.nextInt(), in.nextInt());
        }

        if (q != 0){

            paiXu1(0,n-1);

            for (int i = 0; i < n; i++) {
                allPlants[i].index = i;
            }

            int countQ = n - q - 1;
            boolean lackQ = false;
            Plant undetermined = new Plant(0,0);

            if (allPlants[n-q].hMinusS > 0) {
                lackQ = true;
                undetermined = allPlants[n-q];
                for (int i = 0; i < n; i++) {
                    if ( i > countQ ){   // from (n-q) to (n-1)
                        countSum += allPlants[i].height;  //q magic * 1
                    } else {
                        countSum += allPlants[i].strength;  //no magic
                    }
                }
            } else if (allPlants[n-1].hMinusS <= 0) {
                for (int i = 0; i < n; i++) {
                    countSum += allPlants[i].strength;  //no magic
                }
            } else {   // 若q不为0，则q必定富余
                int eFFZ = erFenFindZero(n-q, n-1) - 1;
                for (int i = 0; i < n; i++) {   //  <=????
                    if ( i > eFFZ ){   // from eFFZ to (n-1)
                        countSum += allPlants[i].height;  //q magic * 1
                    } else {
                        countSum += allPlants[i].strength;  //no magic
                    }
                }
            }

            if (p != 0){

                paiXu3(0,n-1);

                long[] allCountSum = new long[n];
                for (int i = 0; i < n; i++) {
                    allCountSum[i] = countSum;

                    if (lackQ && allPlants[i].index <= countQ)   {  // countQ = n - q - 1;
                        allCountSum[i] -= undetermined.hMinusS;
                    } else if (allPlants[i].hMinusS > 0){
                        allCountSum[i] -= allPlants[i].hMinusS;
                    }
                    allCountSum[i] += allPlants[i].hMultiply2P_MinusS;
                }

                for (int i = 0; i < n; i++) {
                    countSum = Math.max(countSum, allCountSum[i]);
                }
            }

        } else {
            for (int i = 0; i < n; i++) {
                countSum += allPlants[i].strength;
            }
        }

        System.out.println(countSum);
    }

    private static int erFenFindZero (int left, int right){
        int mid;  long midHMS;
        do{
            if (right - left == 1){
                if (allPlants[left].hMinusS >= 0){
                    return left;
                } else {
                    return right;
                }
            }

            mid = left + ( right - left ) / 2;
            midHMS = allPlants[mid].hMinusS;
            if ( midHMS == 0 ){
                return mid;
            } else if ( midHMS > 0 ) {
                right = mid;
            } else {
                left = mid;
            }
        } while (left < right);

        return right;
    }

    private static void exchangePlants (int m, int n){
        temp         = allPlants[m];
        allPlants[m] = allPlants[n];
        allPlants[n] = temp;
    }

    private static void paiXu1(int left, int right) {
        if (right - left == 0) {
            return;
        } else if (right - left == 1){
            if (allPlants[left].hMinusS > allPlants[right].hMinusS) {
                exchangePlants(left,right);
            }
        } else if (right - left == 2){
            if (allPlants[left+1].hMinusS > allPlants[right].hMinusS) {
                exchangePlants(left+1, right);
            }
            if (allPlants[left].hMinusS > allPlants[left+1].hMinusS) {
                exchangePlants(left,left+1);
            }
            if (allPlants[left+1].hMinusS > allPlants[right].hMinusS) {
                exchangePlants(left+1, right);
            }
        } else {
            int middle = (left + right) / 2;
            paiXu1(middle+1, right);
            paiXu1(left, middle);
            paiXu2(left, right);
        }
    }

    private static void paiXu2(int left, int right) {
        int middle = (left + right) / 2;
        int pointer1 = middle+1, pointer2 = left;
        int step = left;
        do {
            if (allPlants[pointer1].hMinusS < allPlants[pointer2].hMinusS) {
                tempPlants[step] = allPlants[pointer1];
                pointer1++;
            } else {
                tempPlants[step] = allPlants[pointer2];
                pointer2++;
            }
            step++;
        } while (pointer1 < right+1 && pointer2 < middle+1);

        while (pointer1 < right+1) {
            tempPlants[step] = allPlants[pointer1];
            pointer1++;
            step++;
        }

        while (pointer2 < middle+1) {
            tempPlants[step] = allPlants[pointer2];
            pointer2++;
            step++;
        }

        for (int i = left; i < step; i++) {
            allPlants[i] = tempPlants[i];
        }
    }

    private static void paiXu3(int left, int right) {
        if (right - left == 0) {
            return;
        } else if (right - left == 1){
            if (allPlants[left].hMultiply2P_MinusS > allPlants[right].hMultiply2P_MinusS) {
                exchangePlants(left,right);
            }
        } else if (right - left == 2){
            if (allPlants[left+1].hMultiply2P_MinusS > allPlants[right].hMultiply2P_MinusS) {
                exchangePlants(left+1, right);
            }
            if (allPlants[left].hMultiply2P_MinusS > allPlants[left+1].hMultiply2P_MinusS) {
                exchangePlants(left,left+1);
            }
            if (allPlants[left+1].hMultiply2P_MinusS > allPlants[right].hMultiply2P_MinusS) {
                exchangePlants(left+1, right);
            }
        } else {
            int middle = (left + right) / 2;
            paiXu3(middle+1, right);
            paiXu3(left, middle);
            paiXu4(left, right);
        }
    }

    private static void paiXu4(int left, int right) {
        int middle = (left + right) / 2;
        int pointer1 = middle+1, pointer2 = left;
        int step = left;
        do {
            if (allPlants[pointer1].hMultiply2P_MinusS < allPlants[pointer2].hMultiply2P_MinusS) {
                tempPlants[step] = allPlants[pointer1];
                pointer1++;
            } else {
                tempPlants[step] = allPlants[pointer2];
                pointer2++;
            }
            step++;
        } while (pointer1 < right+1 && pointer2 < middle+1);

        while (pointer1 < right+1) {
            tempPlants[step] = allPlants[pointer1];
            pointer1++;
            step++;
        }

        while (pointer2 < middle+1) {
            tempPlants[step] = allPlants[pointer2];
            pointer2++;
            step++;
        }

        for (int i = left; i < step; i++) {
            allPlants[i] = tempPlants[i];
        }
    }
}

class Plant {
    long height;
    long strength;
    int index; /////////
    long hMinusS;
    long hMultiply2P_MinusS;

    public Plant(long h, long s){
        height = h;
        strength = s;
        hMinusS = h - s;
        hMultiply2P_MinusS = h * power2(Main.p) - s;
    }

    public static int power2 (int p){
        int count = 1;
        for (int i = 0; i < p; i++) {
            count *= 2;
        }
        return count;
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