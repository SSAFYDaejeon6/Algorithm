/**Main_B_17836_공주님을구해라_이동준 11952KB 88ms
 * Facts
 * 	성의 크기 N, M <= 100
 * 
 * Idea
 * 	검을 집은 뒤에는 경로가 단순해진다.
 * 	용사는 검을 잡은 경로 vs 안 잡은 경로를 비교해야 한다.
 * 프로세스
 * 	BackTracking을 가미한 BFS를 진행한다.
 * 	
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_B_17836_공주님을구해라_이동준 {
	final static int PN = 17836;
	final static int[] dr = {0, -1, 0, 1};
	final static int[] dc = {1, 0, -1, 0};
	static int N, M, T;
	static BufferedReader br;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new FileReader((new StringBuilder("./b/")).append(PN).append(".txt").toString()));
		StringTokenizer	st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		String tempStr;
		int[] sword = {0, 0};
		for(int r = 0; r < N; r++) {
			tempStr = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = tempStr.charAt(2 * c);
				if(map[r][c] == '2') sword = new int[] {r,c};
			}
		}
		int swordLength = N - sword[0] + M - sword[1] - 2; 
		Deque<int[]> curQ = new ArrayDeque<>();
		Deque<int[]> nextQ = new ArrayDeque<>();
		
		curQ.offer(new int[] {0,0});
		boolean[][] isVisited = new boolean[N][M];
		isVisited[0][0] = true;
		int[] current;
		int cr, cc, nr, nc;
		int t = 0;
		int swordT = Integer.MAX_VALUE;
		int noSwordT = Integer.MAX_VALUE;
		while(true) {
			current = curQ.poll();
			cr = current[0]; cc = current[1];
			if(map[cr][cc] == '2') swordT = t + swordLength;
			if(cr == N - 1 && cc == M - 1) {
				noSwordT = t;
				break;
			}
			for(int dir = 0; dir < 4; dir++) {
				nr = cr + dr[dir]; nc = cc + dc[dir];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(map[nr][nc] == '1') continue;
				if(isVisited[nr][nc]) continue; 
				isVisited[nr][nc] = true;
				nextQ.offer(new int[] {nr, nc});
			}
			if(curQ.isEmpty()) {
				t++;
				if(t > T) break;
				Deque<int[]> temp = curQ;
				curQ = nextQ;
				nextQ = temp;
				if(curQ.isEmpty()) break;
			}
		}
		int finalTime = Math.min(swordT, noSwordT);
		if(finalTime > T) System.out.println("Fail");
		else System.out.println(finalTime);
	}
	
}
