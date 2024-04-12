import java.io.*;
import java.util.*;

//31432kb	468ms
public class Main_G4_14500_테트로미노 {
	static int N, M, res;
	static int[][] map;
	
	//19개의 테트로미노 조합 집합...
	static int[][] dr = {  {1, 2, 3}, { 0, 0, 0},
							{0, 0, -1}, {0, 0, 1}, {1, 1, 1}, {1, 0, 0},
							{0, 1, 2}, {0, 1, 2}, {1, 2, 2}, {0, -1, -2},
							{1, 1, 2}, {0, -1, -1}, {1, 1, 2}, {0, 1, 1},
							{0, 1, 0}, {1, 1, 2}, {-1, 0, 1}, {0, -1, 0},
							{0, 1, 1}
						};
	static int[][] dc = { 	{0, 0, 0}, {1, 2, 3},
							{1, 2, 2}, {1, 2, 2}, {0, 1, 2}, {0, 1, 2},
							{1, 0, 0}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1},
							{0, 1, 1}, {1, 1, 2}, {0, -1, -1}, {1, 1, 2},
							{1, 1, 2}, {0, 1, 0}, {1, 1, 1}, {1, 1, 2},
							{1, 0, 1}
						};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		findMaxValue();
		
		System.out.println(res);
	}
	
	private static void findMaxValue() {
		int nr = 0, nc = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int k=0; k<dr.length; k++) {
					int sum = map[i][j];
					boolean b = false;
					for(int idx=0; idx<3; idx++) {
						nr = i+dr[k][idx];
						nc = j+dc[k][idx];
						if(!range(nr, nc)) {
							b = true;
							break;
						}
						sum += map[nr][nc];
					}
					if(!b) {
						res = Math.max(res, sum);
					}
				}
			}
		}
	}

	private static boolean range(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
