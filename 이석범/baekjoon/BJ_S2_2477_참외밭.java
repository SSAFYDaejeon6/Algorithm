import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_S2_2477_참외밭 {


    public static void main(String[] args) throws IOException {
        //입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());


        List<Integer> list = new LinkedList<>();

        //밭의 최대 넓이 구하는 변수
        int maxWidth = 0;
        int maxHeight = 0;

        //가로와 세로가 가장 길때의 인덱스
        int wideIdx = 0;
        int heightIdx = 0;

        //정육면체 이므로 6번 반복
        for(int i=0; i<6;i++) {
            st = new StringTokenizer(br.readLine());
            //방향과 길이 input
            //편의상 동, 서를 가로 남, 북을 세로로 정함
            int direction = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());


            //동, 서 일 경우 길이의 최대값을 구하여 maxWidth에 대입
            //그때의 idx도 wideIdx에 대입
            if(direction == 1 || direction == 2) {
                if(len > maxWidth) {
                    maxWidth = len;
                    wideIdx = i;
                }
            }
            //남, 북일 경우 길이의 최대값을 구하여 maxHeight 대입
            //그때의 idx도 heightIdx 대입
            else {
                if(len > maxHeight) {
                    maxHeight = len;
                    heightIdx = i;
                }
            }
            //리스트에는 len값만 저장
            list.add(len);
        }

        //작은 가로, 세로 값 구하기 위한 인덱스 2개 준비
        int smallIdx1 = 0;
        int smallIdx2 = 0;

        //가로가 긴 인덱스가 높이가 긴 인덱스보다 작은 경우
        if(wideIdx < heightIdx) {
            //가로가 긴 인덱스 바로 옆에 세로가 긴 인덱스가 있을 경우
            //가로가 긴 인덱스에 +3번째 +4번째가 작은 가로 세로 인덱스 임
            //아웃 오브 인덱스 방지하기 위해 원형 큐와 같이 나머지 연산으로 인덱스 계산
            if(list.get(wideIdx+1) == maxHeight) {
                smallIdx1 = (wideIdx+3) % 6;
                smallIdx2 = (wideIdx+4) % 6;

            }
            //바로 옆에 없을 경우 +2, +3번째가 작은 가로 세로
            else {
                smallIdx1 = (wideIdx+2) % 6;
                smallIdx2 = (wideIdx+3) % 6;
            }
        }
        //마찬가지로 세로로 계산
        else {
            if(list.get(heightIdx+1) == maxWidth) {
                smallIdx1 = (heightIdx+3) % 6;
                smallIdx2 = (heightIdx+4) % 6;
            } else {
                smallIdx1 = (heightIdx+2) % 6;
                smallIdx2 = (heightIdx+3) % 6;
            }
        }


        //밭의 넓이를 구하고 1m^2당 참외의 개수를 곱하면 답
        int result = (maxHeight * maxWidth) - (list.get(smallIdx1) * list.get(smallIdx2));
        System.out.println(result * num);


    }
}