public class test2 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int number = 10002;
        int[] a = new int[number];
        for (int i = 0; i < number; i++) {
            a[i] = in.nextInt();
        }
        int z;
        for (int i = 0; i < number / 2; i++) {
            for (int j = 0; j < number - i - 1; j++) {
                if (a[j] > a[j+1]) {
                    z = a[j];
                    a[j] = a[j+1];
                    a[j+1] = z;
                }
            }
        }

        for (int i = 0; i < number; i++) {
            out.print(a[i]+" ");
        }
        out.println("");

        if (number % 2 == 1) {
            out.println( 2 * a[number/2] );
        } else {
            out.println( a[number/2] + a[number/2-1] );
        }

        out.close();
    }
}