
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N과 M의 범위가 크기 때문에 오버플로우 또는 시간초과가 날 수 있음
 * DP 이용
 * 15396KB | 156ms
 */
public class 다리놓기 {

	// nCr 조합의 개수 구하기 func(n-1, r-1) + func(n-1, r)
	// 한번 구한 nCr의 값을 또 다시 구하지 않기위해 dp배열에 저장
	static int dpf(int n, int m, int[][] dp) {
		if(n==m || m==0) return 1;
		
		if(dp[n][m] != 0) return dp[n][m];
		
		return dp[n][m] = dpf(n-1, m-1, dp) + dpf(n-1, m, dp);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int tc = 0; tc < t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int [][]dp = new int[n+1][m+1];
			
			sb.append(dpf(n, m, dp) + "\n");
		
		}
		System.out.println(sb);
		
	}

}
