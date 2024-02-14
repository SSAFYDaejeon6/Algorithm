import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11572KB |    76ms
// 풀이 : B부터 시작해 끝자리가 1인 경우와 2로 나누어 떨어질 때 연산량 증가
public class B16953 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int cnt = 1;
        while(B >= A) {
            if(B == A) {
                System.out.println(cnt);
                return;
            }
            String b = B + "";
            if(b.charAt(b.length() - 1) == '1') {
                b = b.substring(0, b.length() - 1);
                cnt++;
                B = Integer.parseInt(b);
            }

            else if(B % 2 == 0) {
                B = B / 2;
                cnt++;
            }
            else {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(-1);
    }
}
