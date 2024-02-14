/**Main_B_14889_스타트와링크_이동준 20164 kb 372 ms
 *Idea
 * NextPermutation
 * 	또, 덧셈을 미리 해둬서 i, j 조합을 한 번만 센다.(윗 삼각행렬)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B_14889_스타트와링크_이동준 {
	static BufferedReader br;
	static int T;
	static int N;
	static int[][] synergies;
	static int[] pickMap;
	static int MIN;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
//				br = new BufferedReader(new FileReader("./b/14889.txt"));
		MIN = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		synergies = new int[N][N];
		String[] sta;
		for(int r = 0; r < N; r++) {
			sta = br.readLine().split(" ");
			for(int c = 0 ; c < N; c++) {
				synergies[r][c] = Integer.parseInt(sta[c]);
			}
		}
		
		//입력끝
		for(int c = 1; c < N; c++) {//윗 삼각행렬
			for(int r = 0; r < c; r++) {
				synergies[r][c] += synergies[c][r];
			}
		}
		
		pickMap = new int[N];
		for(int i = (N / 2) - 1; i > -1; i--) pickMap[i] = 1;
		Arrays.sort(pickMap);
		do {
			int[] AB = new int[2];
			for(int i = 0; i < N; i++) {
				for(int j = i + 1; j < N; j++) {
					if(pickMap[j] != pickMap[i]) continue;
					AB[pickMap[i]] +=  synergies[i][j];
				}
			}
			MIN = Math.min(MIN, Math.abs(AB[0] - AB[1]));
		}while(nextPermutation(pickMap));
		System.out.println(MIN);
	}
	static boolean nextPermutation(int[] perm) {
		int endIdx = perm.length - 1;
		int cursor = endIdx;
		for(; cursor > 0 && perm[cursor - 1] >= perm[cursor];) cursor--;
		if(cursor == 0) return false;//there's no next perm
		--cursor;
		int cursor2 = endIdx;
		for(; perm[cursor2] <= perm[cursor];) cursor2--;
		swap(perm, cursor, cursor2);
		cursor2 = endIdx;
		for(++cursor; cursor < cursor2;) swap(perm, cursor++, cursor2--);//오름차순
		return true;
	}

	static void swap(int[] arr, int a, int b) {
		int temp = arr[b];
		arr[b] = arr[a];
		arr[a] = temp;
	}
}
