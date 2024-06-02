import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//18340kb 164ms
public class BJ_G3_2616_소형기관차 {

    static int N, M;
    static int[] list;
    static int[] sumList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //열차수
        N = Integer.parseInt(st.nextToken());
        list = new int[N+1];
        sumList = new int[N+1];

        //각 손님 수
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N;i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        //최대 M개 소형 기관차 3개
        M = Integer.parseInt(br.readLine());

        //누적해서 구함
        for(int i=1; i<=N;i++) {
//            if(i==0) sumList[i] = list[i];
            sumList[i] = sumList[i-1]+list[i];
        }

        int[][] dp = new int[4][N+1];

        for(int i=1;i<=3;i++){
            for(int j=i * M;j<=N;j++)
                //i는 기관차 번호 -> 3개만 존재
                //만약 직전 번호를 선택하는 게 큰 경우와 이전 선택한 것의 j-m번째를 고른 경우로 나눠야함a
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-M] + sumList[j] - sumList[j-M]);
        }

        System.out.println(dp[3][N]);



    }
}
