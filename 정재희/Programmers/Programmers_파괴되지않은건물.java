/**
 * 2차원 DP 이용
 * 
 *  n   -n
 *    0
 * -n    n
 */
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        int k = skill.length;
        
        int[][] dp = new int[N][M];
        
        for(int i=0;i<k;i++){
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = type == 1? -skill[i][5] : skill[i][5];
            
            dp[r1][c1] += degree;
            if(c2 < M-1) dp[r1][c2+1] += -degree;
            if(r2 < N-1) dp[r2+1][c1] += - degree;
            if(r2 < N-1 && c2 < M-1) dp[r2+1][c2+1] += degree;
        }
        
        for(int i=0;i<M;i++){   // 각 열에서의 누적합 계산
            for(int j=1;j<N;j++){
                dp[j][i] += dp[j-1][i];
            }
        }
        
        for(int i=0;i<N;i++){   // 각 행에서의 누적합 계산 및 건물 개수 세기
            for(int j=0;j<M;j++){
                if(j > 0) dp[i][j] += dp[i][j-1];
                if(dp[i][j]+board[i][j] > 0) answer++;
            }
        }
        
        
        return answer;
    }
}