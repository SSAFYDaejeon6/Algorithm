import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * bfs 탐색을 통해 현재 위치에서 조건에 가능한 곳을 탐색하고
 * 인구 이동을 하는 범위를 리스트에 저장
 * 이후 리스트에 저장된 위치를 토대로 인구 이동 실행
 * 293480KB | 504ms
 */
public class 인구이동 {

	static int N, L, R;
	static int[][] map;
	static int res, cnt;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int check;
	static List<Loc> list;
	
	static class Loc {
		int x, y;

		public Loc(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static boolean rangeCheck(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static int bfs(int x, int y) {
		Queue<Loc> q = new ArrayDeque<>();
		visited[x][y] = true;
		int sum = map[x][y];
		q.add(new Loc(x, y));

		while (!q.isEmpty()) {
			int r = q.peek().x;
			int c = q.poll().y;
			for (int i = 0; i < 4; i++) {
				int nx = r + dx[i];
				int ny = c + dy[i];

				if (!rangeCheck(nx, ny))
					continue;
				if (visited[nx][ny])
					continue;

				int dis = Math.abs(map[nx][ny] - map[r][c]);
				if (L <= dis && dis <= R) {
					visited[nx][ny] = true;
					sum += map[nx][ny];
					list.add(new Loc(nx, ny));
					q.add(new Loc(nx, ny));
				}
			}
		}

		return sum;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			boolean check = false;
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]) continue;
					list.clear();
					list.add(new Loc(i, j));
					int sum = bfs(i, j);
					if(sum != map[i][j]) {
						check = true;
						for(int k=0; k<list.size(); k++) {
							map[list.get(k).x][list.get(k).y] = sum / list.size(); 
						}
					}
				}
			}
			if(!check) break;
			res++;
		}
		
		System.out.println(res);

	}

}
