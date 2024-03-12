package BaekJoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 295,500KB | 536ms
 * 
 * 1. 큐 이용하여 인구 수 차이가 범위 안에 들어오는 나라들 가져오기
 * 2. 값 계산 후 연합 나라들 인구 수 갱신
 * 3. 모든 나라를 방문할 때까지 반복
 * 4. 이를 바뀌는 날이 없을 때까지 반복
 */
public class BOJ_N16234_인구이동 {
	static int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	static int[][] countries;
	static int N, L, R;
	static boolean[][] isVisited;
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		countries = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				countries[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean flag = true;   // 인구 이동이 있었는지 체크
		int days = 0;          // 인구 이동된 날 세기
		while(flag) {
			flag = false;
			isVisited = new boolean[N][N];     // 방문한 나라 체크
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(isVisited[i][j]) continue;
					if(combineCountry(i, j)) flag = true;   // 연합 나라가 발생한 경우만 인구 이동했음 표시
				}
			}
			days += flag? 1:0;
		}
		System.out.println(days);
		
	}
	
	/*
	 * 주변 나라들과의 인구 수 차이를 계산 -> 범위 안에 포함되는 나라들의 인구 수 갱신
	 */
	private static boolean combineCountry(int i, int j) {
		List<int[]> list = new ArrayList<>();
		Queue<int[]> queue = new ArrayDeque<>();
		
		int sum = countries[i][j];
		int cnt = 1;
		
		isVisited[i][j] = true;
		queue.offer(new int[] {i, j});
		list.add(new int[] {i, j});
		int[] pos;
		while(!queue.isEmpty()) {
			pos = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = pos[0] + deltas[d][0];
				int nc = pos[1] + deltas[d][1];
				
				if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1 || isVisited[nr][nc]) continue;
				
				int diff = Math.abs(countries[pos[0]][pos[1]] - countries[nr][nc]);
				if(diff < L || diff > R) continue;
				
				isVisited[nr][nc] = true;
				int[] nn = new int[] {nr, nc};
				queue.offer(nn);
				list.add(nn);
				sum += countries[nr][nc];
				cnt++;
			}
		}
		if(cnt == 1) return false;
		
		int peoples = sum / cnt;
		for (int[] p : list) {
			countries[p[0]][p[1]] = peoples;
		}
		
		return true;
	}

}
