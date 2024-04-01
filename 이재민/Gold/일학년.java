import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 양수이고 20 이하의 수에서 등식의 개수를 구하는 문제이므로
 * 범위가 크지 않아서 dp를 생각할 수 있음
 * 출력이  2^63 - 1이라서 long타입
 * + 또는 -만 나오기 때문에 +, -를 해서 값을 저장
 * 11568KB | 80ms
 */

public class 일학년 {
	static int N;
	static int[] arr;
	static long[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
	
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		dp = new long[N][21];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][arr[0]] = 1;
		
		for(int i=1; i<N-1; i++) {
			for(int j=0; j<21; j++) {
				if(dp[i-1][j] != 0) {
					if(j - arr[i] >= 0) dp[i][j-arr[i]] += dp[i-1][j];
					if(j + arr[i] < 21) dp[i][j+arr[i]] += dp[i-1][j];
				}
			}
		}
		System.out.println(dp[N-2][arr[N-1]]);
		
	}

}