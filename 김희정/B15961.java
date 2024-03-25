import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
[BOJ] G4 15961 회전 초밥
 168376KB    |	512ms
목표 : 손님이 먹을 수 있는 초밥 종류의 최댓값
1. 초밥은 k번 연속해서 먹어야 함
2. 무료로 제공되는 초밥 1개
    - 벨트에 해당하는 초밥이 없을 경우, 요리사가 새로 만들어 제공

[풀이]
sushi[i] : 현재 범위에서(k) i번 초밥의 갯수
i-1값은 빼고, i+k-1값은 추가
 */
public class B15961 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[d + 1];
        int[] plate = new int[N];
        for (int i = 0; i < N; i++) {
            plate[i] = Integer.parseInt(br.readLine());
        }

        //로직
        int max = 1;
        sushi[c]++;
        for(int i = 0; i < k; i++){
            if(sushi[plate[i]] == 0) max++;
            sushi[plate[i]]++;
        }
        int cnt = max;
        for(int i = 1; i < N; i++){
            int pop = plate[i-1];
            sushi[pop]--;
            if(sushi[pop] == 0) cnt--;

            int add = plate[(i+k-1) % N];
            if(sushi[add] == 0) cnt++;
            sushi[add]++;

            max = Math.max(cnt, max);
        }

        System.out.println(max);
    }

}
