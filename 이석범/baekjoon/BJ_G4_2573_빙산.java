package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author SEOK BEOM LEE
 *132360kb 444ms
 */
public class BJ_G4_2573_빙산 {
	
	static int R,C;
	static int[][] graph;
	
	static class Node {
		int r;
		int c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}

	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static void BFS(int r, int c) {
		
		boolean[][] visited = new boolean[R][C];
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(r, c));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int curR = node.r;
			int curC = node.c;
			
			for(int d=0; d<4;d++) {
				int nr = curR + dr[d];
				int nc = curC + dc[d];				
				
				if(nr < 0 || nr >=R||nc <0 || nc >=C || visited[nr][nc]) continue;
				
				
				
				
			}
			
			
			
		}
		
	}
	
	static void melt() {
		int[][] zeroCnt = new int[R][C];
		
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				int cnt = 0;
				
				if(graph[r][c] > 0) {
					
					for(int d=0; d<4;d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(nr < 0 || nr >=R||nc <0 || nc >=C) continue;
						
						if(graph[nr][nc]==0) cnt++;
						
					}
				}
				
				zeroCnt[r][c] = cnt;
				
			}
		}
		
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				graph[r][c] = graph[r][c] - zeroCnt[r][c] > 0 ? graph[r][c] - zeroCnt[r][c] : 0;
			}
		}
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new int[R][C];
		for(int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
//		print();
		
		boolean flag = false;
		int cnt = 0;
		while(true) {
//			System.out.println(cnt);
			
			//얼음이 x면
			if(!check()) {
				System.out.println(0);
				return;
			}
			
			cnt++;

			// 빙산 녹이기
			melt();
			
			int ice = 0;
			visited = new boolean[R][C];
			//다음 빙산 개수 구하기
			
			for(int r=0; r<R;r++) {
				for(int c=0; c<C;c++) {
					if(graph[r][c] > 0 && !visited[r][c]) {
						ice++;
						dfs(r, c);
					}
				}
			}
			
			if(ice >= 2) {
				System.out.println(cnt);
				return;
			}
			
		}
		
		
		
	}
	
	static boolean[][] visited;
	
	//dfs로 연결된 빙산 구하기
	static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for(int d=0; d<4;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(nr < 0 || nr >=R||nc <0 || nc >=C || visited[nr][nc]) continue;
			
			if(graph[nr][nc] > 0 && !visited[nr][nc]) dfs(nr, nc);
			
		}
		
	}
	
	//얼음있는지 구하기
	static boolean check() {
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				if(graph[r][c]>0) return true;
			}
		}
		
		return false;
	}
	
	static void print() {
		for(int[] r:graph) {
			for(int c:r) {
				System.out.print(c+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
