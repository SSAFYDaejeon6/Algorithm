package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


//293928kb 544ms
//unionfind도 하나의 방법
public class BJ_G4_16234_인구이동 {

	static int[][] graph, check;

	static int N, minNum, maxNum;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static class Node {
		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static boolean[][] visited;

	static List<Num> list = new ArrayList<>();	

	static class Num{
		int group;
		int sum;
		int cnt;

		Num(int group, int sum, int cnt) {
			this.group = group;
			this.sum = sum;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return group+" "+sum+" "+cnt;
		}


	}

	//그룹을 check배열로 체크
	static void BFS(int r, int c, int group) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(r, c));
		visited[r][c] = true;

		//리스트에 연결된 수와 그때의 합을 구하기 
		int sum = 0;
		int cnt = 0;

		while(!queue.isEmpty()) {			
			Node current = queue.poll();
			cnt++;
			int curR = current.r;
			int curC = current.c;
			
			//현재 그룹 체크
			check[curR][curC] = group;
			int people = graph[curR][curC];
			
			//합에 추가
			sum += people;
			for(int i=0; i<4;i++) {
				int nr = curR + dr[i];
				int nc = curC + dc[i];
					
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
				int diff = Math.abs(people - graph[nr][nc]);
				
				//조건에 만족하면 큐에 넣기
				if(diff >= minNum && diff <=maxNum) {
					queue.offer(new Node(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}

		//리스트에 정보 저장
		list.add(new Num(group, sum, cnt));
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		minNum = Integer.parseInt(st.nextToken());
		maxNum = Integer.parseInt(st.nextToken());

		graph = new int[N][N];


		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		//시간 출력
		int time = 0;
		
		while(true) {
		
			visited = new boolean[N][N];
			check = new int[N][N];
			int cnt = 0;
			
			//BFS하기
			for(int r=0; r<N;r++) {
				for(int c=0; c<N;c++) {
					if(check[r][c] == 0) {
						BFS(r, c, ++cnt);
					}
				}
			}
			
			//만약 모든 땅이 다 연결안되있으면 cnt는 땅의 개수가 됨
			if(cnt == N*N) {
				System.out.println(time);
				return;
			}
			
			//땅 인구수 갱신
			for(int r=0; r<N;r++) {
				for(int c=0; c<N;c++) {
					int idx = check[r][c];
					int newNum = list.get(idx-1).sum / list.get(idx-1).cnt;
					graph[r][c] = newNum;
				}
			}
//			print();
			list.clear();
			
			time++;
		}
	}

	static void print() {

		for (int[] is : graph) {
			for (int i : is) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
