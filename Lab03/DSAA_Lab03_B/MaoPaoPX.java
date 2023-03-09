public class MaoPaoPX {

    public static void main(String[] args) {
        int[] arr= new int[100001];//创建数组
        QReader in = new QReader();
        QWriter out = new QWriter();

        for (int i = 0; i < 100001; i++) {
            arr[i] = in.nextInt();
        }

        for(int i=0;i<arr.length-1;i++) {
            for(int j=0;j<arr.length-i-1;j++) {
                if(arr[j]>arr[j+1]) {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }

//        for (int i = 0; i < 100001; i++) {
//            out.print(arr[i]+" ");
//        }
//        out.println("");

        out.println( 2 * arr[50000] );

//       out.println( a[number/2] + a[number/2-1] );

        out.close();
    }
}