import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 제일 원초적인 방법으로 접근
 * 주사위 아랫면을 1번 인덱스에 두고
 * 각 면을 인덱스 별로 가져오기
 * 
 * 만약 동쪽으로 주사위가 굴려지면 윗면 -> 오른쪽, 오른쪽 -> 아래, 아래 -> 왼쪽, 왼쪽 -> 위 이렇게 가기 때문에
 * 굴리는 방향에 따라 값 갱신
 * 11872KB | 84ms
 */
public class 주사위굴리기 {

	static int N, M, startX, startY, K;
	static int[][] map;
	static int[] dice;
	static int left, right, up, down, front, back;
	static int dx[] = {0, 0, 0, -1, 1};
	static int dy[] = {0, 1, -1, 0, 0};
	static boolean rangeCheck(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dice = new int[7];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int d = Integer.parseInt(st.nextToken());
			down = dice[1];
			up = dice[6];
			right = dice[3];
			left = dice[4];
			front = dice[5];
			back = dice[2];
			
			
			if(!rangeCheck(startX + dx[d], startY + dy[d])) continue;
			startX += dx[d];
			startY += dy[d];
			if (d == 1) {
				dice[4] = down;
				dice[3] = up;
				dice[1] = right;
				dice[6] = left;
			} 
			
			else if (d == 2) {
				dice[3] = down;
				dice[4] = up;
				dice[6] = right;
				dice[1] = left;
			} 
			
			else if (d == 3) {
				dice[5] = down;
				dice[2] = up;
				dice[6] = front;
				dice[1] = back;
			} 
			
			else {
				dice[2] = down;
				dice[5] = up;
				dice[1] = front;
				dice[6] = back;
			}
			if(map[startX][startY] == 0) {
				map[startX][startY] = dice[1];
			}
			else {
				dice[1] = map[startX][startY];
				map[startX][startY] = 0;
			}
			sb.append(dice[6]).append('\n');
		}
		System.out.println(sb);
	}

}
