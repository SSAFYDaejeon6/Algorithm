import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 입력 받을 때 숫자의 좌표값을 저장 (1차원)
 * 칸에 11이라는 숫자가 들어가면 bingo[11]에 저장 하는 것
 * 가로세로, 대각선에 대한 변수를 만들어서 좌표에 따른 값 갱신
 * 5가 되면 행이나 열 또는 대각선이 다 채워졌으므로 빙고
 */
public class 빙고 {
	static int[][] 가로세로;
	static int 대각선1, 대각선2, bCnt; // bCnt = 빙고 개수
	static Pair[] bingo;
	
	
	static class Pair{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	// [x][0] => 가로 [y][1] => 세로
	static void plus(int x, int y) {
		가로세로[x][0]++;
		가로세로[y][1]++;
		if(x==y) 대각선1++;
		if(x+y == 4) 대각선2++;
	}
	
	// 계속해서 bCnt가 올라가는 것을 방지
	static void bingoCheck(int x, int y) {
		if(가로세로[x][0]>=5) {
			가로세로[x][0] = 0;
			bCnt++;
		}
		if(가로세로[y][1]>=5) {
			가로세로[y][1] = 0;
			bCnt++;
		}
		if(대각선1 >= 5) {
			대각선1 = 0;
			bCnt++;
		}
		if(대각선2 >= 5) {
			대각선2 = 0;
			bCnt++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		bingo = new Pair[26];
		가로세로 = new int[5][2];

		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				int input = Integer.parseInt(st.nextToken());
				bingo[input] = new Pair(i, j);
			}
		}
		
		
		// 결과값
		int cnt = 1;

		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				int input = Integer.parseInt(st.nextToken());
				int x = bingo[input].x;
				int y = bingo[input].y;
				
				plus(x, y);
				
				bingoCheck(x, y);
				
				if(bCnt>=3) {
					System.out.println(cnt);
					System.exit(0); // 프로그램 종료
				}
				cnt++;
			}
		} 
	}

}
