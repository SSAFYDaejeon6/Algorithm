import java.util.Scanner;

public class BJ_S4_2839_설탕배달 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        //처음 5로 나눈 값을 계산
        int count = num / 5;
        int result = num - count * 5;

        //만약 나머지에서 3으로 나눌 수 없으면 count에서 1을 빼고 result에서 5를 더한다
        //3으로 나누어 지면 count로 나눈 값을 계산
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
