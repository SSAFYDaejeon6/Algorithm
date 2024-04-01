package test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *11568kb 76ms
 */
public class BJ_G5_5557_1학년 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		int last = 0;
		
		int[] inputList = new int[n-1];
		long[][] dp = new long[n-2][21];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n;i++) {
			if(i==n-1) last = Integer.parseInt(st.nextToken());
			else inputList[i] = Integer.parseInt(st.nextToken());
		} 
		
		//0번째 와 첫번째 계산시 사용
		int one = inputList[0] + inputList[1];
		int two = inputList[0] - inputList[1];
		//0 0 0일경우 +, - 둘다 되므로 = 1 이 아니라 += 1로 해야함
		if(one >= 0 && one <= 20) dp[0][one] += 1;
		if(two >= 0 && two <= 20) dp[0][two] += 1;
		
		for(int i=1;i<n-2;i++) {
			for(int j=0; j<=20;j++) {
				if(dp[i-1][j]==0) continue;
				//System.out.println(j);
				//조건에 맞으면 이전 경우의 수를 더함
				if(j+inputList[i+1] >= 0 && j+inputList[i+1] <= 20) {
					int idx = j+inputList[i+1];
					dp[i][idx] += dp[i-1][j];
				}
				
				if(j-inputList[i+1] >= 0 && j-inputList[i+1] <= 20) {
					int idx = j-inputList[i+1];
					dp[i][idx] += dp[i-1][j];
				}
				
			}
		}
		
//		for(int r=0; r<n-2;r++) {
//			for(int c=0; c<21;c++) {
//				System.out.print(dp[r][c]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
	
		System.out.println(dp[n-3][last]);
	}
	
	
}
