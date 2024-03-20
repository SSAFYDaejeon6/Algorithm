import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 우선순위 큐를 사용 (매번 정렬이 일어나서 좀 느림)
 * 나머지 주석은 코드에
 * 21040KB | 200ms
 */

public class 상어초등학교 {
	static int N, res;
	static int[][] map;
	static List<Integer>[] friends; // 좋아하는 친구 담기
	// 현재 위치에 앉았을 때 주변 빈 공간과 좋아하는 친구 수를 담는 우선순위 큐
	static PriorityQueue<Loc> q; 
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] 만족도 = {0, 1, 10, 100, 1000};

	static class Loc implements Comparable<Loc> {
		int x, y; // 위치
		int empty, friends; // 빈 공간 및 좋아하는 친구 수

		public Loc(int x, int y, int empty, int friends) {
			this.x = x;
			this.y = y;
			this.empty = empty;
			this.friends = friends;
		}

		// Comparable을 상속 받아 Loc를 참조타입으로 하는
		// 우선순위 큐의 정렬 순서를 재정의
		@Override
		public int compareTo(Loc o) {
			if(this.friends == o.friends) {
				if(this.empty == o.empty) {
					if(this.x == o.x) {
						// 4번 조건 중 열의 번호가 작은 칸
						return this.y - o.y;
					}
					
					// 3번 조건 중 행의 번호가 작은 칸
					return this.x - o.x;
				}
				// 2번 조건 인접 칸에 빈 칸이 제일 많은 칸
				return o.empty - this.empty;
			}
			// 1번 조건 인접 칸에 좋아하는 학생이 가장 많은 칸
			return o.friends - this.friends;
		}

	}

	static boolean rangeCheck(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static void location(int sNum) {
		q = new PriorityQueue<>();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c] != 0 ) continue;
				int fCnt = 0; // 좋아하는 친구 수
				int eCnt = 0; // 빈 자리 수

				// 인접 칸 탐색
				for (int k = 0; k < 4; k++) {
					int nr = r + dx[k];
					int nc = c + dy[k];

					if (!rangeCheck(nr, nc))
						continue;
					
					// 빈 공간이면
					if(map[nr][nc] == 0) {
						eCnt++;
						continue;
					}
					
					// 빈 공간이 아닐 때 좋아하는 친구가 있는 칸인지 탐색
					for(int p=0; p<4; p++) {
						if(map[nr][nc] == friends[sNum].get(p)) {
							fCnt++;
							break;
						}
					}
				}
				// 현재 r, c에서 탐색이 끝나면 빈공간과 친구의 수를 pq에 삽입
				q.add(new Loc(r, c, eCnt, fCnt));
			}
		}
		
		// 자리 앉히기
		// 우선순위 큐로 정렬을 했기 때문에 맨 앞에 있는(peek)자리가
		// 현재 학생(sNum)이 앉을 자리
		map[q.peek().x][q.peek().y] = sNum;
		
	}
	
	// 만족도 계산
	static void sum() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				// 좋아하는 친구는 몇명?
				int fCnt = 0;
				for(int k=0; k<4; k++) {
					int nr = r + dx[k];
					int nc = c + dy[k];
					
					if(!rangeCheck(nr, nc)) continue;
					// 현재 자리에 앉아 있는 학생
					int sNum = map[r][c];
					for(int p=0; p<4; p++) {
						if(map[nr][nc] == friends[sNum].get(p)) {
							fCnt++;
							break;
						}
					}
				}
				res += 만족도[fCnt];
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		friends = new List[N * N + 1];

		for (int i = 1; i <= N * N; i++) {
			friends[i] = new ArrayList<>();
		}

		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());

			int sNum = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				friends[sNum].add(Integer.parseInt(st.nextToken()));
			}
			location(sNum);
		}
		
		sum();
		
		System.out.println(res);
		
	}
}
