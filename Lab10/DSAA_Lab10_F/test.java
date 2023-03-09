import java.util.Random;

public class test {
    public static void main(String[] args) {
        int n = 100;
        System.out.println(1);
        System.out.println(100+" "+20);
        Random r1 = new Random();
        while (n-- > 0){
            System.out.println((r1.nextInt(100)+1)+" "+(r1.nextInt(100)+1));
        }
        n = 20;
        int temp1 = r1.nextInt(100)+1;
        int temp2;
        while (n-- > 0){
            temp2 = r1.nextInt(100)+1;
            System.out.println(temp1+" "+temp2);
            temp1 = temp2;
        }
    }
}
