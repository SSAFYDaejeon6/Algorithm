package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 12716kb 136ms
 * 문제 해석
	NxM 지도 존재
	오른쪽은 동쪽, 위쪽은 북쪽
	지도의 좌표(r,c) 
	r: 북쪽으로부터 떨어진 칸의 개수
	c: 서쪽으로부터 떨어진 칸의 개수
	
	놓여져 있는 곳의 좌표(x,y)
	가장 처음 주사위는 모든 면 0
	
	각 칸에 정수
	주사위 굴렸을 때, 이동한 칸에 쓰여 있는 수가 0이면 주사위의 바닥면에 쓰여 있는 수가 복사
	0이 아닌 경우 칸에 쓰여 있는 수가 주사위 바닥면으로 복사, 칸에 쓰여 있는 수 0
	
	주사위 놓은 곳의 좌표와 이동시키는 명령이 주어졌을 때,
	이동했을 때 마다 상단에 쓰여 있는 값 구하기
	
	** 바깥 이동 시 해당 명령 무시 & 출력X
	
	[입력]
	1. 세로 N 가로 M ≤20, 주사위 놓은 곳의 좌표 x, y, 명령의 개수 k ≤1000
	2. 지도 상태 (주사위 놓은 칸에 쓰여 있는 수는 항상 0) 
	    각 칸의 수는 10 미만의 자연수 또는 0
	3. 명령
	    1: 동 2: 서 3: 북 4: 남
	
	[출력]
	이동할 때마다 윗 면에 쓰여 있는 수 출력
 */
public class Main_G4_14499_주사위굴리기 {
	static int N, M, x, y, K;
	static int[] dice = new int[7];
	/*
	  2
	4 1 3
	  5
	  6
	 */
	static int[] dx = {0, 0, 0, -1, 1}, dy = {0, 1, -1, 0, 0}; //1: 동 2: 서 3: 북 4: 남
	static int[][] map;
	static int[][] move = {{}, {3, 1, 4, 6}, {4, 1, 3, 6},
			{5, 1, 2, 6}, {2, 1, 5, 6}}; //각 방향별로 움직이는 주사위 위치 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			int order = Integer.parseInt(st.nextToken());
			if(roll(order)) System.out.println(dice[1]);
		}
	}
	
	//주사위 굴림
	private static boolean roll(int o) {
		if(!range(x+dx[o], y+dy[o])) return false;
		x += dx[o];
		y += dy[o];
		int temp = dice[move[o][0]];
		for(int i=0; i<3; i++) {
			dice[move[o][i]] = dice[move[o][i+1]];
		}
		
		if(map[x][y] > 0) {
			dice[6] = map[x][y];
			map[x][y] = 0;
		}else {
			dice[6] = temp;
			map[x][y] = temp;
		}
		
		return true;
	}

	private static boolean range(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}

}
