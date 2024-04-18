package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 15,980KB | 96ms
 * 
 * 위상 정렬 이용
 */
public class BOJ_N2623_음악프로그램 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] parents = new int[N+1];
		int[][] adjList = new int[N+1][N+1];
		int[] idx = new int[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int preVal = Integer.parseInt(st.nextToken());
			for (int j = 1; j < cnt; j++) {
				int val = Integer.parseInt(st.nextToken());
				adjList[preVal][idx[preVal]++] = val;
				parents[val]++;
				preVal = val;
			}
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if(parents[i] == 0) queue.offer(i);
		}
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while(!queue.isEmpty()) {
			int val = queue.poll();
			sb.append(val).append("\n");
			cnt++;
			for (int i = 0; i < idx[val]; i++) {
				int nx = adjList[val][i];
				if(--parents[nx] == 0)
					queue.offer(nx);
			}
		}
		if(cnt < N) System.out.println(0);  // 싸이클 발생
		else System.out.println(sb);
	}

}
