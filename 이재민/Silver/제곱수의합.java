import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 하나씩 써보면서 10개정도 써보면 규칙을 찾을 수 있음
 * 1, 4, 9처럼 제곱근이 정수로 존재하는 수는 무조건 1의 값을 가짐
 * 어떤 수 x가 있을 때 x를 이루는 수는 그보다 작은 수들의 제곱의 합으로 이루어지기 때문에
 * x에서 1^2, 2^2 등을 빼면서 최소값 찾기
 * 
 */
public class 제곱수의합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n+1];
		
		dp[1] = 1;
		
		for(int i=1; i<=n; i++) {
			dp[i] = i;
			for(int j=1; j*j<=i; j++) {
				// 현재 값은 이전의 가능한 어떠한 값에서 + 1
				dp[i] = Math.min(dp[i], dp[i - j*j]+1); 
			}
		}
		System.out.println(dp[n]);
	}

}
