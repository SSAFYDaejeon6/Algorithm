/**Main_B_16918_봄버맨_이동준 17904KB 152ms
 * 
 * Facts
 * 	R x C 크기 격자판
 * IDEA
 * 	2초 시작, 4초 시작, 6초 시작...마다 모든 칸에 폭탄이 채워진다.
 * 	2초 시작 채워진거 -> 5초에 전부 빈칸 됨. 이때 추가로 빈칸 생김.(4초에 생긴거 일부 지워짐)
 * 	초 시작
 			1	2	3	4	5	6	7	8	9	10	11	12
 * 	채움			0		3		5		7		9		11
 * 	터짐				0		2		4		6		8(7 - 6)??	
 *			2: 채움
 *			3: 0에서 있던대로 터져서 2 밀어내고 빈공간(0보다 큼)
 *			4: 3의 빈공간 채움
 *			5: 2에서 있던대로 터짐. 밀린 2 원복. 단 빈공간임
 *			6: 2모양 복구.!!!
 *패턴화 해볼 수 있다.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B_16918_봄버맨_이동준 {
	final static int PN = 16918;
	final static char floor = '.';
	final static char bomb = 'O';
	static BufferedReader br;
	static int R;
	static int C;
	static int N;//N초뒤
	static Bomb[][] map;
	static List<Bomb> bombList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader((new StringBuilder("./b/")).append(PN).append(".txt").toString()));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new Bomb[R][C];
		bombList = new ArrayList<>();
		String tempStr;
		for(int r = 0; r < R; r++) {
			tempStr = br.readLine();
			for(int c = 0; c < C; c++) {
				if(tempStr.charAt(c) == bomb) bombList.add(new Bomb(r, c, 2));
			}
		}
		//입력끝
		if(N >= 6) N = 2 + (N - 2) % 4;
		for(int t = 1; t < N; t++) { //t초부터 t + 1초까지 시뮬레이션 한다. for loop의 끝은 t + 1시점의 시작상태가 된다.
			for(int i = bombList.size() - 1; i >= 0; i--) {
				bombList.get(i).countDown();
			}
			for(int i = bombList.size() - 1; i >= 0; i--) {//없어진 폭탄 제거
				if(bombList.get(i).dead) bombList.remove(i);//조회가 오래걸려
			}
			if(t % 2 == 1) {
				for(int r = 0; r < R; r++) {
					for(int c = 0; c < C; c++) {
						if(map[r][c] != null) continue;
						bombList.add(new Bomb(r, c));
					}
				}
			}
//			System.out.println("--------------" + (t + 1) + "-------------------");
//			printMap();
		}
		printMap();
	}
	
	static void printMap() {
		StringBuilder sb = new StringBuilder();
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
//				sb.append((map[r][c] == null) ? floor : (char) (map[r][c].timer + '0'));
				sb.append((map[r][c] == null) ? floor : bomb);
			}
			sb.append('\n');
		}
		System.out.print(sb);		
	}
	
	private static class Bomb{//
		final static int[] dr = {0, -1, 0, 1};//우 상 좌 하
		final static int[] dc = {1, 0, -1, 0};
		int r, c, timer;
		boolean dead;

		Bomb(int r, int c, int timer) {
			super();
			this.r = r;
			this.c = c;
			this.timer = timer;
			map[r][c] = this;
		}

		Bomb(int r, int c) {
			this(r, c, 3);
		}

		void countDown() {
			timer--;
			if(timer == 0) explode();
		}

		void explode() {
			int nr, nc;
			Bomb target;
			for(int dir = 0; dir < 4; dir++) {
				nr = r + dr[dir]; nc = c + dc[dir];
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				target = map[nr][nc];
				if(target != null) {
					if(target.timer <= 1) continue;//타이머 1 이하인 폭탄은 면역
					target.kill();
				}
			}
			kill();
		}

		void kill() {
			map[r][c] = null;
			dead = true;
		}
	}
}
