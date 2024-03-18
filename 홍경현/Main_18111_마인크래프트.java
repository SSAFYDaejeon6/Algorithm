package Algorithm.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 35472kb	560ms
 * [문제 해석]
	1x1x1 크기의 블록들로 이루어진 3차원 세계
	자유롭게 땅을 파거나 집 짓기 가능
	
	고르지 않은 땅에 집 짓기 불가능
	땅의 높이를 모두 동일하게 만드는 땅 고르기 작업
	
	N, M 크기의 집터, 맨 왼쪽 위 좌표 (0, 0)
		1) 좌표 (i, j)의 가장 왼쪽 위에 있는 블록을 제거하여 인벤토리
		2) 인벤토리에서 블록 하나를 꺼내어 좌표(i, j)의 가장 위에 있는 블록 위에 놓음
		
	1번 작업: 2초
	2번 작업: 1초
	
	밤에는 무서운 몬스터 등장 -> 최대한 빠르게 땅 고르기 작업
	땅 고르기 작업에 걸리는 최소 시간과 그 경우 땅의 높이 출력
	
	인벤토리에 B개의 블록, 땅의 높이는 256 블록을 초과할 수 없으며 음수가 될 수 없음
	
	[입력]
	1. N, M, B (1<=M,N<=500, 0<=B<=6.5x10^7)
	2. N M 땅의 높이
	
	[출력]
	걸리는 시간과 땅의 높이
 */
public class Main_18111_마인크래프트 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		int min = 256;
		int max = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(min > map[i][j]) min = map[i][j]; //가장 낮은 블럭
				if(max < map[i][j]) max = map[i][j]; //가장 높은 블럭
			}
		}
		
		int result = Integer.MAX_VALUE;
		int height = 0;
		
		for(int k=min; k<=max; k++) {
			int block = B;
			int time = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					int num = map[i][j]-k;
					if(num > 0) { //블럭을 제거해야 할 때
						block += num;
						time += (num*2);
					}else if(num < 0) { //블럭을 놓아야 할 때
						block += num;
						time -= num;
					}
				}
			}
			
			if(block < 0) break;
			
			if(result >= time) {
				result = time;
				height = k;
			}
		}
		
		System.out.println(result + " " + height);
	}

}
