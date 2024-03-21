
/*
 * 누적합 문제
 * 완탐으로 풀면 시간복잡도에서 걸리기 때문에 누적합을 이용해야 함
 * 하지만 누적합을 어떻게?
 * 
 * 2차원 배열에 대해 r, c와 degree까지 주어지는 문제라 누적합에 대해 
 * 생각하기에 쉽지가 않음
 * 일단 1차원 배열에서 이 문제에서 사용할 로직에 대해 공부하는 것을 추천
 * 백준 -> 19951번 태상이의 훈련소 생활 문제를 읽고 구글링해서 해설을 해본 다음
 * 이 문제에 접근하는 것을 추천
 */

public class 파괴되지않은건물 {

	static int solution(int[][] board, int[][] skill) {
		int answer = 0;
		int N = board.length;
		int M = board[0].length;
		int dp[][] = new int[N+1][M+1];
		
		for(int i=0; i<skill.length; i++) {
			int type = skill[i][0];
			int r1 = skill[i][1];
			int c1 = skill[i][2];
			int r2 = skill[i][3];
			int c2 = skill[i][4];
			int degree = skill[i][5];
			
			if(type==1) degree *= -1;
			
			dp[r1][c1] += degree;
			dp[r1][c2+1] += -(degree);
			dp[r2+1][c1] += -(degree);
			dp[r2+1][c2+1] += degree;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=1; j<M; j++) {
				dp[i][j] += dp[i][j-1];
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=1; j<M; j++) {
				dp[j][i] += dp[j-1][i];
			}
		}
	
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(board[i][j] + dp[i][j] > 0) {
					answer++;
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) {
		int[][] board = {{5, 5, 5, 5, 5},
				{5, 5, 5, 5, 5},
				{5, 5, 5, 5, 5},
				{5, 5, 5, 5, 5}};
		int[][] skill = {{1, 0, 0, 3, 4, 4},
				{1, 2, 0, 2, 3, 2},
				{2, 1, 0, 3, 1, 2},
				{1, 0, 1, 3, 3, 1}};
		System.out.println(solution(board, skill));
	}

}
