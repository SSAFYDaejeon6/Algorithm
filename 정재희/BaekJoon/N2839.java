import java.util.Arrays;
import java.util.Scanner;
public class N2839 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int a = 5, b =3;
		int n_five = N / 5;
		int remain;
		// 5의 개수가 몇 개인지에 따라 값이 결정됨
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
