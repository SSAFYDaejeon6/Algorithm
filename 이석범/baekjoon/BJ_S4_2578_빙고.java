import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//12120kb 100ms
//비트마스킹으로 체크를 하면서 풀었다
public class BJ_S4_2578_빙고 {
    static final int N = 6;
    static boolean[][] check = new boolean[N][N];

    //가로 빙고 체크
    static int countWidthBingo() {
        int result = 0;
        for(int x=1; x<N;x++) {
            int bitmask = 0;
            for(int y=1; y<N;y++) {
                if(check[x][y]) bitmask += 1 << y;
            }
            if(bitmask==Integer.parseInt("111110", 2)) result++;
        }
        return result;
    }

    //세로빙고 체크
    static int countHeightBingo() {
        int result = 0;
        for(int y=1; y<N;y++) {
            int bitmask = 0;
            for(int x=1; x<N;x++) {
                if(check[x][y]) bitmask += 1 << x;
            }
            if(bitmask==Integer.parseInt("111110", 2)) result++;
        }
        return result;
    }

    //대각선 체크
    static int countdiaBingo() {
        int result = 0;
        int bitmask = 0;
        for(int x=1; x<N;x++) {
            if(check[x][x]) bitmask += 1 << x;
        }
        if(bitmask==Integer.parseInt("111110", 2)) result++;

        bitmask = 0;
        for(int x=1; x<N;x++) {
            if(check[x][N-x]) bitmask += 1 << x;
        }
        if(bitmask==Integer.parseInt("111110", 2)) result++;

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] graph = new int[N][N];

        Queue<Integer> queue = new ArrayDeque<>();

        for(int i=1; i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N;j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N;j++) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }
        }
        int count = 0;
        //큐에서 빼면서 순서대로 체크
        //빙고의 개수가 순간 3개이상이 될수있으므로 조심
        //25*25가 최대일듯
        while(!queue.isEmpty()) {
            int output = queue.poll();
            count++;
            for(int i=1; i<N;i++) {
                for(int j=1; j<N;j++) {
                    if(output==graph[i][j]) check[i][j] = true;
                    if(countdiaBingo()+countHeightBingo()+countWidthBingo() >= 3) {
                        System.out.println(count);
                        return;
                    }
                }
            }

        }

    }
}
