package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  dfs 이용, 넓이 체크
 *  dfs 순회 후 개수 체크
 *  50,740KB | 284ms
 */
public class BOJ_N1926_그림 {
	static int n, m;
	static int max_square = 0, cur_square;
	static int[][] map;
	static int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		int cnt = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] == 0) continue;
				cur_square = 0;
				dfs(i, j);
				cnt++;
				max_square = Math.max(max_square, cur_square);
			}
		}
		
		System.out.println(cnt + "\n" + max_square);
	}
	
	static void dfs(int i, int j) {
		if(i < 0 || i > n-1 || j < 0 || j > m-1 || map[i][j] == 0)
			return;
		
		map[i][j] = 0;
		cur_square++;
		for (int d = 0; d < dir.length; d++) {
			dfs(i+dir[d][0], j + dir[d][1]);
		}
		
	}
}
