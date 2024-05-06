package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/* 11540kb 80ms
 * [문제해석]
	- 필드에 여러 가지 색깔의 뿌요를 놓음
		중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어짐
	- 뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결 
		-> 연결된 같은 색 뿌요들이 한꺼번에 사라짐, 이때 1연쇄가 시작됨
	- 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 중력의 영향을 받아 차례대로 아래로 떨어짐
	- 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터짐
		터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어남
	- 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 함
		여러 그룹이 터지더라도 한 번의 연쇄가 추가됨
	
	상대방의 필드가 주어졌을 때 연쇄가 몇 번 연속으로 일어날지 계산
	
	[입력]
	12개의 줄에 필드의 정보, 각 줄에 6개의 문자
	'.' : 빈공간
	R : 빨강
	G : 초록
	B : 파랑
	P : 보라
	Y : 노랑
	
	[출력]
	몇 연쇄가 되는지
	하나도 터지지 않는다면 0을 출력
	
	[문제 해결 프로세스]
	1. BFS 탐색으로 4개 이상 연결된 구역 찾음
		-> 4개 이상이면 '.'으로 만들기
	2. (11, 0)부터 시작해서 != '.'을 만나면 아래가 '.'일 때까지 내림
	3. 다시 BFS 탐색
 */
public class Main_G4_11559_PuyoPuyo {
	static char[][] map = new char[12][6];
	static Queue<Node> q = new ArrayDeque<>();
	static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
	static Node[] res = new Node[12*6];
	
	static class Node{
		int r, c;
		char s;

		public Node(int r, int c, char s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", s=" + s + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<12; i++) {
			String str = br.readLine();
			for(int j=0; j<6; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		int result = 0;
		
		boolean check = true;
		
		while(check) {
			Queue<Node> queue = new ArrayDeque<>();
			boolean[][] visit = new boolean[12][6];
			check = false;
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(map[i][j] == '.' || visit[i][j]) continue;
					queue.add(new Node(i, j, map[i][j]));
					visit[i][j] = true;
					char s = map[i][j];
					int count = 0;
					res[count] = new Node(i, j, map[i][j]);
					while(!queue.isEmpty()) {
						Node node = queue.poll();
						for(int d=0; d<4; d++) {
							int nr = node.r+dr[d];
							int nc = node.c+dc[d];
							if(!range(nr, nc) || visit[nr][nc] || map[nr][nc] != s) continue;
							queue.add(new Node(nr, nc, s));
							count++;
							res[count] = new Node(nr, nc, map[nr][nc]);
							visit[nr][nc] = true;
						}
					}
					if(count>=3) {
						for(int k=0; k<=count; k++) {
							map[res[k].r][res[k].c] = '.';
							check = true;
						}
					}
				}
			}
			
			for(int i=11; i>=0; i--) {
				for(int j=5; j>=0; j--) {
					if(map[i][j] == '.') continue;
					down(i, j, map[i][j]);
				}
			}
			
			if(check) result++;
		}
		
		System.out.println(result);
	}

	private static void down(int r, int c, char s) {
		map[r][c] = '.';
		int nr = r;
		while(true) {
			nr++;
			if(nr>=12) {
				map[--nr][c] = s;
				break;
			}
			if(map[nr][c] != '.') {
				map[--nr][c] = s;
				break;
			}
		}
	}

	private static boolean range(int nr, int nc) {
		return nr>=0 && nr<12 && nc>=0 && nc<6;
	}

}
