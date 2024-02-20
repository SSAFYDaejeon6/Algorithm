import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 모든 그래프를 탐색하면서 search라는 찾아야 하는 변수를 하나 선언
 * flag라는 boolean 배열도 하나 선언해서 dfs에 들어갈 때 마다 초기화 해주고
 * 만약 다음 노드가 찾아야 하는 노드랑 같으면 시작하는 x에서 search까지 갈 수 있는 것
 */

public class 경로찾기 {

	static int N, search;
	static List<Integer> list[];
	static boolean flag;
	static boolean[] visited;
	
	static void dfs(int x) {
		visited[x] = true;
		for(int nx : list[x]) {
			// 다음 노드가 찾아야하는 값이랑 같으면 갈 수 있는 것
			if(nx == search) flag = true;
			if(!visited[nx]) {
				dfs(nx);
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					list[i].add(j);
				}
			}
		}
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				flag = false;
				visited = new boolean[N+1];
				search = j;
				dfs(i);
				sb.append((flag ? 1 : 0) + " ");
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

}
