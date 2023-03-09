public class test {
    public static void main(String[] args) {
//        int a = 1;
//        for (int i = 0; i < 32; i++) {
//            System.out.println( a *= 2 );
//        }

        int[] power = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768,65536,131072,262144,524288,1048576,2097152,4194304,8388608,16777216,33554432,67108864,134217728,268435456,536870912,1073741824};

        for (int i = 0; i < power.length-1; i++) {
            double a = power[i+1];
            double b = power[i];
            System.out.println(a/b);
        }
    }
}