import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 	11760KB	|	80ms
 * 풀이 : dp[i][j] : i, j에 도달할 수 있는 경로의 갯수
 * 점화식 : dp[i+length][j] = dp[i+length][j] + dp[i][j] 
 * 출발 지점의 수(dp[i][j]만큼 증가시키는 이유는
 * 출발 지점의 DP가 5인 경우는 해당 출발지점까지 오는 경우의 수가 5가지 이므로 
 * 해당 출발 점에서 다음 위치로 이등 가능한 경우의 수 또한 5가지 존재하기 때문
 * 
 */
public class B1890 {
	static int[] dx = {0,1};
	static int[] dy = {1,0};
	static int N;
	static boolean[][] visited;
	static long[][] dp;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//로직
		dp = new long[N][N];
		dp[0][0]=1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N;j++) {
				int length = map[i][j];
				if(length == 0) continue;	// length==0인 경우, dp[i][j] += dp[i][j]로 같은 값이 누적되므로 처리
				if(i + length < N) dp[i+length][j] +=dp[i][j];
				if(j + length < N) dp[i][j + length] +=dp[i][j];
			}
		}
		System.out.println(dp[N-1][N-1]);
	}

}
