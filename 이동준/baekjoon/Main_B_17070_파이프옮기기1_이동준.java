import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Main_B_17070_파이프옮기기1_이동준 11576KB 	80ms
 * facts
 * 		NxN 크기 격자판
 * 		행/열->1부터 시작
 * 		파이프는 가로, 세로, 좌상우하 대각선의 형태를 가질 수 있다.
 * 			대각선일 경우에는 파이프 머리 도착지점의 좌, 상 역시 빈 칸이여야 한다.
 		3 <= N <= 16
	idea
		매 이동마다 가로 또는 아래로 움직이는 것이 보장된다. 따라서 N 의 최댓값이 16이므로,
		아무리 길어도 32번의 이동 안에는 움직일 수 있다.
		즉, 재귀를 이용한 DFS를 쓰는 것이 현명해 보인다.
		이때, 어떤 점에서 가로/대각선/세로로 진행했을때 최종점에 도달하는 경우의 수를 기록해놓을 수 있다.
	
	프로세스
		void 재귀함수
			현재 r, 현재 c, 현재 점 진입방향
			자신의 가능한 모든 방향에 대해서 
				자신의 해당 방향에 써있으면 continue
				안써있으면 해당 방향의 점에 대해 해당 방향으로 진입했을때 가능한 경우의수 자기자신에 기록
			 return값 자신의 가능한 모든 방향의 경우의 수 값.
 */


public class Main_B_17070_파이프옮기기1_이동준{
	static int N;
	final static int[] dr = {0, 1, 1};//우, 대각선, 하
	final static int[] dc = {1, 1, 0};
	final static boolean[][] validDir = {
			{true, true, false},
			{true, true, true},
			{false, true, true},
		};
	static char[][] map;
	static int[][][] counts;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		counts = new int[N][N][];
		String tempStr;
		for(int r = 0; r < N; r++) {
			tempStr = br.readLine();
			for(int c = 0; c < N; c++) {
				counts[r][c] = new int[] {-1,-1,-1};
				map[r][c] = tempStr.charAt(2 * c);
			}
		}
		//입력 끝
		//시작점은 0행 1열, 진입방향은 0
		DFS(0, 1, 0);
		System.out.println(counts[0][1][0] + counts[0][1][1]);
	}
	
	static void DFS(int r, int c, int inputDir) {
		int nr, nc, curSum;
		for(int nextDir = 0; nextDir < 3; nextDir++) {
			if(!validDir[inputDir][nextDir]) continue;
			if(counts[r][c][nextDir] != -1) continue;
			nr = r + dr[nextDir]; nc = c + dc[nextDir];
			if(!cSqr(nr, nc) || (nextDir == 1 && (!cSqr(nr - 1, nc) || !cSqr(nr, nc - 1)))) {
				counts[r][c][nextDir] = 0;//못놓거나 가망 없음
				continue;
			}
			if(nr == N - 1 && nc == N - 1) {//마지막 점 찾음
				counts[r][c][nextDir] = 1;
				continue;				
			}
			DFS(nr, nc, nextDir);
			curSum = 0;
			for(int nextOfNext = 0; nextOfNext < 3; nextOfNext++) {
				if(!validDir[nextDir][nextOfNext]) continue;
				curSum += counts[nr][nc][nextOfNext];
			}
			counts[r][c][nextDir] = curSum;
		}
	}
	static boolean cSqr(int r, int c) {
		if(r < 0 || r >= N || c <0 || c >= N) return false;
		if(map[r][c] != '0') return false;
		return true;
	}
}