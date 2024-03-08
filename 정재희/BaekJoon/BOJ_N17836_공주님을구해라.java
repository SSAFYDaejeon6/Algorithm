package BaekJoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 	13896KB | 132ms
 * 
 *  1. 무기를 얻기 전까지 레벨별 BFS 탐색
 *  2. 무기 얻은 후 -> 무기 얻은 애는 벽과 상관없이 고 | 아닌 애는 기존과 동일하게 진행 (0일때만 고)
 *  3. 무기 얻기 전과 얻은 후 따로 방문 관리 하기
 *  
 */
public class BOJ_N17836_공주님을구해라 {
	static class Node{
		int r;
		int c;
		boolean isGet;
		public Node(int r, int c, boolean isGet) {
			super();
			this.r = r;
			this.c = c;
			this.isGet = isGet;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][M+1];
		int[][] deltas = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<Node> queue = new ArrayDeque<>();
		boolean[][][] isVisited = new boolean[2][N+1][M+1];  //0 : 무기 얻기 전, 1: 무기 얻은 후
		
		queue.offer(new Node(1, 1, false));  // 출발 저장
		isVisited[0][1][1] = true;
		int cnt = 0;
		
		Node e;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size -- > 0) {  // 레벨별 BFS 탐색
				e = queue.poll();
				
				if(e.r == N && e.c == M) {  // 먼저 도달했을 때, 그대로 종료
					System.out.println(cnt);
					return;
				}
				
				for (int d = 0; d < 4; d++) {
					int nr = e.r + deltas[d][0];
					int nc = e.c + deltas[d][1];
					
					if(nr < 1 || nr > N || nc < 1 || nc > M) continue;
					
					if(map[nr][nc] == 2) {  // 무기를 만났을 때
						if(isVisited[0][nr][nc] || isVisited[1][nr][nc]) continue;
						isVisited[0][nr][nc] = true;
						isVisited[1][nr][nc] = true;
						queue.offer(new Node(nr, nc, true));
						continue;
						
					}
					
					if(e.isGet) {   // 무기를 얻은 애
						if(isVisited[1][nr][nc]) continue;
						isVisited[1][nr][nc] = true;
						queue.offer(new Node(nr, nc, e.isGet));
					}
					
					else {  // 무기를 얻지 못한 애
						if(isVisited[0][nr][nc] || map[nr][nc] == 1) continue;
						isVisited[0][nr][nc] = true;
						queue.offer(new Node(nr, nc, e.isGet));
					}
				}
			}
			if(++cnt > T) {  // T시간 내에 도착하지 못한 경우
				System.out.println("Fail");
				return;
			}
		}
		System.out.println("Fail");
	}
}
