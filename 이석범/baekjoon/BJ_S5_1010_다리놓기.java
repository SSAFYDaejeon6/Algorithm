package test;

import java.util.Scanner;

//17888KB, 212ms
public class BJ_S5_1010_다리놓기 {
	
	
	//n의 최대 개수가 30이므로 2^30으로 시간초과 가 난다
	//하지만 경우의 수를 구하는 것이기 때문에 mCn을 구하면 된다
	//mCn의 경우 m-1Cn + m-1Cn-1 로 m-1개에서 n개를 뽑는 경우의수 + m-1개에서 n-1개를 뽑아 m을 뽑아지는 경우의 수를 합하면 된다.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		int n, m;
		int[][] dp;
		for(int i=1;i<=test;i++) {
			n = sc.nextInt();
			m = sc.nextInt();
			dp = new int[m+1][n+1];

			for(int x=1; x<=m;x++) {
				for(int y=0;y<=n;y++) {
					//y개 0이면 아무것도 안뽑는 경우의 수이므로 1개
					if(y==0) {
						dp[x][y] = 1;
						continue;
					}

					//nCn일 경우 모두 뽑는 것이므로 1개 이후에는 보지 않아도 되므로 break로 반복문 탈출
					if(x==y) {
						dp[x][y] = 1;
						break;
					}
					
					//위의 언급한 점화식
					dp[x][y] = dp[x-1][y-1] + dp[x-1][y];

				}
			}
			System.out.println(dp[m][n]);
			
		}
	}

}
