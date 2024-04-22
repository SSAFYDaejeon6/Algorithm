import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이 문제 안보고 풀었으면
 * 천재임
 */

public class 행렬곱셈순서 {
	static final int MAX_VALUE = Integer.MAX_VALUE;
	static int N;
	static int[][] arr, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		int arr[][] = new int[N+1][2];
		
		int dp[][] = new int[N+1][N+1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[i][0] = r;
			arr[i][1] = c;
		}
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < N-i; j++) {
				dp[j][i+j] = MAX_VALUE;
				for(int k = j; k < j+i; k++) {
					int val = dp[j][k] + dp[k+1][i+j] + (arr[j][0] * arr[k][1] * arr[i+j][1]);
					dp[j][i+j] = Math.min(dp[j][i+j], val);
					
				}
			}
		}
		System.out.println(dp[0][N-1]);
	}
}