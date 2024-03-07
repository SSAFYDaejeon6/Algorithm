package test;

import java.io.*;
import java.util.*;


/**
 *23,204 kb, 132 ms
 */
public class SWEA_7465_창용마을무리의개수 {
	
	static int N;
	static int[] parent;
	
	//부모 찾기
	static int find(int x) {
		if(parent[x] != x) parent[x] = find(parent[x]);
		
		return parent[x];
	}
	
	//합치키
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a < b) parent[a] = b;
		else if(a > b) parent[b] = a;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			parent = new int[N+1];
			for(int i=1; i<N+1;i++) {
				parent[i] = i;
			}
			
			int m = Integer.parseInt(st.nextToken());
			
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			boolean[] isParent = new boolean[N+1];
			
			for(int i=1; i<=N;i++) {
				isParent[find(i)] = true;
			}
			int cnt = 0;
			for(int i=1; i<=N;i++) {
				if(isParent[i]) cnt++;
			}
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		
		System.out.println(sb);
				
	}
}