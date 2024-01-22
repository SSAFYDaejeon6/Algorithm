import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] schedule = new int[n+1][2]; //0 : 기간, 1 : 이익
        for(int i = 1; i < n+1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        //로직
        int[] dp = new int[n+2]; //날짜 i부터 상담했을 때 벌 수 있는 돈의 최댓값 (역순)
        for(int day = n; day > 0; day--){
            int nextDay = day + schedule[day][0];
            if(nextDay > n + 1) dp[day] = dp[day+1]; //일을 못하면, 
            										//i+1날짜부터 일해서 번 돈의 최댓값과 i날짜부터 일해서 번 돈의 최댓값이 같기 때문
            else{
            	//일을 할 수 있는 경우
            	//1. 일을 하지 않았을 때(dp[day+1))
            	//2. 일을 했을 때 (dp[nextDay] + schedule[day][1])
                dp[day] = Math.max(dp[day+1], dp[nextDay] + schedule[day][1]);
                int tmpSum = 0;
            } 
        }

        //결과
        System.out.println(dp[1]);
    }
}
