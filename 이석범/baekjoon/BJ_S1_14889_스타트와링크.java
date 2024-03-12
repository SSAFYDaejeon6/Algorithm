import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//15736kb, 276ms
public class BJ_S1_14889_스타트와링크 {

    static int n;
    static int[][] graph;
    static int[] startNumbers;
    static int[] linkNumbers;
    static boolean[] selected;
    static int answer = Integer.MAX_VALUE;

    //최대 20개중에서 10개를 뽑는 경우의수 약 18.5만
    static void comb(int cnt, int idx) {
        //만약 다 골랐을 경우 nC2를 하여 각각의 케미의 합을 구한다
        //최대 10C2==45 즉 최대 약 1000만
        if(cnt==n/2) {
            int startSum = 0, linkSum = 0;
            int linkIdx = 0;
            //선택되지 않은 사람은 링크팀으로
            for(int i=0; i< n;i++) {
                if(!selected[i]) linkNumbers[linkIdx++] = i;
            }

            //2명씩 뽑아 케미를 더함
            for(int i=0; i<cnt;i++) {
                for(int j=i+1; j<cnt;j++) {
                    int x = startNumbers[i];
                    int y = startNumbers[j];
                    startSum += graph[x][y] + graph[y][x];
                }
            }

            for(int i=0; i<cnt;i++) {
                for(int j=i+1; j<cnt;j++) {
                    int x = linkNumbers[i];
                    int y = linkNumbers[j];
                    linkSum += graph[x][y] + graph[y][x];
                }
            }
            //케미의 차이가 최소가 되는 것을 구함
            int absSum = Math.abs(startSum-linkSum);
            answer = Math.min(absSum, answer);

            return;
        }

        for(int i=idx; i<n;i++) {
            selected[i] = true;
            startNumbers[cnt] = i;
            comb(cnt+1, i+1);
            selected[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        selected = new boolean[n];
        startNumbers = new int[n/2];
        linkNumbers = new int[n/2];
        for(int x=0; x<n;x++) {
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<n;y++) {
                graph[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        comb(0, 0);
        System.out.println(answer);
    }
}
