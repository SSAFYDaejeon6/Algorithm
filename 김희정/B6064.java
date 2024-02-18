import java.io.*;
import java.util.*;
public class B6064 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int lcm = M*N / getGCD(M,N);    //최소공배수
            int ans = -1;
            x -= 1;     //나누어 떨어지는 경우 체크 못하므로 -1로 계산
            y -= 1;
            for(int j = x; j <= lcm; j += M) {  // 마지막 해 : M,N의 최소공배수
                if (j % N == y) {
                    ans = j + 1;
                    break;
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }

    static int getGCD(int x, int y){
        if(x % y == 0){
            return y;
        }
        return getGCD(y, x%y);
    }
}
