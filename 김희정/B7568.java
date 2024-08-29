import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
[BOJ] 7568 덩치
(x,y),(p,q)라 할 때, x > p, y >q면 a의 덩치가 더 크다
자신의 덩치보다 큰 사람 k명 => 자신의 등수 k+1
목표 : 각 사람의 덩치 등수 계산
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][]  build = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            build[i][0] = Integer.parseInt(st.nextToken());
            build[i][1] = Integer.parseInt(st.nextToken());
        }

        //로직
        int[] k = new int[N];
        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                if(build[i][0] > build[j][0] && build[i][1] > build[j][1]){
                    k[j]++;
                }
                if(build[i][0] < build[j][0] && build[i][1] < build[j][1]) {
                    k[i]++;
                }

            }
        }

        //출력
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            sb.append(k[i] + 1).append(" ");
        }

        System.out.println(sb);
    }
}
