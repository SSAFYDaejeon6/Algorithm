/** Main_B_14500_테트로미노_이동준 36248KB 536ms
 * 0412 스터디 문제 테트로미노

테트로미노는 다음 경우 중 하나다.

1. 길이 4짜리 블록
2. 2x3 블록 안에서 사각형 두개 제외하기
3. 위 둘의 회전

Process

sliding window 로 푼다

1. 길이 4짜리 블록 세로 체크
2. 길이 4짜리 블록 가로 체크
3. 2x3 블록 가로 체크
    
    2x3 arr scope 가지고 있음.
    
    2x3범위의 총 합을 구한다. → 이건 sliding window로
    
    이제 테트로미노를 만들기 위해 square 두개 제외시킨다. 
    
    1차원 좌표로 중첩 for문으로 제외시킴. 이때 테트로미노가 안 되는 경우는 다음과 같다.
    
    1. 제외시킨 두 squre의 2차원 좌표 c 값이 일치하고, 둘다 1임(가운데 자름)
    2. 두 square 의 행은 다른데 c 값 차이는 1임.
4. 세로 체크
    1. 제외시킨 두 squre의 2차원 좌표 r 값이 일치하고, 둘다 1임(가운데 자름)
    2. 두 square 의 c는은 다른데 r값 차이는 1임.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_14500_테트로미노_이동준 {
	static BufferedReader br;
	final static int NORMAL = 0, TRANSPOSED = 1;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int N, M;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][][] map = new int[2][][];
		map[NORMAL] = new int[N][M];
		map[TRANSPOSED] = new int[M][N];
		for(int r = 0; r < N ;r ++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[NORMAL][r][c] = Integer.parseInt(st.nextToken());
				map[TRANSPOSED][c][r] = map[NORMAL][r][c];
			}
		}
		//입력 끝
		//목표로 하는 최댓값
		int MAX = 0;
		
		//가로 체크
		int[] rowBar = new int[4];
		for(int direction = NORMAL; direction < 2; direction++) {
			int[][] curMap = map[direction];
			for(int r = 0; r < curMap.length; r++) {
				int rowBarSum = 0;
				System.arraycopy(curMap[r], 0, rowBar, 0, 4);
				for(int i : rowBar) {
					rowBarSum += i;
				}
				MAX = Math.max(MAX, rowBarSum);
				for(int c = 4; c < curMap[0].length; c++) {
					rowBarSum -= curMap[r][c - 4];
					rowBarSum += curMap[r][c];
					MAX = Math.max(MAX, rowBarSum);
				}			
			}			
		}
		
		//2x3 체크
		int[][] rectangle = new int [2][3];
		for(int direction = NORMAL; direction < 2; direction++) {
			int[][] curMap = map[direction];
			int rLimit = curMap.length - 1;
			for(int r = 0; r < rLimit; r++) {
				int recSum = 0;
				System.arraycopy(curMap[r], 0, rectangle[0], 0, 3);
				System.arraycopy(curMap[r + 1], 0, rectangle[1], 0, 3);
				for(int[] ir : rectangle) {
					for(int i : ir) {
						recSum += i;
					}
				}
				MAX = Math.max(MAX, recCheck(rectangle, recSum));
				for(int c = 3; c < curMap[0].length; c++) {
					recSum -= curMap[r][c - 3];
					recSum -= curMap[r + 1][c - 3];
					recSum += curMap[r][c];
					recSum += curMap[r + 1][c];
					System.arraycopy(rectangle[0], 1, rectangle[0], 0, 2);
					System.arraycopy(rectangle[1], 1, rectangle[1], 0, 2);
					rectangle[0][2] = curMap[r][c];
					rectangle[1][2] = curMap[r + 1][c];
					MAX = Math.max(MAX, recCheck(rectangle, recSum));
				}	
			}
		}
		
		System.out.println(MAX);
	}
	static int recCheck(int[][] rec, int sum) {
		int max = 0;
		for(int a = 0; a < 6; a++) {
			for(int b = a + 1; b < 6; b++) {
				int ar = a / 3, ac = a % 3, br = b / 3, bc = b % 3;
				if(ac == bc && ac == 1) continue; //가운데 싹둑해서 테트로미노 안됨
				if(ar != br && Math.abs(ac - bc) == 1) continue;//빈 스퀘어가 서로 어긋남
				max = Math.max(max, sum - rec[ar][ac] - rec[br][bc]);
			}
		}
		return max;
	}
}
