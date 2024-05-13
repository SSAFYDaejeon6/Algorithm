package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 음의 가중치 사이클 판단하는 문제
 * 음의 가중치를 이용하기 때문에 벨만포드 사용
 * 19508KB | 392ms
 */

public class 웜홀 {
	
	static List<Node> list;
	static int[] dist;
	static int N, M, W;
	
	static class Node{
		int from, to, weight;

		public Node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
	}

	static boolean isCycle() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<list.size(); j++) {
				Node node = list.get(j);
				
				if(dist[node.to] > dist[node.from] + node.weight) {
					dist[node.to] = dist[node.from] + node.weight;
					
					if(i == N-1) return true;
				}
				
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		list = new ArrayList<>();
		for(int tc=0; tc<t; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				list.add(new Node(from, to, weight));
				list.add(new Node(to, from, weight));
			}
			
			for(int i=0; i<W; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				list.add(new Node(from, to, -weight));
			}
			
			dist = new int[N+1];
			sb.append(isCycle() ? "YES" : "NO").append("\n");
			list.clear();
		}
		System.out.println(sb);
	}
}