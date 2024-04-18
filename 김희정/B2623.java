import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// [BOJ] 2623 음악프로그램
//	13508KB |	108ms
// 위상정렬 수행
public class B2623 {
	
	static int N;
	static int M;
	
	static int[] degrees;
	static List<Integer>[] adjList;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		degrees = new int[N+1];
		adjList = new List[N+1];
		for(int i = 1; i <= N; i++) adjList[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());

			for(int j = 1; j < cnt; j++) {
				int to = Integer.parseInt(st.nextToken());
				
				adjList[from].add(to);
				degrees[to]++;
				from = to;
			}
		}
		BFS();
		System.out.println(sb);
		
	}
	
	static void BFS() {
		Queue<Integer> q = new ArrayDeque<>();
		
		//1. 진입 차수가 0인 가수 모두 큐에 넣기
		for(int i = 1; i <= N; i++) {
			if(degrees[i] == 0) q.add(i);
		}
		int cnt = 0;
		while(!q.isEmpty()) {
			int from = q.poll();
			cnt++;
			sb.append(from).append('\n');
			
			//from이 선수인 가수들의 진입차수 -1 줄임
			for(int to : adjList[from]) {
				//진입차수 0이 됐다는 것은 순서에 들 수 있다는 의미로 큐에 넣어줌
				if(--degrees[to] == 0) q.offer(to);
			}
		}
		
		//싸이클 발생
		if(cnt < N) {
			System.out.println("0");
			System.exit(1);
		}
	}
}
