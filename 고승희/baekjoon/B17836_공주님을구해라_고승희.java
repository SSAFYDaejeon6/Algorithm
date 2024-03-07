import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/** 13576kb	128ms
 * 성 (N, M) 3~100 => (N-1, M-1)
 * 성 입구 (1,1) => (0,0)
 * 제한시간 T (1~10000)
 * 
 * T시간 이내
 * 용사는 한 칸 이동시 한 시간이 걸린다. 정확히 T시간만에 도달해도 구출 가능
 * 용사는 상하좌우로 이동 가능
 * 
 * 성에는 명검 '그람'이 숨겨져 있다 (반드시 1개)
 * 용사가 그람을 구하면 마법벽을 부수고 그 공간으로 갈 수 있다
 * 그람이 있는 곳에 도착시 바로 사용 가능
 * 
 * 
 * 0: 빈 공간, 1: 마법 벽, 2: 그람 공간
 * (1,1)과 (N, M)은 0이다.
 * 
 * [목표]
 * 용사가 제한시간 T시간 이내에 마법벽을 피해 (N, M) 도달 -> 최단시간 출력
 * T시간 이내에 구출할 수 없다면 Fail 출력
 * 
 * [문제풀이]
 * 0. 레벨별 bfs
 * 1. 시작지점에서부터 상하좌우로 bfs이동 -> 그람 획득시 해당 위치에서 (N,M)까지 최단거리 구하기
 * 	- 다음위치: 범위내, 마법벽 X, 방문 X
 * 2. 그람 미획득시, 마법벽 피해서 계속 진행
 * 
 * T시간 이내에 도달 못할 시 break
 * 
 * */
public class B17836_공주님을구해라_고승희 {
	static int N, M, T;
	static int[][] map;
	static boolean[][][] visited;
	static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};//상우하좌
	static int pr, pc;
	static int time;
	static class Node{
		int r, c;
		boolean gram = false;

		public Node(int r, int c, boolean gram) {
			super();
			this.r = r;
			this.c = c;
			this.gram = gram;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", gram=" + gram + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 2) {
					pr = i;
					pc = j;
				}
				map[i][j] = temp;
			}
		}		
		bfs();
	}
	
	static void bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		visited[0][0][0] = true;
		queue.add(new Node(0,0,false));
		A: while(!queue.isEmpty()) {
			//레벨별 bfs 진행
			int size = queue.size();
			
			while (size-- > 0 ) {
				Node temp = queue.poll();
				if (time > T) {
					break A;
				}
				if (temp.r == N-1 && temp.c == M-1 ) { //공주위치 도착시
					System.out.println(time);
					return;
				}
				
				if (temp.gram == true) { //그람 획득한 경우
					if (temp.c < M-1) { //column이 M-1보다 작은 경우
						visited[temp.r][temp.c+1][1] = true;
						queue.add(new Node(temp.r, temp.c+1, true));
					} else { //row가 N-1보다 작은 경우
						visited[temp.r+1][temp.c][1] = true;
						queue.add(new Node(temp.r+1, temp.c, true));
					}
				}
				else { //그람 없는 경우
					for (int d=0; d<4; d++) {
						int nr = temp.r + delta[d][0];
						int nc = temp.c + delta[d][1];
						if (!isInRange(nr,nc)) continue; //범위내
						if (map[nr][nc] == 1) continue; //마법벽
						if (visited[nr][nc][0]) continue; //방문여부 체크
						if (nr == pr && nc == pc) { //그람 획득시
							visited[nr][nc][1] = true;
							queue.add(new Node(nr,nc,true));
						} else { //그람 획득하는 경우가 아닐 때 
							visited[nr][nc][0] = true;
							queue.add(new Node(nr,nc, false));
						}
					}
				}
			}
			time++;
		}
		System.out.println("Fail"); // 도달실패 
	}
	
	static boolean isInRange(int r, int c) {
		return ( r >= 0 && r < N && c >= 0 && c < M);
	}

}
