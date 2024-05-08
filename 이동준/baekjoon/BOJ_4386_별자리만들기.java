/**BOJ_4386_별자리만들기 12728KB 116ms
## [스터디문제](https://www.acmicpc.net/problem/4386)
### Facts
가중치 만드는거 연산횟수 대략 1만번 해야함
### IDEA
Kruskal 알고리즘을 쓰면 된다.
### Process
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_4386_별자리만들기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		MyNode[] nodes = new MyNode[N];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[i] = new MyNode(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		//입력 끝
		List<Edge> edges = new ArrayList<>(N * N);
		for(int fc = 0 ; fc < N; fc++) {
			for(int lc = fc + 1; lc < N; lc++) {
				edges.add(new Edge(nodes[fc], nodes[lc]));
			}
		}
		Collections.sort(edges);
		for(Edge edge : edges) {
			edge.from.merge(edge.to, edge.length);
			if(edge.from.findSet().count == N) {
				System.out.println(edge.from.parent.totalLength);
				System.exit(0);
			}
		}
	}
	
	private static class MyNode {
		double x, y, totalLength;
		int rank, count;
		MyNode parent;
		MyNode(double x, double y){
			this.x = x;
			this.y = y;
			this.parent = this;
			count = 1;
		}
		
		MyNode findSet() {
			if(this.parent.equals(this)) return this;
			return this.parent = this.parent.findSet();
		}
		
		boolean merge(MyNode b, double weight) {
			MyNode AP = findSet();
			MyNode BP = b.findSet();
			if(AP.equals(BP)) return false;
			if(AP.rank >= BP.rank) {
				BP.parent = AP;
				AP.totalLength += BP.totalLength + weight;
				AP.count += BP.count;
				AP.rank++;
			}else {
				AP.parent = BP;
				BP.totalLength += AP.totalLength + weight;
				BP.count += AP.count;
				BP.rank++;
			}
			return true;
		}
	}
	
	private static class Edge implements Comparable<Edge>{
		MyNode from, to;
		double length;
		Edge(MyNode from, MyNode to){
			this.from = from;
			this.to = to;
			this.length = Math.sqrt(Math.pow(from.x - to.x, 2) + Math.pow(from.y - to.y, 2));

		}
		public int compareTo(Edge o) {
			return Double.compare(this.length, o.length);
		}
	}
}
