import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_6064_카잉달력 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());

        A: for(int i=0; i<n;i++) {
            int[] dp = new int[40001];
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            //약 4만까지 x에 M을 더한 값들을 저장
            for(int j=0; j<40001;j++) {
                dp[j] = x+ (j*M);
            }
            //위에 저장한 dp의 값을 N으로 나머지 연산한게 y와 같다면 그때의 dp값이 날짜를 가리킨다.
            //다만 5 2 4 2 처럼 나머지가 0이 되는 경우가 있는데 이때 y값은 N값 즉 최대가 되므로 N으로 반환
            for(int j=0; j<40001;j++) {
                int tmp;
                if(dp[j] % N == 0) tmp = N;
                else {
                    tmp = dp[j] % N;
                }

                if(tmp == y) {
                    sb.append(dp[j]).append("\n");
                    continue A;
                }
            }
            sb.append(-1).append("\n");
        }
        System.out.print(sb);

    }
}
