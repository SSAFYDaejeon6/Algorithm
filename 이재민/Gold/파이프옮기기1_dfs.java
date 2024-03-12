import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 파이프 옮기기1 dfs 풀이
 * dp에서 현재 위치로 올 수 있는 이전 경로를 계산했던 경우와 다르게
 * 현재 위치에서 다음 경로 즉, 가로, 세로, 대각선으로 갈 수 있는 경로를 탐색
 * 문제에서 주어진 목적지인 오른쪽 최하단에 도달하게 되면 값을 ++ 해줌
 * N의 범위가 크지 않아서 가능한 방법
 * 16032KB | 196ms
 */
public class 파이프옮기기1_dfs {
	static int N;
	static int[][] map;
	static int res;
	
	static boolean rangeCheck(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	static void dfs(int x, int y, int dir) {
//		System.out.println(x + " " + y + " " + dir);
		if(x == N-1 && y == N-1) {
			res++;
			return ;
		}
		//현재 가로
		if(dir == 0) {
			if(rangeCheck(x, y+1) && map[x][y+1]==0) {
				dfs(x, y+1, 0);
			}
		}
		// 현재 세로
		else if(dir==1) {
			if(rangeCheck(x+1, y) && map[x+1][y]==0) {
				dfs(x+1, y, 1);
			}
		}
		
		// 현재 대각선
		else if(dir==2) {
			if(rangeCheck(x, y+1)  && map[x][y+1]==0) {
				dfs(x, y+1, 0);
			}
			if(rangeCheck(x+1, y) && map[x+1][y]==0) {
				dfs(x+1, y, 1);
			}
		}
		if(rangeCheck(x+1, y+1)) {
			if(map[x+1][y+1] == 1 || map[x+1][y] == 1 || map[x][y+1] == 1)
				return;
			dfs(x+1, y+1, 2);
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 1, 0);
		System.out.println(res);
	}

}
