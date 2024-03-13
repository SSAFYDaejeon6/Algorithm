import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 왼쪽 최상단에서 오른쪽 최하단으로 파이프를 옮기는 문제
 * 이동할 수 있는 모든 경우의 수(경로)를 구하는 문제
 * 이동할 수 있는 조건이 있고, N의 범위가 크지 않아서
 * dfs로 충분히 풀 수 있는 문제인 것 같음 (현재 풀이는 dp이고 dfs로도 풀어서 올릴 것임)
 * 현재 위치에서 갈 수 있는 곳이 아닌 column을 2부터 시작하면서 현재 위치에서
 * 어떤 값이 올 수 있는지를 판단
 * 3차원 배열에서 0 - 가로, 1 - 세로, 2 - 대각선
 * 
 * 현재 위치에서 가로 즉 j-1에서 오는 경로를 생각 (ex 가로에서 온다면 이전 방향이 가로, 대각선)
 * **** 만약 이전 가로 경로에서 세로로 파이프가 위치해 있을 경우 가로로 이동할 수 없음 ****
 * dp[i][j] += dp[i][j-1][0] + dp[i][j-1][2]
 * 
 * 나머지 세로, 대각선 경로는 코드를 참조
 * 11732KB | 80ms
 */

public class 파이프옮기기1 {
	static int N;
	static int[][] map;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N][3]; // 가로, 세로, 대각선
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for(int i=0; i<3; i++) {
			dp[0][1][0] = 1;
//		}
		
		for(int i=0; i<N; i++) {
			for(int j=2; j<N; j++) {
				if(map[i][j] != 0) continue;
				// 현재 파이프 기준이 가로라면 가로 또는 대각선에서 올 수 있음
				dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2];

				// 현재 파이프 위치 기준이 세로라면?
				if(i==0) continue;
				dp[i][j][1] += dp[i-1][j][1] + dp[i-1][j][2];
				
				// 현재 파이프 위치 기준이 대각선이라면?
				if(map[i-1][j] != 0 || map[i][j-1] != 0) continue;
				dp[i][j][2] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
			}
		}
		
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
	}

}
