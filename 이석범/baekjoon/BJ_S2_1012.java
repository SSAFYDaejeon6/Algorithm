package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_1012 {
	
	static int[][] graph;
	static int count;
	
	static int n, m;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static void BFS(int x, int y) {
		if(graph[x][y] == 1) graph[x][y] = 0;
		else return;
		for(int i=0; i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx>=0 && nx < n && ny >=0 && ny < m) {
				BFS(nx, ny);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new  InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		for(int i=0; i< testCase;i++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			graph = new int[n][m];
			for(int j=0; j< k;j++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				graph[x][y] = 1;
			}
			
			for(int x=0; x<n;x++) {
				for(int y=0; y<m;y++) {
					if(graph[x][y] == 0) continue;
					else {
						count++;
						BFS(x, y);
					}
				}
			}
			
			System.out.println(count);
			count = 0;
		}
		

		
	}
}
