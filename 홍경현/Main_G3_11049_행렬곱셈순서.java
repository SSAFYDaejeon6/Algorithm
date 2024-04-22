package algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/* 14620kb 216ms
 * [문제 해석]
	NxM 행렬 A, MxK 행렬 B
	A와 B를 곱할 때 필요한 곱셈의 연산 수는 총 NxMxK번
	행렬 N개를 곱하는데 필요한 곱셈 연산의 수는 행렬을 곱하는 순서에 따라 다름
	
	행렬 N개의 크기, 모든 행렬을 곱하는데 필요한 곱셈 연산 횟수의 최솟값 구하기
	입력으로 주어진 행렬의 순서를 바꾸면 안됨
	
	[입력]
	1. N <=500
	2. r, c <=500
	* 항상 순서대로 곱셈을 할 수 있는 크기만 입력으로 주어짐
	
	[출력]
	최솟값
	
	[문제 해결 프로세스]
	1) 순열 => 시간 초과
	2) DP
		dp[i][j] = min(dp[i][j],
		dp[i][k]+dp[k+1][j]+arr[i][0]*arr[k][1]*arr[j][1];
		-> i부터 j까지 행렬 곱셈을 한 경우의 수
 */
public class Main_G3_11049_행렬곱셈순서 {
	static int N;
	static int[][] arr;
	static int[][] dp;
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		dp = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int j=1; j<N; j++) {
			for(int i=0; i<N-j; i++) {
				dp[i][i+j] = INF;
				for(int k=i; k<i+j; k++) {
					dp[i][i+j] = Math.min(dp[i][i+j], dp[i][k]+dp[k+1][i+j]+arr[i][0]*arr[k][1]*arr[i+j][1]);
				}
			}
		}

		System.out.println(dp[0][N-1]);
		
	}

}
