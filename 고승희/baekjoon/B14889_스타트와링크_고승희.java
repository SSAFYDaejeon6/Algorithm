/*14232kb 276ms
 * 요리사 문제와 유사함
 * 1. 입력받기
 * 2. N/2 선택하여 조합 만들기
 * 3. 해당 조합에서 각각 능력치의 합 구하기
 * 4. 능력치끼리의 차 구하기 
 * 5. 능력치 차이의 최소값 구하기
 * */
package alg0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B14889_스타트와링크_고승희 {
	static int N;
	static int map[][];
	static boolean checked[];
	static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		checked = new boolean[N];
		min = Integer.MAX_VALUE;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//입력완료
		makeTeams(0,0);
		
		System.out.println(min);
		
		
	}
	static void makeTeams(int cnt, int start) {
		if (cnt == N/2) {
			score();
			return;
		}
		for (int i=start; i<N; i++) {
			checked[i] = true;
			makeTeams(cnt+1, i+1);
			checked[i] = false;
		}
	}
	
	static void score() {
		int scoreA = 0;
		int scoreB = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (checked[i] && checked[j]) scoreA += map[i][j];
				if (!checked[i] && !checked[j]) scoreB += map[i][j];
			}
		}
		int dif = Math.abs(scoreA - scoreB);

//		System.out.println("결과, checked"+Arrays.toString(checked)+"scoreA"+scoreA+"scoreB"+scoreB+"dif"+dif+"min"+min);
		min = Math.min(min, dif);
	}

}
