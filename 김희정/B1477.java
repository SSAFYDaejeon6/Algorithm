import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[BOJ] 1477 G4 휴게소 세우기
    11612KB     |	80ms
 */
public class B1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] restArea = new int[N+2];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            restArea[i] = Integer.parseInt(st.nextToken());
        }
        restArea[N+1] = L;
        //정렬
        Arrays.sort(restArea);

        //이분 탐색 : 휴게소 간 구간 구하기
        int left = 1;
        int right = L-1;
        while(left<=right){
            int mid = (left+right)/2;
            int sum = 0;
            for(int i = 1; i < N+2; i++){
                sum += (restArea[i] - restArea[i-1] - 1) / mid;
            }
            if(sum <= M){    //더 많은 휴게소가 필요한 경우 -> 간격을 좁힌다.
                right = mid-1;
            }
            else{   // 더 적은 휴게소가 필요한 경우 -> 간격을 넓힌다.
                left = mid+1;
            }
        }
        System.out.println(left);
    }
}
