import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		int max = 0;
		
		//앞뒤로 공간을 하나씩 더 할당
		int[] t = new int[n+2];
		int[] p = new int[n+2];
		int[] dp = new int[n+2];
		
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		// bottom-up
		for(int i=1; i<=n; i++) {
			if(i + t[i] < n+2) { // 시간 넘어가지 않는 범위면
				dp[i] = Math.max(dp[i], dp[i-1]); // 일을 하지 않는게 이득일 수 있음
				
				// 1일차에 3일 일하면 1, 2, 3일 일하는거고 금액은 4일에 받는다고 생각하고 dp[4]에 저장하는 것
				// 금액 받는날 이미 저장되어 있는 값과 (시작하는날에 저장된 값 + 일하면 받는값)을 비교
				dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
				max = Math.max(max, dp[i + t[i]]); // 항상 마지막 날에는 일해야 하는 조건이 없기 때문에 최대값 갱신
			}
		}
		
		System.out.println(max);
	}
}
