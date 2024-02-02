package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B14888 {
	static int[] operator;
	static int[] output;
	static int n;
	static boolean[] visited;
	static int[] arr;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		operator = new int[n - 1]; // 0 : +, 1 : -, 2 : *, 3 : /
		int[] tem = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			tem[i] = Integer.parseInt(st.nextToken());
		}
		
		int k = 0;
		for(int i = 0; i < 4; i++) {
			if(tem[i] == 0) continue;
			while(tem[i] > 0 && k < n-1) {
				operator[k] = i;
				tem[i]--;
				k++;
			}
		}	//연산자 갯수만큼 해당 연산자의 index값 저장 operator = {0, 0, 1, 2, 3}

		visited = new boolean[n - 1];
		output = new int[n - 1];
		perm(0);	//나올 수 있는 연산자 순열 만들어서 결과 체크
		System.out.println(max);
		System.out.println(min);

	}

	private static void perm(int idx) {
		if (idx == n - 1) {
			int result = arr[0];
			for (int i = 0; i < n - 1; i++) {
				if (output[i] == 0) { // +
					result = result + arr[i + 1];
				}
				if (output[i] == 1) { // -
					result = result - arr[i + 1];
				}
				if (output[i] == 2) { // *
					result = result * arr[i + 1];
				}
				if (output[i] == 3) { // /
					if (result < 0) {
						result = -1 * result;
						result = -1 * result / arr[i + 1];
					}else {
						result = result / arr[i + 1];
					}
					
				}
			}
			// 최대 최소판단
			max = Math.max(max, result);
			min = Math.min(min, result);
		}

		for (int i = 0; i < n - 1; i++) {
			if (!visited[i]) {
				output[idx] = operator[i];
				visited[i] = true;
				perm(idx + 1);
				visited[i] = false;
			}
		}
	}

}
