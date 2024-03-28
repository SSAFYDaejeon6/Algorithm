import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Sliding window 기법 사용
 * 
 * 180,296KB | 568ms
 * 
 */
public class BOJ_N15961_회전초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());  // 접시의 수
		int d = Integer.parseInt(st.nextToken());  // 초밥 가짓수
		int k = Integer.parseInt(st.nextToken());  // 연속 접시 수
		int c = Integer.parseInt(st.nextToken());  // 쿠폰 번호
		
		int[] belt = new int[N];
		int[] categories = new int[d+1];
		int cnt = 0;
		int[] counts = new int[N];
		
		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
			if(i < k) {
				if(belt[i] == c) continue;  // 쿠폰인 경우 세지 않음.
				if(++categories[belt[i]] == 1) cnt++; 
			}
		}
		
		counts[0] = cnt;
		int maxVal = counts[0];
		for(int i=1;i<N;i++) {
			counts[i] = counts[i-1];
			// 윈도우 이동 후 그 전 값 빠짐
			if(--categories[belt[i-1]] == 0) counts[i]--;
			
			int add = (i+k-1) % N;  // 윈도우 이동 후 추가될 값의 인덱스
			if(belt[add] == c) continue;
			if(++categories[belt[add]] == 1) counts[i]++; 
			maxVal = Math.max(maxVal, counts[i]);
		}
		System.out.println(maxVal+1);
	}

}
