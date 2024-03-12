import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_9465_스티커 {

	static int[][] dp;
	static int[][] graph;

	public static void main(String[] args) throws IOException {
		//입력값 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());

		for(int i=0;i<testCase;i++) {
			//입력
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			//경우의 수로 선택 안한 경우, 1번선택한 경우, 2번선택한 경우
			dp = new int[num][3];
			graph = new int[num][2];

			st = new StringTokenizer(br.readLine());
			for(int k=0; k<num;k++) {
				int input = Integer.parseInt(st.nextToken());
				graph[k][0] = input;
			}
			st = new StringTokenizer(br.readLine());
			for(int k=0;k<num;k++) {
				int input = Integer.parseInt(st.nextToken());
				graph[k][1] = input;
			}

			//0번째 인덱스는 주어짐
			dp[0][0] = 0;
			dp[0][1] = graph[0][0];
			dp[0][2] = graph[0][1];

			for(int j=1; j<num;j++) {
				//안골랐을 때는 1번 혹은 2번을 고르는게 최선
				dp[j][0] = Math.max(dp[j-1][1], dp[j-1][2]);
				//1번골랐을 때는 이전값 중 2번 혹은 안 고른 것에 현재 1번값 더하기
				dp[j][1] = Math.max(dp[j-1][0], dp[j-1][2])+graph[j][0];
				//2번골랐을 때는 이전값 중 1번 혹은 안 고른 것에 현재 1번값 더하기
				dp[j][2] = Math.max(dp[j-1][1], dp[j-1][0])+graph[j][1];
			}
			
			//num-1번째 최댓값을 구하면 됨
			int max = Math.max(dp[num-1][0], dp[num-1][1]);
			max = Math.max(max, dp[num-1][2]);
			System.out.println(max);
		}
	}
}
