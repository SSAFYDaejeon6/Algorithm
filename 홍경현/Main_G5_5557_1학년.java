package Algorithm.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 11572kb 80ms
 * [문제 해석]
	숫자가 줄 지어있는 것을 보기만 하면
	마지막 두 숫자 사이에 = 을 넣고
	나머지 숫자 사이에는 + - 를 넣어 등식 만들기
	
	올바른 등식을 만들려고 함
	아직 학교에서 음수를 배우지 않았고
	20 넘는 수는 모름
	왼쪽부터 계산할 때 중간에 나오는 수가 모두 0이상 20이하여햐 함
	
	상근이가 만들 수 있는 올바른 등식의 수 구하기
	
	[입력]
	1. 3<=N<=100
	2. 정수 N개 공백
	
	[출력]
	올바른 등식의 개수
 */
public class Main_G5_5557_1학년 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N-1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N-1; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		int last = Integer.parseInt(st.nextToken());
		
		//2차원 DP
		long dp[][] = new long[N-1][21];
		dp[0][num[0]] = 1;
		
		for(int i=1; i<N-1; i++) {
			for(int j=0; j<=20; j++) {
				long pre = dp[i-1][j];
				if(pre != 0) {
					if(j+num[i] <= 20)
						dp[i][j+num[i]] += pre;
					if(j-num[i] >= 0)
						dp[i][j-num[i]] += pre;
				}
			}
			
		}
		System.out.println(dp[N-2][last]);
	}

}
