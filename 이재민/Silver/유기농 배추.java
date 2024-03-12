import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 그래프에서 0은 배추가 없는 땅, 1은 배추가 있는 땅
 * 배추가 있는 땅이 서로 연결되어 있으면 1마리의 배추흰지렁이로 연결된 땅을 전부 탐색할 수 있음
 * 서로 연결된 지역이 몇개가 있는지 탐색하는 bfs or dfs 그래프 탐색 문제
 */

public class Main {
	
	// 좌표(x, y)를 사용하기 위한 클래스 Pair
	public static class Pair{
		int x, y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] map;
	static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static boolean check(int x, int y, int m, int n) {
		if(x<0 || x>=m) return false;
		if(y<0 || y>=n) return false;
		if(map[x][y] == 0) return false;
		return true;
	}
	
	// bfs
	public static void bfs(int i, int j, int m, int n) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(i, j));
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			q.remove();
			
			for(int dir = 0; dir<4; dir++) {
				int nx = x + d[dir][0];
				int ny = y + d[dir][1];
				
				// 범위에 벗어나지 않고 배추가 있는 땅이라면
				// 재탐색 하지 않도록(반복문이 무한하게 돌지 않도록) 0으로 지정
				if(check(nx, ny, m, n)) {
					map[nx][ny] = 0;
					q.add(new Pair(nx, ny));
				}
			}
			
			
			
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.parseInt(st.nextToken());
		
		for(int tc = 0; tc<t; tc++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int cnt = 0;
			map = new int[m][n];
			
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[x][y] = 1;
			}
			
			// 순차적으로 그래프를 돌다가 배추가 있는 땅을 발견하고
			// 배추가 있는 땅은 배추흰지렁이가 주변 배추에도 전파하기 때문에 bfs 
			for(int i=0; i<m; i++) {
				for(int j=0; j<n; j++) {
					if(map[i][j] == 1) {
						bfs(i, j, m, n);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
			
		}
		
		
	}

}