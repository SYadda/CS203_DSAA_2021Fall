import java.util.Arrays;

public class test2 {
    public static int[] all;
    public static long count = 0;
    public static void main(String[] args) {
        QReader in = new QReader();
        all = new int[1000];
        int n = in.nextInt() / 2;
        for (int i = 0; i < n; i++) {
            in.nextInt();
            all[i] = in.nextInt();
        }

        Arrays.sort(all);
        for (int i = 0; i < n; i++) {
            System.out.print(all[i]+" ");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            Arrays.sort(all);
            in.nextInt();
            System.out.println(  search(in.nextInt(), i)  );
        }
        System.out.println(count);
    }

    public static int search(int target, int i) {
        int start = i;
        int end = 999;
        while (end - start > 1) {
            int mid = start + (end - start) / 2;
            if (all[mid] == target) {
                return 0;
            } else if (target < all[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        int a = Math.abs(all[end] - target);
        int b = Math.abs(target - all[start]);
        if (a<b){
            all[end] = 0;
            count += a;
            return a;
        } else {
            all[start] = 0;
            count += b;
            return b;
        }
    }
}