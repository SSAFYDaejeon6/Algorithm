package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author SEOK BEOM LEE
 *42040kb	300ms
 */
public class BJ_G3_17779_게리맨더링2 {

	static int N;
	static int[][] graph;

	//1, 2 있을 경우 d1, d2의 최대값은 N-2
	static void check(int r, int c) {
		for(int x=1; x<=N-2;x++) {
			for(int y=1; y<=N-2;y++) {
				//밑으로 행이 r+x+y이 경계선 넘어가면 x
				//열로는 c를 기준으로 x, y경계선 넘어가면 x
				if(r+x+y<=N && 1 <= c-x && c+y<=N) {
					search(r, c, x, y);					
				}
			}
		}
	}

	static int result = Integer.MAX_VALUE;


	static void search(int X, int Y, int d1, int d2) {
		int[][] tmpGraph = new int[N+1][N+1];

		int[] sum = new int[6];

		//시작 y
		int sy = Y;
		//끝 Y
		int ey = Y;
		int cnt1 = d1;
		int cnt2 = d2;
		
		//5의 경우를 모두 더함
		for (int x = X; x <= X + d1 + d2; x++) {
			for (int y = sy; y <= ey; y++) {
				sum[5] += graph[x][y];
				tmpGraph[x][y] = 5;
			}

			if (cnt1 > 0) {
				sy--;
				cnt1--;
			} 
			else sy++;

			if (cnt2 > 0) {
				ey++;
				cnt2--;
			} 
			else ey--;
		}

		//1의 경우
		for (int r = 1; r < X + d1; r++) {
			for (int c = 1; c <= Y; c++) {
				if (tmpGraph[r][c] != 5) {
					sum[1] += graph[r][c];
					tmpGraph[r][c] = 1;
				}
			}
		}

		//2의 경우
		for (int r = 1; r <= X + d2; r++) {
			for (int c = Y + 1; c <= N; c++) {
				if (tmpGraph[r][c] != 5) {
					sum[2] += graph[r][c];
					tmpGraph[r][c] = 2;
				}
			}
		}

		//3의 경우
		for (int r = X + d1; r <= N; r++) {
			for (int c = 1; c < Y - d1 + d2; c++) {
				if (tmpGraph[r][c] != 5) {
					sum[3] += graph[r][c];
					tmpGraph[r][c] = 3;
				}
			}
		}

		//4의 경우
		for (int r = X + d2 + 1; r <= N; r++) {
			for (int c = Y - d1 + d2; c <= N; c++) {
				if (tmpGraph[r][c] != 5) {
					sum[4] += graph[r][c];
					tmpGraph[r][c] = 4;
				}
			}
		}

		//최소값 저장
		Arrays.sort(sum);
		result = Math.min(result, sum[5] - sum[1]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		graph = new int[N+1][N+1];

		for(int r=1; r<=N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=N;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		//		print(graph);

		for(int r=1; r<=N;r++) {
			for(int c=1; c<=N;c++) {
				check(r, c);
			}
		}
		System.out.println(result);
	}
	static void print(int[][] graph) {
		for(int[] r:graph) {
			for(int c:r) {
				System.out.print(c+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}