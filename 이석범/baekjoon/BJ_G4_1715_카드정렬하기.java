import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//28404KB, 484ms
public class BJ_G4_1715_카드정렬하기 {
    public static void main(String[] args) throws IOException {
        //우선순위 큐 선언
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n;i++) {
            queue.offer(Integer.parseInt(br.readLine()));
        }
        int cnt = 0;
        //두개를 꺼내고 큐가 최종적으로 1개만 될때까지 더하고 넣음
        while(queue.size() != 1) {
            int num1 = queue.poll();
            int num2 = queue.poll();
            cnt += num1+num2;
            queue.offer(num1+num2);
        }


        System.out.println(cnt);
    }
}
