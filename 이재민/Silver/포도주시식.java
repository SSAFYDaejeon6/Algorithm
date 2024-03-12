import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  index가 하나씩 늘어나기 때문에 포도주를 먹지 않는 것이 최대가 될 수 있음
 *	arr[i] + arr[i-1] + dp[i-3] i와 i-1 2일을 연속으로 먹었으니 i-3을 dp로 최대값을 가져옴
 *  arr[i] + dp[i-2] i를 먹고 dp[i-2]로 i-2의 최대값을 가져와서 비교
 *  12992KB | 108ms
 */

public class 포도주시식 {
	static int N;
	static int[] dp, arr;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int arrSize = N < 4 ? 4 : N + 1;
		arr = new int[arrSize];
		dp = new int[arrSize];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		dp[1] = arr[1];
		dp[2] = arr[1] + arr[2];
		dp[3] = Math.max(arr[2] + arr[3], Math.max(arr[1] + arr[3], dp[2]));

		for (int i = 4; i <= N; i++) {
			dp[i] = dp[i - 1];
			dp[i] = Math.max(dp[i], arr[i] + arr[i - 1] + dp[i - 3]);
			dp[i] = Math.max(dp[i], arr[i] + dp[i - 2]);
		}
		System.out.println(dp[N]);
	}

}
