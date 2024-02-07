import java.util.Scanner;

//13568kb 184ms
public class BJ_S2_1699_제곱수의합 {
    static final int MAX = 100_001;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[MAX];
        for(int i=1; i<MAX;i++) {
            dp[i] = i;
        }

        //n의 제곱인 경우는 1개가 최소이므로 1개를 저장
        for(int i=1; i<Math.sqrt(MAX);i++) {
            int multi = (int)Math.pow(i, 2);
            dp[multi] = 1;
        }

        //1 2 3 4 5 6 7 8
        //1 2 3 1 2 3 4 2
        //5의 경우 4+1, 3+2 -> 2개
        //6의 경우 5+1, 4+2 -> 3개
        //시간 초과 난다
//        for(int i=3; i<MAX;i++) {
//            if(dp[i] == 1) continue;
//            for(int j=i-1; j>= i /2;j--) {
//                dp[i] = Math.min(dp[i], dp[j]+dp[i-j]);
//            }
//        }

        //18 -> 18-1 + 1, 18-4 + 1

        //n에 대해서 n보다 작은 제곱수들을 뺀 값에 dp에서 1을 더한 값들중 최소를 구함
        for(int i=2; i<MAX;i++) {
            for(int j=1; j*j<=i;j++) {
                int idx = i-(j*j);
                dp[i] = Math.min(dp[i], dp[idx]+1);
            }
        }

        System.out.println(dp[n]);


    }
}
