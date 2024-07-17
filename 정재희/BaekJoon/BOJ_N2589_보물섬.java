package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 217,628KB | 480ms
 */
public class BOJ_N2589_보물섬 {
	static char[][] map;
	static int r, c;
	static int maxDis;
	static Queue<int[]> queue = new ArrayDeque();
	static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		maxDis = 0;
		map = new char[r][c];
		for (int i = 0; i < r; i++) {
			String input_str = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = input_str.charAt(j);
			}
		}
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(map[i][j] == 'W') continue;
				bfs(i, j);
			}
		}
		
		System.out.println(maxDis);

	}
	
	static void bfs(int i, int j) {
		boolean[][] isVisited = new boolean[r][c];
		queue.clear();
		queue.add(new int[] {i, j, 0});
		isVisited[i][j] = true;
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			for (int d = 0; d < dir.length; d++) {
				int nr = tmp[0] + dir[d][0];
				int nc = tmp[1] + dir[d][1];
				
				if(nr < 0 || nr > r-1 || nc < 0 || nc > c-1 
						|| isVisited[nr][nc] || map[nr][nc] == 'W')
					continue;
				
				queue.offer(new int[] {nr, nc, tmp[2]+1});
				maxDis = Math.max(maxDis, tmp[2]+1);
				isVisited[nr][nc] = true;
			}
		}
		
	}

}
