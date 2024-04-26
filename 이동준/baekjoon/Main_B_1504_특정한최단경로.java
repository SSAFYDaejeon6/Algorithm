/**Main_B_1504_특정한최단경로 82176KB 704ms
 * 	facts
 * 		무방향 그래프
 * 		1->N 최단거리
 * 		정점 800이하
 * 		edge 20만 이하
 * 		1 <= weight <= 1000
 * 		두 정점 사이 edge 유일
 * 		필수경유정점 a,b 두 개
 * Idea
 * 		a-b최단경로
 * 		시작점-a + ab + b-끝 vs
 * 		시작점-b + ab + a-끝 비교
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_1504_특정한최단경로 {
	static int N, E;
	static List<int[]>[] adjList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adjList = new List[N];
		for(int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<int[]>();
		}
		for(int l = 0; l < E; l++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new int[] {to, weight});
			adjList[to].add(new int[] {from, weight});
		}
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken()) - 1;
		int B = Integer.parseInt(st.nextToken()) - 1;

		//입력 끝
		int start = 0;
		int end = N - 1;
		Map<Integer, Long> fromStart = dijk(start, new ArrayList<Integer>(Arrays.asList(A, B)));
		Map<Integer, Long> fromEnd = dijk(end, new ArrayList<Integer>(Arrays.asList(A, B)));
		
		long AB = dijk(A, new ArrayList<Integer>(Arrays.asList(B))).get(B);
		long BA = AB;
		long SA = fromStart.get(A);
		long SB = fromStart.get(B);
		long AE = fromEnd.get(A);// AE = EA because directionless graph
		long BE = fromEnd.get(B);
		long result = Math.min(SA + AB + BE, SB + BA + AE);
		if(result >= (long) Integer.MAX_VALUE) { //if any of distances consisting of path == Integer.Max Value, the path will be considered as invalid
			System.out.println(-1); //final result(no possible path)
		}else {
			System.out.println(result);
		}
	}
	
	static Map<Integer, Long> dijk(int start, List<Integer> ends) {
		Collections.sort(ends);
		Map<Integer, Long> result = new HashMap<>();
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[1], b[1]));
		boolean[] visited = new boolean[N];
		pq.offer(new int[] {start, 0});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			if(visited[cur[0]]) continue;
			int targetIdx;			
			if((targetIdx = Collections.binarySearch(ends, cur[0])) >= 0) {
				ends.remove(targetIdx);
				result.put(cur[0], (long) cur[1]);
				if(ends.size() == 0) return result;
			}			
			visited[cur[0]] = true;
			for(int[] edge : adjList[cur[0]]) {
				if(visited[edge[0]]) continue;
				pq.offer(new int[] {edge[0], edge[1] + cur[1]});
			}
		}
		for(int unresolved : ends) {
			result.put(unresolved, (long) Integer.MAX_VALUE);//unreachable
		}
		return result;
	}
}
