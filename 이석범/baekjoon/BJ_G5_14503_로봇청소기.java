package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * RxC이 입력
 * r c d // 0은 북, 1은 동, 2는 남, 3은 서
 * M개의 줄 // 0은 청소x, 1은 벽
 * 
 * 로봇청소기
 * 1. 현재 칸이 청소가 x이면 청소
 * 2. 주변 4칸 중 청소 안된 빈칸이 없는 경우, 바라보는 방향을 유치한 채
 * 한칸 후진하고 1번으로 돌아감
 * 바라보는 뒤쪽 칸이 벽이라 후진 못하면 작동 불가
 * 빈칸이 있는 경우 반시계 방향으로 90도 회전
 * 바라보면 방향을 기준으로 앞쪽 칸이 청소x이면 한칸 전진
 * 1번으로 돌아감
 * 
 * 출력은 청소하는 칸의 개수
 * 11852kb 88ms
 */
public class BJ_G5_14503_로봇청소기 {
	static int R, C;
	
	static int[][] graph;
	
	//북 동 남 서
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int cnt = 0;
	
	static void move(int r, int c, int dir) {
		//1번 만족시
		if(graph[r][c] == 0) {
			graph[r][c] = 2;
//			System.out.println(r+" "+c);
			cnt++;
		}
		
		int nr, nc;
		
		//빈칸 확인하기
		int zeroCnt = 0;
		for(int i=0; i<4;i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc] == 1) continue;
			
			if(graph[nr][nc] == 0) zeroCnt++;
		}
		
		//2번 인 경우
		if(zeroCnt == 0) {
			nr = r + dr[(dir+2) % 4];
			nc = c + dc[(dir+2) % 4];
			if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc] == 1) return;
			move(nr, nc, dir);
		}
		//반시계로 회전
		else {
			dir = dir - 1 >= 0 ? dir - 1 : 3;
			nr = r + dr[dir];
			nc = c + dc[dir];
			if(nr >= 0 && nr < R && nc >= 0 && nc < C &&graph[nr][nc] == 0) {
				r = nr;
				c = nc;
			}
			move(r, c, dir);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new int[R][C];
		
		st = new StringTokenizer(br.readLine());
		int startR = Integer.parseInt(st.nextToken());
		int startC = Integer.parseInt(st.nextToken());
		int startDir = Integer.parseInt(st.nextToken());
		
		
		for(int r=0; r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		move(startR, startC, startDir);
		System.out.println(cnt);
	}
}
