import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14500 {
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int max;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

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
			}
		}
		
		// 로직
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				DFS(i, j, 0, map[i][j]);
				visited[i][j] = false;
				
				//ㅏ, ㅓ
				if(i+1 < N && i-1 >= 0) {
					int sum = map[i-1][j] + map[i][j] + map[i+1][j];
					//ㅏ
					if(inRange(i, j+1)) {
						max = Math.max(max, sum + map[i][j+1]);
					}
					// ㅓ
					if(inRange(i, j-1)) {
						max = Math.max(max, sum + map[i][j-1]);
					}
				}
				// ㅗ,ㅜ 모양 판단
				if (!inRange(i, j - 1) || !inRange(i, j + 1))
					continue;
				int sum = map[i][j - 1] + map[i][j + 1] + map[i][j];
				// ㅗ
				if (inRange(i - 1, j)) {
					max = Math.max(max, sum + map[i - 1][j]);
				}
				// ㅜ
				if (inRange(i + 1, j)) {
					max = Math.max(max, sum + map[i + 1][j]);
				}

			}
		}

		System.out.println(max);
	}

	static void DFS(int x, int y, int count, int sum) {

		if (count == 3) {
			max = Math.max(sum, max);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!inRange(nx, ny) || visited[nx][ny])
				continue;
			visited[nx][ny] = true;
			DFS(nx, ny, count + 1, map[nx][ny] + sum);
			visited[nx][ny] = false;
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
}
