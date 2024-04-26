package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author SEOK BEOM LEE
 *68644kb	864ms
 */
public class BJ_G4_1504_특정한최단경로 {
	
	//max_value로 하면 오버플로 발새 위험
	static final int INF = 100_000_000;
	
	static int V, E;
	
	static List<Node>[] list;
	static int[] distance;
	
	static class Node implements Comparable<Node> {
		int next;
		int cost;
		Node(int next, int cost) {
			this.next = next;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}
	
//	static int result = 0;
	
	static int Dijstra(int start, int end) {
//		Arrays.fill(distance, Integer.MAX_VALUE);
		//시작지점에서 각 노드 사이의 최단 경로
		Arrays.fill(distance, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		distance[start] = 0;
		while (!pq.isEmpty()) {
			Node current = pq.poll();
			int idx = current.next;
			int cost = current.cost;
			//현재 노드와 연결된 곳들 보면서 기존 경로까지 비용보다 작으면 갱신 후 큐에 넣기
			for(int i=0; i<list[idx].size();i++) {
				Node next = list[idx].get(i);
				
				int nextIdx = next.next;
				int len = next.cost;
				if(len+cost>distance[nextIdx]) continue;
				distance[nextIdx] = len+cost;
				pq.offer(new Node(nextIdx, distance[nextIdx]));
			}
		}
//		result += distance[end];
		
		return distance[end];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		list = new List[V+1];
		distance = new int[V+1];

		
		for(int i=1; i<=V;i++) {
			list[i]  = new ArrayList<>();
		}
		
		for(int i=0; i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b,cost));
			list[b].add(new Node(a, cost));
			
		}
		st = new StringTokenizer(br.readLine());
		int first = Integer.parseInt(st.nextToken());
		int second = Integer.parseInt(st.nextToken());
		
		
		//1 -> first -> second -> end로 가는 경우
		int sum1 = 0;
		sum1 += Dijstra(1, first);
//		System.out.println(sum1);
		sum1 += Dijstra(first, second);
//		System.out.println(sum1);
		sum1 += Dijstra(second, V);
//		System.out.println(sum1);
		
		//1 -> second -> first -> end로 가는 경우
		int sum2 = 0;
		sum2 += Dijstra(1, second);
		sum2 += Dijstra(second, first);
		sum2 += Dijstra(first, V);
		
//		System.out.println(sum1+" "+sum2);
		
		if(sum1>= INF && sum2 >=INF) System.out.println(-1);
		else System.out.println(Math.min(sum1, sum2));
		
	}
}
