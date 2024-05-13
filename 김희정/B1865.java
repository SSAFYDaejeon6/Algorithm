package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
 * [BOJ] 1865 웜홀
 	20060KB |	404ms	
 * 시간이 되돌아가 있는 경우 => 음의 사이클이 있는 경우 판단
 * 벨만포드 알고리즘
 * - 모든 정점에 대해 각 정점마다 벨만 포드 돌리기 => 시간 초과
 * - 모든 정점을 시작 정점으로 초기화 => 동시에 시작하기
 */
public class B1865 {

	static class Edge {
		int v;
		int w;
		int cost;

		public Edge(int v, int w, int cost) {
			super();
			this.v = v;
			this.w = w;
			this.cost = cost;
		}

	}

	static List<Edge> graph;
	static StringBuilder sb;
	static final int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		StringTokenizer st;
		A: while (TC-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();

			// 도로정보
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				graph.add(new Edge(v, w, cost));
				graph.add(new Edge(w, v, cost));

			}
			// 웜홀 정보
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				int cost = -1 * Integer.parseInt(st.nextToken());

				graph.add(new Edge(v, w, cost));

			}

			if (!bellmanFord(N, graph.size())) {
				continue A;

			}

			sb.append("NO").append('\n');
		}

		System.out.println(sb);
	}

	static boolean bellmanFord(int N, int M) {
		// 모든 정점에서 동시에 시작함 => INF로 초기화 x, 모든 정점 0으로 초기화
		int[] dist = new int[N + 1];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Edge edge = graph.get(j);

				if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
					dist[edge.w] = dist[edge.v] + edge.cost;
				}
			}
		}
		// 음수 가중치 확인
		for (int i = 0; i < M; i++) {
			Edge edge = graph.get(i);

			if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
				sb.append("YES").append('\n');
				return false;

			}
		}

		return true;
	}
}
