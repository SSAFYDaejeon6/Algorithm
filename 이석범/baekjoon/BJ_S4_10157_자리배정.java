import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S4_10157_자리배정 {
    public static void main(String[] args) throws IOException {
        //입력값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken()); //이게 나중
        int r = Integer.parseInt(st.nextToken()); //이게 먼저

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        //만약 r*c 보다 k가 크면 즉 좌석수보다 k값이 크면 배정할 수 없으므로 0출력
        if(k > r * c) {
            System.out.println(0);
            return;
        }

        // (c, r)
        int[] position = {1, 0};

        //delta
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        //처음에 (1,0)부터 시작하므로 c는 -1을 빼야됨
        c = c - 1;

        //순서가 r -> c-1 -> r-1 -> c-2 -> r-2 ... 1 -> 0

        //r,c의 최대값이 1000이므로 1000* 1000만큼 반복
        for(int i=0; i< 1_000_001;i++) {
            //i가 짝수이면 r값이 바뀜
            if(i% 2 == 0) {
                //k가 r보다 크면 현재라인에는 끝낼 수 없음
                //그러므로 k에 r을 뺌
                if(k > r) {
                    k = k - r;
                    //delta에 따른 값 넣기
                    position[1] += r * dx[i % 4];
                    //다음에는 r-1번째 이므로 1을 뺌
                    r = r - 1;
                } else {
                    //현재 라인에서 끝내는 경우 k값을 delta에 따라 r의 값에 추가
                    position[1] += k * dx[i % 4];
                    break;
                }
            }
            //i가 홀수면 c값이 바뀜
            else {
                //k가 c보다 크면 현재라인에는 끝낼 수 없음
                //그러므로 k에 c을 뺌
                if(k > c) {
                    //delta에 따른 값 넣기
                    k = k - c;
                    position[0] += c * dy[i % 4];
                    //다음에는 c-1번째 이므로 1을 뺌
                    c = c - 1;
                } else {
                    //현재 라인에서 끝내는 경우 k값을 delta에 따라 c의 값에 추가
                    position[0] += k * dy[i % 4];
                    break;
                }
            }
        }


        System.out.println(position[0]+ " "+position[1]);
    }
}