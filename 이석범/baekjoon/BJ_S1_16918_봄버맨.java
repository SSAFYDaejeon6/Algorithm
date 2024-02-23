package algo0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//18508kb 568ms
//200^3 => 800만
//큐로 폭탄 넣어서 하면 빠를듯
public class BJ_S1_16918_봄버맨 {
	static int R, C, time;

	static int[][] graph;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	
	/**
	 * 주변 폭발
	 */
	static void bomb(int r, int c) {
		graph[r][c] = 0;
		for(int i=0; i<4;i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc] == 2) continue;
			graph[nr][nc] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());

		graph = new int[R][C];

		for(int r=0; r<R;r++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for(int c=0; c<C;c++) {
				if(str.charAt(c)=='.') graph[r][c] = 0;
				else graph[r][c] = 1;
			}
		}

		//1초는 그대로 므로 1초가 아닌 경우 
		if(time!=1) {
			for(int i=2;i<=time;i++) {
//				print();
				for(int r=0; r<R;r++) {
					for(int c=0; c<C;c++) {
						//i가 짝수번째이면 전체적으로 +1
						if(i%2==0) graph[r][c] += 1;
						//아닌 경우 폭발
						else {
							if(graph[r][c] == 2) bomb(r, c);
						}
					}
				}
			}
		}
		print();

	}

	static void print() {
		for (int[] is : graph) {
			for (int i : is) {
				if(i==0) System.out.print(".");
				else System.out.print("O");
			}
			System.out.println();
		}
	}
}
