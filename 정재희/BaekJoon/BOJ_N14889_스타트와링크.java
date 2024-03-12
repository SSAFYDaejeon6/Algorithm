package BaekJoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_N14889_스타트와링크 {
	/**
	 * N개 중 N/2 개 조합을 뽑은 뒤 합 계산 수행
	 * 시간: 208ms | 메모리: 14,476KB
	 */
	static int N;
	static int[] picked;
	static int[] unpicked;
	static int minDiff = Integer.MAX_VALUE;
	static int[][] ability;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ability = new int[N][N];
		picked = new int[N/2];
		unpicked = new int[N/2];
		
		StringTokenizer st;
		/* 입력 받기 */
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination(0, 0);
		System.out.println(minDiff);
			
	}
	/**
	 * N개 중 N/2개 뽑기, 뽑은 후 sum 계산 및 최소값 갱신
	 * @param start
	 * @param depth
	 */
	private static void combination(int start, int depth) {
		if(depth == (N/2)) {
			int startSum = 0, linkSum = 0;
			int idxS = 0, idxL = 0;
			for (int i = 0; i < N; i++) {
				if(idxS < N/2 && i == picked[idxS]) {
					idxS++;
					continue;
				}
				unpicked[idxL++] = i;
			}
			for (int i = 0; i < N/2; i++) {
				for (int j = i; j < N/2; j++) {
					startSum += ability[picked[i]][picked[j]];
					startSum += ability[picked[j]][picked[i]];
					
					linkSum += ability[unpicked[i]][unpicked[j]];
					linkSum += ability[unpicked[j]][unpicked[i]];
				}
			}
			minDiff = Math.min(minDiff, Math.abs(startSum - linkSum));
			return;
		}
		for (int i = start; i < N; i++) {
			picked[depth] = i;
			combination(i+1, depth+1);
		}
	}

}
