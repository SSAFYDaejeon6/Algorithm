package Algorithm.Study;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/* 13012kb 100ms
 * 문제 해석
	규칙
	1. 포도주 잔 선택 → 그 잔에 들어있는 포도주 모두 마셔야 함, 마신 후 원래 위치
	2. 연속으로 놓여 있는 3잔을 모두 마실 수 없음
	
	많은 양의 포도주 맛보기 → 어떤 포도주 잔을 선택?
	1부터 N까지 번호가 붙어 있는 N개의 포도주 잔, 각 포도주 잔에 들어있는 포도주의 양
	효주를 도와 가장 많은 양의 포도주를 마실 수 있도록 하는 프로그램
	
	[입력]
	1. 포도주 잔의 개수 n ≤10,000
	2. 포도주 양의 순서 (1,000 이하의 음이 아닌 정수)
	
	[출력]
	최대로 마실 수 있는 포도주의 양
 */
public class Main_S1_2156_포도주시식{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] wine = new int[n+1];
		for(int i=1; i<=n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}

		//[0]: i번 째 잔 미선택
		//[1]: 연속적으로 1번 선택 (i번 째 잔)
		//[2]: 연속적으로 2번 선택 (i-1번 쨰, i번 째 잔)
		int[][] dp = new int[n+1][3];
		dp[1][1] = wine[1];
		
		for(int i=2; i<=n; i++) {
			//현재 잔을 선택하지 않기 때문에, 이전 포도주의 양 중에 가장 큰 값을 저장
			dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
			dp[i][1] = dp[i-1][0] + wine[i]; 
			dp[i][2] = dp[i-1][1] + wine[i];
		}
		
		System.out.println(Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2])));
	}
}
