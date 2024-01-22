import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S3_14501 {

    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        //0번인덱스는 날짜, 1번인덱스는 비용
        int[][] list = new int[n][2];
        for(int i=0; i<n;i++) {
            st = new StringTokenizer(br.readLine());
            list[i][0] = Integer.parseInt(st.nextToken());
            list[i][1] = Integer.parseInt(st.nextToken());
        }

        //dp 선언
        int[] dp = new int[n+1];
        int max = 0;
        for(int i=0; i<n; i++) {
            //최대값을 구해야 하므로 현재 날짜 전까지 최대값을 구함
            //중간에 상담을 하지 않는 날이 있어도 비용이 더 클 수 있음
            max = Math.max(max, dp[i]);
            //현재 날짜부터 상담 일수를 더함
            int day = list[i][0]+ i;

            //정상적으로 상담이 될 경우 그 전날까지 최대 비용에 상담 비용을 상담이 끝나는 날에 기존 값이랑 비교하여 큰 값을 저장
            if(day<=n) dp[day] = Math.max(list[i][1] + max, dp[day]);
                //만약 상담끝나는 기간이 n보다 크거나 같으면 즉 퇴사 날 보다 넘을 경우는 max값
            else dp[i] = max;
        }

//        //dp출력
//        for (int i : dp) {
//            System.out.println(i);
//        }

        int result = 0;
        for (int i : dp) {
            result = Math.max(result, i);
        }
        System.out.println(result);
    }

}
