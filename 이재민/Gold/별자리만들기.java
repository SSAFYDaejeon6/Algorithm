package algo0507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 최소 비용 => MST
 * 크루스칼 이용
 * 각 좌표만 주어지고 가중치(거리)는 안주어짐
 * 모든 별들은 별자리 위의 선을 통해 서로 직/간접적으로 이어져 있어야한다
 * => 모든 별들에 대한 가중치 구해주기 (2중for문)
 * 이후 크루스칼 이용해 최소값 출력
 * 12632KB | 100ms
 */

public class 별자리만들기 {
	static int N;
	static Loc[] l;
	static int[] p;
	static List<Node> list;
	static double res;

	static class Loc {
		int v;
		double x, y;

		public Loc(int v, double x, double y) {
			this.v = v;
			this.x = x;
			this.y = y;
		}
	}

	static class Node implements Comparable<Node> {
		int from, to;
		double weight;

		public Node(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.weight, o.weight);
		}

	}

	static void makeSet() {
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
	}

	static int findSet(int x) {
		if (p[x] == x)
			return x;
		return p[x] = findSet(p[x]);
	}

	static boolean union(int x, int y) {
		x = findSet(x);
		y = findSet(y);

		if (x == y)
			return false;

		p[x] = y;

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		l = new Loc[N];
		p = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());

			l[i] = new Loc(i, x, y);
		}

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double w = Math.sqrt(Math.pow(l[i].x - l[j].x, 2) + Math.pow(l[i].y - l[j].y, 2));
				list.add(new Node(i, j, w));
			}
		}
		
		Collections.sort(list);
		makeSet();
		int cnt = 0;
		for(Node node : list) {
			int from = node.from;
			int to = node.to;
			double weight = node.weight;
			if(!union(from, to)) continue;
			res += weight;
			if(++cnt == N-1) break;
		}
		System.out.printf("%.2f\n", res);
	}
}
