import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N*M은 시간제한을 넘어감
 * 카잉 달력은 최대 돌리는 횟수를 N과 M의 최소 공배수로 구할 수 있음
 * 카잉달력과 최소공배수에 대해서는 육십갑자 내용을 찾아보면 될듯
 * for문 로직 처리 잘하면 N*M도 통과하길래 구하진 않음
 * 15488KB | 280ms
 */
public class 카잉달력 {
	
    public static void main(String[] args) throws IOException {
        int T;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

       for(int i=0; i<T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int res = -1;
            // 1 1 1 1 같은 입력에서는 값이 나오지 않아서
            // 처음에 -1 시켜주기
            x--;
            y--;
            
            // 10 12 3 9일 때 
            // x가 3이고 10의 주기로 돌기 때문에 1의 자리는 무조건 3일 수 밖에 없음
            for(int j=x; j<M*N; j+=M) {
            	if(j%N == y) {
            		res = j+1;
            		break;
            	}
            }
           System.out.println(res);
        }

    }
}
