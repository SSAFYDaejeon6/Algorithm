package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 12716kb 116ms
 * 크루스칼 알고리즘 
 */
public class Main_G3_4386_별자리만들기 {
	static int N;
	static Node[] node;
	static List<Edge> list = new ArrayList<>();
	static int[] parent;
	
	static class Node{
		int idx;
		double x, y;

		public Node(int idx, double x, double y) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		
		public double dist(Node n) {
			return Math.sqrt(Math.pow(this.x-n.x, 2)+Math.pow(this.y-n.y, 2));
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int start, end;
		double dist;
		
		public Edge(int start, int end, double dist) {
			super();
			this.start = start;
			this.end = end;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return (int) (this.dist - o.dist);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		node = new Node[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			node[i] = new Node(i, x, y);
		}
		
		//모든 별들의 거리 구하기
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				double dist = node[i].dist(node[j]);
				list.add(new Edge(i, j, dist));
			}
		}
		
		Collections.sort(list);
		
		parent = new int[N];
		for(int i=0; i<N; i++) {
			parent[i] = i;
		}
		
		kruskal();
	}

	private static void kruskal() {
		double res = 0;
		
		for(int i=0; i<list.size(); i++) {
			int start = list.get(i).start;
			int end = list.get(i).end;
			if(find(start) != find(end)) {
				boolean check = union(start, end);
				if(check) res += list.get(i).dist;
			}
		}
		
		System.out.println(res);
	}

	private static boolean union(int start, int end) {
		int ps = find(start);
		int pe = find(end);
		
		if(ps == pe) return false;
		if(ps>pe) parent[pe] = ps;
		else parent[ps] = pe;
		return true;
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}

}
