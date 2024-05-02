package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * @author SSAFY
 *12392kb 84ms
 */
public class BJ_G4_11559_PuyoPuyo {
	static final int R = 12, C = 6;

	static char[][] graph;

	static class Node {
		int r;
		int c;
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	//게임 시작
	static int game() {
		boolean flag = true;
		int stage = 0;
		//
		while(flag) {
			//시작시 false로 초기화
			flag = false;
			for(int r=0; r<R;r++) {
				for(int c=0; c<C;c++) {
					if(graph[r][c]=='.') continue;
					else {
						//한번이라도 터질경우 true
						if(BFS(r, c, graph[r][c])) flag = true;
					}
				}
			}
			//			print();
			//반응이 되었으므로 stage+1, down함수
			if(flag) {		
				stage++;
				down();
			}
		}

		return stage;
	}

	//반응되고 나서 밑으로 내려가는 함수
	static void down() {
		for(int c=0; c<C;c++) {
			//deque로 .이 아닌 것들만 넣고 나머지는 .으로 채우기
			Deque<Character> deque = new ArrayDeque<>();
			for(int r=0; r<R;r++) {
				if(graph[r][c] != '.') deque.offerLast(graph[r][c]);
			}
			for(int r=R-1; r>=0; r--) {
				if(deque.size()>0) {
					graph[r][c] = deque.pollLast();
				}
				else graph[r][c] = '.';
			}
		}
	}

	static boolean BFS(int r, int c, char ch) {
		Queue<Node> queue = new ArrayDeque<>();
		List<Node> list = new ArrayList<>();
		boolean[][] visited = new boolean[R][C];
		queue.offer(new Node(r, c));
		list.add(new Node(r, c));
		visited[r][c] = true;

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int curR = node.r;
			int curC = node.c;
			for(int d=0; d<4;d++) {
				int nr = curR + dr[d];
				int nc = curC + dc[d];

				if(nr < 0 || nr >= R || nc < 0 || nc >= C ||visited[nr][nc]) continue;

				if(graph[nr][nc]==ch) {
					queue.offer(new Node(nr, nc));
					list.add(new Node(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
		//4개이상일 때만 .으로 바꾸고 true값 반환
		if(list.size()>=4) {
			for(Node node:list) {
				graph[node.r][node.c] = '.';
			}
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		graph = new char[R][C];		

		for(int r=0; r<R;r++) {
			String tmp = br.readLine();
			for(int c=0; c<C;c++) {
				graph[r][c] = tmp.charAt(c);
			}
		}
//		print();

		int stage = game();
		System.out.println(stage);
//		print();
	}

	static void print() {
		for(char[] r:graph) {
			for(char c: r) {
				System.out.print(c+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
