import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 12,756KB | 96ms
 * 
 * 1. 최대 수 가지치기 (9876543210)
 * 2. 0부터 감소하는 수로 수 뽑기 (N번째 수가 될 때까지)
 */
public class BOJ_N1038_감소하는수 {
	static int digit;
	static int N;
	static int[] res;
	static int limit = 1000000;
	static int count = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		if(N > 1022) { // 9876543210 일 때가 최대 (1022번째 수)
			System.out.println(-1);
			return;
		}
		int i = 1;
		while(true) {
			digit = i;
			res = new int[digit];
			deCount(0);
			i++;
		}
	}
	private static void deCount(int idx) {
		if(idx == digit) {
			long num = 0;
			long times = 1;
			for (int i = digit-1; i >= 0; i--) {
				num += res[i] * times;
				times *= 10;
			}
			
			if(N == count) {
				System.out.println(num);
				System.exit(0);
			}
			
			count++;
			return;
		
		}
		
		for (int i = 0; i <= 9; i++) {
			if(idx > 0 && res[idx-1] <= i) continue;
			res[idx] = i;
			deCount(idx+1);
		}
		
	}

}
