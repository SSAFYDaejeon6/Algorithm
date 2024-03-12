import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

/*
 * 벽부수고 이동하기 처럼 검을 먹은것과 안먹은것으로 3차원 배열 이용
 * 레벨별 bfs를 활용하여 탐색
 * 14492KB | 148ms
 */

public class 공주님을구해라 {

	static int N, M, T;
	static int[][] map;
	static boolean visited[][][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static class Hero {
		int x, y;
		boolean gramr;

		public Hero(int x, int y, boolean gramr) {
			this.x = x;
			this.y = y;
			this.gramr = gramr;
		}

	}

	static boolean rangeCheck(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	static boolean bfs() {
		Queue<Hero> q = new ArrayDeque<>();
		
		// 밑에 두 코드는 용사가 시작하는 0, 0 자리에 바로 그람이 있는
		// 조건을 실험 0, 0에는 그람이 없는 것 같음
		q.add(new Hero(0, 0, map[0][0] == 2 ? true : false));
		visited[0][0][0] = true;
		int cnt = 0;
		
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Hero hero = q.poll();
				if(hero.x == N-1 && hero.y == M-1) {
					System.out.println(cnt);
					System.exit(0);
				}

				for (int d = 0; d < 4; d++) {
					int nx = hero.x + dx[d];
					int ny = hero.y + dy[d];
					if (!rangeCheck(nx, ny))
						continue;

					// 그람을 소유하고 이동할 때
					// 맵이 어떻든 상관없이 전부 이동 가능
					// 모든 벽을 부실 수 있기 때문
					if (hero.gramr) {
						if (visited[nx][ny][1])
							continue;
						visited[nx][ny][1] = true;
						q.add(new Hero(nx, ny, hero.gramr));
					} 
					// 그람을 소유하지 않을 때
					else {
						// 다음 맵이 그람이 있는 맵이라면
						// 문제 조건에서 바로 사용할 수 있기 때문에 바로 소유한 것으로 판단
						if (map[nx][ny] == 2) {
							if(visited[nx][ny][1]) continue;
							visited[nx][ny][1] = true;
							q.add(new Hero(nx, ny, true));
						}
						// 벽도 아니고 그람도 아니면
						else if(map[nx][ny] == 0) {
							if(visited[nx][ny][0]) continue;
							visited[nx][ny][0] = true;
							q.add(new Hero(nx, ny, hero.gramr));
						}
					}
				}
			}
			cnt++;
			if(cnt > T) {
				return false;
			}
		}
		// 시간은 안넘지만 이동을 못할 때
		// visited로 이동한 곳을 관리하기 때문에
		// 시간이 T는 넘지 않지만 bfs가 끝날 수 있음
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		System.out.println(!bfs() ? "Fail" : 0);

	}

}
