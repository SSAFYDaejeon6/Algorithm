package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//14660kb 120ms
public class BJ_G5_21608_상어초등학교 {
	
	static boolean[][] graph;
	
	static int[][] map;
	
	static int N;
	
	static Deque<Integer> deque = new ArrayDeque<>();
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		graph = new boolean[N*N+1][N*N+1];
		map = new int[N+1][N+1];
		
		for(int i=0; i<N*N;i++) {
			st = new StringTokenizer(br.readLine());
			
			int idx = Integer.parseInt(st.nextToken());
			deque.addLast(idx);
			
			for(int j=0; j<4;j++) {
				int to = Integer.parseInt(st.nextToken());
				graph[idx][to] = true;
			}
		}
		boolean flag = false;
		while(!deque.isEmpty()) {
			int idx = deque.pollFirst();
			int resultR = 0;
			int resultC = 0;
			int resultCnt = 0;
			int resultBlank = 0;
			
			if(!flag) {
				map[2][2] = idx;
				flag = true;
				continue;
			}
			
			
			for(int r=1; r<=N;r++) {
				for(int c=1; c<=N;c++) {
					if(map[r][c]!= 0) continue;
					int tmpR = 1;
					int tmpC = 1;
					int tmpCnt = 0;
					int tmpBlankCnt = 0;
					
					for(int d=0; d<4;d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(nr <= 0 || nr > N || nc <= 0 || nc > N) continue;
						//공백이면 
						if(map[nr][nc]==0) tmpBlankCnt++;
						
						//공백이 아니고 좋아하는 학생인 경우cnt++
						else {
							if(graph[idx][map[nr][nc]]) {
								tmpCnt++;
							}
						}
					}
					
					//블랭크와 인접한 친구 찾기
					//1번 좋아하는 학생이 많으면 저장
					if(resultCnt < tmpCnt) {
						resultR = r;
						resultC = c;
						resultCnt = tmpCnt;
						resultBlank = tmpBlankCnt;
					}
					//같은 거면
					else if(resultCnt == tmpCnt) {
						//2번 공백 많은 곳 저장
						if(resultBlank < tmpBlankCnt) {
							resultR = r;
							resultC = c;
							resultCnt = tmpCnt;
							resultBlank = tmpBlankCnt;
						}
						//비어있는 칸도 같으면 그냥 이전것을 사용
						//단 0,0으로 아무것도 안되어있으면
						//마지막 갱신
						else if(resultBlank==tmpBlankCnt) {
							if(resultR==0 && resultC==0) {
								resultR = r;
								resultC = c;
								resultCnt = tmpCnt;
								resultBlank = tmpBlankCnt;
							}
						}
					}
					
					
				}
			}
			map[resultR][resultC] = idx;

		}
		
		int result = 0;
		
		
		for(int r=1; r<=N;r++) {
			for(int c=1; c<=N;c++) {
				int from = map[r][c];
				int cnt = 0;
				for(int d=0; d<4;d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if(nr <= 0 || nr > N || nc <= 0 || nc > N) continue;
					
					int to = map[nr][nc];
					
					if(graph[from][to]) cnt++;
				}
				if(cnt==0) continue;
				//0 1 10 100 100순
				result += (int)Math.pow(10, cnt-1);
			}
		}
		
		System.out.println(result);
		
//		for(int[] is:map) {
//			for(int i:is) {
//				System.out.print(i+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();

		
	}
}
