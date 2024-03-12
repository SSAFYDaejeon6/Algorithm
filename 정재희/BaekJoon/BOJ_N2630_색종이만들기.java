package BaekJoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_N2630_색종이만들기 {
	/**
	 * 1. 조건에 맞지 않는 경우, 범위를 반씩 줄여서 탐색하는 재귀 함수
	 * 2. 칸 안에 모두 같은 수이거나, 칸이 1개인 경우의 조건을 검사하는 함수
	 * 
	 * 시간: 112ms, 메모리: 13,664KB
	 */

	static String[][] paper;  // 색종이 저장 배열
	static int[] res = new int[2];  // 0: 하얀색, 1: 파란색
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		paper = new String[N][];
		
		for (int i = 0; i < N; i++) {
			paper[i] = br.readLine().split(" ");
		}
		
		splitSearch(0, 0, N);
		System.out.println(res[0]);
		System.out.println(res[1]);
	}
	/**
	 * 
	 * @param r : 행의 시작 위치
	 * @param c : 열의 시작 위치
	 * @param n : 범위의 크기
	 */
	private static void splitSearch(int r, int c, int n) {
		if(n == 1 || search(r, c, n)) {   // 모두 같은 수이거나 칸이 1개인 경우, 종료
			res[Integer.parseInt(paper[r][c])]++;
			return;
		}
		int newN = n/2;
		// 종이 4등분해서 반복 작업 수행
		splitSearch(r, c, newN);
		splitSearch(r+newN, c, newN);
		splitSearch(r, c+newN, newN);
		splitSearch(r+newN, c+newN, newN);
	}
	
	/**
	 * 해당 구역 안에 모든 값이 동일한지 검사하는 함수
	 * @param r : 행의 시작 위치
	 * @param c : 열의 시작 위치
	 * @param n : 범위의 크기
	 * @return true: 해당 범위의 값이 모두 동일하다
	 */
	private static boolean search(int r, int c, int n) {
		String preV = paper[r][c];
		for (int i = r; i < r+n; i++) {
			for (int j = c; j < c+n; j++) {
				if(!preV.equals(paper[i][j])) return false;
			}
		}
		return true;
	}

}
