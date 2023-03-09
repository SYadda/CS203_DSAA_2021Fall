import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int strLen = str.length();
        int strPossible = strLen * (strLen+1) / 2;
        int strPossibleOrigin = strPossible;
        String[] allStrOne = str.split("");
        String[] allStr = new String[strPossible];
        for (int i = 0; i < strPossible; i++) {
            allStr[i] = "";
        }

        int count = 0;
        for (int i = 1; i <= strLen; i++) {  // i:substring.length
            for (int j = 0; j <= strLen - i; j++) {     //j:substring.start
                int temp = i;
                int temp2 = j;
                while (temp-- > 0){
                    allStr[count] += allStrOne[temp2];
                    temp2++;
                }
                count++;
            }
        }

        for (int i = 0; i < strPossibleOrigin; i++) {
            boolean delete = false;
            for (int j = 0; j < i; j++) {
                if (allStr[i].equals(allStr[j])){
                    delete = true;
                }
            }
            if (delete){
                strPossible--;
            }
        }

        System.out.println(strPossible);
    }
}