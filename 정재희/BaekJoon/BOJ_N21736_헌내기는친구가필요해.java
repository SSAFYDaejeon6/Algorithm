package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS 이용 시뮬레이션
 * 25,884KB | 220ms
 */
public class BOJ_N21736_헌내기는친구가필요해 {
	static class Node{
		int i;
		int j;
		
		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		char[][] map = new char[N][M];
		boolean[][] isVisited = new boolean[N][M];
		Queue<Node> queue = new ArrayDeque();
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'I') {
					queue.offer(new Node(i, j));
					isVisited[i][j] = true;
				}
			}
		}
		
		Node node;
		while(!queue.isEmpty()) {
			node = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int dr = node.i + deltas[d][0];
				int dc = node.j + deltas[d][1];
				
				if(dr < 0 || dr > N-1 || dc < 0 || dc > M-1) continue;
				if(isVisited[dr][dc] || map[dr][dc] == 'X') continue;
				
				isVisited[dr][dc] = true;
				if(map[dr][dc] == 'P') 	cnt++;
				queue.offer(new Node(dr, dc));				
			}
		}
		
		System.out.println(cnt == 0? "TT" : cnt);
		
	}

}
