import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 11688kb 80ms
 * [문제 해석]
	새 집 NxN 격자판
	(r,c): (1,1)부터 시작 -> 각 칸은 빈 칸 |  벽
	
	파이프 형태
	ㅡ ㅡ (2개의 연속된 칸)
	회전 가능 (가로, 세로, 대각선 ↘)
	
	파이프를 빈 칸만 차지하게 이동
	밀 수 있는 방향은 총 3가지 (오른쪽, 아래, 오른쪽아래대각선)
	45도로만 회전 가능
	
	처음 파이프(1,1) (1,2) 차지
	방향을 가로
	파이프 한쪽 끝을 (N, N)로 이동시키는 방법의 개수
	
	[입력]
	1. 2<= N <= 16
	2. 집의 상태
		0: 빈칸
		1: 벽
	
	[출력]
	이동시키는 방법의 수, 없으면 0
	1,000,000보다 작거나 같음 <int형
	
	[문제 해결 프로세스]
	dp로 풀기
	현재 위치 (r-1, c-1)-(r,c)
	1. 가로 (0)
		(1) (r, c+1) = 0 : home[r][c+1][0]++;
		(2) (r+1, c) = 0 && (r+1, c+1) = 0 && (r, c+1) = 1 : home[r+1][c+1][2]++;
	2. 세로 (1)
		(1) (r+1, c) = 0 : home[r][c+1][1]++;
		(2) (r+1, c) = 0 && (r+1, c+1) = 0 && (r, c+1) = 1 : home[r+1][c+1][2]++;
	3. 대각선(2)
		(1) (r, c+1) = 0 : home[r][c+1][0]++;
		(2) (r+1, c) = 0 : home[r][c+1][1]++;
		(3) (r+1, c) = 0 && (r+1, c+1) = 0 && (r, c+1) = 1 : home[r+1][c+1][2]++;
	
	if : r == N, c == N 
		break;
 */
public class Main_17070_파이프옮기기1 {
	static int N;
	static int[][] map, dp[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		dp = new int[N+1][N+1][3];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][2][0] = 1;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==N && j==N) break;
				if(dp[i][j][0] > 0 && j<N) { //가로
					if(map[i][j+1]==0) dp[i][j+1][0] += dp[i][j][0];
					if(i<N && map[i][j+1]==0 && map[i+1][j] == 0 && map[i+1][j+1]==0)
						dp[i+1][j+1][2] += dp[i][j][0];
				}
				if(dp[i][j][1] > 0 && i<N) { //세로
					if(map[i+1][j]==0) dp[i+1][j][1] += dp[i][j][1];
					if(j<N && map[i][j+1]==0 && map[i+1][j] == 0 && map[i+1][j+1]==0)
						dp[i+1][j+1][2] += dp[i][j][1];
				}
				if(dp[i][j][2] > 0) { //대각선
					if(j<N && map[i][j+1]==0) dp[i][j+1][0] += dp[i][j][2];
					if(i<N && map[i+1][j]==0) dp[i+1][j][1] += dp[i][j][2];
					if(j<N && i<N && map[i][j+1]==0 && map[i+1][j] == 0 && map[i+1][j+1]==0)
						dp[i+1][j+1][2] += dp[i][j][2];
				}
			}
		}
		
		System.out.println(dp[N][N][0]+dp[N][N][1]+dp[N][N][2]);
		
	}

}
