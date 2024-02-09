/*13352kb	100ms
 * 
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1012_유기농배추_고승희 {
	static int T, M, N, K;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx= {-1,1,0,0}; //상하좌우
	static int[] dy= {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}
			
			int count = 0; 
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (map[i][j]==1 && !visited[i][j]) {
						dfs(i, j);
						count++;
					}
				}
			}
			System.out.println(count);
		}	
	}
	static void dfs(int x, int y) {
		visited[x][y] = true; // 방문체크
		
		//상하좌우 이동
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >=0 && nx < N && ny >= 0 && ny < M) {
				if (map[nx][ny]==1 && !visited[nx][ny]) {
					dfs(nx, ny);
				}
			}
		}
	}
}
