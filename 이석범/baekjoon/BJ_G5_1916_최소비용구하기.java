package algo0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//51424kb 492ms
public class BJ_G5_1916_최소비용구하기 {

	//우선순위 큐에 넣어야하기 때문에 comparable을 상속받아 오름차순으로 정렬
	static class Node implements Comparable<Node> {
		int index;
		int cost;

		Node(int index, int cost) {
			this.index = index;
			this.cost = cost;

		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}



	}

	static int n, m;
	static int start, end;
	static int[] distance;

	static List<Node>[] list;

	
	static void dijstra(int start) {
		//우선순위 큐에 시작노드 저장하고 시작노드는 0의 비용으로 초기화
		PriorityQueue<Node> pq = new PriorityQueue<>();
		distance[start] = 0;
		pq.add(new Node(start, 0));

		while(!pq.isEmpty()) {
			
			//큐에서 빼고
			Node current = pq.poll();

			//뺀 노드의 인덱스와 거리 저장
			int index = current.index;
			int dist = current.cost;
			
			//만약 뺀 노드의 인덱스가 거리 배열에 저장된 거리보다 길면 최소비용이 아니므로 continue
			if(distance[index] < dist) continue;

			//지금 현재 노드와 연결된 노드들을 보면서 현재노드까지의 비용+연결된 노드의 비용이 더 적을 경우 갱신 후 우선순위 큐에 저장
			for (Node node : list[index]) {
				int cost = dist + node.cost;
				int next = node.index;
				if(distance[next] > cost) {
					distance[next] = cost;
					pq.offer(new Node(next, cost));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		//거리를 저장하기 위한 배열 초기는 max값으로 초기화
		distance = new int[n+1];
		Arrays.fill(distance, Integer.MAX_VALUE);

		
		list = new ArrayList[n+1];
		for(int i=1;i<n+1;i++) {
			list[i] = new ArrayList<>();
		}

		for(int i=0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			list[first].add(new Node(second, cost));
		}


		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dijstra(start);

		System.out.println(distance[end]);

	}
}
