import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
// [BOJ] 1941 소문난 칠공주
//	88508KB |	276ms
public class B1941 {
	static char[][] map;
	static boolean[] visited;
	static int[] output;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];
		answer = 0;
		for (int i = 0; i < 5; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < 5; j++) {
				map[i][j] = line[j];
			}
		}
		// 로직
		// 1. 7명 뽑기
		output = new int[7];
		comb(0, 0,0);

		System.out.println(answer);
	}

	static void comb(int idx, int start, int yCnt) {
		if(yCnt >= 4) return;
		
		if (idx == 7) {
			//7명 뽑기 완료	
      //2. 7명 후보 모두 인접한지 검사
			visited = new boolean[7];
			BFS();
			return;
		}

		for (int i = start; i < 25; i++) {
			output[idx] = i;
			comb(idx+1, i+1, (map[i/5][i%5] == 'S') ? yCnt : yCnt+1);
		}
	}
	
	static void BFS() {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {output[0] / 5, output[0] % 5});
		visited[0] = true;
		
		int cnt = 1;	//이미 한명 체킹하므로 1로 초기화
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = curr[0] + dx[d];
				int ny = curr[1] + dy[d];
				int nnum = nx*5 + ny;
				
				if(!inRange(nx, ny)) continue;
				
				//뽑은 7명이 서로 인접해 있는지 확인
				for(int i = 0; i < 7; i++) {
					if(!visited[i] && output[i] == nnum) {	//상하좌우로 연결된 것 중 뽑은 7명 후보에 존재한다.
						q.add(new int[] {nx,ny});
						visited[i] = true;
						cnt++;
					}
				}
			}
		}
		if(cnt == 7) {
			//뽑은 7명이 모두 인접해 있다면
			answer++;
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < 5 && 0 <= y && y < 5;
	}
	
}
