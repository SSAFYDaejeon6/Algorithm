package algo0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**11820kb	84ms
 * 점프
 * NxN 게임판
 * 가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 규칙에 맞게 점프를 해서 가는 것
 * 
 * 반드시 오른쪽이나 아래쪽으로만 이동 
 * 각 칸의 수: 현재 칸에서 갈 수 있는 거리
 * 0: 진행을 막는 종착점
 * 한 번 점프를 할 때 방향을 바꾸면 안됨 
 * [목표] 규칙에 맞게 이동할 수 있는 경로의 개수를 구하라.
 * 
 * */
public class 점프 {
	static int[][] delta = {{0, 1}, {1, 0}}; //우 하
	static int N;
	static int[][] map;
	static long[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		dp = new long [N][N];
		for (int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = 1;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				int val = map[i][j];
				if (val == 0) continue;
				if (i+val < N) dp[i+val][j] += dp[i][j];
				if (j+val < N) dp[i][j+val] += dp[i][j];
			}
		}
		System.out.println(dp[N-1][N-1]);
		
	}

}
