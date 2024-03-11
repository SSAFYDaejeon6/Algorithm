package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//11824kb 84ms
public class BJ_S1_1890_점프 {
	
	static int N;
	static int[][] graph;
	static long[][] dp;
	
	static long result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		dp = new long[N][N];
		
		for(int r=0; r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = 1;
		//값이 너무 커지고 개수도 많아서 dp로 풀어야함
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				//현재값이 0인 경우 건너뜀
				if(dp[r][c]==0 || graph[r][c]==0) continue;
				else {
					//아닌경우 점프한 곳에 자신의 값 추가
					int depth = graph[r][c];
					if(depth+c < N) {
						dp[r][depth+c] += dp[r][c];
					}
					
					if(depth+r < N) {
						dp[depth+r][c] += dp[r][c];
					}
					
				}
			}
		}
		System.out.println(dp[N-1][N-1]);
	}
}
