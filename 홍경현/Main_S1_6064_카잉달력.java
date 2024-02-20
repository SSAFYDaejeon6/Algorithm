package Algorithm.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 	40232kb 552ms
 * <문제 해석>
	M과 N보다 작거나 같은 두 개의 자연수 x, y
	각 년도를 <x:y>와 같은 형식으로 표현
	첫 번째 해: <1:1>
	두 번째 해: <2:2>
	<x:y>의 다음해 <x’:y’>이라 할 때
	x<M ? x’ = x+1 : x’ = 1
	y<N ? y’ = y+1 : y’ = 1
	<M:N>은 달력의 마지막 해
	
	네 개의 정수 M, N, x, y가 주어질 때, <x:y> 는 몇 번째 해?
	
	<입력>
	1. T
	2. M N x y (1≤M, N≤40,000, 1≤x≤M, 1≤y≤N)
	
	<출력>
	정수 k (k번째 해), 유효하지 않으면 -1
 */
public class Main_S1_6064_카잉달력 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int max = N*M;
			int m = M, n = N;
			
			//M과 N의 최대공배수 (마지막 년도)
			while(true) {
	            if(m % n == 0) {
	                max /= n;
	                break;
	            }
	            int r = m % n;
	            m = n;
	            n = r;
	        }
			
			int result = -1;
			
			//x와 y가 1인 상황을 처리해주기 위해 x와 y를 각각 -1
			x--;
			y--;
			
			//j%N이 Y가 될 때의 값 +1 를 result 값에 삽입
			for(int j=x; j<max; j+=M) {
            	if(j%N == y) {
            		result = j+1;
            		break;
            	}
            }

			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}
}
