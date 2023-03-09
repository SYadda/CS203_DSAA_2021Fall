import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int h = sc.nextInt();
        int[] a = new int[n-1];     long sumA = 0;     int maxA = 1;     int minA = h;
        int[] b = new int[n-1];     long sumB = 0;     int maxB = 1;     int minB = h;

        for (int i = 0; i < n-1; i++) {
            a[i] = sc.nextInt();
            sumA += a[i];
            maxA = Math.max(a[i], maxA);
            minA = Math.min(a[i], minA);
        }
        for (int i = 0; i < n-1; i++) {
            b[i] = sc.nextInt();
            sumB += b[i];
            maxB = Math.max(b[i], maxB);
            minB = Math.min(b[i], minB);
        }

        long max_sumA = sumA - minA;     long middle_sumA = sumA - maxA - minA;      long min_sumA = sumA - maxA;
        long max_sumB = sumB - minB;     long middle_sumB = sumB - maxB - minB;      long min_sumB = sumB - maxB;

        if ( n == 2 ) {
            System.out.println("IMPOSSIBLE");
        } else {
            if ( middle_sumA > middle_sumB ){
                if ( min_sumA > max_sumB){
                    System.out.println( 1-h );
                } else if ( min_sumA == max_sumB){
                    int cha1 = 1 - (maxB - 1);
                    int cha2 = (minA + 1) - h;
                    int cha3 = Math.min( cha1, cha2);
                    System.out.println( cha3 );
                } else {
                    long cha_Sum = middle_sumA - middle_sumB;
                    long outIn = 1 - cha_Sum;
                    long outUp = 0;
                    long outDown = 0;

                    if ( max_sumA > max_sumB ){
                        outDown = 1 - minB;
                    }
                    if ( min_sumA > min_sumB ){
                        outUp = maxA - h;
                    }

                    outIn = Math.min( outIn, outUp );
                    outIn = Math.min( outIn, outDown );
                    System.out.println(outIn);
                }

            } else if ( middle_sumA == middle_sumB ) {
                System.out.println("1");
            } else {
                if ( maxA <= minB ) {
                    System.out.println("IMPOSSIBLE");
                } else {
                    if ( n == 3 ) {
                        System.out.println("1");
                    } else {
                        if ( max_sumA <= min_sumB ){
                            System.out.println("IMPOSSIBLE");
                        } else {
                            long cha_Sum = max_sumA - min_sumB;
                            long cha = maxA - minB;
                            System.out.println( cha - cha_Sum + 1 );
                        }
                    }
                }
            }
        }
    }
}