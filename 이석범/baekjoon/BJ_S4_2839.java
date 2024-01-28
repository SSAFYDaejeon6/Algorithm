import java.util.Scanner;

public class BJ_S4_2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int count = num / 5;
        int result = num - count * 5;
        while (result <= num) {
            if (result % 3 != 0) {
                result += 5;
                count--;
            } else {
                count += result / 3;
                break;
            }
        }
        if(count == 0) System.out.println(-1);
        else System.out.println(count);
    }
}
