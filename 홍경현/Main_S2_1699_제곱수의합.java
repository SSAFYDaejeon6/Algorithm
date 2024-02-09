package Algorithm.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*12136kb	116ms
 * 문제 해석
	자연수 N은 그보다 작거나 같은 제곱수들의 합
	
	입력
	1 ≤ N ≤ 100,000
	
	출력
	제곱수 항의 최소 개수
 */

public class Main_S2_1699_제곱수의합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int dp[] = new int[N+1];
		
		//i 이하의 수 중 제곱수가 될 수 있는 값 중 최소를 비교하여 dp[i] 값에 저장
		for(int i=1; i<=N; i++) {
			dp[i] = i;
			for(int j=1; j*j<i; j++) {
				if(dp[i]>dp[i-j*j]+1) dp[i] = dp[i-j*j]+1;
			}
		}

		System.out.println(dp[N]);
	}
}
