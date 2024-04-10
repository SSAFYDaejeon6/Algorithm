import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제에서 주어진대로 구현
 * 처음 블록의 최대 높이와 최소 높이를 구함
 * 블록을 제거하는 것 보다 놓는게 시간이 덜 걸리므로 최대값부터 시작해서 걸리는 시간 구하기
 * 최소시간을 구하면 됨
 * 34524KB | 592ms
 */

public class 마인크래프트 {

	static int N, M, B;
	static int[][] map;
	static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	static int time = Integer.MAX_VALUE, height;

	static void block(int h) {
		int t = 0, item = B;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 현재 time(최소시간을 담고 있는 변수)보다 커질 경우 탐색할 필요가 없음
				if (t > time)
					return;
				if (map[i][j] == h)
					continue;
				int s = Math.abs(map[i][j] - h);

				if (map[i][j] > h) {
					t += 2 * s;
					item += s;
				} else if (map[i][j] < h) {
					t += s;
					item -= s;
				}
			}
		}
		if (item >= 0 && time > t) {
			time = t;
			height = h;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
			}
		}
		for (int i = max; i >= min; i--) {
			block(i);
		}

		System.out.println(time + " " + height);

	}

}
