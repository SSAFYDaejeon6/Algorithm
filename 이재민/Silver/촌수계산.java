import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 시작 노드부터 인접 노드를 현재 노드 + 1을 해서 몇촌인지 구해주면 됨
 * BFS 사용
 * 입력은 List의 배열 즉, 2차원 배열 사용
 * 11652KB | 76ms
 */

public class 촌수계산 {

	static int n, start, end , m; // 사람 수, 구해야 하는 사람들의 촌수, 관계의 개수
	static List<Integer> list[];
	static int visited[]; // 방문 체크 및 촌수 구하기

	
	public static int bfs(int v) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(v);
		visited[v] = 1; // 시작 노드의 값이 0이면 방문체크 하기가 어려움
		int res = -1;
		while(!q.isEmpty()) {
			int x = q.peek();
			q.poll();
			
			// 1로 시작을 했기 때문에 -1해서 값을 넘겨줌
			if(x == end) {
				res = visited[x]-1;
				break;
			}
			
			for(int i=0; i<list[x].size(); i++) {
				int nx = list[x].get(i);
				if(visited[nx] == 0) {
					visited[nx] = visited[x] + 1; // nx는 x와 + 1촌 관계라는 듯
					q.add(nx);
				}
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
		
			list[x].add(y);
			list[y].add(x);
		}
		
		visited = new int[n+1];
		System.out.println(bfs(start));
	}

}