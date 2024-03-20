// 상어 초등학교 - 최적화 전 코드

/**
 * 13,496KB | 112ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_N21608_상어초등학교_최적화전 {
	static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] classroom = new int[N+1][N+1];
		int[][] position = new int[N*N+1][2];
		int[][] likes = new int[N*N+1][4];
		
		List<int[]> likeNear = new ArrayList<>();
		
		for(int i=1;i<=N*N;i++) {
			likeNear.clear();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < 4; j++) {
				likes[s][j] = Integer.parseInt(st.nextToken());
				
				if(position[likes[s][j]][0] == 0 && position[likes[s][j]][1] == 0) continue;
				
				int r = position[likes[s][j]][0];
				int c = position[likes[s][j]][1];
				
				for (int d = 0; d < 4; d++) {
					int nr = r + deltas[d][0];
					int nc = c + deltas[d][1];
					
					if(nr < 1 || nr > N || nc < 1 || nc > N || classroom[nr][nc] != 0) continue;
					
					likeNear.add(new int[] {nr, nc});
				}
				
			}
			
			if(likeNear.size() == 0) getPosition(s, classroom, position, null);  // 2중 반복문 -> 비어있는 곳 찾고, 비어있는 인접한 칸이 많은 곳으로 착석시켜
			else getLikeNear(s, classroom, position, likeNear);
		
		}
		
		
		System.out.println(calculateJumsu(classroom, position, likes, N));

	}
	
	
	private static int calculateJumsu(int[][] classroom, int[][] position, int[][] likes, int N) {
		int sum = 0;
		for (int i = 1; i <= N*N; i++) {
			int r = position[i][0];
			int c = position[i][1];
			
			int cnt = 0;
			for (int d = 0; d < 4; d++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				
				if(nr < 1 || nr > N || nc < 1 || nc > N) continue;
				
				int val = classroom[nr][nc];
				
				for (int j = 0; j < 4; j++) {
					if(val == likes[i][j]) {
						cnt++;
						break;
					}
				}
			}
			if(cnt == 0) continue;
			sum += Math.pow(10, cnt-1);
		}
		return sum;
	}
	
	private static void getLikeNear(int s, int[][] classroom, int[][] position, List<int[]> likeNear) {
		// likeNear 돌면서
		int N = classroom.length;
		int[][] counted = new int[N][N];
		
		int maxCount = 0;
		
		for(int[] pos : likeNear) {
			if(++counted[pos[0]][pos[1]] > maxCount)
				maxCount = counted[pos[0]][pos[1]];
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if(counted[i][j] == maxCount)
					counted[i][j] = 0;
				else
					counted[i][j] = 1;
			}
		}
		getPosition(s, classroom, position, counted);
	}
	
	private static void getPosition(int s, int[][] classroom, int[][] position, int[][] counted) {
		int N = classroom.length;
		
		int[][] remain = counted==null? classroom:counted;
		
		int maxCnt = 0;
		int r=N, c=N;
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if(remain[i][j] != 0) continue;
				
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i + deltas[d][0];
					int nc = j + deltas[d][1];

					if(nr < 1 || nr > N-1 || nc < 1 || nc > N-1 || classroom[nr][nc] != 0) continue;
					
					cnt++;
				}
				if(cnt == 4) {
					classroom[i][j] = s;
					position[s][0] = i;
					position[s][1] = j;
					return;
				}
				
				if(cnt == maxCnt) {
					if(r > i) {
						r = i; c = j;
					}
					else if(r == i) {
						if(c > j) {
							r = i; c = j;
						}
					}
				}
				
				if(cnt > maxCnt) {
					r = i;
					c = j;
					maxCnt = cnt;
				}
				
			}
		}
		
		classroom[r][c] = s;
		position[s][0] = r;
		position[s][1] = c;
	}

}
