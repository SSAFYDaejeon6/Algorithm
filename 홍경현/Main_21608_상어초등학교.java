package Algorithm.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 14976kb 148ms
 * [문제 해석]
	NxN 교실, 학생수 N^2
	학생 번호 1번 ~ N^2번
	(1,1)칸~(N,N)칸
	
	학생의 순서, 각 학생이 좋아하는 학생 4명 조사
	
	ㅣr1-r2ㅣ+ ㅣc1-c2ㅣ = 1을 만족하는 두 칸이 인접하다
		1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 장리를 정함
		2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸
		3. 2를 만족하는 칸이 여러 개라면 행의 번호가 가장 작은칸, 열의 번호가 가장 작은칸
	
	각 학생과 인접한 칸에 앉은 좋아하는 학생의 수
	그 값이 0이면 0
	1이면 1
	2이면 10
	3이면 100
	4이면 1000
	
	학생의 만족도의 총합
	
	[입력]
	1. N <=20
	2. N^2개의 줄 : 학생의 번호, 학생이 좋아하는 4명의 번호
	
	[출력]
	만족도 총 합
 */
public class Main_21608_상어초등학교 {
	static int N;
	static int[][] map;
	static List<Integer>[] list; //친한 친구 리스트
	static Queue<Integer> queue = new ArrayDeque<>(); //순서
	static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		list = new List[N*N+1];
		for(int i=1; i<=N*N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			for(int k=0; k<4; k++) list[idx].add(Integer.parseInt(st.nextToken()));
			queue.add(idx);
		}
		
		int n = queue.poll();
		map[1][1] = n; 
		
		while(!queue.isEmpty()) {
			n = queue.poll();
			search(n);
		}
		
		int result = 0;
		int[] res = {0, 1, 10, 100, 1000};
		
		//학생의 만족도 계산
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int like = 0;
				n = map[i][j];
				for(int d=0; d<4; d++) {
					int nr = i+dr[d];
					int nc = j+dc[d];
					if(!range(nr,nc)) continue;
					for(int k=0; k<4; k++) {
						if(map[nr][nc] == list[map[i][j]].get(k)) {
							like++;
							break;
						}
					}
				}
				result += res[like];
			}
		}
		System.out.println(result);
	}

	private static void search(int n) {
		int likeMax = 0; //최대 좋아하는 인접 학생 수
		int empty = 0; //최대 비어있는 칸
		int r = N+1; 
		int c = -N+1;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] > 0) continue;
				int like = 0;
				int curEmpty = 0;
				//현재 비어있는 자리면 사방 탐색하여 좋아하는 인접 학생 수 및 비어있는 칸 계산
				for(int d=0; d<4; d++) {
					int nr = i+dr[d];
					int nc = j+dc[d];
					if(!range(nr,nc)) continue;
					if(map[nr][nc] == 0) curEmpty++;
					else { //리스트 돌기
						for(int k=0; k<4; k++) {
							if(map[nr][nc] == list[n].get(k)) {
								like++;
								break;
							}
						}
					}
				}
				
				if(like>likeMax) { //현재 좋아하는 학생의 수가 최대면
					likeMax = like;
					r = i;
					c = j;
					empty = curEmpty;
				}else if(like == likeMax) {//현재 좋아하는 학생의 수가 최대 수와 같다면 비어있는 칸 확인
					if(empty < curEmpty) { 
						r = i;
						c = j;
						empty = curEmpty;
					}else if(empty == curEmpty) { //비어있는 칸이 갔다면 r과 c행 비교
						if(r > i) {
							r = i;
							c = j;
							empty = curEmpty;
						}else if(r==i) {
							if(c>j) {
								c = j;
								empty = curEmpty;
							}
						}
					}
				}
			}
		}
		
		map[r][c] = n;
	}

	private static boolean range(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}

}
