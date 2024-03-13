

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 11,824KB | 88ms
 * 
 * DP 이용 지나간 횟수 더하기
 */
public class BOJ_N1890_점프 {
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[][] map = new int[N][N];
        long[][] dp = new long[N][N];
        int[][] deltas = {{0, 1}, {1, 0}};
        
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        if(map[0][0] == 0) {
            System.out.println(0);
            return;
        }
        
        dp[0][0] = 1;
        
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(dp[i][j] == 0 || map[i][j] == 0) continue; // 거치지 않은 경로 및 이동 불가 위치 pass
				for(int d=0;d< 2;d++) {
	                int nr = i + deltas[d][0]*map[i][j];
	                int nc = j + deltas[d][1]*map[i][j];
	                if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1) continue;
	                
	                dp[nr][nc] += dp[i][j];
				}
			}
		}
        
        System.out.println(dp[N-1][N-1]);
        
    }

}