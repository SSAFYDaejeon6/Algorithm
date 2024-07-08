package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//25876KB 284ms
public class BJ_S2_21736_헌내기는친구가필요해 {

	static int R, C;
	static char[][] graph;
	static boolean[][] visited;
	static int startR, startC;
	static int cnt;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	//BFS로 x이거나 경계 넘어가거나 하기 전까지 P만나면 cnt+1
	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(startR, startC));
		visited[startR][startC] = true;

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int r = node.r;
			int c = node.c;

			for(int d=0; d<4;d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
//				System.out.println(nr+" "+nc);

				if(nr < 0 || nr >=R || nc < 0 || nc >=C ||visited[nr][nc]||graph[nr][nc]=='X') continue;

				if(graph[nr][nc]=='P') cnt++;

				queue.offer(new Node(nr, nc));
				visited[nr][nc] = true;
			}
		}

	}

	static class Node {
		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}


	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());


		graph = new char[R][C];
		visited = new boolean[R][C];

		for(int r=0; r<R;r++) {
			String tmp = br.readLine();

			for(int c=0; c<C;c++) {
				graph[r][c] = tmp.charAt(c);
			}

		}

		A: for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				if(graph[r][c]=='I') {
					startR = r;
					startC = c;
					graph[r][c] = 'O';
					break A;
				}
			}
		}
//		System.out.println(startR +" "+ startC);
		
		BFS();
		System.out.println(cnt==0 ? "TT": cnt);

	}
}
