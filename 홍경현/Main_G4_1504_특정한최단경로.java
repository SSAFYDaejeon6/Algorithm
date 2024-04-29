package algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*67272kb	672ms
 * [문제 해석]
	방향성 없는 그래프
	1번 정점 -> N번 정점으로 최단 거리로 이동
	두 가지 조건을 만족하면서 이동하는 특정한 최단경로
	임의로 주어진 두 정점을 반드시 통과해야 함
	한번 이동했던 정점 및 간선도 다시 이동 가능
	
	[입력]
	1. 2<=N<=800, 0<=E<=200,000
	2. a, b, c : a 정점에서 b번 정점까지의 양방향 길이가 존재하며, 그 거리가 1<=c<=1000다
	3. 임의의 두 정점 u, v
	
	[출력]
	두 개의 정점을 지나는 최단 경로의 길이
	없으면 -1 출력
	
	[문제 해결 프로세스]
	1. 1->v1->v2->N
	2. 1->v2->v1->N
	두 경로 중 최소 거리
 */
public class Main_G4_1504_특정한최단경로 {

	static int N, E;
	static List<Node>[] list;
	static int INF = Integer.MAX_VALUE;
	
	static class Node implements Comparable<Node>{
		int idx;
		int dist;
		
		public Node(int idx, int dist) {
			super();
			this.idx = idx;
			this.dist = dist;
		}
		
		@Override
		public String toString() {
			return "Node [idx=" + idx + ", dist=" + dist + "]";
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		if(E==0) {
			System.out.println(-1);
			return;
		}
		
		long[] dist1 = new long[N+1];
		long[] dist2 = new long[N+1];
		list = new List[N+1];
		
		for(int i=1; i<=N; i++) {
			dist1[i] = INF;
			dist2[i] = INF;
			list[i] = new ArrayList<>();
		}
		
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b, c));
			list[b].add(new Node(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		//다익스트라
		dijkstra(v1, dist1);
		dijkstra(v2, dist2);
		
		long les = Math.min(dist1[1]+dist2[N]+dist1[v2], dist1[N]+dist2[1]+dist1[v2]);
//		System.out.println(Arrays.toString(dist1));
//		System.out.println(Arrays.toString(dist2));
		System.out.println(les>=INF?-1:les);
	}

	private static void dijkstra(int start, long[] dist) {
		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			for(Node n : list[node.idx]) {
				if(dist[n.idx] > dist[node.idx]+n.dist) {
					dist[n.idx] = dist[node.idx]+n.dist;
					pq.offer(n);
				}
			}
		}
	}

}
