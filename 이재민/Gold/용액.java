package algo0515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * N이 10만이라 완탐은 무조건 터짐
 * 2개의 합에 대해서 0에 가까운 값을 찾는것이고
 * 입력이 오름차순으로 주어지기 때문에 투포인터 사용
 * 31256KB | 264ms
 */
public class 용액 {
	static int N, a, b;
	static int[] arr;
	static int min_value;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0;
		int e = N-1;
		min_value = Integer.MAX_VALUE;
		while(s<e) {
			int m = arr[s] + arr[e];
			// 작은값이면 갱신 s와 e값 갱신을 위해 m 계산시에는 절대값을 적용하지 않음
			if(Math.abs(m) < min_value) {
				min_value = Math.abs(m);
				a = arr[s];
				b = arr[e];
			}
			// 만약 m이 0보다 크면 값을 줄여주기 위해서 e를 뺌
			if(m > 0) e--;
			// 만약 m이 0보다 작거나 같다면 값을 키워주기 위해서 s를 더해줌
			else s++;
		}
		System.out.println(a + " " + b);
	}

}
