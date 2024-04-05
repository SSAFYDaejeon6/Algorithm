import java.io.*;
import java.util.*;
/* 12252kb 88ms
 * [문제 해석]
	수빈이는 현재 점 N, 동생은 K에 있음
	수빈이는 걷거나 순간이동을 할 수 있음
	수빈이의 위치가 X일 때 걷는다면 1초 후 X-1 또는 X+1로 이동하게 됨
	수빈이의 위치가 주어졌을 때 수빈이가 동생을 찾을 수 있는 가장 빠른 시간
	
	[입력]
	수빈이 위치 N과 동생의 위치 K
	
	[출력]
	동생을 찾는 가장 빠른 시간 출력
	
	[문제 해결 프로세스]
	dp[x-1] = min(dp[x]+1, dp[x-1]) 
	dp[x*2] = min(dp[x], dp[x*2])
	dp[x+1] = min(dp[x+1], dp[x]+1)
	
	게시판 보면 DP 문제가 아니라는데 이렇게 푸는 게 맞는지 모르겠음
 */
public class Main_G5_13549_숨바꼭질 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//고려할 점 1. 동생의 위치가 뒤에 있을 때
		if(K<N) {
			System.out.println(N-K);
			return;
		}
		
		//고려할 점 2. 동생의 위치와 같을 때
		if(K==N) {
			System.out.println(0);
			return;
		}
		
		int dp[] = new int[111111];
		Arrays.fill(dp, 100001);
		
		//초기 dp값 설정
		for(int i=0; i<N; i++) dp[i] = N-i;
		for(int i=N+1; i<=K; i++) dp[i] = i-N;
		dp[N] = 0;
		
		//N보다 작은 구간에서는 순간이동만 고려
		for(int i=1; i<=N; i++) {
			dp[i*2] = Math.min(dp[i*2], dp[i]);
		}
		
		for(int i=N+1; i<=K+1; i++) {
			//고려할 점 3. dp[i-1]보다 현재 위치에서 뒤로 가는 게 더 빠를 때 dp[(i-1)*2]의 값도 갱신해줌
			if(dp[i-1] > dp[i]+1) { 
				dp[i-1] = dp[i]+1;
				if((i-1)*2<110000) dp[(i-1)*2] = Math.min(dp[(i-1)*2], dp[i-1]);
			}
			dp[i+1] = Math.min(dp[i+1], dp[i]+1);
			if(i*2<110000) dp[i*2] = Math.min(dp[i*2], dp[i]);
		}
		
		System.out.println(dp[K]);
	}

}
