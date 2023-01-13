import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String[] rome = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    static String[] romeDecimal = new String[]{"X","XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
    static int[] arab = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static void main(String[] args) throws Exception {
        while (true){
            String s = new Scanner(System.in).nextLine();
            System.out.println(operation(parsing(s)));
        }

    }
    public static String[] parsing(String str) throws Exception {
        String[] result = str.split(" ");
        if (result.length != 3) {
            throw new Exception();
        }
        if (Arrays.asList(rome).contains(result[0])) {
            if (!Arrays.asList(rome).contains(result[2])) {
                throw new Exception();
            }
        }
        if (!Arrays.asList(rome).contains(result[0])) {
            if (Arrays.asList(rome).contains(result[2])) {
                throw new Exception();
            }
        }
        return result;
    }


    public static String operation(String[] str) throws Exception {
        int a = 0;
        int b = 0;
        int sum = 0;
        String sumString= "";

        boolean isRome = Arrays.asList(rome).contains(str[0]);

        if(isRome){
            int arg = Arrays.binarySearch(rome,str[0]);
            int arg1 = Arrays.binarySearch(rome,str[2]);
            if(arg<0||arg1<0){
                throw new Exception();
            }
        } else {
            int arg = Arrays.binarySearch(arab, Integer.parseInt(str[0]));
            int arg1 = Arrays.binarySearch(arab, Integer.parseInt(str[2]));
            if(arg<0||arg1<0){
                throw new Exception();
            }
        }

        if (isRome) {
            for (int i = 0; i < rome.length; i++) {
                if (rome[i].equals(str[0])) {
                    a = arab[i];
                }
            }
            for (int i = 0; i < rome.length; i++) {
                if (rome[i].equals(str[2])) {
                    b = arab[i];
                }
            }
        } else {
            for (int i = 0; i < arab.length; i++) {
                if (arab[i] == Integer.parseInt(str[0])) {
                    a = arab[i];
                }
            }
            for (int i = 0; i < arab.length; i++) {
                if (arab[i] == Integer.parseInt(str[2])) {
                    b = arab[i];
                }
            }
        }

        if (str[1].equals("+")) {
            sum = a + b;
        }
        if (str[1].equals("-")) {
            sum = a - b;
            if (isRome && sum < 0) {
                throw new Exception();
            }
        }
        if (str[1].equals("*")) {
            sum = a*b;
        }
        if (str[1].equals("/")) {
            sum = a/b;
        }
        sumString= String.valueOf(sum);

        if(isRome){
            if(sum<=10){
                sumString= rome[sum-1];
                return sumString;
            } else if(sum%10==0){
                sumString = romeDecimal[(sum/10)-1];
            } else{
                sumString = romeDecimal[(sum/10)-1]+"" + rome[(sum%10)-1];
            }

        }
        return sumString;
    }
}