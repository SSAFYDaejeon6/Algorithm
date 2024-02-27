package algo0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//12716kb 136ms
public class BJ_G4_14499_주사위굴리기 {

	static int R, C, curR, curC, num;

	static int[][] graph;

	static int[] nextList = new int[5];

	static int[] cntList = new int[6+1];

	static int[] dr = {0, 0, 0, -1, 1};
	static int[] dc = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		curR = Integer.parseInt(st.nextToken());
		curC = Integer.parseInt(st.nextToken());
		num = Integer.parseInt(st.nextToken());

		graph = new int[R][C];

		for(int r=0; r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		//초기에는 윗면이 1
		nextList[1] = 3;
		nextList[2] = 4;
		nextList[3] = 5;
		nextList[4] = 2;

		int current = 1;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<num;i++) {
			int rotate = Integer.parseInt(st.nextToken());

			int nr = curR + dr[rotate];
			int nc = curC + dc[rotate];
			if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			int tmp = 0;

			switch(rotate) {
			case 1:
				tmp = nextList[1];
				System.out.println(cntList[tmp]);

				//남북으로만 바꾸기
				nextList[2] = current;
				nextList[1] = 7 - current;

				//복사 후 0으로 초기화
				if(graph[nr][nc] == 0) {
					graph[nr][nc] = cntList[7-tmp];
				}
				else {
					cntList[7-tmp] = graph[nr][nc];
					graph[nr][nc]  = 0;
				}
				current = tmp;
				break;
			case 2:
				tmp = nextList[2];
				System.out.println(cntList[tmp]);

				//남북으로만 바꾸기
				nextList[1] = current;
				nextList[2] = 7 - current;

				//복사 후 0으로 초기화
				if(graph[nr][nc] == 0) {
					graph[nr][nc] = cntList[7-tmp];
				}
				else {
					cntList[7-tmp] = graph[nr][nc];
					graph[nr][nc]  = 0;
				}
				current = tmp;
				break;
			case 3:
				tmp = nextList[3];
//				System.out.println(tmp);
				System.out.println(cntList[tmp]);
//				System.out.println();
				
				//남북으로만 바꾸기
				nextList[4] = current;
				nextList[3] = 7 - current;

				//복사 후 0으로 초기화
				if(graph[nr][nc] == 0) {
					graph[nr][nc] = cntList[7-tmp];
				}
				else {
					cntList[7-tmp] = graph[nr][nc];
					graph[nr][nc]  = 0;
				}
				current = tmp;
				break;
			case 4:
				tmp = nextList[4];
				System.out.println(cntList[tmp]);

				//남북으로만 바꾸기
				nextList[3] = current;
				nextList[4] = 7 - current;

				//복사 후 0으로 초기화
				if(graph[nr][nc] == 0) {
					graph[nr][nc] = cntList[7-tmp];
				}
				else {
					cntList[7-tmp] = graph[nr][nc];
					graph[nr][nc]  = 0;
				}
				current = tmp;
				break;
			}

			curR = nr;
			curC = nc;
		}
	}
}
