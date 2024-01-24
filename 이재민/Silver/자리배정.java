import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	// Pair를 객체로 받아서 좌표를 한번에 표현
	public static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// 범위가 벗어나거나 이미 방문한 곳인지 체크
	public static boolean check(int x, int y, int r, int c, int[][] map) {
		if (x < 0 || x >= r)
			return false;
		if (y < 0 || y >= c)
			return false;
		if (map[x][y] != 0)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());

		// 공연장 좌석의 크기보다 대기번호가 크면 공연을 볼 수 없음
		if (r * c < k) {
			System.out.println(0);
			return;
		}
		
		int[][] map = new int[r][c]; // 가로 세로 반대로 저장
		int resX = 0, resY = 0; // 결과값 저장

		int i = 0; // 공연장을 시계방향으로 돌기 위한 idx

		map[0][0] = 1; // 시작 지점을 1번 대기지역으로
		Queue<Pair> q = new LinkedList<>(); // bfs queue
		q.add(new Pair(0, 0));

		// queue start
		while (!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			q.remove();
			
			// 정답 저장
			if (map[x][y] == k) {
				resX = y;
				resY = x;
				break;
			}

			int nx = x + d[i % 4][0];
			int ny = y + d[i % 4][1];
			
			// d배열이 상 우 하 좌 순으로 되어 있음
			// 체크 하고 범위에서 벗어나면 i의 크기를 올려주고 %로 상 우 하 좌 순으로 계속 이동 
			if (!check(nx, ny, r, c, map)) {
				i++;
				nx = x + d[i % 4][0];
				ny = y + d[i % 4][1];
			}

			q.add(new Pair(nx, ny));
			map[nx][ny] = map[x][y] + 1;

		}

		System.out.println((resX + 1) + " " + (resY + 1));
	}
}
