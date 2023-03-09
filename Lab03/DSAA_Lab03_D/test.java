import java.util.Random;

public class test {
    public static int tempL, tempR;
    public static Random rand = new Random();
    public static int[] temp = new int[1000000000];

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        quickSort(a, 0, n-1);

        int nDivide2 = n / 2;

        for (int i = 0; i < nDivide2; i++) {
            out.print(a[i]+" ");
            out.print(a[i + nDivide2]+" ");
        }

        out.close();
    }

    public static void quickSort (int[] a, int left, int right){
        if (right - left < 1){
            return;
        } else if ( right - left == 1 && a[right] < a[left] ){
            int exchange = a[right];
            a[right] = a[left];
            a[left] = exchange;
            return;
        }

        int p = left + rand.nextInt( right - left );
        int pivot = a[p];
        tempL = left;
        tempR = right;

        for (int i = left; i <= right; i++) {
            if ( i != p ){
                if ( a[i] < pivot ){
                    temp[tempL++] = a[i];
                } else {
                    temp[tempR--] = a[i];
                }
            }
        }
        temp[tempL] = pivot;

        for (int i = left; i <= right; i++) {
            a[i] = temp[i];
        }

        p = tempL;

        quickSort(a, left,p-1);
        quickSort(a,p+1, right);
    }
}