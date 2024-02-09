import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S3_18429_근손실 {

    //메서드에서도 사용해야 하기 때문에 변수 설정
    static int[] weightList;
    static int n, k;
    static int count;
    static boolean[] visited;

    //백트레킹으로 풀기 위해 함수 생성
    static void Exercise(int weight, int day) {
        //day가 0부터 시작하기 때문에 만약 day가 n이면 모든 운동이 가능하다는 의미
        //따라서 count에 1을 더함
        if(day == n) {
            count++;
            return;
        }

        //백트랙킹이기 때문에 반복문을 돌리면서 조건에 맞으면 재귀 호출
        for(int i=0; i< n; i++) {
            int curWeight = weight + weightList[i] - k;
            if(!visited[i] && curWeight >= 500) {
                //조건에 만족하면 방문 후 day+1에서 탐색
                visited[i] = true;
                Exercise(curWeight, day+1);
                //탐색후 false로 변경
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //입력값 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        weightList = new int[n];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i< n; i++) {
            weightList[i] = Integer.parseInt(st.nextToken());
        }
        Exercise(500, 0);
        System.out.println(count);
    }
}
