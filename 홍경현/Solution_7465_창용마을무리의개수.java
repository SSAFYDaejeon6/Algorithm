package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 23,788 kb 133 ms
 * 문제해석
	N명의 사람
	1번~N번 사람까지 번호
	두 사람이 서로 아는 관계이거나 몇 사람을 거쳐서 알 수 있는 관계 → 하나의 무리
	몇 개의 무리가 존재하는지 계산
	
	[입력]
	1. T
	2. N M
	3. 서로 알고 있는 두 사람의 번호
	
	[출력]
	#t 무리의 개수
 */
public class Solution_7465_창용마을무리의개수 {
	static int N, M, result;
	static List<Integer> list[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			list = new List[N+1];
			result = 0;
			
			for(int i=1; i<=N; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				list[b].add(a);
			}
			
			bfs();
			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	//bfs 탐색
	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean visit[] = new boolean[N+1];
		
		while(!check(visit)) {
			for(int i=1; i<=N; i++) {
				if(visit[i]) continue;
				q.add(i);
				visit[i] = true;
				break;
			}
			
			while(!q.isEmpty()) {
				int n = q.poll();
				for(int node : list[n]) {
					if(visit[node]) continue;
					q.add(node);
					visit[node] = true;
				}
			}
			result++;
		}
	}

	//모든 요소를 방문했는지 체크
	private static boolean check(boolean[] visit) {
		for(int i=1; i<=N; i++) {
			if(!visit[i]) return false;
		}
		return true;
	}

}
