package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/** 20028kb 284ms
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
public class Main_S1_11403_경로찾기_최적화 {
	static int N;
	static int[][] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		//중간 지점 k를 경유하여 i와 j가 연결된다면 해당 구간을 1로 저장
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(graph[i][k]+graph[k][j] == 2) {
						graph[i][j] = 1;
					}
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
	}

}
