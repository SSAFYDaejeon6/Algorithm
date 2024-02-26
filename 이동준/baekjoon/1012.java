import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * 
 * 
 * 먼저, MAP에 배추 정보 채움
그리고 한줄씩 읽으면서

이때 Check 는 좌, 상만 이루어짐 -> {0, -1} -> { -1, 0}

→이것만으로도 충분함. 상 체크가 해당 Node의 하 체크를 대신하는 것과 같고, 좌 체크는 해당 노드의 우 체크를 하는것과 같기 때문

---- 반례- > 좌우로 뒤집은 ㄴ 모양의 경우 서로 다른 두 group으로 판정됨
새 방법
먼저 MAP에 배추 정보 채우고
한줄씩 읽으면서 Cascading

 */
public class Main {
	static int[][] garden;
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new FileReader("1012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {

			String[] aLine = br.readLine().split(" ");
			int C = Integer.parseInt(aLine[0]);
			int R = Integer.parseInt(aLine[1]);
			int K = Integer.parseInt(aLine[2]);
			int wormsUsed = 0;
                 
			garden = new int[R][C];

			for(int i = 0; i < K; i++) {
				aLine = br.readLine().split(" ");
				garden[Integer.parseInt(aLine[1])][Integer.parseInt(aLine[0])] = -1;
			}

			for(int rCursor = 0; rCursor < R; rCursor++) {
				for(int cCursor = 0; cCursor < C; cCursor++) {
					if(garden[rCursor][cCursor] == -1) {
						wormsUsed++;
						garden[rCursor][cCursor] = wormsUsed;
						cascade(rCursor, cCursor, wormsUsed);
					}
				}
			}
			System.out.println(wormsUsed);
		}
	}

	public static void cascade(int middleR, int middleC, int wormsUsed) {
		int[] dr = {0, -1, 0, 1};//E, N, W, S
		int[] dc = {1, 0, -1, 0};      
		int newR;
		int newC;
		for(int direction = 0; direction < 4; direction++) {
			newR = middleR + dr[direction];
			newC = middleC + dc[direction];
			if((0 <= newR && newR < garden.length) && (0 <= newC && newC < garden[0].length)) {
				if(garden[newR][newC] == -1) {
					garden[newR][newC] = wormsUsed;
					cascade(newR, newC, wormsUsed);
				}
			}
		}
	}
}
