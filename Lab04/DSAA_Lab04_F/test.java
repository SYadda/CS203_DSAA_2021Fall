public class test {
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            s.append((char) ((int) (Math.random() * 26) + 97));
        }
        System.out.println(s);
        System.out.println(1000);
        for (int i = 0; i < 1000; i++) {
            int ran = (int) (Math.random() * 3) + 1;
            if (ran == 1){
                System.out.println(ran+" "+(char)((int) (Math.random() * 26) + 97)+" "+(int)(Math.random() * 50000) );
            } else if (ran == 2){
                System.out.println(ran+" "+(int)(Math.random() * 50000) );
            } else {
                int z1 = (int)(Math.random() * 50000);
                int z2 = (int)(Math.random() * 50000);
                System.out.println(ran+" "+Math.min(z1,z2)+" "+Math.max(z1,z2) );
            }
        }
    }
}