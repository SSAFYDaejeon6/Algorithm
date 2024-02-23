package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/** 14972kb 112ms
 * 문제 해석
	RxC 격자판
	빈 칸: .
	폭탄: O
	
	폭탄 → 3초 후 폭발 → 폭탄 칸 파괴 후 빈칸, 인접한 네 칸 함께 파괴
	인접 칸 폭탄이 있는 경우 → 폭발 없이 파괴
	
	봄버맨: 폭탄 면역력, 모든 칸 이동 가능
	<이동 규칙>
	- 가장 처음 봄버맨은 일부 칸에 폭탄 설치 → 설치 시간 모두 동일
	- 다음 1초동안 정적
	- 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄 설치 → 모든 칸 폭탄O
	- 1초가 지난후 3초 전에 설치된 폭탄이 모두 폭발
	- 3과 4 반복
	
	폭탄 설치 초기 상태 → N초 후 격자판 상태
	
	[입력]
	1. R, C, N ≤ 200
	2. 격자판 초기 상태
	
	[출력]
	N초 후 격자판 상태
	
	[최적화에 대한 고민]
	1. 폭탄을 터뜨릴 때마다 상태가 바뀌는 게 1, 3, 5 ... 번 터뜨릴 때와 2, 4, 6... 번 터뜨릴 때의 상태가 같음
	 	1) N%4 == 1일 때, 폭탄을 두 번 터뜨릴 때의 상태와 같음
		2) N%4 == 3일 때, 폭탄을 한 번 터뜨릴 때의 상태와 같음
	2. 고려해야 할 점. N==1일 때와 N%4 == 1 일 때의 상태는 같을 수도 있고 다를 수도 있음
 *
 */
public class Main_S1_16918_봄버맨_최적화 {
	static class Node{
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int R, C, N;
	static char[][] map, temp;
	static Queue<Node> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		temp = new char[R][C];
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'O') q.add(new Node(i, j));
			}
		}
		
		if(N%2==0) {
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					sb.append('O');
				}
				sb.append('\n');
			}
		} else if(N==1){
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
		}else if(N%4==3) {
			boom(1);
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					sb.append(temp[i][j]);
				}
				sb.append('\n');
			}
		}else {
			boom(2);
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					sb.append(temp[i][j]);
				}
				sb.append('\n');
			}
		}
		
		System.out.println(sb);
	}
	
	private static void boom(int n) {
		int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
		for(int i=0; i<n; i++) {
			for(int r=0; r<R; r++) Arrays.fill(temp[r], 'O');
			while(!q.isEmpty()) {
				Node node = q.poll();
				temp[node.r][node.c] = '.';
				for(int j=0; j<4; j++) {
					if(!range(node.r+dr[j], node.c+dc[j])) continue;
					temp[node.r+dr[j]][node.c+dc[j]] = '.';
				}
			}
			
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					if(temp[r][c] == 'O') q.add(new Node(r, c));
				}
			}
		}
	}

	private static boolean range(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}

}
