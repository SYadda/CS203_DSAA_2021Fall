import java.util.Scanner;

public class Main {
    static int N;
    static long M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            N = sc.nextInt();
            M = sc.nextLong();
            long max = 10000000000L;   long min = -10000000000L;   long mid;
            do {
                mid = min + (max - min) / 2;
                long m = yanZhengMatrix(mid);
                if ( m == M ){
                    min = mid;
                    max = mid;
                    break;
                } else if ( m < M ){
                    min = mid + 1;
                } else {
                    max = mid;
                }
            } while ( max - min > 1);

            if ( yanZhengMatrix(min) < M ){
                System.out.println(  yanZhengMatrix2(max)  );
            } else {
                System.out.println(  yanZhengMatrix2(min)  );
            }
        }
    }

    private static long yanZhengMatrix(double x){ // 确定整个矩阵中有多少个数小于等于x
        long count = 0;
        for (int j = 1; j < N+1; j++) {
            count += yanZhengLie(x,j);
        }
        return count;
    }

    private static int yanZhengLie(double x, int j){  // 确定第j列有多少个数小于等于x
        int left = 1;
        int right = N;
        int mid;
        do {
          mid = left + (right - left) / 2;
          if ( number(mid,j) > x ){
              right = mid;
          } else {
              left = mid;
          }
        } while ( right - left > 1 );

        if (left == 1 && number(1,j) > x ){
            return 0;
        } else if (left == N-1 && number(N,j) < x ){
            return N;
        } else {
            return left;
        }
    }

    static long yanZhengMatrix2(double x){ // 确定整个矩阵中，小于等于x的最大值（逐列遍历）
        long compare = -10000000000L;
        for (int j = 1; j < N+1; j++) {
            compare = Math.max(compare, yanZhengLie2(x,j) );
        }
        return compare;
    }

    private static long yanZhengLie2(double x, int j){  // 确定第j列中，小于等于x的最大值
        int left = 1;
        int right = N;
        int mid;
        do {
            mid = left + (right - left) / 2;
            if ( number(mid,j) > x ){
                right = mid;
            } else {
                left = mid;
            }
        } while ( right - left > 1 );

        if (left == 1 && number(1,j) > x ){
            return -10000000000L;
        } else if (left == N-1 && number(N,j) < x ){
            return number(N,j);
        } else {
            return number(left,j);
        }
    }

    static long number(int i, int j){   //求A[i][j]的值
        long i1 = i, j1 = j;
        return i1 * (i1 + 12345) + j1 * (j1 - 12345) + i1 * j1;
    }
}