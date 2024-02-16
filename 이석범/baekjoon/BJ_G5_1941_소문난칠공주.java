package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 5x5를 입력
 * 조합으로 25C7을 할 경우 480,700
 * 각 경우에대해 /5 %5로하여 계산 1부터 시작하기 때문에 1을 빼고 해야함
 * y, s 카운트 세기
 * s가 적어도 4명이상 존재
 * 그리고 가로세로가 연결되어있어야함
 * 최대 2초
 * 187084kb 280ms
 */
public class BJ_G5_1941_소문난칠공주 {
	
	static final int N = 5;
	static final int MAX_COUNT = 7;
	
	static int[] combList = new int[MAX_COUNT];
	
	static char[][] graph = new char[N][];
	
	static int[][] checkGraph;
	
	static int result;
	
	
	//0,0으로 해야하므로 0부터 시작
	static void comb(int cnt, int idx) {
		if(cnt==MAX_COUNT) {
			check();
			return;
		}
		
		for(int i=idx;i<N*N;i++) {
			combList[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
	
	static class Node {
		int r;
		int c;
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	
	static void BFS(int r, int c) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(r, c));
		checkGraph[r][c] = 0;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			int x = current.r;
			int y = current.c;
			
			for(int i=0; i<4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				if(checkGraph[nx][ny] == 1) {
					checkGraph[nx][ny] = 0;
					
					queue.offer(new Node(nx, ny));
				}
			}
			
		}
		
	}
	
	//s가 4명이상
	static void check() {
		int S = 0;
		
		int checkR = 0;
		int checkC = 0;
		
		checkGraph = new int[N][N];
		
		for(int i=0; i<MAX_COUNT;i++) {
			int c = combList[i] % 5;
			int r = combList[i] / 5;
			
			if(graph[r][c] == 'S') S++;
			
			checkGraph[r][c] = 1;
			checkR = r;
			checkC = c;
		}
		//연결되어있는지 확인
		if(S >= 4) {
			BFS(checkR, checkC);
			for(int i=0; i<N;i++) {
				for(int j=0; j<N;j++) {
					if(checkGraph[i][j] == 1) return;
				}
			}
			
//			for (int[] cs : checkGraph) {
//				for(int ch : cs) {
//					System.out.print(ch+" ");
//				}
//				System.out.println();
//			}
//			
//			System.out.println();
			result++;
		}
		
	}
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			graph[i] = st.nextToken().toCharArray();
		}
		
		comb(0, 0);
		System.out.println(result);
	}

}
