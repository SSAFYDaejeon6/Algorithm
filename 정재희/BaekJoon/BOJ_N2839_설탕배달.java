package BaekJoon;
import java.util.Arrays;
import java.util.Scanner;
public class BOJ_N2839_설탕배달 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int a = 5, b =3;
		int n_five = N / 5;
		int remain;
		// 5�� ������ �� �������� ���� ���� ������
		while(n_five > -1) {
			remain = N - n_five*a;
			// if(n_five <= 0 && ) break;
			if(remain%3 == 0) {
				System.out.println(n_five+(remain/3));
				return;
			}
			else {
				if(n_five <= 0) {
					System.out.println(-1);
					return;
				}
				n_five--;
			}
		}
	}

}
