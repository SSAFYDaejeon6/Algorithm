import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 2839 설탕배달
 * 3kg과 5kg중 5kg의 비중이 많아야 적은 수의 봉지로 가져갈 수 있음
 * 3kg을 계속 빼주면서 3kg 봉지를 하나씩 더하면서 5kg으로 계속 나누기
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		int n5 = 0; // 5kg 개수
		int n3 = 0; // 3kg 개수

		while (true) {
			// 더 이상 봉지를 추가 할 수 없으면 반복문 중단
			// t가 0이거나 t가 5로 나누어 떨어진다면 정확히 t킬로그램을 만들 수 있음
			// t가 0보다 작으면 정확히 t킬로그램 만들 수 없으므로 -1을 출력
			if(t<=0) break;
			if(t%5 == 0) {
				n5 = t / 5;
				break;
			}
			n3++;
			t = t - 3;
		}
		
		if(t < 0) System.out.println(-1);
		else System.out.println(n5 + n3);

	}

}