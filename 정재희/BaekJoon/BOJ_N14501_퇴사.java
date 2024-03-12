package BaekJoon;

import java.util.Scanner;

public class BOJ_N14501_퇴사 {
	// 유튜브 영상을 참고하여 공부하였습니다.
		// 각 날에 상담한 경우와 상담하지 않은 경우 고려
		/**  뒤에서 부터 판단
		 *   1. 상담이 가능한지 판단 (경계 조건 확인)
		 *   2. 상담을 한다? --> 상담 끝날 날짜의 비용과 덧셈 연산 수행 (dp[i+T[i]] + p[i])
		 *   3. 삼담을 안한다? --> 그 다음 날의 비용 그대로 유지됨 (dp[i+1]) (내가 상담을 안했으니, 다음날은 상담을 했을 것임)
		 *   4. 2와 3중에 큰 값을 선택하여 저장
		 *   5. 반복
		 *   Day  1  2  3  4  5  6  7 
		 *   Ti   3  5  1  1  2  4  2
		 *   Pi   10 20 10 20 15 40 200
		 * 		  ________________상담불가
		 * 	상담 O |45|20|45|35|15| 0| 0|
		 * 	상담 X |45|45|35|15|_0|_0|_0|
		 */	
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int N = sc.nextInt();
			int[] T = new int[N];
			int[] P = new int[N];
			int[] dp = new int[N+1];
			
			/* input */
			for(int i=0;i<N;i++) {
				T[i] = sc.nextInt();
				P[i] = sc.nextInt();
			}
			
			for(int i=N-1;i>-1;i--) {  // 뒤에서부터 접근
				int nxt_days = i + T[i];  // 상담이 끝난 다음 날
				if(nxt_days > N) dp[i] = dp[i+1];  // 상담 일이 경계를 넘어가는 경우, i+1의 값을 가져와 저장
				else {   // 상담이 기간 안에 끝나는 경우, 상담을 한 경우와 하지 않은 경우에 대해 최댓값 계산
					dp[i] = Math.max(P[i] + dp[nxt_days], dp[i+1]);
				}
			}
			System.out.println(dp[0]);	
		}

	}

