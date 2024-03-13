

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11,692KB | 80ms
 */
public class BOJ_N17070_파이프옮기기1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		
		int[][][] dp = new int[3][N][N];
		// 0 : 가로, 1: 세로, 2: 대각선
		dp[0][0][1] = 1;
		
		int[][][] deltas = {
				{{0, 1}, {1, 1}},
				{{1, 0}, {1, 1}},
				{{0, 1}, {1, 0}, {1, 1}}
		};
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] != 0) continue;
				for (int k = 0; k < 3; k++) {
					if(dp[k][i][j] == 0) continue;
					for (int d = 0; d < deltas[k].length; d++) {
						int nr = i + deltas[k][d][0];
						int nc = j + deltas[k][d][1];
						
						if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1 || map[nr][nc] != 0) continue;
						int val = deltas[k][d][0] + deltas[k][d][1];

						if(val == 1)
							dp[deltas[k][d][0]][nr][nc] += dp[k][i][j];
						else {
							if(map[nr][j] != 0 || map[i][nc] != 0) continue;
							dp[val][nr][nc] += dp[k][i][j];
						}
					}
				}
			}
		}
		
		System.out.println(dp[0][N-1][N-1] + dp[1][N-1][N-1] + dp[2][N-1][N-1]);
	}

}
