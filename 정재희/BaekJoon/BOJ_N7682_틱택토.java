import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 11,460KB | 80ms
 * 
 * 최종상태인 경우
 * 1) 둘 중 하나만 이긴 경우
 * 	- X가 이겼을 때, x가 선공이라, 1개 더 많아야 함
 *  - O가 이겼을 때, o는 후공이니깐 x가 놓여있는 개수와 같아야 함
 * 2) 둘 다 이기지 못한 경우, 게임판이 꽉 찼을 때
 *  - 게임판은 9개로 홀수이므로 선공인 x의 개수가 항상 1개 더 많아야 함
 */
public class BOJ_N7682_틱택토{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String str = br.readLine();
			if(str.equals("end")) {
				System.out.println(sb);
				break;
			}
			
			int xCount = 0;  // x 개수
			int oCount = 0;  // o 개수
			
			// 0 인덱스 : x정보, 1 인덱스: o 정보
			int[][] row = new int[2][3];  // 각 행에 대한 결과
			int[][] col = new int[2][3];  // 각 열에 대한 결과
			int[][] diag = new int[2][2];  // 양쪽 대각선에 대한 결과
			
			boolean xFlag = false; // x가 이겼는지에 대한 여부
			boolean oFlag = false;  // o이 이겼는지에 대한 여부
			for (int i = 0; i < 9; i++) {
				char c = str.charAt(i);
				int j = i/3;
				int k = i%3;
				if(c == 'X') {
					row[0][j] |= (1 << k);
					col[0][k] |= (1 << j);
					if(j == k)
						diag[0][0] |= (1 << j);
					if(j+k == 2)
						diag[0][1] |= (1 << j);
					
					xCount++;
				}
				else if(c == 'O') {
					row[1][j] |= (1 << k);
					col[1][k] |= (1 << j);
					if(j == k)
						diag[1][0] |= (1 << j);
					if(j+k == 2)
						diag[1][1] |= (1 << j);
					oCount++;
				}
				if(row[0][j] == 7 || col[0][k] == 7 || diag[0][0] == 7|| diag[0][1] == 7) xFlag = true;
				if(row[1][j] == 7 || col[1][k] == 7 || diag[1][0] == 7|| diag[1][1] == 7) oFlag = true;
			}
			
			int diff = xCount - oCount;
		
			boolean isValid = false;
			if(xFlag^oFlag) {
				if(xFlag) {
					if(diff == 1) isValid = true;
				}
				else if(oFlag) {
					if(diff == 0) isValid = true;
				}
			}
			if(!xFlag && !oFlag){
				if(diff == 1 && (xCount + oCount) == 9) isValid = true;
			}
						
			if(!isValid) sb.append("invalid").append("\n");
			else sb.append("valid").append("\n");
		}
	}

}