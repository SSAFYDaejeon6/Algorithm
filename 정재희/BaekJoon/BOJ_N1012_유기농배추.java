package BaekJoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_N1012_유기농배추 {
	static int M;
	static int N;
	static int[][] bat;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(reader.readLine());
		for(int test_case=1;test_case<=T;test_case++) {
			StringTokenizer st = new StringTokenizer(reader.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int cnt = 0;
			bat = new int[M][N];

			
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(reader.readLine());
				bat[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=1;
			}
			
			//DFS 이용 
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if(bat[i][j] == 1) {  // 1이 있는 경우, 탐색 수행
						getBugs(i, j);
						cnt++;   // 주변에 1이 더이상 없는 경우, 반환됨 -> 벌레 마리수 증가
					}
				}
			}
			
			System.out.println(cnt);
		}
		
	}
	private static void getBugs(int x, int y) {
		if(x < 0 || x > M-1 || y < 0 || y > N-1 || bat[x][y] == 0) return;
		bat[x][y] = 0;
		for(int d=0;d<dr.length;d++) {
			int nr = x + dr[d];
			int nc = y + dc[d];
			getBugs(nr, nc);
		}
	}

}
