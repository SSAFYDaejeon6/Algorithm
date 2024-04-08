import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 	22632KB	|	300ms
// 풀이 : 우선순위 큐로 다익스트라 구현
public class B13549 {
	
	static int N;
	static int K;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Dijkstra(N);
		System.out.println(answer);
	}
	
	static void Dijkstra(int n) {
		// 숫자, 시간
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[1] - o2[1]);
		pq.add(new int[] {n,0});
		boolean[] visited = new boolean[100001];
		
		answer = 0;
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			answer = curr[1];
			
			if(curr[0] == K) {
				break;
			}
			
			visited[curr[0]] = true;
			if(curr[0] * 2 <= 100000 && !visited[curr[0] * 2]) {
				pq.offer(new int[] {curr[0]*2, answer});
			}
			
			if(curr[0] + 1 <= 100000 && !visited[curr[0]+1]) {
				pq.offer(new int[] {curr[0]+1, answer+1});
			}
			
			if(curr[0] -1 >= 0  && !visited[curr[0]-1]) {
				pq.offer(new int[] {curr[0]-1, answer+1});
			}
			
		}
	
	}
	
}
