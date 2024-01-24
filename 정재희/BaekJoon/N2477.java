package 정재희.BaekJoon;

import java.util.Scanner;

public class N2477 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		final int line = 6;
		int[] dir = new int[line];   // 방향 입력 배열
		int[] lens = new int[line];  // 길이 입력 배열
		int res = N;
		
		for(int i=0;i<line;i++) {
			dir[i] = sc.nextInt();
			lens[i] = sc.nextInt();
		}
		
		// 2번 순회했을 때, 두개의 방향이 2번 반복되는 경우가 작은 사각형
		for(int i=0;i<line*2;i++) {
			int j1 = i%line, j2 = (i+1)%line, j3 = (i+2)%line, j4 = (i+3)%line;
			if(dir[j1] == dir[j3] && dir[j2] == dir[j4]) {   //작은 사각형의 변을 찾은 경우
				res *= (lens[j1] + lens[j3])*(lens[j2] + lens[j4]) - (lens[j2]*lens[j3]);
				//전체 사각형에서 작은 사각형의 넓이 빼기 + 감자 개수와 곱 연산 수행
				break;
			}
		}
		System.out.println(res);
	}
}
