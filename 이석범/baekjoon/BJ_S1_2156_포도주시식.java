package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *	13372kb 120ms
 */
public class BJ_S1_2156_포도주시식 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());


		//0은 선택 1은 선택x
		int[] input = new int[10001];
		int[] dp = new int[10001];

		for(int i=0; i<n;i++) {
			st = new StringTokenizer(br.readLine());
			input[i] = Integer.parseInt(st.nextToken());
		}

		if(n==1) {
			System.out.println(input[0]);
			return;
		}
		else {
			dp[0] = input[0];
			dp[1] = input[0] + input[1];
			dp[2] = Math.max(dp[1], Math.max(input[0], input[1]) + input[2]);
			
			/*
			 * i번째 선택 할 경우  
			 * dp[i-2] + dp[i], 혹은 dp[i-3] + input[i-1] + input[i]
			 * 
			 * 선택 안할 경우
			 * dp[i-1]과 같음
			 * 
			 */
			
			for(int i=3; i<=n;i++) {
				dp[i] = Math.max(dp[i-1], dp[i-3] +input[i-1] + input[i] );
				dp[i] = Math.max(dp[i], dp[i-2]+input[i]);
			}
			
			System.out.println(dp[n]);
			
		}
	}
}
