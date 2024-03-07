package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *NxM크기 T시간 내에 도착해야함
 *0, 0에서 시작 N,M의 위치에 있는 공주를 구해야함
 *정확히 T시간에 도착해도 ㄱㅊ
 *0,0에 그람이 존재할 수도 잇을 듯?
 *100x100이면 상관없을 듯
 *13372kb 136ms
 */
public class BJ_G5_17836_공주님을구해라 {
	static int R, C, T;
	
	static int[][] graph;
	
	static class Node {
		int r;
		int c;
		boolean flag;
		
		Node(int r, int c, boolean flag) {
			this.r = r;
			this.c = c;
			this.flag = flag;
		}
	}
	
	//0은 일반 1은 그람
	static boolean[][][] visited;
	
	static int[] gram = new int[2];
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		
		if(gram[0] == 0 && gram[1]==0) {
			queue.offer(new Node(0, 0, true));
			visited[1][0][0] = true;
		}
		else {
			queue.offer(new Node(0, 0, false));
			visited[0][0][0] = true;
		}
		
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(--size>=0) {
				Node current = queue.poll();
				
				if(current.r == R-1 && current.c == C-1) return cnt;
				
				for(int d=0; d<4;d++) {
					int nr = current.r + dr[d];
					int nc = current.c + dc[d];
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					
					//그람 인경우
					if(current.flag) {
						if(visited[1][nr][nc]) continue;
						visited[1][nr][nc] = true;
						queue.offer(new Node(nr, nc, true));
						
					}
					
					//아닌 경우
					else {
						if(visited[0][nr][nc] || graph[nr][nc] == 1) continue;
						
						if(graph[nr][nc] == 2) {
							visited[1][nr][nc] = true;
							queue.offer(new Node(nr, nc, true));
						}
						else {
							visited[0][nr][nc] = true;
							queue.offer(new Node(nr, nc, false));
							
						}
						
					}
					
				}
			}
			cnt++;
			if(cnt > T) return 0;
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		graph = new int[R][C];
		visited = new boolean[2][R][C];
		
		for(int r=0; r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		//그람 찾기
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				if(graph[r][c]==2) {
					gram[0] = r;
					gram[1] = c;
					break;
				}
			}
		}
		int result = BFS();
		System.out.println(result == 0 ? "Fail":result);
	}
}
