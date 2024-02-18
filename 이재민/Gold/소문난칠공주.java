import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 조합을 뽑고 그래프 탐색을 돌리는 문제
 * 여러가지 시도
 * BFS 보다는 DFS가 메모리도 적게 먹고 속도도 빨랐음 (자료구조 문제인듯)
 * 방문체크 할 때 2차원 배열보다는 1차원 배열에서 메모리와 속도에서 성능이 더 좋음
 */

public class 소문난칠공주 {
	static int N, res;
	static char[][] loc;
	static int start;
	static boolean[] picked;
	static boolean[] visited;
	static int linkCnt;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static boolean moveCheck(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static void dfs(int x, int y) {
		visited[x * N + y] = true;
		for (int i = 0; i < 4; i++) {
			// 7명이 서로 연결되어 있으면 더 이상 안돌려도 됨
			if(linkCnt==7) return;
			int nx = x + dx[i];
			int ny = y + dy[i];

			// picked는 현재 좌표가 뽑은 수 인지 판단
			// 그걸 응용해서 방문처리를 1차원 배열
			if (moveCheck(nx, ny) && !visited[nx * N + ny] && picked[nx * N + ny]) {
				linkCnt++;
				dfs(nx, ny);
			}
		}
	}

	static void combi(int cnt, int idx, int cntY) {
		// 가지 치기
		// 임도연파 >= 4명 볼 필요 없음
		// 첫 자리가 18보다 크면 7개를 뽑을 수 없음
		if (cntY >= 4 || (cnt == 0 && idx > 18))
			return;

		if (cnt == 7) {
			visited = new boolean[N*N];
			linkCnt = 1;
			dfs(start / 5, start % 5);
			res = linkCnt == 7 ? res + 1 : res;

			return;
		}

		for (int i = idx; i < N * N; i++) {
			// 그래프 탐색을 시작할 위치 정하기
			if (cnt == 0)
				start = i;

			picked[i] = true;
			combi(cnt + 1, i + 1, loc[i / 5][i % 5] == 'Y' ? cntY + 1 : cntY);
			picked[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = 5;

		loc = new char[N][];
		picked = new boolean[N * N];

		for (int i = 0; i < N; i++) {
			loc[i] = br.readLine().toCharArray();
		}
		combi(0, 0, 0);
		System.out.println(res);
	}

}