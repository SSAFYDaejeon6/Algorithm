package alg0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1260_dfsbfs {
	static int N, M, V;
	static boolean[][] map;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		map = new boolean[N+1][N+1];
		check = new boolean[N+1];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[a][b] = map[b][a] = true;
		}
		
		dfs(V);
		System.out.println();
		check = new boolean[N+1];
		bfs(V);
	}
	
	// 깊이 우선 탐색(재귀)
	static void dfs(int V) {
		check[V] = true;
		System.out.print(V+" ");
		
		if (V == map.length) {
			return;
		}
		for (int j=1; j<map.length; j++) {
			if (map[V][j] == true && check[j] == false) {
				dfs(j);
			}
		}
	}
	
	//너비우선탐색(큐)
	static void bfs(int V) {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(V);
		check[V] = true;
		System.out.print(V+" ");
		
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			for (int i=1; i<map.length; i++) {
				if (map[temp][i] == true && check[i] == false) {
					queue.add(i);
					check[i] = true;
					System.out.print(i + " ");
				}
			}
		}
	}
}
