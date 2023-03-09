public class test {
    public static void main(String[] args) {
        QWriter qw = new QWriter();
        qw.println(10);
        for (int i = 0; i < 10; i++) {
            qw.println(100000);
            for (int j = 1; j < 50001; j++) {
                qw.print(j+" ");
            }
            for (int j = 1; j < 50001; j++) {
                qw.print(1+" ");
            }
        }
        qw.close();
    }
}