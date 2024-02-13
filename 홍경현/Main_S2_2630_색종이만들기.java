package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 13716kb 116ms
 * 문제해석
	NxN 종이 (N=2^k, k≤7)
	1. 전체 종이가 같은 색x → 가로 세로 중간 부분을 잘라서 네 개의 N/2xN/2
	2. 위 과정 반복하여 모두 같은 색으로 칠해질 때까지 반복
	하얀색 색종이와 파란색 색종이의 개수
	
	입력
	1. N
	2. 정사각형 칸 (0: 흰색, 1: 파란색)
	
	출력
	1. 하얀색 색종이 개수
	2. 파란색 색종이 개수
 */

public class Main_S2_2630_색종이만들기 {
	static int N, arr[][], result[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		result = new int[2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		part(0, 0, N, arr[0][0]);
		System.out.printf("%d\n%d\n", result[0], result[1]);
	}
	
	//(r,c)부터 (r+n, c+n)까지 색상이 동일하다면 result 증가, 다르다면 n/2 분할하여 다시 탐색
	private static void part(int r, int c, int n, int color) {
		for(int i=r; i<r+n; i++) {
			for(int j=c; j<c+n; j++) {
				if(arr[i][j] != color) {
					part(r, c, n/2, arr[r][c]); //왼쪽 상단
					part(r, c+n/2, n/2, arr[r][c+n/2]); //오른쪽 상단
					part(r+n/2, c, n/2, arr[r+n/2][c]); //왼쪽 하단
					part(r+n/2, c+n/2, n/2, arr[r+n/2][c+n/2]); //오른쪽 하단
					return;
				}
			}
		}
		result[color]++;
	}

}
