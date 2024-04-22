package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//dp[i][j] = Min(dp[i][j], dp[i][k] + dp[k+1][j] + (mat[i][0] * mat[k][1] * mat[j][1]))
// 코드 참고
public class boj11049 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // n

        int[][] arr = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0]= Integer.parseInt(st.nextToken());
            arr[i][1]= Integer.parseInt(st.nextToken());
        }




        int[][] dp = new int[n][n];

        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = arr[i][0] * arr[i][1] * arr[i + 1][1];
        }

        for (int i = 2; i < n; i++) {
            for (int start = 0; start  < n-i; start++) { // 시작
                int end = start + i; // 끝
                dp[start][end] = Integer.MAX_VALUE;

                for (int mid = start; mid < end; mid++) { // 중간 자르기
                    dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid + 1][end] + (arr[start][0] * arr[mid][1] * arr[end][1]));
                }
            }
        }
        System.out.println(dp[0][n - 1]);

    }
}
