package algo0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * depth를 4로 기준으로 dfs로 돌리면 T자형 블록을 제외하고
 * 다른 블록 모양들은 전부 탐색할 수 있음 
 * 
 * T자형 블록은 dfs의 depth가 2일때 따로 처리
 * 28864KB | 268ms
 */

public class Main_B_14500_테트로미노_가지치기 {
	static int N, M;
	static int[][] map;
	static int res;
	static int max_map;
	static boolean[][] visited;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	// T모양 블록
	static int[][] dtx = {
			{-1, 0},
			{-1, 0},
			{1, 0},
			{1, 0}
	};
	static int[][] dty = {
			{0, 1},
			{0, -1},
			{0, -1},
			{0, 1}
	};
	

	static boolean rangeCheck(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	static void dfs(int x, int y, int cnt, int sum) {
		// 현재 sum값과 남아있는 depth(4-cnt) * 맵에서 가장 큰 수가 현재 결과값(res)보다 작으면
		// 볼 필요가 없음 -> 가지치기
		if(sum + max_map * (4-cnt) < res)
			return;
		
		if (cnt == 4) {
			res = Math.max(res, sum);
			return;
		}

		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!rangeCheck(nx, ny)) continue;
			if(visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			dfs(nx, ny, cnt+1, sum + map[nx][ny]);
			visited[nx][ny] = false;
		}
		
		if(cnt == 2) {
			for(int i=0; i<4; i++) {
				int nx1 = x + dtx[i][0];
				int nx2 = x + dtx[i][1];
				int ny1 = y + dty[i][0];
				int ny2 = y + dty[i][1];
				
				if(!rangeCheck(nx1, ny1)) continue;
				if(!rangeCheck(nx2, ny2)) continue;
				if(visited[nx1][ny1]) continue;
				if(visited[nx2][ny2]) continue;
				dfs(x, y,  cnt+2, sum + map[nx1][ny1] + map[nx2][ny2]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max_map = Math.max(map[i][j], max_map);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visited[i][j] = false;
			}
		}
		System.out.println(res);
	}
}
