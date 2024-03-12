import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.util.*;

// [BOJ] 17836 공주님을 구해라!
// 	14592KB	|	136ms
/*
 * 0 : 빈 공간
 * 1 : 마법의 벽
 * 2 : 그람
 */
public class B17836 {
	static int N;
	static int M;
	static int T;

	static int[][] map;
	static int answer = Integer.MAX_VALUE;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		BFS();
		if (answer == Integer.MAX_VALUE) {
			System.out.println("Fail");
		} else {
			System.out.println(answer);
		}
	}

	static void BFS() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][2];	//0 : 검이 없을 때, 1 : 검이 있을 떄
		q.offer(new int[] { 0, 0, 0 });
		visited[0][0][0] = true;
		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int[] curr = q.poll();
				if (curr[0] == N - 1 && curr[1] == M - 1) {
					if (time <= T) {
						answer = Math.min(answer, time);
					}
					return;
				}
				for (int i = 0; i < 4; i++) {
					int nx = curr[0] + dx[i];
					int ny = curr[1] + dy[i];
					int knife = curr[2];
					if (!inRange(nx, ny))
						continue;
					if (visited[nx][ny][knife])
						continue;
					if(knife == 0) {	//검을 아직 뽑지 못했으면
						if(map[nx][ny] != 1) {
							visited[nx][ny][0] = true;
							q.offer(new int[] { nx, ny, 0 });
						}
						if(map[nx][ny] == 2) {
							visited[nx][ny][1] = true;
							q.offer(new int[] { nx, ny, 1 });
						}
			
					}else {
						//검을 뽑은 후라면 다 통과
						q.offer(new int[] { nx, ny, 1 });
						visited[nx][ny][1] = true;
					}
					
				}

			}
			time++;

		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
}
