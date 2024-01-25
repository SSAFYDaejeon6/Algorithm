package com.ssafy.baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_10157 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken()); // 7
		int R = Integer.parseInt(st.nextToken()); // 6
		
		int target = Integer.parseInt(br.readLine()); // 11		
		
		if(target == C*R && C == R) { // edge case : C x R 인경우 다음 탐색하려고 하는 square이 딱 하나라 조건에 맞지 않음
			System.out.println(C/2 + " " + R/2);
		} else {
			getAnswer(C, R, 1, target, 1, 1); // 시작은 항상 왼쪽 아래부터 시작
		}
		 
		
	}
	
	public static void getAnswer(int C, int R, int startPoint, int target, int x, int y) {
		/**
		 * @params
		 * C,R		 	각각 가로 세로
		 * startPoint	는 1에서 한 바퀴 둘레 반경 만큼 증가
		 * target		구하고자 하는 좌표의 대기번호
		 * x,y			구하고자 하는 좌표 (1,1) 시작 (1,1)씩 증가하다, 범위안에있으면 한바퀴 돌면서 search
		 */
		int squareWidth = C;
		int squareHeight = R;
		
		int squareRange = startPoint + 2*(squareWidth+squareHeight) - 4; // 한 바퀴 둘레
			
		
		if(2*(squareWidth+squareHeight) - 4 < 0) { // 할 수 없음 why?
			System.out.println(0);
			return;
		}
		
		if(target < squareRange ) { // 만약 한 바퀴 둘레 안에 값이 있다면?
			printAnswer(C, R, startPoint, target, x, y);
		} else if(target >= squareRange){ // 다음 한바퀴로 가자
			getAnswer(C-2, R-2, squareRange, target, x+1, y+1);
		} 
		
		return;
	}
	
	public static void printAnswer(int C, int R, int startPoint, int target, int x, int y) {
		/**
		 * @params
		 * target		현재 위치 x,y 부터 한바퀴를 돌 때 무조건 존재해야하는 타겟
		 * C,R			줄어들거나 그대로인 width와 height 
		 */
		int[] dx = new int[] {0,1,0,-1};
		int[] dy = new int[] {1,0,-1,0}; // 북동남서
		
//		System.out.println(target +" " + startPoint);
		if(target == startPoint) {
			System.out.println(x + " " + y);
			return;
		}
		for(int i=0; i<4; i++) {
			if(i==0) {
				for(int j=0; j<R-1; j++) { // 북쪽 탐색
					x += dx[i];
					y += dy[i];
					if(process(x, y, ++startPoint, target)) return;
				}
			} else if(i==1){
				for(int j=0; j<C-1; j++) { // 동쪽 탐색
					x += dx[i];
					y += dy[i];
					if(process(x, y, ++startPoint, target)) return;
				}
			} else if(i==2){
				for(int j=0; j<R-1; j++) { // 남쪽 탐색
					x += dx[i];
					y += dy[i];
					if(process(x, y, ++startPoint, target)) return;
				}
			} else if(i==3){
				for(int j=0; j<C-2; j++) { // 서쪽으로 갈때는 첫 시작 점을 가면 안되므로 -1을 한번더 해줌
					x += dx[i];
					y += dy[i];
					if(process(x, y, ++startPoint, target)) return;
				}
			}		
					
		}
		
	}
	
	public static boolean process(int x, int y, int startPoint, int target) {
		/**
		 * 탐색하면서 target과 일치하는지 여부를 검사 이 함수는 무조건 답을 찾아냄
		 * @params
		 * x,y			현재 탐색하는 좌표
		 * startPoint	배열을 사용하지 않기때문에 좌표값의 값을 나타내며 동시에 갱신함
		 * target		찾고자하는 값
		 * 
		 * @return
		 * 값이 일치하는지 여부
		 * 
		 */
		if(startPoint == target) {
			System.out.println(x+ " " +y);
			return true;
		} else {
			return false;
		}
	}

}
