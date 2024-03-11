import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main_B_1890_점프_이동준 11516KB 76ms
 * Facts
 * 		NxN 게임판
 * 		N <= 100
		한 칸에서 오른쪽 점프 또는 아래 점프만 가능
 *		최종 경로의 개수를 구해야 함
 *		경로의 개수는 Long이여야 함 (OverFlow 가능)
 *Idea
 *		일단 완탐으로 풀 수 있나?
 *		2^100-> 불가능
 *		시작점 -> 어떤 점 A -> 도착점을 생각할 때
 *		시작점 -> A 경우의 수 * A-> 도착점 = A를 지나 도착하는 경우의 수 이다.
 *		한 점에서 뻗어나가는 경우의 수는 단 두 개이다.
 *		N* N 개의 지점을 모두 탐색한다고 하자.
 *		매 지점마다, 자신의 오른쪽과 아래에 도달 가능한 점에 위 공식을 써서 해당 점까지 도달하는 경우의 수를 누적시킨다.
 *		왼쪽에서 ㅇ른쪽으로, 위에서 아래로 탐색하므로, 이런 방식으로 도달한 점은 해당 점까지 도달 가능한 모든 경우의 수를 계산한 것이 보장된다(이동은 아래 또는 오른쪽으로만 가능하므로)
 *
 *	프로세스
 *		입력을 받으면서 동시에 계산한다.
 *		Long[] 배열을 만들고, 곱셈을 이용해 결과를 누적시킨다.
 *	
 */
public class  Main_B_1890_점프_이동준 {
	final static int[] dr = {1, 0};
	final static int[] dc = {0, 1};
	static int N;
	static long[][] occurance;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		occurance = new long[N][N];
		occurance[0][0] = 1L;
		int nr, nc, current;
		String tempStr;
		for(int r = 0; r < N; r++) {
			tempStr = br.readLine();
			for(int c = 0; c < N; c++) {
				current = tempStr.charAt(2 * c) - '0';
				if(current == 0) continue;
				for(int dir = 0; dir < 2; dir++) {
					nr = r + current * dr[dir]; nc = c + current * dc[dir];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					occurance[nr][nc] += occurance[r][c];
				}
			}
		}
		System.out.println(occurance[N - 1][N  - 1]);
	}
}
