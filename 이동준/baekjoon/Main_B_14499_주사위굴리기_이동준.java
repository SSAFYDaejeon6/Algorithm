/**Main_B_14499_주사위굴리기_이동준 11920KB 88ms
 * Facts
 * 		지도 N X M -> 최저 1 최대 20
 * 		주사위 초기좌표 x, y
 * 		명령개수 K 최대 1000
 * 		주사위를 놓은 칸에 쓰여 있는 수는 항상 0이다.
 * 		주사위
 * 			앞
 * 		왼 윗 우 
 			뒤
 *			밑
 *		
 *	IDEA
 *		Face 객체를 구현하자.
 *		각 객체는 6개의 Socket중 하나에 해당하며, 상하좌우 굴림에 따라 계속 자리를 바꾼다.
 *		위 굴림
 *			앞 -> 밑,  밑-> 뒤, 뒤 -> 윗, 윗 -> 앞
 *		오른 굴림
 *			왼 -> 윗, 윗- > 우, 우 -> 밑, 밑 -> 왼
 *		데이터 구조
 *			Face[]4 Vertical
 *				순서대로 앞, 윗, 뒤, 밑 이다
 *				주사위 북쪽으로 굴리기
 *				0 을 기억하고, 123 을 012 에 복사한다. 3자리에 기억해둔 0을 넣는다
 *				1(위쪽)을 Horizontal의 1에 복사한다.
 *				주사위 남쪽으로 굴리기
 *				3 을 기억하고, 012를 123에 복사한다. 0 자리에 기억해둔 걸 넣는다
 *				1(위쪽)을 Horizontal의 1에 복사한다.
 *			Face[] Horizontal
 *				순서대로 좌 윗 우 이다.
 *				주사위 동쪽으로 굴리기
 *				2를 기억하고, 0 1을  12에 복사한다. Vertical[3](밑) 을 복사해서 0 에 놓고 기억해둔 걸 Vertical[3] 에 넣는다.
 *				1(위쪽)을 Vertical[1] 에 복사한다.
 *				주사위 서쪽으로 굴리기
 *				0을 기억하고, 1 2 를 0 1에 복사한다.  vertical [3] 를 복사해서 2에 놓고 기억해둔 걸 3자리에 놓는다
 *				1을 v 1에 복사한다.
 *			
 */		
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_14499_주사위굴리기_이동준 {
	final static int PN = 14499;
	static int N, M, r, c, K;
	static int[][] map;
	static int[] instructions;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader((new StringBuilder("./b/")).append(PN).append(".txt").toString()));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		instructions = new int[K];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			instructions[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		//----입력 끝
		StringBuilder sb = new StringBuilder();
		Dice dice = new Dice(r, c);
		int floorNumber;
		for(int i = 0; i < K; i++) {
			if(!dice.roll(instructions[i])) continue;//못 굴리면 걍 무시
			floorNumber = map[dice.r][dice.c];
			if(floorNumber == 0) {
				map[dice.r][dice.c] = dice.get()[Dice.DOWNFACE];
				
			}else {
				dice.get()[Dice.DOWNFACE] = floorNumber;
				map[dice.r][dice.c] = 0;
			}
			sb.append(dice.get()[Dice.UPFACE]).append('\n');
		}
		System.out.print(sb);
	}
	static class Dice{
		static final int EAST = 0;
		static final int WEST = 1;
		static final int NORTH = 2;
		static final int SOUTH = 3;
		static final int UPFACE = 1;
		static final int DOWNFACE = 3;
		static final int[] dr = {0, 0, -1, 1};//동, 서 ,북 ,남
		static final int[] dc = {1, -1, 0, 0};//동, 서 ,북 ,남
		int[] vertical;
		int[] horizontal;
		int r, c;
		
		public Dice(int r, int c) {
			vertical = new int[4]; // 순서대로 앞, 윗, 뒤, 밑 이다
			horizontal = new int[3]; //순서대로 좌 윗 우 이다.
			this.r = r;
			this.c = c;
		}
		
		int[] get() {
			return vertical;
		}
		
		boolean roll(int dir) {
			switch(dir){
			case EAST:
				return rollEAST();
			case WEST:
				return rollWEST();
			case NORTH:
				return rollNORTH();
			case SOUTH:
				return rollSOUTH();
			}
			return false;
		}
		
		boolean rollNORTH() {
			int[] next = nextCoord(r, c, NORTH);
			if(next[2] == 0) return false;
			r = next[0]; c = next[1];
			int temp = vertical[0];
			System.arraycopy(vertical, 1, vertical, 0, 3);
			vertical[3] = temp;
			horizontal[1] = vertical[1];
			return true;
		}
		
		boolean rollSOUTH() {
			int[] next = nextCoord(r, c, SOUTH);
			if(next[2] == 0) return false;
			r = next[0]; c = next[1];
			int temp = vertical[3];
			System.arraycopy(vertical, 0, vertical, 1, 3);
			vertical[0] = temp;
			horizontal[1] = vertical[1];
			return true;
		}
		
		boolean rollEAST() {
			int[] next = nextCoord(r, c, EAST);
			if(next[2] == 0) return false;
			r = next[0]; c = next[1];
			int temp = horizontal[2];
			System.arraycopy(horizontal, 0, horizontal, 1, 2);
			horizontal[0] = vertical[3];
			vertical[3] = temp;
			vertical[1] = horizontal[1];
			return true;
		}
		
		boolean rollWEST() {
			int[] next = nextCoord(r, c, WEST);
			if(next[2] == 0) return false;
			r = next[0]; c = next[1];
			int temp = horizontal[0];
			System.arraycopy(horizontal, 1, horizontal, 0, 2);
			horizontal[2] = vertical[3];
			vertical[3] = temp;
			vertical[1] = horizontal[1];
			return true;
		}
		
		static int[] nextCoord(int r, int c, int dir) {
			r = r + dr[dir]; c = c + dc[dir];
			if(r < 0 || r >= N || c < 0 || c >= M) return new int[] {r, c, 0};//fail
			return new int[] {r, c, 1};
		}
	}
}
