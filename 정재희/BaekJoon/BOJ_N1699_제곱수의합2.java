package BaekJoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  구글 풀이를 참고하여 공부하였습니다.
 *  
 * i\n  0   1   2   3   4   5   6   7   8   9   10   11
 * 1^2  0   1   2   3   4   5   6   7   8   9   10   11
 * 2^2  // //  //  //   1   2   3   4   2   3   4    5   -> i^2부터 값 입력, min((i-1, n), (i-1, n-4)+1)
 * 3^2  // //  //  //  //  //  //  //  //   1   2    3  
 * 
 *  시간: 144ms  |  메모리: 12,644KB
 */
public class BOJ_N1699_제곱수의합2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = (int)Math.sqrt(N);
		
		int[] dp = new int[N+1];
		
		for (int i = 0; i < N+1; i++) {  // 1의 제곱으로 표현되는 제곱항의 개수, 초기값으로 저장
			dp[i] = i;
		}
		
		for (int i = 2; i < M+1; i++) {
			int r = (int) Math.pow(i, 2);  // i의 제곱이후부터 dp배열 업데이트 수행
			for (int j = r; j < N+1; j++) {
				dp[j] = Math.min(dp[j],  dp[j-r]+1);
			}
		}
		System.out.println(dp[N]);		
	}
	
}
