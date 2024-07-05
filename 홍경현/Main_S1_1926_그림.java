package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/* 55904KB 348ms
 * [문제 해석]
 * 그림의 개수와 그림 중 넓이가 가장 넓은 것의 넓이 출력
 * 그림 = 1로 연결된 것
 * 대각선으로 연결이 된 것은 떨어진 그림
 * 
 * [입력]
 * 1. n (1<=n<=500), m (1<=m<=500)
 * 2. n+1 줄까지 그림의 정보 (0과 1이 공백을 두고 주어짐)
 * 
 * [출력]
 * 1. 그림의 개수
 * 2. 최대 넓이
 * 
 * [문제 해결 프로세스]
 * 1) (0,0)부터 (n-1, m-1)까지 반복문 돌면서 BFS 탐색 -> 방문 배열
 * 2) 각 그림의 크기를 현재 최대 크기와 비교하여 갱신
 * 
 * [시간복잡도]
 * 1) 입력 읽기 500*500 => 250,000
 * 2) 반복문  500*500 => 250,000
 * 3) BFS 500*500 => 25,000
 * => 3 x O(n x m)
 */
public class Main_S1_1926_그림 {

	static int cnt, max, n, m;
	static boolean[][] visited;
	static int[][] draw;
	
	public static class Node{
		int n, m;

		public Node(int n, int m) {
			super();
			this.n = n;
			this.m = m;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		draw = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				draw[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(draw[i][j] == 0 || visited[i][j]) continue;
				visited[i][j] = true;
				bfs(i, j);
				cnt++;
			}
		}
		
		System.out.println(cnt);
		System.out.println(max);
	}

	private static void bfs(int n, int m) {
		int dr[] = {0, 1, 0, -1}, dc[] = {1, 0, -1, 0};
		int count = 0;
		
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(n, m));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int r = node.n;
			int c = node.m;
			count++;
			
			for(int i=0; i<4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(!range(nr, nc) || visited[nr][nc]
						|| draw[nr][nc] == 0) continue;
				q.add(new Node(nr, nc));
				visited[nr][nc] = true;
			}
		}
		
		max = Math.max(max, count);
	}

	private static boolean range(int r, int c) {
		return r>=0 && c>=0 && r<n && c<m;
	}

}
