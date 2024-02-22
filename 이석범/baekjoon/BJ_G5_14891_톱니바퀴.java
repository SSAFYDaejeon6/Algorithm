package algo0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 톱니바퀴 4개존재
 * 1 2 3 4
 * 톱니바퀴의 이는 8개 존재
 * 12시부터 입력받음
 * 영향을 주는 인덱스는 2와 6이 영향을 줄듯
 * 1은 s극 0은 n극
 * 같은 극이 맞닿아있으면 반대방향으로 회전
 * 다른 극이 맞닿아 있으면 같은 방향으로 회전
 * 방향이 1이면 시계
 * -1이면 반시계
 * 
 * 입력
 * 톱니바퀴 4개
 * 회전 횟수
 * 번호 방향
 * 
 * k번 회전시킨 후 점수의 합을 출력
 * 1번 톱니바퀴의 12시 방향이 s이면 1점
 * 2번 s면 2점 ...
 * n이면 0점
 * 
 * 1. 큐 4개를 사용
 * 2. 쉬프트 연산 사용
 * 3. /, %연산 사용
 * 11720kb 80ms
 */
public class BJ_G5_14891_톱니바퀴 {

	//톱니바퀴 리스트
	static int[] tob = new int[4];

	//재귀에서 재방문 방지
	static boolean[] visited = new boolean[4];

	//돌리기
	static void rotate(int num, int dir) {
		//방문처리
		visited[num] = true;
		
		int last = 0;
		switch(num) {
		case 0:
			if(!visited[num+1]) {
				//이게 아니라
				//((tob[num] & (1 << 5)) != (1 << 5) 처럼 해야함
				if(((tob[num] & (1 << 5)) != 0 ? 1: 0) == (((tob[num+1] & (1 << 1)) != 0) ? 1 : 0)) {
					//					loopCheck(num+1, -1);
				}

				else rotate(num+1, dir == 1 ? -1:1);
			}
			break;
		case 1:
		case 2:
			if(!visited[num+1]) {
				if(((tob[num] & (1 << 5)) != 0 ? 1: 0) == (((tob[num+1] & (1 << 1)) != 0) ? 1 : 0)) {
					//System.out.println("true "+num);
					//loopCheck(num+1, -1);
				}
				else rotate(num+1, dir == 1 ? -1:1);
			}

			if(!visited[num-1]) {
				if(((tob[num] & (1 << 1)) != 0 ? 1 : 0) == (((tob[num-1] & (1 << 5)) != 0) ? 1: 0)) {
					//					loopCheck(num-1, -1);
				}
				else rotate(num-1, dir == 1 ? -1:1);
			}

			break;
		case 3:
			if(!visited[num-1]) {
				if(((tob[num] & (1 << 1)) != 0 ? 1 : 0) == (((tob[num-1] & (1 << 5)) != 0) ? 1: 0)) {
					//					loopCheck(num-1, -1);
				}
				else rotate(num-1, dir == 1 ? -1:1);
			}

			break;
		}

		//last는 11시 -> 0, first는 12시-> 7
		//dir은 1이면 시계
		if(dir==1) {			
			last = (tob[num] & 1) != 0 ? 1:0;
			tob[num] = tob[num] >> 1;
			tob[num] = tob[num] | (last << 7);
			
		} 
		else {
			last = (tob[num] & (1<<7)) != 0 ? 1:0;
			
			tob[num] &= Integer.parseInt("01111111", 2);
			tob[num] = tob[num] << 1;
			tob[num] |= last;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		

		for(int i=0; i<4;i++) {
			tob[i] = Integer.parseInt(br.readLine(), 2);
		}


		int m = Integer.parseInt(br.readLine());
		for(int i=0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			rotate(num-1, dir);

			visited = new boolean[4];

		}
		int result = 0;
		for(int i=0;i<4;i++) {
			if((tob[i] & (1 << 7)) != 0) result += 1<<i;
		}
		System.out.println(result);
	}

}
