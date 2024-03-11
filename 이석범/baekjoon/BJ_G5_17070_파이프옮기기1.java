package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//11620kb 80ms
public class BJ_G5_17070_파이프옮기기1 {
	
	static int N;
	static int[][] graph;
	
	static int[][][] dp;
	
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 1, 0};
	
	//가능한 경우 이전 위치의 경우의수를 더함
	static void add(int r, int c, int d, int sum) {
		
		int nr = r + dr[d];
		int nc = c + dc[d];
		
		if(nr < 0 || nr >= N || nc < 0 || nc >= N) return;

		if(d!= 1) {
			if(graph[nr][nc]==1) return;
			
		}
		
		
		//대각선은 3부분 다 검사해야함
		else {
			if(graph[nr][nc] == 1 || graph[nr-1][nc] == 1 || graph[nr][nc-1] == 1 ) return;
		}
		
		//조건에 만족하면 더함
		dp[nr][nc][d] += sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		//0은 오른쪽, 1은 대각 아래, 2는 아래
		dp = new int[N][N][3];
		
		for(int r=0; r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		dp[0][1][0] = 1;
		
		for(int r=0; r<N;r++) {
			for(int c=0; c<N;c++) {
				
				if(r==N-1 && c== N-1) {
					int sum = 0;
					for(int i=0; i<3;i++) {
						sum += dp[r][c][i];
					}
					
					System.out.println(sum);
					return;
				}
				for(int i=0; i<3;i++) {
					if(dp[r][c][i] == 0) continue;
					
					switch(i) {
					case 0:
						//0과 1
						add(r, c, 0, dp[r][c][i]);
						add(r, c, 1, dp[r][c][i]);
						break;
					case 1:
						//0, 1, 2
						add(r, c, 0, dp[r][c][i]);
						add(r, c, 1, dp[r][c][i]);
						add(r, c, 2, dp[r][c][i]);
						break;
					case 2:
						//1, 2
						add(r, c, 1, dp[r][c][i]);
						add(r, c, 2, dp[r][c][i]);
						break;
					}
				}
			}
		}
		
		
		
	}
}
