import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 22648KB | 188ms
 */

public class 마법사상어와비바라기 {

	static int N, M;
	static int[][] a;
	static List<Pair> list;
	static boolean[][] cloud; // 현재 구름이 있는 지역은 구름이 생기지 않게 하기 위한 배열

	// 대각선 판별
	static int[] diagX = { -1, 1, 1, -1 };
	static int[] diagY = { 1, 1, -1, -1 };
	// 8방향
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };

	static boolean rangeCheck(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		a = new int[N][N];
		list = new ArrayList<>();
		cloud = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		list.add(new Pair(N - 1, 0));
		list.add(new Pair(N - 1, 1));
		list.add(new Pair(N - 2, 0));
		list.add(new Pair(N - 2, 1));

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			// 이동을 전부 다 하고 4방향 체크를 해야함
			// 이동
			for (int j = 0; j < list.size(); j++) {
				int r = list.get(j).r;
				int c = list.get(j).c;
				r = (r + N + ((s % N) * dx[d - 1])) % N;
				c = (c + N + ((s % N) * dy[d - 1])) % N;
				list.get(j).r = r;
				list.get(j).c = c;
				a[r][c]++;

				cloud[r][c] = true;
			}

			// 4방향 체크
			for (int j = 0; j < list.size(); j++) {
				int r = list.get(j).r;
				int c = list.get(j).c;
				for (int k = 0; k < 4; k++) {
					int nr = r + diagX[k];
					int nc = c + diagY[k];
					if (rangeCheck(nr, nc) && a[nr][nc] != 0) {
						a[r][c]++;
					}
				}
			}
			// 새로운 구름을 담기 위해 리스트 초기화
			list.clear();
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (a[j][k] >= 2 && !cloud[j][k]) {
						list.add(new Pair(j, k));
						a[j][k] -= 2;
					}
					// 다음에는 다시 구름이 생성될 수 있음
					if (cloud[j][k])
						cloud[j][k] = false;
				}
			}
		}
			int sum = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sum += a[i][j];
				}
			}
			System.out.println(sum);
	}
}