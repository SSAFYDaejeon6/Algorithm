import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 조건에 맞게 시간에 따라 설치 및 폭발
 * 주어진대로 단순하게 구현함
 * 21336KB | 640ms
 */
public class 봄버맨 {

	static int R, C, N;
	static int[][] map;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static boolean rangeCheck(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

	static void installBomb() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1)
					map[i][j] = 0;
				
				else {
					map[i][j]++;
				}
				
			}
		}
	}

	static void explosion() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 3) {
					map[i][j] = -1;
					for(int k=0; k<4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(rangeCheck(nx, ny) && map[nx][ny] != 3) {
							map[nx][ny] = -1;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if (line.charAt(j) == '.')
					map[i][j] = -1;
				else if (line.charAt(j) == 'O')
					map[i][j] = 1;
			}
		}

		int time = 1;

		while (time < N) {
			time++;
			installBomb();
			explosion();
			
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1)
					System.out.print('.');
				else
					System.out.print('O');
			}
			System.out.println();
		}

	}

}
