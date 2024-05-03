import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 	11,612KB | 84ms
 * 
 * 1. 일단 터뜨리기
 *  - BFS 이용
 *  - 문자
 *   각 Node에  LinkedList? 연결정보 저장
 *   현재 문자
 *   개수
 * 2. 떨어뜨리기
 *  - top 정보 저장
 * 3. 반복 (-> 더 이상 터뜨릴 것이 없을 때까지) 12*6 
 */
public class BOJ_N11559_PuyoPuyo {
	static int N = 12;
	static int M = 6;
	static class Node{
		int r, c;
		char ch;
		Node next;
		
		public Node(int r, int c, char ch, Node next) {
			super();
			this.r = r;
			this.c = c;
			this.ch = ch;
			this.next = next;
		}
	}
	static char[][] map;
	static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static void main(String[] args) throws IOException {

		map = new char[N][];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int count = 0;
		while(true) {
			boolean isStop = puyoBreak();
			if(isStop) break;
			count++;
			puyoDown();
			
		}
		System.out.println(count);
	}
	
	private static void puyoDown() {
		char[][] temp = new char[N][M];
		int[] top = new int[M];
		
		for (int i = 0; i < M; i++) {
			top[i] = N-1;
		}
		
		for (int i = N-1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != '.') temp[top[j]--][j] = map[i][j];
			}
		}
		
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < M; j++) {
				if(temp[i][j] == '\u0000') map[i][j] = '.';
				else map[i][j] = temp[i][j];
			}
		}
	}
	private static boolean puyoBreak() {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[N][M];
		boolean isStop = true;
		Node node;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int cnt = 0;
				if(map[i][j] != '.' && !isVisited[i][j]) {
					queue.clear();
					Node arr = new Node(i, j, map[i][j], null);
					queue.offer(arr);
					isVisited[i][j] = true;
					cnt++;
					while(!queue.isEmpty()) {
						node = queue.poll();
						for (int d = 0; d < 4; d++) {
							int nr = node.r + deltas[d][0];
							int nc = node.c + deltas[d][1];
							if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1 || isVisited[nr][nc]) continue;
							if(map[nr][nc] != node.ch) continue;
							
							isVisited[nr][nc] = true;
							arr = new Node(nr, nc, node.ch, arr);
							queue.offer(arr);
							cnt++; 
						}
					}
					
					if(cnt < 4) continue;
					for(Node temp = arr;temp!=null;temp=temp.next) {
						map[temp.r][temp.c]='.';
					}
					isStop = false;
				}
			}
		}
		
		return isStop;
		
	}

}
