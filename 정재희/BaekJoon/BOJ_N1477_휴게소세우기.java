import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 이분 탐색
 * 11,612KB | 80ms
 */
public class BOJ_N1477_휴게소세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] shelter = new int[N+2];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			shelter[i] = Integer.parseInt(st.nextToken());
		}
		shelter[0] = 0;
		shelter[N+1] = L;
		Arrays.sort(shelter);

		int start = 1;
		int end = L-1;
		int res = 0;
		while(start <= end) {
			int cnt = 0;
			int mid = (start+end)/2;  // 휴게소 간 간격
			for (int i = 1; i < N+2; i++) {
				int len = shelter[i] - shelter[i-1];
				cnt += len/mid;
				if(len % mid == 0) cnt--;
			}
			if(cnt > M) { // 휴게소 간 간격을 넓혀야 함
				start = mid+1;
			}
			else {
				res = mid;
				end = mid-1;
			}		
		}
		
		System.out.println(res);
	}

}
