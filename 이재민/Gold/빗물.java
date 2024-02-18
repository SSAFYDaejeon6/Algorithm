import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 현재 인덱스에서의 빗물은 왼쪽에서 제일 큰값과 오른쪽에서 제일 큰 값을 비교해
 * 그 중 작은 것 만큼 쌓임
 * 하지만 현재 인덱스에서 가장 큰 부분이 왼쪽 오른쪽이 아닌 인덱스 부분이라면 빗물이 고일 수 없음
 * 삼각형 지붕에 빗물이 고일 수 있나를 생각해보면 될듯
 * 11800KB | 92ms 
 */

public class 빗물 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int res = 0;
		int[] arr = new int[W];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < W - 1; i++) {
			
			int left = 0;
			int right = 0;
			
			for(int j=0; j<i; j++) {
				left = Math.max(left, arr[j]);
			}

			for(int j=i+1; j<W; j++) {
				right = Math.max(right, arr[j]);
			}
			
			res += (Math.min(left, right)  > arr[i]) ?
					Math.min(left, right) - arr[i] : 0;
		}
		
		System.out.println(res);
	}

}
