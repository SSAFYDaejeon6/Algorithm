/**Main_B_11403_경로찾기_이동준 12168KB 100ms
 * Facts
 * 	AdjMatrix A 를 만들었을 때,AA의 각 i행 j열 은 해당 정점에서 j까지 가는 길이 1의 경로 개수이다
 * 	AAA는 2의 경로이다....
 * 	AAAA는 4의 경로이다....
 * Idea
 * 	생각해볼 건 두 가지다.
 * 	
 * 	몇 번을 또는 언제까지 곱해야 하는가?
 * 		1: 경로존재를 기록하는 행렬이 꽉 차면
 * 		2: 더는 새로운 경로를 발굴해내지 못할 때
 * 			연산 결과 새 경로가 없음 -> 이미 방문한적 있는 node만 방문함 -> 이후 경로는 무조건 반복될 것!		
 * 		
 * Cycle
 * 	Cycle이 존재할 경우 해당 Cycle에 진입 가능한 모든 행은 여러 연산에 걸쳐 순환하게 될 것이다. 그러나 경로의 크기는 증가할 것이다.
 * 		
 * 
 * 프로세스
 * 	행렬곱을 정의한다.
 * 	boolean[][] checked를 정의한다
 * 	checked의 false 원소 개수를 기억한다
 * 
 * 	입력행렬 A를 받고, AA를 진행한다.
 * 	결과 행렬을 스캔해서 0이 아닌 원소가 checked에서 false이면 true로 바꾸고 true이면 냅둔다.
 * 	TODO: 위 두 작업은 한번에 가능하다
 * 
 * 	만약 false 원소 개수가 안 줄었으면 연산을 중지하고, 아니면 A를 계속 곱한다.
 * 	
 * TODO: 개선사항
 * 행렬 곱셈 시, i 행을 j열에 곱할 때 i 행은 무조건 0 처리해야 한다. -> Cycle을 조금이라도 제거하기 위함.
 * 	
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_11403_경로찾기_이동준 {
	final static int PN = 11403;
	final static char ONECHAR = '1';
	static BufferedReader br;
	static int N;
	static boolean[][] A;
	static boolean[][] checked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader((new StringBuilder("./b/")).append(PN).append(".txt").toString()));
		N = Integer.parseInt(br.readLine());
		A = new boolean[N][N];
		checked = new boolean[N][N]; //절대적인 찾은 경로 수는 중요하지 않고, 업데이트 되는지 안되는지만 체크하면 된다.

		String tempString;
		for(int r = 0; r < N; r++) {
			tempString = br.readLine();
			for(int c = 0; c < N; c++) {
				if(tempString.charAt(2 * c) == ONECHAR) {
					A[r][c] = true;
					checked[r][c] = true;
				}
			}
		}
		//입력끝
		boolean[][] multResult = A;
		do {
			multResult = Multiply(multResult, A);
		}while(isUpdated(multResult));
		//출력
		StringBuilder sb = new StringBuilder();
		for(boolean[] eachLine : checked) {
			for(boolean path : eachLine) {
				sb.append((path) ? 1 : 0).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	
	static boolean[][] Multiply(boolean[][] a, boolean[][] b){
		int targetR = a.length; int targetC = b[0].length;
		boolean[][] result = new boolean[targetR][targetC];
		boolean[] aRow;
		for(int r = 0; r < targetR; r++) {
			for(int c = 0; c < targetC; c++) {
				aRow = a[r];
				for(int i = 0; i < aRow.length; i++) {
					if(!(aRow[i] && b[i][c])) continue;
					result[r][c] = true;
					break;// 이 문제에서는 경로가 존재하는지가 중요하고, 경로 개수는 중요하지 않다
				}
			}
		}
		return result;
	}
	
	static boolean isUpdated(boolean[][] multResult) {
		boolean result = false;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(multResult[r][c] && !checked[r][c]) {//지금까지 미발견된 경로 발견
					result = true;
					checked[r][c] = true;
				}
			}
		}
		return result;
	}
}
