import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_N4386_별자리만들기 {
	/**
	 * 1. 간선 정보 계산
	 * 2. prim 알고리즘 적용
	 * 
	 *   12,204KB | 92ms
	 */
	static class Edge implements Comparable<Edge>{
		int to;
		float weight;
		public Edge(int to, float weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Float.compare(this.weight, o.weight);
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		float[][] nodes = new float[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			nodes[i][0] = Float.parseFloat(st.nextToken());
			nodes[i][1] = Float.parseFloat(st.nextToken());
		}
		
		float[][] weightInfo = new float[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				float weight = (float) Math.sqrt(Math.pow(nodes[i][0]-nodes[j][0], 2) + Math.pow(nodes[i][1]-nodes[j][1], 2)); 
				weightInfo[i][j] = weight;
				weightInfo[j][i] = weight;
			}
		}
		
		float[] minEdge = new float[n];
		Arrays.fill(minEdge, Float.MAX_VALUE);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, 0));
		boolean[] isVisited = new boolean[n];
		
		float result = 0;
		int count = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if(isVisited[e.to]) continue;
			
			result += e.weight;
			isVisited[e.to] = true;
			
			if(++count == n) break;
			
			for (int i = 0; i < n; i++) {
				if(!isVisited[i] && weightInfo[e.to][i] != 0 && weightInfo[e.to][i] < minEdge[i]) {
					minEdge[i] = weightInfo[e.to][i];
					pq.offer(new Edge(i, minEdge[i]));
				}
			}
		}
		
		System.out.printf("%.2f", result);
	}

}
