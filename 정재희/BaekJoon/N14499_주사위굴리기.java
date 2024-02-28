import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11,848KB | 88ms
 * 
 * 처음 주사위의 모든 면 -> 0
 * 칸 = 0 => 주사위(바닥면) -> 칸
 * 칸 != 0 => 칸의 수 -> 주사위(바닥면) & 칸 = 0으로 설정
 * 
 * 좌표, 이동시키는 명령 -> 주사위 상단에 쓰여 있는 값을 구하는 프로그램
 * 
 * 주사위 -> 마주보는 면의 합 = 7
 * 주사위 배열 -> 각 칸의 값
 * 
 * 바깥 이동 X -> 이동가능한지 검사한 후에 이동
 *
 * 처음 칸은 무조건 0
 * 1: 동, 2: 서, 3: 북, 4: 남
 * 
 * 1. 입력
 * 2. 밑의 수 초기화 -> 이동 시 좌표 범위 검사 -> 이동
 *   - 주사위 이동 => 배열 이용
 * 3. 해당 좌표의 값 검사
 *    0 -> 해당 주사위의 바닥면의 값을 해당 좌표에 복사 
 *    0 이상 -> 해당 주사위 바닥면 값에 복사, 해당 좌표 0으로 초기화
 *    주사위 윗면 수 출력
 */
public class N14499_주사위굴리기 {

	public static void main(String[] args) throws IOException {
		int[] move = {1, 3, 4, 2, 5}; // 현재, 동, 서, 북, 남
		int[][] deltas = {null, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		int[] dice = new int[7];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int dir = Integer.parseInt(st.nextToken());
			r += deltas[dir][0];
			c += deltas[dir][1];
			if(r < 0 || r > N-1 || c < 0 || c > M-1) {  // 범위 검사
				r -= deltas[dir][0];
				c -= deltas[dir][1];
				continue;
			}
			/* 주사위 이동 */
			int floor = move[dir];  // 이동 후 바닥 값 가져오기
			move[dir] = 7 - move[0];  // 이동 후 원래 인덱스에 7-이전 바닥 값
			move[0] = floor;          // 현재 바닥 값을 0번 인덱스로 이동
			//홀: +1, 짝: -1
			move[dir % 2 == 1? dir+1:dir-1] = 7-move[dir];
			
			// 지도 칸에 따른 명령 수행
			switch(map[r][c]) { 
				case 0:
					map[r][c] = dice[floor];
					break;
				default:
					dice[floor] = map[r][c];
					map[r][c] = 0;
			}

			sb.append(dice[7-floor]).append("\n");
		}
		
		System.out.println(sb);
	}

}
