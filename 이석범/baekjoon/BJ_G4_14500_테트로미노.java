package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *32064kb 668ms
 */
public class BJ_G4_14500_테트로미노 {


	static int R, C;
	static int[][] graph;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static int max = Integer.MIN_VALUE;


	static void DFS(int remain, int r, int c, boolean[][] visited, int num) {
		
		if(remain==0) {
			max = Math.max(max, num);
			return;
		}
		
		for(int d=0; d<4;d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			
			if(nr < 0 || nr >=R || nc < 0 || nc >= C||visited[nr][nc]) continue;
			//뻐큐 모양의 경우
			//2번 인덱스에서 한쪽으로 가고 다시 한쪽으로 가야하므로 dfs에 nr,nc가 아니라 r, c를 다시 하면 됨
			if(remain==2) {
				visited[nr][nc] = true;
				DFS(remain-1, r, c, visited, num+graph[nr][nc]);
				visited[nr][nc] = false;
			}
			
			visited[nr][nc] = true;
			DFS(remain-1, nr, nc, visited, num+graph[nr][nc]);
			visited[nr][nc] = false;
			
			
			
		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				StringTokenizer st = new StringTokenizer(br.readLine());
				R = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				graph = new int[R][C];
		
				for(int r=0; r<R;r++) {
					st = new StringTokenizer(br.readLine());
					for(int c=0; c<C;c++) {
						graph[r][c] = Integer.parseInt(st.nextToken());
					}
				}

		boolean[][] visited = new boolean[R][C];
		
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				visited[r][c] = true;
				DFS(3, r, c, visited, graph[r][c]);
				visited[r][c] = false;
			}
		}

		System.out.println(max);

	}

}
