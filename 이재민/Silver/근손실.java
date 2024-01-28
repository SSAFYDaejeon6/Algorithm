import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 순열을 구하는 문제
 */

public class Main {

	static int[] arr;
	static int[] v;
	static boolean[] visited;
	static int res = 0;

	// 순열 dfs
	static void dfs(int n, int k, int cnt, int sum) {
		
		// n개중 n개를 뽑는 순열
		if (cnt == n) {
			res++;
			return;
		}

		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				// 현재 구한 순열에서 중량이 500이 넘지 않으면 재귀를 수행하지 않음
				if (sum + arr[i] - k >= 500) {
					v[cnt] = arr[i];
					visited[i] = true;
					dfs(n, k, cnt + 1, sum + arr[i] - k);
					visited[i] = false;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		arr = new int[n];
		v = new int[n];
		visited = new boolean[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dfs(n, k, 0, 500);

		System.out.println(res);
	}
}
