package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/* 14716kb 132ms
 * 문제 해석
	NxM 크기의 성
	용사의 위치 (1,1)
	마법 벽을 피해 (N,M) 위치의 공주님 구출
	
	T시간 이내 용사를 만나야 함
	상하좌우 이동
	
	전설의 명검 → 벽을 부수고 갈 수 있음
	
	[입력]
	1. N, M, T
	2. 성의 구조
	
	0: 빈공간
	1: 벽
	2: 그람
	
	[출력]
	최단시간 or Fail
 */
public class Main_17836_공주님을구해라 {
	static int N, M, T, result;
	static int[][] map;
	
	static class Node{
		int r, c, idx;

		public Node(int r, int c, int idx) {
			super();
			this.r = r;
			this.c = c;
			this.idx = idx;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		System.out.println(result <= T ? result : "Fail"); //T시간 이내면 result값, 아니면 Fail 출력
	}

	private static void bfs() {
		boolean[][][] visit = new boolean[N+1][M+1][2]; //0: 그람 못 얻음 1: 그람 얻음
		int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(1, 1, 0));
		visit[1][1][0] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			result++;
			for(int k=0; k<size; k++) {
				Node n = q.poll();
				
				for(int i=0; i<4; i++) {
					int nr = n.r + dr[i];
					int nc = n.c + dc[i];
					if(nr == N && nc == M) return;
					if(!range(nr, nc) || visit[nr][nc][n.idx]) continue;
					//그람을 얻지 못한 경우
					if(n.idx == 0) {
						if(map[nr][nc] == 1) continue; //벽이면 지나가지 못함
						if(map[nr][nc] == 2) { //그람을 얻을 경우 그람을 얻은 방문 배열을 넣음
							q.add(new Node(nr, nc, 1));
							visit[nr][nc][1] = true;
							continue;
						}
						//빈 공간이면 방문 배열 체크
						q.add(new Node(nr, nc, 0)); 
						visit[nr][nc][0] = true;
					}else { //그람을 얻은 상태면 벽, 빈공간 상관없이 큐에 담음
						q.add(new Node(nr, nc, 1)); 
						visit[nr][nc][1] = true;
					}
				}
			}
			
		}
		
		result = T+1; //목적지에 도달하지 못하면 T+1
	}

	private static boolean range(int nr, int nc) {
		return nr>=1 && nr <=N && nc>=1 && nc<=M;
	}

}
