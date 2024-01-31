package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_1074_Z {
	
	static int func(int n, int r, int c) {
		
		//n이 1일 경우 2^2의 형태임
		//0 1
		//2 3 의 형태이므로 r이 행c가 열로 계산하고 반환
		if(n==1) {
			if(r==0 && c==0) {
				return 0;
			}
			if(r==0 && c==1) {
				return 1;
			}
			if(r==1 && c==0) {
				return 2;
			}
			if(r==1 && c==1) {
				return 3;
			}
			
		}
		
		//나누는 기준 ex) n이 3일 경우 2^(n-1) = 4 로 0, 1, 2, 3을 구함
		
		int tmp = (int)Math.pow(2, n-1);
		
		//0, 1, 2, 3 중 어디에 속하는지 파악 하기 위해 행과 열을 tmp = 2^(n-1)로 나눔
		//ex) 3일 경우 4로 나눈다
		int tmpR = (r / tmp);
		int tmpC = (c / tmp);

		
		//같은 방식으로 0, 1, 2, 3에 속하는 지 구할 수 있음
		int cnt = 0;
		if(tmpR == 0 && tmpC==0) cnt = 0;
		if(tmpR == 0 && tmpC==1) cnt = 1;
		if(tmpR == 1 && tmpC==0) cnt = 2;
		if(tmpR == 1 && tmpC==1) cnt = 3;
		
		//다음 재귀를 위해 r과 c를 2^(n-1)의 형태로 적용
		r -= tmpR * tmp;
		c -= tmpC * tmp;
		
		//계산해보면 n이 3일 경우 15, 31, 47, 63이 같은 위치
		//즉 16씩 더해야 함 -> 16은 2^(n-1)의 제곱에 cnt 곱하여 위치에 따라 보정
		return (cnt * (int)Math.pow(tmp, 2)) + func(n-1, r, c);
	}
	
	
	public static void main(String[] args) throws IOException {
		//입력값 넣기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		//n-> 2^n * 2^n 그래프 형식
		//r행 c열
		//0행 0열 부터 시작
		int output = func(n, r, c);
		System.out.println(output);
	}
}
