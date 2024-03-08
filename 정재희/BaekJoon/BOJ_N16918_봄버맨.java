package BaekJoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 시간: 220ms | 메모리 : 60,188KB
 */
public class BOJ_N16918_봄버맨 {
	static int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	static Queue<int[]> queue = new ArrayDeque<>();
	static int N;
	static int R, C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int[][] bomb = new int[R][C];
		
		/* 입력 시, 초기 단계 같이 수행 */
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				if(str.charAt(j) == 'O') bomb[i][j]+=2;
			}
		}
		
		/* 남은 초 만큼 폭탄 놓기와 제거 반복 */
		while(--N > 0) {
			putBomb(bomb);
			
			if(queue.size() > 0) {
				removeBomb(bomb);
			}
		}
		
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(bomb[i][j] == 0? '.' : 'O');
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	
	private static void putBomb(int[][] bomb) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 폭탄 초 증가 -> 제거될 대상이면 큐에 넣기
				if(++bomb[i][j] == 4) queue.offer(new int[] {i, j});
			}
		}
	}
	
	private static void removeBomb(int[][] bomb) {
		int []pos;
		/* 큐에 있는 것 + 4방 폭탄 제거 */
		while(!queue.isEmpty()) {
			pos = queue.poll();
			bomb[pos[0]][pos[1]] = 0;
			for (int d = 0; d < 4; d++) {
				int nr = pos[0] + deltas[d][0];
				int nc = pos[1] + deltas[d][1];
				
				if(nr >= 0 && nr < R && nc >=0 && nc < C) bomb[nr][nc] = 0;
			}
		}
		
	}
}
