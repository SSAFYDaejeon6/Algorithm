package algoStudy;

import java.io.*;
import java.util.*;

/* 12252kb 96ms
 * [문제 해석]
	가수의 출연 순서 정학
	보조 PD의 수 M명에게 각자 담당한 가수의 출연 순서 정해오기
	이 순서들을 모아서 전체 가수의 순서 정하기
	
	[입력]
	1. 가수의 수 N, 보조 PD의 수 M
	2. 각 보조 PD가 담당한 가수의 수, 가수들의 순서
	
	1<=N, M<=1,000
	
	[출력]
	가수들의 출연 순서
	불가능하면 0 출력
	
	[문제 해결 프로세스]
	위상 정렬
 */
public class Main_G3_2623_음악프로그램 {
	static int N, M;
	static List<Integer>[] graph;
	static int[] parent, degree;
	static Queue<Integer> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		parent = new int[N+1];
		degree = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
			parent[i] = 1;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			for(int j=1; j<m; j++) {
				int y = Integer.parseInt(st.nextToken());
				graph[x].add(y);
				degree[y]++;
				x = y;
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(degree[i]==0) q.add(i);
		}
		
		while(!q.isEmpty()) {
			int n = q.poll();
			sb.append(n).append('\n');
			for(int node : graph[n]) {
				if(--degree[node] == 0) q.add(node);
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(degree[i]>0) {
				System.out.println(0);
				return;
			}
		}
		
		System.out.println(sb);
		
	}
	
}
