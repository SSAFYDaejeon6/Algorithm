import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*11800kb 84ms
 * [문제 해석]
	NxN 게임판에 수
	목표: 가장 왼쪽 위 칸 -> 가장 오른쪽 아래칸 점프
	
	각 칸에 적혀있는 수: 현재 칸에서 갈 수 있는 거리
	오른쪽 or 아래쪽만 이동 가능
	0은 더 이상 진행을 막는 종착점, 항상 현재 칸에 적혀있는 수만큼 오른쪽 or 아래쪽 가야 함
	한 번 점프를 할 때 방향을 바꿀 수 없음
	
	경로의 개수 구하기
	
	[입력]
	1. 4<=N<=100
	2. 게임판 (0<=map[][]<=9)
	
	[출력]
	경로의 개수
 */
public class Main_1890_점프 {
	static int N;
	static int[][] map;
	static long[][] dp;
	static int[] dr = {0, 1}, dc = {1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new long[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//해당 위치로 갈 수 있는 경로의 수 
		dp[0][0] = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 0) break;
				if(i+map[i][j] < N) dp[i+map[i][j]][j] += dp[i][j]; //아래쪽 이동
				if(j+map[i][j] < N) dp[i][j+map[i][j]] += dp[i][j]; //오른쪽 이동
			}
		}
		
		System.out.println(dp[N-1][N-1]);
	}

}
