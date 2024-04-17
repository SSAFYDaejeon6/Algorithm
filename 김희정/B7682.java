import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 11584KB  |	80ms
/*
 * 최종 상태
0. 최종 상태가 아닌 조건 
	- (x의 갯수 - o의 갯수) == 1 or 0
1. X가 3칸을 이었을 때
	- x의 갯수 + 1 == o의 갯수( 갯수가 같으면 안됨)
2. O가 3칸을 이었을 때
	- x의 갯수 == o의 갯수( x가 더 크면 안됨)
3. 게임판이 가득 찼을 때
	- 이어진 3칸이 없을 때
	- x의 갯수 + 1 == o의 갯수

3칸이어졌는지 판단
1. 가로(case 3개)
2. 세로(case 3개)
3. 대각선(case 2개)
 */
public class B7682 {
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String line = br.readLine();
			if (line.equals("end")) {
				return;
			}

			map = new char[3][3];
			int xCnt = 0;
			int oCnt = 0;
			for (int i = 0; i < 9; i++) {
				char curr = line.charAt(i);
				map[i / 3][i % 3] = curr;
				if (curr == 'X')
					xCnt++;
				if (curr == 'O')
					oCnt++;
			}
			
			int diff = xCnt - oCnt;
			if (!(diff == 0 || diff == 1)) {
				System.out.println("invalid");
				continue;
			}

			if (diff == 0) {
				// O가 3칸을 이었을 때
				if (isConnected('O') && !isConnected('X')) {
					System.out.println("valid");
					continue;
				}
			}
			
			if(diff == 1) {
				//X가 3칸을 이었을 때
				if(isConnected('X') && !isConnected('O')) {
					System.out.println("valid");
					continue;
				}
				if(xCnt + oCnt == 9) {
					//게임판이 가득 찼을 때
					if(isConnected('O')) {
						System.out.println("invalid");
						continue;
					}
					System.out.println("valid");
					continue;
				}

				
			}
			
			System.out.println("invalid");

		}
	}

	static boolean isConnected(char target) {
		// 가로
		A: for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[i][j] != target) {
					continue A;
				}
			}
			return true;
		}

		// 세로
		A: for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[j][i] != target) {
					continue A;
				}
			}
			return true;
		}
		// 대각선
		boolean flag1 = true;
		boolean flag2 = true;
		for(int i = 0; i < 3; i++) {
			if(map[i][i] != target) {
				flag1 =  false;
			}
	
			for(int j = 0; j < 3; j++) {
				if(i+j == 2 && map[i][j] != target) {
					flag2 = false;
				}
			}
		}
		if(!flag1 && !flag2) return false;
		return true;
	}
}
