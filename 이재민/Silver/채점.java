import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 채점 {
	static int N;
	static int[][] map; 
	static long[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		
		map = new int[N][N];
		dp = new long[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(dp[i][j] == 0 || map[i][j] == 0) continue;
				int jump = map[i][j];
				long d = dp[i][j];
				if(i + jump < N) {
					dp[i+jump][j]+=d;
				}
				if(j + jump < N) {
					dp[i][j+jump]+=d;
				}

			}
		}
		
		
		System.out.println(dp[N-1][N-1]);
	}

}