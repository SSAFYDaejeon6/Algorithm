package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/** 138092kb 820ms
 * 문제 해석
	방향 그래프 G
	모든 정점(i, j)에 대해 i에서 j로 가는 길이가 양수인 경로의 존재 유무
	
	입력
	정점의 개수 N ≤100
	그래프의 인접 행렬
	
	출력
	i에서 j로 가는 길이가 양수인 경로가 있으면 1, 없으면 0
 *
 */
public class Main_S1_11403_경로찾기_DFS {
	static int N;
	static int[][] graph, result;
	static boolean[][] b;
	static boolean check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		result = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				b = new boolean[N][N];
				check = false;
				result[i][j] = dfs(i, j);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
	}

	//DFS 탐색을 통해 i와 j로 가는 경로가 있으면 1, 없으면 0을 반환
	private static int dfs(int i, int j) {
		for(int k=0; k<N; k++) {
			if(graph[i][k] == 0 || b[i][k]) continue;
			if(k==j) check = true;
			if(check) return 1;
			
			b[i][k] = true;
			dfs(k, j);
		}
		return check ? 1 : 0;
	}
}
