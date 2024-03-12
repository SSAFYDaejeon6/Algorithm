import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * dfs를 이용해 사람들간의 관계를 추적
 * 26636KB | 146ms 
 */

public class 창용마을의무리 {
	
	static int T;
	static int N, M;
	static List<Integer> list[];
	static boolean[] visited;
	static int res;
	
	static void dfs(int x) {
		visited[x] = true;
		for(int nx : list[x]) {
			if(!visited[nx]) {
				dfs(nx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			res = 0;
			visited = new boolean[N+1];
			list = new List[N+1];
			
			for(int i=1; i<=N; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list[x].add(y);
				list[y].add(x);
			}
			
			for(int i=1; i<=N; i++) {
				if(!visited[i]) {
					dfs(i);
					res++;
				}
			}
			sb.append("#" + tc + " " + res).append('\n');
		}
		System.out.println(sb);
	}
}
