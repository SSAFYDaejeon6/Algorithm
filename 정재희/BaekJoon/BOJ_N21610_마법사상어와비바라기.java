import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 12,436KB | 144ms
 * 
 * 1. 초기 구름 위치 boolean 배열에 저장
 * 2. 이동
 * 	- 이동 후 새로운 boolean에 위치 체크
 *  - 값 증가
 * 3. 새로운 구름 위치가 담겨 있는 boolean배열에서 true인 경우만, 
 *    대각선 탐색 -> 물 복사 (경계 체크, 값 1이상임 체크)
 * 4. 새로운 구름 위치가 있던 경우 제외, 기존 boolean 배열에 또 다른 새로운 구름 생성 및 체크
 *   - 새로운 구름 배열은 초기화
 */
public class BOJ_N21610_마법사상어와비바라기 {

	static int[][] deltas = {{0, 0}, 
			{0, -1}, {-1, -1}, {-1,0}, {-1, 1}, 
			{0, 1}, {1, 1}, {1, 0}, {1, -1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] bucket = new int[N+1][N+1];
		boolean[][] cloud = new boolean[N+1][N+1];
		boolean[][] newCloud = new boolean[N+1][N+1];
		
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				bucket[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cloud[N][1] = cloud[N][2] = cloud[N-1][1] = cloud[N-1][2] = true;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			// 이동 위치 계산
			int dr = deltas[d][0] * s;
			int dc = deltas[d][1] * s;
			
			
			moveCloud(dr, dc, bucket, cloud, newCloud); // 이동과 동시에 +1
			
			cloneMagic(bucket, newCloud);  // 물 복사
			
			generateCloud(bucket, cloud, newCloud);  // 새로운 구름 생성

			
		}
		
		/* 물의 양 계산*/
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cnt += bucket[i][j];
			}
		}
		System.out.println(cnt);
	
	}

	// 새로운 구름 생성
	private static void generateCloud(int[][] bucket, boolean[][] cloud, boolean[][] newCloud) {
		int N = bucket.length-1;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cloud[i][j] = false;
				
				if(!newCloud[i][j] && bucket[i][j] >= 2) {
					cloud[i][j] = true;
					bucket[i][j] -= 2;
				}
				newCloud[i][j] = false;
			}
		}
	}
	
	// 비 내린 곳 물 복사
	private static void cloneMagic(int[][] bucket, boolean[][] newCloud) {
		int N = bucket.length-1;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(!newCloud[i][j]) continue;
				int cnt = 0;
				for (int d = 2; d < 9; d+=2) {
					int nr = i + deltas[d][0];
					int nc = j + deltas[d][1];
					
					if(nr < 1 || nr > N || nc < 1 || nc > N || bucket[nr][nc] < 1) continue;
					
					cnt++;
				}
				bucket[i][j] += cnt;
			}
		}
	}
	
	// 구름 이동 및 비 내려
	private static void moveCloud(int dr, int dc, int[][] bucket, boolean[][] cloud, boolean[][] newCloud) {
		int N = bucket.length-1;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(cloud[i][j] == false) continue;
				
				/* 구름 이동 */
				int nr = (dr+i)%N;
				int nc = (dc+j)%N;
				if(nr < 1) nr += N;
				if(nc < 1) nc += N;
			
				bucket[nr][nc]++;
				newCloud[nr][nc] = true;
			}
		}
	}
}
