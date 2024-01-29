import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2839_설탕배달 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        int cnt = Integer.MAX_VALUE;

		for (int i = 0; i <= N / 5; i++) {
			int remaining = N - (5 * i);
			if (remaining % 3 ==0) {
				int j = remaining /3;
				cnt = Math.min(cnt,  i+j);
			}
		}
		if (cnt == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else System.out.println(cnt);
	}
}
