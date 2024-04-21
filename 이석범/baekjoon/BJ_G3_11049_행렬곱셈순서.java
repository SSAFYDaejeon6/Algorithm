package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author SEOK BEOM LEE
 *	13924kb 208ms
 */
public class BJ_G3_11049_행렬곱셈순서 {
	

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] input = new int[n+1];
        int[][] dp = new int[n][n];

        //5 3, 3 2, 2 6이 들어올 경우 3 3 이 같고 2 2가 같으므로
        //n+1개를 입력 받음
        //최종적으로 5 3 2 6
        for(int i=0; i<n;i++) {
            st = new StringTokenizer(br.readLine());
            input[i] = Integer.parseInt(st.nextToken());
            input[i+1] = Integer.parseInt(st.nextToken());
        }
//        System.out.println(Arrays.toString(input));
        
        //n이 1이면 0출력
        if(n==1) {
            System.out.println(0);
        }
        else {
        	//구간 간격 0~2, 0~3 ...으로 감
        	for(int i=2; i<n+1;i++) {
        		//0부터 시작
        		for(int j=0; i+j<n+1;j++) {
        			
        			dp[j][i+j-1] = Integer.MAX_VALUE;
        			//k를 기준으로 왼쪽 오른쪽 나누어서 계산 후 합침
        			for(int k=j; k<i+j-1;k++) {
        				int value = dp[j][k] + dp[k+1][j+i-1] + (input[j]*input[k+1]*input[j+i]);
        				//최솟값
    					dp[j][j+i-1] = Math.min(dp[j][j+i-1], value);
        			}
        		}
        	}
        	System.out.println(dp[0][n-1]);
        }


    }
}